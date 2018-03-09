package jehc.xtmodules.xtweb;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseBTreeGridEntity;
import jehc.xtmodules.xtcore.base.BaseZTreeEntity;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtmodel.XtDataDictionary;
import jehc.xtmodules.xtservice.XtDataDictionaryService;

/**
* 数据字典 
* 2015-05-24 08:09:31  邓纯杰
*/
@Controller
@RequestMapping("/xtDataDictionaryController")
public class XtDataDictionaryController extends BaseAction{
	@Autowired
	private XtDataDictionaryService xtDataDictionaryService;
	/**
	* 载入初始化页面
	* @param xt_data_dictionary 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtDataDictionary",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtDataDictionary(XtDataDictionary xt_Data_Dictionary,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-data-dictionary/xt-data-dictionary-list");
	}
	
	/**
	* 载入子初始化页面
	* @param xt_data_dictionary 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtDataDictionaryChild",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtDataDictionaryChild(XtDataDictionary xt_Data_Dictionary,HttpServletRequest request,Model model){
		String xt_data_dictionary_id = request.getParameter("xt_data_dictionary_id");
		model.addAttribute("xt_data_dictionary_id", xt_data_dictionary_id);
		return new ModelAndView("pc/xt-view/xt-data-dictionary/xt-data-dictionary-child-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_data_dictionary 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtDataDictionaryListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtDataDictionaryListByCondition(HttpServletRequest request){
//		String xt_data_dictionary_pid = request.getParameter("xt_data_dictionary_pid");
//		Map<String, Object> condition = new HashMap<String, Object>();
//		commonHPager(condition,request);
//		condition.put("xt_data_dictionary_pid", xt_data_dictionary_pid);
//		List<XtDataDictionary>XtDataDictionaryList = xtDataDictionaryService.getXtDataDictionaryListByCondition(condition);
//		PageInfo<XtDataDictionary> page = new PageInfo<XtDataDictionary>(XtDataDictionaryList);
//		return outPageStr(page,request);
		List<BaseBTreeGridEntity> list = new ArrayList<BaseBTreeGridEntity>();
		Map<String, Object> condition = new HashMap<String, Object>();
		List<XtDataDictionary> XtDataDictionaryList = xtDataDictionaryService.getXtDataDictionaryListByCondition(condition);
		for(int i = 0; i < XtDataDictionaryList.size(); i++){
			XtDataDictionary XtDataDictionary = XtDataDictionaryList.get(i);
			BaseBTreeGridEntity BaseBTreeGridEntity = new BaseBTreeGridEntity();
			BaseBTreeGridEntity.setId(XtDataDictionary.getXt_data_dictionary_id());
			BaseBTreeGridEntity.setPid(XtDataDictionary.getXt_data_dictionary_pid());
			BaseBTreeGridEntity.setText(XtDataDictionary.getXt_data_dictionary_name());
			BaseBTreeGridEntity.setContent(XtDataDictionary.getXt_data_dictionary_remark());
//			BaseZTreeEntity.setIcon("../deng/images/icons/target.png");
			if(0==XtDataDictionary.getXt_data_dictionary_state()){
				BaseBTreeGridEntity.setTempObject("启用");
			}else{
				BaseBTreeGridEntity.setTempObject("禁用");
			}
			if(StringUtils.isEmpty(XtDataDictionary.getXt_data_dictionary_pid()) || "0".equals(XtDataDictionary.getXt_data_dictionary_pid())){
				BaseBTreeGridEntity.setIntegerappend("0");
			}else{
				BaseBTreeGridEntity.setIntegerappend("1");
			}
			list.add(BaseBTreeGridEntity);
		}
		return outStr(BaseBTreeGridEntity.buildTree(list));
	}
	/**
	* 获取对象
	* @param xt_data_dictionary_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtDataDictionaryById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtDataDictionaryById(String xt_data_dictionary_id,HttpServletRequest request){
		XtDataDictionary xt_Data_Dictionary = xtDataDictionaryService.getXtDataDictionaryById(xt_data_dictionary_id);
		return outDataStr(xt_Data_Dictionary);
	}
	/**
	* 添加
	* @param xt_data_dictionary 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtDataDictionary",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtDataDictionary(XtDataDictionary xt_Data_Dictionary,HttpServletRequest request){
		int i = 0;
		if(null != xt_Data_Dictionary && !"".equals(xt_Data_Dictionary)){
			xt_Data_Dictionary.setXt_data_dictionary_id(UUID.toUUID());
			i=xtDataDictionaryService.addXtDataDictionary(xt_Data_Dictionary);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_data_dictionary 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtDataDictionary",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtDataDictionary(XtDataDictionary xt_Data_Dictionary,HttpServletRequest request){
		int i = 0;
		if(null != xt_Data_Dictionary && !"".equals(xt_Data_Dictionary)){
			i=xtDataDictionaryService.updateXtDataDictionary(xt_Data_Dictionary);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_data_dictionary_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtDataDictionary",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtDataDictionary(String xt_data_dictionary_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_data_dictionary_id && !"".equals(xt_data_dictionary_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_data_dictionary_id",xt_data_dictionary_id);
			i=xtDataDictionaryService.delXtDataDictionary(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
}
