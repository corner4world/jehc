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

import jehc.bmodules.bmodel.BProductImg;
import jehc.bmodules.bservice.BProductImgService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 基础商品图片 
* 2016-07-07 20:50:43  邓纯杰
*/
@Controller
@RequestMapping("/bProductImgController")
public class BProductImgController extends BaseAction{
	@Autowired
	private BProductImgService bProductImgService;
	/**
	* 载入初始化页面
	* @param b_product_img 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBProductImg",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBProductImg(BProductImg b_Product_Img,HttpServletRequest request,Model model){
		model.addAttribute("b_product_id", b_Product_Img.getB_product_id());
		return new ModelAndView("pc/b-view/b-product-img/b-product-img-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_product_img 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBProductImgListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBProductImgListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<BProductImg> b_Product_ImgList = bProductImgService.getBProductImgListByCondition(condition);
		PageInfo<BProductImg> page = new PageInfo<BProductImg>(b_Product_ImgList);
		return outPageBootStr(page,request);
	}
	/**
	* 获取对象
	* @param b_product_img_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBProductImgById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBProductImgById(String b_product_img_id,HttpServletRequest request){
		BProductImg b_Product_Img = bProductImgService.getBProductImgById(b_product_img_id);
		return outDataStr(b_Product_Img);
	}
	/**
	* 添加
	* @param b_product_img 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBProductImg",method={RequestMethod.POST,RequestMethod.GET})
	public String addBProductImg(BProductImg b_Product_Img,HttpServletRequest request){
		int i = 0;
		if(null != b_Product_Img && !"".equals(b_Product_Img)){
			b_Product_Img.setB_product_img_id(UUID.toUUID());
			b_Product_Img.setXt_userinfo_id(getXtUid());
			b_Product_Img.setB_product_img_ctime(getDate());
			i=bProductImgService.addBProductImg(b_Product_Img);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_product_img 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBProductImg",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBProductImg(BProductImg b_Product_Img,HttpServletRequest request){
		int i = 0;
		if(null != b_Product_Img && !"".equals(b_Product_Img)){
			b_Product_Img.setXt_userinfo_id(getXtUid());
			b_Product_Img.setB_product_img_mtime(getDate());
			i=bProductImgService.updateBProductImg(b_Product_Img);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_product_img_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBProductImg",method={RequestMethod.POST,RequestMethod.GET})
	public String delBProductImg(String b_product_img_id,HttpServletRequest request){
		int i = 0;
		if(null != b_product_img_id && !"".equals(b_product_img_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_product_img_id",b_product_img_id.split(","));
			i=bProductImgService.delBProductImg(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_product_img_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBProductImg",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBProductImg(String b_product_img_id,HttpServletRequest request){
		int i = 0;
		BProductImg b_Product_Img = bProductImgService.getBProductImgById(b_product_img_id);
		if(null != b_Product_Img && !"".equals(b_Product_Img)){
			b_Product_Img.setB_product_img_id(UUID.toUUID());
			i=bProductImgService.addBProductImg(b_Product_Img);
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
	@RequestMapping(value="/exportBProductImg",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBProductImg(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toBProductImgAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toBProductImgAdd(BProductImg bProductImg,HttpServletRequest request, Model model){
		model.addAttribute("b_product_id", bProductImg.getB_product_id());
		return new ModelAndView("pc/b-view/b-product-img/b-product-img-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toBProductImgUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toBProductImgUpdate(String b_product_img_id,HttpServletRequest request, Model model){
		BProductImg bProductImg = bProductImgService.getBProductImgById(b_product_img_id);
		model.addAttribute("bProductImg", bProductImg);
		return new ModelAndView("pc/b-view/b-product-img/b-product-img-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toBProductImgDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toBProductImgDetail(String b_product_img_id,HttpServletRequest request, Model model){
		BProductImg bProductImg = bProductImgService.getBProductImgById(b_product_img_id);
		model.addAttribute("bProductImg", bProductImg);
		return new ModelAndView("pc/b-view/b-product-img/b-product-img-detail");
	}
}
