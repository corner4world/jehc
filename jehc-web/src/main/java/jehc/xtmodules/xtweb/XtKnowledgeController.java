package jehc.xtmodules.xtweb;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import jehc.solrmodules.solrmodel.SolrCore;
import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.solr.utils.SolrUtil;
import jehc.xtmodules.xtcore.solr.utils.pages.PageSolr;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.DateUtils;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.XtKnowledge;
import jehc.xtmodules.xtservice.XtKnowledgeService;

/**
* 平台知识内容; InnoDB free: 4096 kB 
* 2015-09-30 14:49:45  邓纯杰
*/
@Controller
@RequestMapping("/xtKnowledgeController")
public class XtKnowledgeController extends BaseAction{
	@Autowired
	private XtKnowledgeService xtKnowledgeService;
	/**
	* 载入初始化页面
	* @param xt_knowledge 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtKnowledge",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtKnowledge(XtKnowledge xt_Knowledge,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-knowledge/xt-knowledge-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_knowledge 
	* @param request 
	 * @throws UnsupportedEncodingException 
	 * @throws UnsupportedEncodingException 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtKnowledgeListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtKnowledgeListByCondition(BaseSearch baseSearch ,String keywords,HttpServletRequest request) throws UnsupportedEncodingException{
		Map<String, Object> condition = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(keywords)){
			int start = 0;
			int limit = 30;
			String offset = request.getParameter("start");
			String pageSize = request.getParameter("limit");
			if(null != offset){
				start = new Integer(offset);
			}
			if(null != pageSize){
				limit = new Integer(pageSize);
			}
			//////////////////采用SOLR关键词查询//////////////
			PageSolr pageSolr = new PageSolr();
			condition.put("keywords",keywords.trim());
	        pageSolr.setConditions(condition);
	        pageSolr.setCurrent(start);//当前页从0开始
	        pageSolr.setSize(limit);
	        SolrCore solrCore = getSolrCoreByUseonlynumbercode("xtknowledge");
	        pageSolr = solrQueryPage(solrCore.getSolr_url_url()+"/"+solrCore.getSolr_core_name(), pageSolr);
	        List<Object> list = pageSolr.getDatas();
	        return outPageStr(list, pageSolr.getCount(), request);
		}
        //////////////////采用SQL关键词查询///////////////
		condition =  baseSearch.convert();
		commonHPager(condition,request);
		List<XtKnowledge> xt_KnowledgeList = xtKnowledgeService.getXtKnowledgeListByCondition(condition);
		PageInfo<XtKnowledge> page = new PageInfo<XtKnowledge>(xt_KnowledgeList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_knowledge_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtKnowledgeById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtKnowledgeById(String xt_knowledge_id,HttpServletRequest request){
		XtKnowledge xt_Knowledge = xtKnowledgeService.getXtKnowledgeById(xt_knowledge_id);
		return outDataStr(xt_Knowledge);
	}
	/**
	* 添加
	* @param xt_knowledge 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtKnowledge",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtKnowledge(XtKnowledge xt_Knowledge,HttpServletRequest request){
		int i = 0;
		String xt_knowledge_content = request.getParameter("xt_knowledge_content");
		if(null != xt_Knowledge && !"".equals(xt_Knowledge)){
			xt_Knowledge.setXt_knowledge_id(UUID.toUUID());
			xt_Knowledge.setXt_time(CommonUtils.getSimpleDateFormat());
			xt_Knowledge.setXt_knowledge_content(xt_knowledge_content);
			i=xtKnowledgeService.addXtKnowledge(xt_Knowledge);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_knowledge 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtKnowledge",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtKnowledge(XtKnowledge xt_Knowledge,HttpServletRequest request){
		int i = 0;
		String xt_knowledge_content = request.getParameter("xt_knowledge_content");
		if(null != xt_Knowledge && !"".equals(xt_Knowledge)){
			xt_Knowledge.setXt_knowledge_content(xt_knowledge_content);
			i=xtKnowledgeService.updateXtKnowledge(xt_Knowledge);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_knowledge_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtKnowledge",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtKnowledge(String xt_knowledge_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_knowledge_id && !"".equals(xt_knowledge_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_knowledge_id",xt_knowledge_id.split(","));
			i=xtKnowledgeService.delXtKnowledge(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_knowledge_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtKnowledge",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtKnowledge(String xt_knowledge_id,HttpServletRequest request){
		int i = 0;
		XtKnowledge xt_Knowledge = xtKnowledgeService.getXtKnowledgeById(xt_knowledge_id);
		if(null != xt_Knowledge && !"".equals(xt_Knowledge)){
			xt_Knowledge.setXt_knowledge_id(UUID.toUUID());
			i=xtKnowledgeService.addXtKnowledge(xt_Knowledge);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 导出
	* @param excleData 
	* @param excleHeader 
	* @param excleText 
	* @param request 
	* @param request 
	*/
	@RequestMapping(value="/exportXtKnowledge",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtKnowledge(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	

	/**
     * 分页查询，包含查询，分页，高亮及获取高亮处摘要等内容；不同于数据库的查询分页，
     * solr的查询返回值中有文档总数，所以无需再次查询总条数。
     * @param page 自定义的翻页对象，包含查询信息及当前页数据列表。
     */
    public static PageSolr solrQueryPage(String baseURL,PageSolr pageSolr) {
        SolrQuery query = new SolrQuery();
        //为防止和其他变量重名，在变量的开始加上condition的首字母
        if(null != pageSolr.getConditions().get("keywords") && !"".equals(pageSolr.getConditions().get("keywords"))){
        	 String keywords = pageSolr.getConditions().get("keywords").toString();
             query.setQuery("xt_knowledge_keywords:"+SolrUtil.escapeQueryChars(keywords)); //OR必须大写或用“空格”、“||”代替
             //query.setQuery("xt_knowledge_title:"+keywords+" OR xt_knowledge_content:"+keywords+""); //OR必须大写或用“空格”、“||”代替
        }
        /////////////操作排序功能
        query.addSort("xt_time", SolrQuery.ORDER.desc);
        query.setStart((int)pageSolr.getStart());
        query.setRows((int)pageSolr.getSize());
        //设置高亮
        query.setHighlight(true);//开启高亮组件
        query.addHighlightField("xt_knowledge_title");//高亮字段
        query.addHighlightField("xt_knowledge_content");//高亮字段
        query.addHighlightField("xt_knowledge_keywords");//高亮字段
        query.setHighlightSimplePre("<font color='red'>");//标记，高亮关键字前缀
        query.setHighlightSimplePost("</font>");//后缀
        query.setHighlight(true).setHighlightSnippets(1);//获取高亮分片数，一般搜索词可能分布在文章中的不同位置，其所在一定长度的语句即为一个片段，默认为1，但根据业务需要有时候需要多取出几个分片。 - 此处设置决定下文中titleList, contentList中元素的个数
        query.setHighlightFragsize(150);//每个分片的最大长度，默认为100。适当设置此值，如果太小，高亮的标题可能会显不全；设置太大，摘要可能会太长。
        List<Object> xtKonwledgeList = new ArrayList<Object>();
        try {
        	//连接solr服务器
    		HttpSolrServer server = new HttpSolrServer(baseURL);
            QueryResponse rsp = server.query(query);
            SolrDocumentList docs = rsp.getResults();
            //获取所有高亮的字段
            Map<String,Map<String,List<String>>> highlightMap=rsp.getHighlighting();
            Iterator<SolrDocument> iter = docs.iterator();
            while (iter.hasNext()) {
                SolrDocument doc = iter.next();
                String idStr = doc.getFieldValue("id").toString();
                XtKnowledge xtKnowledge = new XtKnowledge();
                xtKnowledge.setXt_knowledge_id(doc.getFieldValue("xt_knowledge_id").toString());
                xtKnowledge.setXt_knowledge_content(doc.getFieldValue("xt_knowledge_content").toString());
                xtKnowledge.setXt_knowledge_title(doc.getFieldValue("xt_knowledge_title").toString());
                xtKnowledge.setXt_userinfo_realName(doc.getFieldValue("xt_userinfo_realName").toString());
                xtKnowledge.setXt_time(DateUtils.formatDateTime((Date)doc.getFieldValue("xt_time")));
                List<String> xt_knowledge_titleList =highlightMap.get(idStr).get("xt_knowledge_title");
                List<String> xt_knowledge_contentList=highlightMap.get(idStr).get("xt_knowledge_contentList");
                //获取并设置高亮的字段标题
                if(null != xt_knowledge_titleList && !xt_knowledge_titleList.isEmpty()){
                	xtKnowledge.setXt_knowledge_title(xt_knowledge_titleList.get(0));
                }
                //获取并设置高亮的字段内容
                if(xt_knowledge_contentList!=null && xt_knowledge_contentList.size()>0){
                	xtKnowledge.setXt_knowledge_content(xt_knowledge_contentList.get(0));
                }
                xtKonwledgeList.add(xtKnowledge);
            }
            pageSolr.setDatas(xtKonwledgeList);
            pageSolr.setCount(docs.getNumFound());
            return pageSolr;
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new ExceptionUtil(e.getMessage(),e.getCause());
        }
    }
    
    /**
     * 自动补全
     * @param keywords
     * @param xt_Knowledge
     * @param request
     * @return
     */
    @ResponseBody
	@RequestMapping(value="/autoXtKnowledgeListByCondition",method={RequestMethod.POST,RequestMethod.GET})
    public String autoXtKnowledgeListByCondition(String keywords,XtKnowledge xt_Knowledge,HttpServletRequest request){
    	if(StringUtil.isEmpty(keywords)){
    		return null;
    	}
    	SolrCore solrCore = getSolrCoreByUseonlynumbercode("xtknowledge");
    	return outItemsStr(SolrUtil.autoCompleteByFacet(solrCore,"xt_knowledge_keywords", SolrUtil.escapeQueryChars(keywords.trim()), null,10));
    }
}
