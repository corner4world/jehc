package jehc.bmodules.bweb;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jehc.bmodules.bmodel.BCategory;
import jehc.bmodules.bservice.BCategoryService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseTreeGridEntity;
import jehc.xtmodules.xtcore.base.BaseZTreeEntity;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 基础-品类 
* 2016-01-04 21:24:03  邓纯杰
*/
@Controller
@RequestMapping("/bCategoryController")
public class BCategoryController extends BaseAction{
	@Autowired
	private BCategoryService bCategoryService;
	/**
	* 载入初始化页面
	* @param b_category 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBCategory",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBCategory(BCategory b_Category,HttpServletRequest request){
		return new ModelAndView("pc/b-view/b-category/b-category-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_category 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBCategoryListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBCategoryListByCondition(HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		String expanded = request.getParameter("expanded");
		String singleClickExpand = request.getParameter("singleClickExpand");
		List<BaseTreeGridEntity> list = new ArrayList<BaseTreeGridEntity>();
		List<BCategory> b_CategoryList = bCategoryService.getBCategoryListAllByCondition(condition);
		for(int j = 0; j < b_CategoryList.size(); j++){
			BCategory b_Category = b_CategoryList.get(j);
			BaseTreeGridEntity BaseTreeGridEntity = new BaseTreeGridEntity();
			BaseTreeGridEntity.setId(b_Category.getB_category_id());
			BaseTreeGridEntity.setPid(b_Category.getB_category_pid());
			BaseTreeGridEntity.setText(b_Category.getB_category_name());
			BaseTreeGridEntity.setContent("创建时间:"+b_Category.getB_category_ctime()+",修改时间:"+b_Category.getB_category_mtime());
			BaseTreeGridEntity.setIcon("../deng/images/icons/target.png");
			BaseTreeGridEntity.setTempObject(""+b_Category.getB_category_status());
			BaseTreeGridEntity.setIntegerappend(b_Category.getXt_userinfo_realName());
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
	* 获取对象
	* @param b_category_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBCategoryById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBCategoryById(String b_category_id,HttpServletRequest request){
		BCategory b_Category = bCategoryService.getBCategoryById(b_category_id);
		return outDataStr(b_Category);
	}
	/**
	* 添加
	* @param b_category 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBCategory",method={RequestMethod.POST,RequestMethod.GET})
	public String addBCategory(BCategory b_Category,HttpServletRequest request){
		int i = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(null != b_Category && !"".equals(b_Category)){
			b_Category.setB_category_id(UUID.toUUID());
			b_Category.setB_category_ctime(getDate());
			b_Category.setB_category_mtime(getDate());
			b_Category.setXt_userinfo_id(CommonUtils.getXtUid());
			i=bCategoryService.addBCategory(b_Category);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_category 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBCategory",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBCategory(BCategory b_Category,HttpServletRequest request){
		int i = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(null != b_Category && !"".equals(b_Category)){
			b_Category.setB_category_mtime(getDate());
			b_Category.setXt_userinfo_id(CommonUtils.getXtUid());
			i=bCategoryService.updateBCategory(b_Category);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_category_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBCategory",method={RequestMethod.POST,RequestMethod.GET})
	public String delBCategory(String b_category_id,HttpServletRequest request){
		int i = 0;
		if(null != b_category_id && !"".equals(b_category_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_category_id",b_category_id.split(","));
			i=bCategoryService.delBCategory(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_category_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBCategory",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBCategory(String b_category_id,HttpServletRequest request){
		int i = 0;
		BCategory b_Category = bCategoryService.getBCategoryById(b_category_id);
		if(null != b_Category && !"".equals(b_Category)){
			b_Category.setB_category_id(UUID.toUUID());
			i=bCategoryService.addBCategory(b_Category);
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
	@RequestMapping(value="/exportBCategory",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBCategory(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	
	/**
	* 读取所有
	* @param b_category 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBCategoryList",method={RequestMethod.POST,RequestMethod.GET})
	public String getBCategoryList(HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		String expanded = request.getParameter("expanded");
		String singleClickExpand = request.getParameter("singleClickExpand");
		List<BaseZTreeEntity> list = new ArrayList<BaseZTreeEntity>();
		List<BCategory> b_CategoryList = bCategoryService.getBCategoryListAllByCondition(condition);
		for(int j = 0; j < b_CategoryList.size(); j++){
			BCategory b_Category = b_CategoryList.get(j);
			BaseZTreeEntity baseZTreeEntity = new BaseZTreeEntity();
			baseZTreeEntity.setId(b_Category.getB_category_id());
			baseZTreeEntity.setPid(b_Category.getB_category_pid());
			baseZTreeEntity.setText(b_Category.getB_category_name());
			baseZTreeEntity.setContent("创建时间:"+b_Category.getB_category_ctime()+",修改时间:"+b_Category.getB_category_mtime());
			baseZTreeEntity.setTempObject(""+b_Category.getB_category_status());
			baseZTreeEntity.setIntegerappend(b_Category.getXt_userinfo_realName());
			if(("true").equals(expanded)){
				baseZTreeEntity.setExpanded(true);
			}else{
				baseZTreeEntity.setExpanded(false);
			}
			if("true".equals(singleClickExpand)){
				baseZTreeEntity.setSingleClickExpand(true);
			}else{
				baseZTreeEntity.setSingleClickExpand(false);
			}
			list.add(baseZTreeEntity);
		}
		return outStr(BaseZTreeEntity.buildTree(list,false));
	}
}
