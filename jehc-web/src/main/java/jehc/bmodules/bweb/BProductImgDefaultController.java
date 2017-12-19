package jehc.bmodules.bweb;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import jehc.bmodules.bmodel.BProductImgDefault;
import jehc.bmodules.bservice.BProductImgDefaultService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 基础商品默认图片 
* 2016-01-09 09:06:38  邓纯杰
*/
@Controller
@RequestMapping("/bProductImgDefaultController")
public class BProductImgDefaultController extends BaseAction{
	@Autowired
	private BProductImgDefaultService bProductImgDefaultService;
	/**
	* 载入初始化页面
	* @param b_product_img_default 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBProductImgDefault",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBProductImgDefault(BProductImgDefault b_Product_Img_Default,HttpServletRequest request,Model model){
		model.addAttribute("b_product_id", b_Product_Img_Default.getB_product_id());
		return new ModelAndView("pc/b-view/b-product-img-default/b-product-img-default-list");
	}
	
	/**
	* 载入DataGrid页面
	* @param b_product_img_default 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBProductImgDefaultDataGrid",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBProductImgDefaultDataGrid(BProductImgDefault b_Product_Img_Default,HttpServletRequest request,Model model){
		model.addAttribute("b_product_id", b_Product_Img_Default.getB_product_id());
		return new ModelAndView("pc/b-view/b-product-img-default/b-product-img-default-datagrid");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_product_img_default 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBProductImgDefaultListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBProductImgDefaultListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		String jehcimg_base_url = CommonUtils.getXtPathCache("jehcimg_base_url").get(0).getXt_path();
		List<BProductImgDefault> b_Product_Img_DefaultList = bProductImgDefaultService.getBProductImgDefaultListByCondition(condition);
		for(int i = 0; i < b_Product_Img_DefaultList.size(); i++){
			b_Product_Img_DefaultList.get(i).setJehcimg_base_url(jehcimg_base_url);
			b_Product_Img_DefaultList.get(i).setJehcimg_base_path_url(jehcimg_base_url+b_Product_Img_DefaultList.get(i).getXt_attachmentPath());
		}
		PageInfo<BProductImgDefault> page = new PageInfo<BProductImgDefault>(b_Product_Img_DefaultList);
		return outPageBootStr(page,request);
	}
	
	/**
	* 获取对象
	* @param b_product_img_default_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBProductImgDefaultById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBProductImgDefaultById(String b_product_img_default_id,HttpServletRequest request){
		BProductImgDefault b_Product_Img_Default = bProductImgDefaultService.getBProductImgDefaultById(b_product_img_default_id);
		return outDataStr(b_Product_Img_Default);
	}
	/**
	* 添加
	* @param b_product_img_default 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBProductImgDefault",method={RequestMethod.POST,RequestMethod.GET})
	public String addBProductImgDefault(BProductImgDefault b_Product_Img_Default,HttpServletRequest request){
		int i = 0;
		if(null != b_Product_Img_Default && !"".equals(b_Product_Img_Default)){
			b_Product_Img_Default.setB_product_img_default_id(UUID.toUUID());
			b_Product_Img_Default.setB_product_img_ctime(getDate());
			b_Product_Img_Default.setXt_userinfo_id(getXtUid());
			i=bProductImgDefaultService.addBProductImgDefault(b_Product_Img_Default);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_product_img_default 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBProductImgDefault",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBProductImgDefault(BProductImgDefault b_Product_Img_Default,HttpServletRequest request){
		int i = 0;
		if(null != b_Product_Img_Default && !"".equals(b_Product_Img_Default)){
			b_Product_Img_Default.setB_product_img_mtime(getDate());
			b_Product_Img_Default.setXt_userinfo_id(getXtUid());
			i=bProductImgDefaultService.updateBProductImgDefault(b_Product_Img_Default);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_product_img_default_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBProductImgDefault",method={RequestMethod.POST,RequestMethod.GET})
	public String delBProductImgDefault(String b_product_img_default_id,HttpServletRequest request){
		int i = 0;
		if(null != b_product_img_default_id && !"".equals(b_product_img_default_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_product_img_default_id",b_product_img_default_id.split(","));
			i=bProductImgDefaultService.delBProductImgDefault(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_product_img_default_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBProductImgDefault",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBProductImgDefault(String b_product_img_default_id,HttpServletRequest request){
		int i = 0;
		BProductImgDefault b_Product_Img_Default = bProductImgDefaultService.getBProductImgDefaultById(b_product_img_default_id);
		if(null != b_Product_Img_Default && !"".equals(b_Product_Img_Default)){
			b_Product_Img_Default.setB_product_img_default_id(UUID.toUUID());
			i=bProductImgDefaultService.addBProductImgDefault(b_Product_Img_Default);
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
	@RequestMapping(value="/exportBProductImgDefault",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBProductImgDefault(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toBProductImgDefaultAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toBProductImgDefaultAdd(BProductImgDefault bProductImgDefault,HttpServletRequest request, Model model){
		model.addAttribute("b_product_id", bProductImgDefault.getB_product_id());
		return new ModelAndView("pc/b-view/b-product-img-default/b-product-img-default-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toBProductImgDefaultUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toBProductImgDefaultUpdate(String b_product_img_default_id,HttpServletRequest request, Model model){
		BProductImgDefault bProductImgDefault = bProductImgDefaultService.getBProductImgDefaultById(b_product_img_default_id);
		model.addAttribute("bProductImgDefault", bProductImgDefault);
		return new ModelAndView("pc/b-view/b-product-img-default/b-product-img-default-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toBProductImgDefaultDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toBProductImgDefaultDetail(String b_product_img_default_id,HttpServletRequest request, Model model){
		BProductImgDefault bProductImgDefault = bProductImgDefaultService.getBProductImgDefaultById(b_product_img_default_id);
		model.addAttribute("bProductImgDefault", bProductImgDefault);
		return new ModelAndView("pc/b-view/b-product-img-default/b-product-img-default-detail");
	}
}
