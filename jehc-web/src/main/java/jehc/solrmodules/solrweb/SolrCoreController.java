package jehc.solrmodules.solrweb;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import jehc.solrmodules.solrmodel.SolrCore;
import jehc.solrmodules.solrmodel.SolrDocument;
import jehc.solrmodules.solrmodel.SolrEntity;
import jehc.solrmodules.solrmodel.SolrIndex;
import jehc.solrmodules.solrmodel.SolrUrl;
import jehc.solrmodules.solrservice.SolrCoreService;
import jehc.solrmodules.solrservice.SolrEntityService;
import jehc.solrmodules.solrservice.SolrUrlService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseTreeGridEntity;
import jehc.xtmodules.xtcore.solr.utils.SolrUtil;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.ReadProperties;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.XtDataDictionary;

/**
* 全文检索多实例配置 
* 2015-12-15 13:07:24  邓纯杰
*/
@Controller
@RequestMapping("/solrCoreController")
@Scope("prototype")
public class SolrCoreController extends BaseAction{
	@Autowired
	private SolrCoreService solrCoreService;
	@Autowired
	private SolrUrlService solrUrlService;
	@Autowired
	private SolrEntityService solrEntityService;
	/**
	* 载入初始化页面
	* @param solr_core 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadSolrCore",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadSolrCore(SolrCore solr_Core,HttpServletRequest request){
		String solr_url_id = request.getParameter("solr_url_id");
		request.setAttribute("solr_url_id", solr_url_id);
		return new ModelAndView("pc/solr-view/solr-core/solr-core-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param solr_core 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getSolrCoreListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrCoreListByCondition(String solr_url_id,SolrCore solr_Core,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		commonHPager(condition,request);
		condition.put("solr_url_id", solr_url_id);
		List<SolrCore> solr_CoreList = solrCoreService.getSolrCoreListByCondition(condition);
		PageInfo<SolrCore> page = new PageInfo<SolrCore>(solr_CoreList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param solr_core_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getSolrCoreById",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrCoreById(String solr_core_id,HttpServletRequest request){
		SolrCore solr_Core = solrCoreService.getSolrCoreById(solr_core_id);
		return outDataStr(solr_Core);
	}
	/**
	* 添加
	* @param solr_core 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addSolrCore",method={RequestMethod.POST,RequestMethod.GET})
	public String addSolrCore(SolrCore solr_Core,SolrDocument solr_Document,String solrIndexSqlJSON,String solr_entity_name,String solrIndexJSON,String solrIndexSqlFiledJSON,HttpServletRequest request){
		int i = 0;
		if(null != solr_Core && !"".equals(solr_Core)){
			solr_Core.setSolr_core_id(UUID.toUUID());
			solr_Core.setXt_userinfo_id(CommonUtils.getXtUid());
			solr_Core.setSolr_core_ctime(getDate());
			solr_Core.setSolr_core_uptime(getDate());
			solr_Document.setSolr_document_id(UUID.toUUID());
			solr_Document.setSolr_document_ctime(getDate());
			solr_Document.setXt_userinfo_id(CommonUtils.getXtUid());
			List<SolrIndex> solr_IndexList = commonSolrIndexList(solrIndexJSON);
			for(int j = 0; j < solr_IndexList.size(); j++){
				solr_IndexList.get(j).setSolr_index_ctime(getDate());
				solr_IndexList.get(j).setXt_userinfo_id(CommonUtils.getXtUid());
				solr_IndexList.get(j).setSolr_index_id(UUID.toUUID());
			}
			i=solrCoreService.addSolrCore(solr_Core,solr_Document,solr_IndexList);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param solr_core 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateSolrCore",method={RequestMethod.POST,RequestMethod.GET})
	public String updateSolrCore(SolrCore solr_Core,SolrDocument solr_Document,String solrIndexSqlJSON,String solr_entity_name,String solrIndexJSON,String solrIndexSqlFiledJSON,HttpServletRequest request){
		int i = 0;
		if(null != solr_Core && !"".equals(solr_Core)){
			solr_Core.setXt_userinfo_id(CommonUtils.getXtUid());
			solr_Core.setSolr_core_uptime(getDate());
			solr_Document.setSolr_document_ctime(getDate());
			solr_Document.setXt_userinfo_id(CommonUtils.getXtUid());
			List<SolrIndex> solr_IndexList = commonSolrIndexList(solrIndexJSON);
			for(int j = 0; j < solr_IndexList.size(); j++){
				solr_IndexList.get(j).setSolr_index_ctime(getDate());
				solr_IndexList.get(j).setXt_userinfo_id(CommonUtils.getXtUid());
			}
			i=solrCoreService.updateSolrCore(solr_Core,solr_Document,solr_IndexList);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param solr_core_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delSolrCore",method={RequestMethod.POST,RequestMethod.GET})
	public String delSolrCore(String solr_core_id,HttpServletRequest request){
		int i = 0;
		if(null != solr_core_id && !"".equals(solr_core_id)){
			String[] solr_core_idList = solr_core_id.split(",");
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("solr_core_id",solr_core_idList);
			for(String solr_core_ids:solr_core_idList){
				SolrCore solr_core = solrCoreService.getSolrCoreById(solr_core_ids);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("solr_document_id", solr_core.getSolr_document_id());
				List<SolrEntity> solr_EntityList = solrEntityService.getSolrEntityListByCondition(map);
				if(!solr_EntityList.isEmpty()){
					return outAudStr(false,"您删除的实例中存在实体，不能删除!");
				}
			}
			condition = new HashMap<String, Object>();
			i=solrCoreService.delSolrCore(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param solr_core_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copySolrCore",method={RequestMethod.POST,RequestMethod.GET})
	public String copySolrCore(String solr_core_id,HttpServletRequest request){
		int i = 0;
		SolrCore solr_Core = solrCoreService.getSolrCoreById(solr_core_id);
		if(null != solr_Core && !"".equals(solr_Core)){
			solr_Core.setSolr_core_id(UUID.toUUID());
			i=solrCoreService.addSolrCore(solr_Core,null,null);
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
	@RequestMapping(value="/exportSolrCore",method={RequestMethod.POST,RequestMethod.GET})
	public void exportSolrCore(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	 * 全量导入索引数据
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/fullimport",method={RequestMethod.POST,RequestMethod.GET})
	public String fullimport(String solr_url_id,String solr_core_name,String solr_document_id,HttpServletRequest request,HttpServletResponse response){
		if(null == solr_document_id || "".equals(solr_document_id)){
			return outAudStr(false,"未获取到文档编号，不能导入数据");
		}else{
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("solr_document_id", solr_document_id);
			List<SolrEntity> solrEntityList = solrEntityService.getSolrEntityListByCondition(condition);
			if(null == solrEntityList ||solrEntityList.isEmpty() || solrEntityList.size() == 0){
				return outAudStr(false,"未获取到实例，不能导入数据");
			}else{
				SolrUrl solr_url = solrUrlService.getSolrUrlById(solr_url_id);
				boolean flag = SolrUtil.fullimport(solr_url.getSolr_url_url(), solr_core_name);
				if(flag){
					return outAudStr(true);
				}else{
					return outAudStr(false);
				}
			}
		}
	}
	/**
	 * 增量导入索引数据
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/dataimport",method={RequestMethod.POST,RequestMethod.GET})
	public String dataimport(String solr_url_id,String solr_core_name,String solr_document_id,HttpServletRequest request,HttpServletResponse response){
		if(null == solr_document_id || "".equals(solr_document_id)){
			return outAudStr(false,"未获取到文档编号，不能导入数据");
		}else{
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("solr_document_id", solr_document_id);
			List<SolrEntity> solrEntityList = solrEntityService.getSolrEntityListByCondition(condition);
			if(null == solrEntityList ||solrEntityList.isEmpty() || solrEntityList.size() == 0){
				return outAudStr(false,"未获取到实例，不能导入数据");
			}else{
				SolrUrl solr_url = solrUrlService.getSolrUrlById(solr_url_id);
				boolean flag = SolrUtil.dataimport(solr_url.getSolr_url_url(), solr_core_name);
				if(flag){
					return outAudStr(true);
				}else{
					return outAudStr(false);
				}
			}
		}
	}
	/**
	 * 创建索引并映射数据库字段
	 * @param solr_url_id
	 * @param solr_core_name
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getSchemaDataConfig",method={RequestMethod.POST,RequestMethod.GET})
	public String getSchemaDataConfig(String solr_url_id,String solr_core_name,HttpServletRequest request,HttpServletResponse response){
		File dataConfigFile = new File(ReadProperties.readConfigProperties("solr_home_path")+"/modules/"+solr_core_name+"/conf/data-config.xml");
		File schemaFile = new File(ReadProperties.readConfigProperties("solr_home_path")+"/modules/"+solr_core_name+"/conf/schema.xml");
		SAXReader sax = new SAXReader();
		try {
			Document dataConfigdocument = sax.read(dataConfigFile);
			Document schemaDocument = sax.read(schemaFile);
			String dataConfig = dataConfigdocument.asXML();  
			String schema = schemaDocument.asXML();
			JSONArray jsonArray = new JSONArray();
		    Map<String, Object> model = new HashMap<String, Object>();
		    model.put("schema", schema);
		    model.put("dataConfig", dataConfig);
		    model.put("solr_core_name", solr_core_name);
		    model.put("solr_url_id", solr_url_id);
		    jsonArray.add(model);
		    return outDataStr(jsonArray);
		} catch (DocumentException e) {
			return null;
		}
	}
	
	/**
	 * 获取索引类型数据字典
	 * @param xt_userinfo_nation
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getSolrIndexTypeList",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrIndexTypeList(HttpServletRequest request,HttpServletResponse response){
		List<XtDataDictionary> xtDataDictionaryList = CommonUtils.getXtDataDictionaryCache("solr_index_type");
		return outComboDataStr(xtDataDictionaryList);
	}
	/**
	 * 获取索引查询类型数据字典
	 * @param xt_userinfo_nation
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getSolrIndexQueryTypeList",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrIndexQueryTypeList(HttpServletRequest request,HttpServletResponse response){
		List<XtDataDictionary> xtDataDictionaryList = CommonUtils.getXtDataDictionaryCache("solr_index_sql_type");
		return outComboDataStr(xtDataDictionaryList);
	}
	
	//////////////////////////////////封装////////////////////////////////
	
	/**
	 * 返回所有集合
	 * @param solrIndexJSON
	 * @return
	 */
	private List<SolrIndex> commonSolrIndexList(String solrIndexJSON){
		try {
			solrIndexJSON = URLDecoder.decode(solrIndexJSON, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<SolrIndex> solr_IndexList = new ArrayList<SolrIndex>();
		JSONArray arr = JSONArray.fromObject(solrIndexJSON);
		Object[] o = arr.toArray();
		for(Object obj:o){ 
		    if (obj instanceof JSONObject) {
		    	JSONObject json = (JSONObject)obj; 
		    	SolrIndex solr_Index = new SolrIndex();
		    	Object solr_index_name = json.get("solr_index_name");
		    	Object solr_index_id = json.get("solr_index_id");
		    	Object solr_index_remark = json.get("solr_index_remark");
		    	Object solr_index_type = json.get("solr_index_type");
		    	Object solr_index_indexed = json.get("solr_index_indexed");
		    	Object solr_index_stored = json.get("solr_index_stored");
		    	Object solr_index_multiValued = json.get("solr_index_multiValued");
		    	if(null != solr_index_name){
		    		solr_Index.setSolr_index_name((String)solr_index_name);
				}
		    	if(null != solr_index_id){
		    		solr_Index.setSolr_index_id((String)solr_index_id);
		    	}
		    	if(null != solr_index_remark){
		    		solr_Index.setSolr_index_remark((String)solr_index_remark);
		    	}
		    	if(null != solr_index_type){
		    		solr_Index.setSolr_index_type((String)solr_index_type);
		    	}
		    	if(null != solr_index_indexed){
		    		solr_Index.setSolr_index_indexed((String)solr_index_indexed);
		    	}
		    	if(null != solr_index_stored){
		    		solr_Index.setSolr_index_stored((String)solr_index_stored);
		    	}
		    	if(null != solr_index_multiValued){
		    		solr_Index.setSolr_index_multiValued((String)solr_index_multiValued);
		    	}
		    	solr_IndexList.add(solr_Index);
		    }
		}
		return solr_IndexList;
	}
	
	/**
	 * 查找Solr实体树
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value="/getSolrEntityListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrEntityListByCondition(String solr_document_id,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("solr_document_id", solr_document_id);
		List<SolrEntity> solr_EntityList = solrEntityService.getSolrEntityListByCondition(condition);
		condition = new HashMap<String, Object>();
		
		String expanded = request.getParameter("expanded");
		String singleClickExpand = request.getParameter("singleClickExpand");
		List<BaseTreeGridEntity> list = new ArrayList<BaseTreeGridEntity>();
		for(int j = 0; j < solr_EntityList.size(); j++){
			SolrEntity solr_Entity = solr_EntityList.get(j);
			BaseTreeGridEntity BaseTreeGridEntity = new BaseTreeGridEntity();
			BaseTreeGridEntity.setId(solr_Entity.getSolr_entity_id());
			BaseTreeGridEntity.setPid(solr_Entity.getSolr_entity_pid());
			BaseTreeGridEntity.setText(solr_Entity.getSolr_entity_name());
			BaseTreeGridEntity.setContent(solr_Entity.getSolr_entity_text());
			BaseTreeGridEntity.setIcon("../deng/images/icons/target.png");
			BaseTreeGridEntity.setIntegerappend(solr_Entity.getXt_userinfo_realName());
			if(("true").equals(expanded)){
				BaseTreeGridEntity.setExpanded(true);
			}else{
				BaseTreeGridEntity.setExpanded(false);
			}
			if("true".equals(singleClickExpand)){
				BaseTreeGridEntity.setSingleClickExpand(true);
			}else{
				BaseTreeGridEntity.setSingleClickExpand(false);
			}
			list.add(BaseTreeGridEntity);
		}
		return outStr(BaseTreeGridEntity.buildTree(list,false));
	}
	
	/**
	 * 查找格式转换器
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value="/getSolrTransformerList",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrTransformerList(){
		List<XtDataDictionary> xtDataDictionaryList = CommonUtils.getXtDataDictionaryCache("solrTransformer");
		return outComboDataStr(xtDataDictionaryList);
	}
}
