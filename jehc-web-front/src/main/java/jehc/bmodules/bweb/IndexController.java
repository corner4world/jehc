package jehc.bmodules.bweb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jehc.bmodules.bmodel.BBrand;
import jehc.bmodules.bmodel.BCategory;
import jehc.bmodules.bmodel.BProduct;
import jehc.bmodules.bmodel.BProductColorDefault;
import jehc.bmodules.bmodel.BProductImgDefault;
import jehc.bmodules.bmodel.BRecommend;
import jehc.bmodules.bservice.BBrandService;
import jehc.bmodules.bservice.BCategoryService;
import jehc.bmodules.bservice.BProductColorDefaultService;
import jehc.bmodules.bservice.BProductImgDefaultService;
import jehc.bmodules.bservice.BProductService;
import jehc.bmodules.bservice.BRecommendService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.CommonUtils;
/**
 * 
 * @author 邓纯杰
 *
 */
@Controller
public class IndexController extends BaseAction{
	@Autowired
	private BBrandService bBrandService;
	@Autowired
	private BCategoryService bCategoryService;
	@Autowired
	private BRecommendService bRecommendService;
	@Autowired
	private BProductService bProductService;
	@Autowired
	private BProductImgDefaultService bProductImgDefaultService;
	@Autowired
	private BProductColorDefaultService bProductColorDefaultService;
	/**
	 * 载入商城首页
	 * @param b_Category
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/index.html",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView index(Model model,HttpServletRequest request){
		String jehcimg_base_url = CommonUtils.getXtPathCache("jehcimg_base_url").get(0).getXt_path();
		//品牌
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("offset", 0);
		condition.put("pageSize", 20);
		List<BBrand> bBrandList = bBrandService.getBBrandListByCondition(condition);
		//一级分类
		condition = new HashMap<String, Object>();
		condition.put("b_category_pid", "0");
		condition.put("offset", 0);
		condition.put("pageSize", 50);
		List<BCategory> bCategoryList = bCategoryService.getBCategoryListAllByCondition(condition);
		for(int i = 0; i < bCategoryList.size(); i++){
			BCategory bCategory = bCategoryList.get(i);
			condition = new HashMap<String, Object>();
			condition.put("offset", 0);
			condition.put("pageSize", 50);
			condition.put("b_category_pid", bCategory.getB_category_id());
			//二级分类
			List<BCategory> bCategoryTwoList = bCategoryService.getBCategoryListAllByCondition(condition);
			condition.put("b_category_pid", bCategory.getB_category_id());
			for(int j = 0; j < bCategoryTwoList.size(); j++){
				BCategory bCategoryTwo = bCategoryTwoList.get(j);
				//三级分类
				condition = new HashMap<String, Object>();
				condition.put("offset", 0);
				condition.put("pageSize", 50);
				condition.put("b_category_pid", bCategoryTwo.getB_category_id());
				List<BCategory> bCategoryThreeList = bCategoryService.getBCategoryListAllByCondition(condition);
				bCategoryTwoList.get(j).setBcategorys(bCategoryThreeList);
			}
			bCategoryList.get(i).setBcategorys(bCategoryTwoList);
		}
		//滚动图片展示
		condition = new HashMap<String, Object>();
		condition.put("offset", 0);
		condition.put("pageSize", 5);
		List<BRecommend> bRecommendList = bRecommendService.getBRecommendListByCondition(condition);
		//商品主信息列表
		condition = new HashMap<String, Object>();
		condition.put("offset", 0);
		condition.put("pageSize", 8);
		List<BProduct> bProductList = bProductService.getBProductListByCondition(condition);
		for(int i = 0; i < bProductList.size(); i++){
			condition = new HashMap<String, Object>();
			condition.put("offset", 0);
			condition.put("pageSize", 4);
			condition.put("b_product_id", bProductList.get(i).getB_product_id());
			bProductList.get(i).setBproductImgDefaultList(bProductImgDefaultService.getBProductImgDefaultListByCondition(condition));
		}
		for(int i = 0; i < bProductList.size(); i++){
			bProductList.get(i).setJehcimg_base_url(jehcimg_base_url);
		}
		model.addAttribute("bProductList", bProductList);
		model.addAttribute("bCategoryList", bCategoryList);
		model.addAttribute("bBrandList", bBrandList);
		model.addAttribute("bRecommendList", bRecommendList);
		model.addAttribute("jehcimg_base_url", jehcimg_base_url);
		return new ModelAndView("pc/index");
	}
	
	/**
	 * 载入商城详情页面
	 * @param b_Category
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/detail/{b_product_id}",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView detail(@PathVariable("b_product_id")String b_product_id,Model model,HttpServletRequest request){
		String jehcimg_base_url = CommonUtils.getXtPathCache("jehcimg_base_url").get(0).getXt_path();
		//品牌
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("offset", 0);
		condition.put("pageSize", 20);
		List<BBrand> bBrandList = bBrandService.getBBrandListByCondition(condition);
		//一级分类
		condition = new HashMap<String, Object>();
		condition.put("b_category_pid", "0");
		condition.put("offset", 0);
		condition.put("pageSize", 50);
		List<BCategory> bCategoryList = bCategoryService.getBCategoryListAllByCondition(condition);
		for(int i = 0; i < bCategoryList.size(); i++){
			BCategory bCategory = bCategoryList.get(i);
			condition = new HashMap<String, Object>();
			condition.put("offset", 0);
			condition.put("pageSize", 50);
			condition.put("b_category_pid", bCategory.getB_category_id());
			//二级分类
			List<BCategory> bCategoryTwoList = bCategoryService.getBCategoryListAllByCondition(condition);
			condition.put("b_category_pid", bCategory.getB_category_id());
			for(int j = 0; j < bCategoryTwoList.size(); j++){
				BCategory bCategoryTwo = bCategoryTwoList.get(j);
				//三级分类
				condition = new HashMap<String, Object>();
				condition.put("offset", 0);
				condition.put("pageSize", 50);
				condition.put("b_category_pid", bCategoryTwo.getB_category_id());
				List<BCategory> bCategoryThreeList = bCategoryService.getBCategoryListAllByCondition(condition);
				bCategoryTwoList.get(j).setBcategorys(bCategoryThreeList);
			}
			bCategoryList.get(i).setBcategorys(bCategoryTwoList);
		}
		//省市
		//商品主信息
		condition = new HashMap<String, Object>();
		condition.put("b_product_id", b_product_id);
		BProduct bProduct = bProductService.getBProductById(b_product_id);
		condition.put("offset", 0);
		condition.put("pageSize", 4);
		condition.put("b_product_img_type", "1");
		//商品默认图片
		List<BProductImgDefault> bProductImgDefaultList = bProductImgDefaultService.getBProductImgDefaultListByCondition(condition);
		//商品默认颜色
		condition.put("offset", 0);
		condition.put("pageSize", 10);
		condition.put("b_product_id", b_product_id);
		List<BProductColorDefault> bProductColorDefaultList = bProductColorDefaultService.getBProductColorDefaultListByCondition(condition);
		//滚动图片展示
		condition = new HashMap<String, Object>();
		condition.put("offset", 0);
		condition.put("pageSize", 5);
		List<BRecommend> bRecommendList = bRecommendService.getBRecommendListByCondition(condition);
		model.addAttribute("bProduct", bProduct);
		model.addAttribute("bCategoryList", bCategoryList);
		model.addAttribute("bBrandList", bBrandList);
		model.addAttribute("jehcimg_base_url", jehcimg_base_url);
		model.addAttribute("bProductColorDefaultList", bProductColorDefaultList);
		model.addAttribute("bProductImgDefaultList", bProductImgDefaultList);
		model.addAttribute("bRecommendList", bRecommendList);
		return new ModelAndView("pc/detail");
	}
	
	/**
	 * 载入商城列表页面
	 * @param b_Category
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/list.html",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView list(Model model,HttpServletRequest request){
		String jehcimg_base_url = CommonUtils.getXtPathCache("jehcimg_base_url").get(0).getXt_path();
		//品牌
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("offset", 0);
		condition.put("pageSize", 20);
		List<BBrand> bBrandList = bBrandService.getBBrandListByCondition(condition);
		//一级分类
		condition = new HashMap<String, Object>();
		condition.put("b_category_pid", "0");
		condition.put("offset", 0);
		condition.put("pageSize", 50);
		List<BCategory> bCategoryList = bCategoryService.getBCategoryListAllByCondition(condition);
		for(int i = 0; i < bCategoryList.size(); i++){
			BCategory bCategory = bCategoryList.get(i);
			condition = new HashMap<String, Object>();
			condition.put("offset", 0);
			condition.put("pageSize", 50);
			condition.put("b_category_pid", bCategory.getB_category_id());
			//二级分类
			List<BCategory> bCategoryTwoList = bCategoryService.getBCategoryListAllByCondition(condition);
			condition.put("b_category_pid", bCategory.getB_category_id());
			for(int j = 0; j < bCategoryTwoList.size(); j++){
				BCategory bCategoryTwo = bCategoryTwoList.get(j);
				//三级分类
				condition = new HashMap<String, Object>();
				condition.put("offset", 0);
				condition.put("pageSize", 50);
				condition.put("b_category_pid", bCategoryTwo.getB_category_id());
				List<BCategory> bCategoryThreeList = bCategoryService.getBCategoryListAllByCondition(condition);
				bCategoryTwoList.get(j).setBcategorys(bCategoryThreeList);
			}
			bCategoryList.get(i).setBcategorys(bCategoryTwoList);
		}
		//省市
		//滚动图片展示
		condition = new HashMap<String, Object>();
		condition.put("offset", 0);
		condition.put("pageSize", 5);
		List<BRecommend> bRecommendList = bRecommendService.getBRecommendListByCondition(condition);
		//商品主信息列表
		condition = new HashMap<String, Object>();
		condition.put("offset", 0);
		condition.put("pageSize", 8);
		List<BProduct> bProductList = bProductService.getBProductListByCondition(condition);
		for(int i = 0; i < bProductList.size(); i++){
			bProductList.get(i).setJehcimg_base_url(jehcimg_base_url);
		}
		model.addAttribute("bProductList", bProductList);
		model.addAttribute("bCategoryList", bCategoryList);
		model.addAttribute("bBrandList", bBrandList);
		model.addAttribute("bRecommendList", bRecommendList);
		model.addAttribute("jehcimg_base_url", jehcimg_base_url);
		return new ModelAndView("pc/list");
	}
}
