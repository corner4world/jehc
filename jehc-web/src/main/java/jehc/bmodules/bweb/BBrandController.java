package jehc.bmodules.bweb;
import java.text.SimpleDateFormat;
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

import jehc.bmodules.bmodel.BBrand;
import jehc.bmodules.bservice.BBrandService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 基础-品牌 
* 2016-01-05 12:46:23  邓纯杰
*/
@Controller
@RequestMapping("/bBrandController")
public class BBrandController extends BaseAction{
	@Autowired
	private BBrandService bBrandService;
	/**
	* 载入初始化页面
	* @param b_brand 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBBrand",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBBrand(BBrand b_Brand,HttpServletRequest request){
		return new ModelAndView("pc/b-view/b-brand/b-brand-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_brand 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBBrandListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBBrandListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<BBrand> b_BrandList = bBrandService.getBBrandListByCondition(condition);
		PageInfo<BBrand> page = new PageInfo<BBrand>(b_BrandList);
		return outPageBootStr(page,request);
	}
	
	/**
	* 读取所有品牌集合
	* @param b_brand 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBBrandList",method={RequestMethod.POST,RequestMethod.GET})
	public List<BBrand> getBBrandList(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		List<BBrand> b_BrandList = bBrandService.getBBrandListByCondition(condition);
		return b_BrandList;
	}
	
	/**
	* 获取对象
	* @param b_brand_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBBrandById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBBrandById(String b_brand_id,HttpServletRequest request){
		BBrand b_Brand = bBrandService.getBBrandById(b_brand_id);
		return outDataStr(b_Brand);
	}
	/**
	* 添加
	* @param b_brand 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBBrand",method={RequestMethod.POST,RequestMethod.GET})
	public String addBBrand(BBrand b_Brand,HttpServletRequest request){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int i = 0;
		if(null != b_Brand && !"".equals(b_Brand)){
			b_Brand.setB_brand_id(UUID.toUUID());
			b_Brand.setB_brand_ctime(getDate());
			b_Brand.setXt_userinfo_id(CommonUtils.getXtUid());
			i=bBrandService.addBBrand(b_Brand);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_brand 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBBrand",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBBrand(BBrand b_Brand,HttpServletRequest request){
		int i = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(null != b_Brand && !"".equals(b_Brand)){
			b_Brand.setB_brand_mtime(getDate());
			b_Brand.setXt_userinfo_id(CommonUtils.getXtUid());
			i=bBrandService.updateBBrand(b_Brand);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_brand_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBBrand",method={RequestMethod.POST,RequestMethod.GET})
	public String delBBrand(String b_brand_id,HttpServletRequest request){
		int i = 0;
		if(null != b_brand_id && !"".equals(b_brand_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_brand_id",b_brand_id.split(","));
			i=bBrandService.delBBrand(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_brand_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBBrand",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBBrand(String b_brand_id,HttpServletRequest request){
		int i = 0;
		BBrand b_Brand = bBrandService.getBBrandById(b_brand_id);
		if(null != b_Brand && !"".equals(b_Brand)){
			b_Brand.setB_brand_id(UUID.toUUID());
			i=bBrandService.addBBrand(b_Brand);
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
	@RequestMapping(value="/exportBBrand",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBBrand(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	
	/**
	* 发送至新增页面
	* @param request 
	*/
	@RequestMapping(value="/toBBrandAdd",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toBBrandAdd(BBrand bBrand,HttpServletRequest request){
		return new ModelAndView("pc/b-view/b-brand/b-brand-add");
	}
	/**
	* 发送至编辑页面
	* @param request 
	*/
	@RequestMapping(value="/toBBrandUpdate",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toBBrandUpdate(String b_brand_id,HttpServletRequest request, Model model){
		BBrand bBrand = bBrandService.getBBrandById(b_brand_id);
		model.addAttribute("bBrand", bBrand);
		return new ModelAndView("pc/b-view/b-brand/b-brand-update");
	}
	/**
	* 发送至明细页面
	* @param request 
	*/
	@RequestMapping(value="/toBBrandDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView toBBrandDetail(String b_brand_id,HttpServletRequest request, Model model){
		BBrand bBrand = bBrandService.getBBrandById(b_brand_id);
		model.addAttribute("bBrand", bBrand);
		return new ModelAndView("pc/b-view/b-brand/b-brand-detail");
	}
}
