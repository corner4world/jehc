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

import jehc.bmodules.bmodel.BProductColor;
import jehc.bmodules.bservice.BProductColorService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 基础商品商户所选颜色 
* 2016-07-02 16:54:11  邓纯杰
*/
@Controller
@RequestMapping("/bProductColorController")
public class BProductColorController extends BaseAction{
	@Autowired
	private BProductColorService bProductColorService;
	/**
	* 载入初始化页面
	* @param b_product_color 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBProductColor",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBProductColor(BProductColor b_Product_Color,HttpServletRequest request,Model model){
		model.addAttribute("b_product_id", b_Product_Color.getB_product_id());
		return new ModelAndView("pc/b-view/b-product-color/b-product-color-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_product_color 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBProductColorListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBProductColorListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<BProductColor> b_Product_ColorList = bProductColorService.getBProductColorListByCondition(condition);
		PageInfo<BProductColor> page = new PageInfo<BProductColor>(b_Product_ColorList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param b_product_color_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBProductColorById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBProductColorById(String b_product_color_id,HttpServletRequest request){
		BProductColor b_Product_Color = bProductColorService.getBProductColorById(b_product_color_id);
		return outDataStr(b_Product_Color);
	}
	/**
	* 添加
	* @param b_product_color 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBProductColor",method={RequestMethod.POST,RequestMethod.GET})
	public String addBProductColor(BProductColor b_Product_Color,HttpServletRequest request){
		int i = 0;
		if(null != b_Product_Color && !"".equals(b_Product_Color)){
			b_Product_Color.setB_product_color_id(UUID.toUUID());
			b_Product_Color.setXt_userinfo_id(getXtUid());
			b_Product_Color.setB_product_color_ctime(getDate());
			i=bProductColorService.addBProductColor(b_Product_Color);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_product_color 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBProductColor",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBProductColor(BProductColor b_Product_Color,HttpServletRequest request){
		int i = 0;
		if(null != b_Product_Color && !"".equals(b_Product_Color)){
			b_Product_Color.setXt_userinfo_id(getXtUid());
			b_Product_Color.setB_product_color_mtime(getDate());
			i=bProductColorService.updateBProductColor(b_Product_Color);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_product_color_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBProductColor",method={RequestMethod.POST,RequestMethod.GET})
	public String delBProductColor(String b_product_color_id,HttpServletRequest request){
		int i = 0;
		if(null != b_product_color_id && !"".equals(b_product_color_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_product_color_id",b_product_color_id.split(","));
			i=bProductColorService.delBProductColor(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_product_color_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBProductColor",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBProductColor(String b_product_color_id,HttpServletRequest request){
		int i = 0;
		BProductColor b_Product_Color = bProductColorService.getBProductColorById(b_product_color_id);
		if(null != b_Product_Color && !"".equals(b_Product_Color)){
			b_Product_Color.setB_product_color_id(UUID.toUUID());
			i=bProductColorService.addBProductColor(b_Product_Color);
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
	@RequestMapping(value="/exportBProductColor",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBProductColor(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toBProductColorAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toBProductColorAdd(BProductColor bProductColor,HttpServletRequest request, Model model){
		model.addAttribute("b_product_id", bProductColor.getB_product_id());
		return new ModelAndView("pc/b-view/b-product-color/b-product-color-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toBProductColorUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toBProductColorUpdate(String b_product_color_id,HttpServletRequest request, Model model){
		BProductColor bProductColor = bProductColorService.getBProductColorById(b_product_color_id);
		model.addAttribute("bProductColor", bProductColor);
		return new ModelAndView("pc/b-view/b-product-color/b-product-color-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toBProductColorDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toBProductColorDetail(String b_product_color_id,HttpServletRequest request, Model model){
		BProductColor bProductColor = bProductColorService.getBProductColorById(b_product_color_id);
		model.addAttribute("bProductColor", bProductColor);
		return new ModelAndView("pc/b-view/b-product-color/b-product-color-detail");
	}
}
