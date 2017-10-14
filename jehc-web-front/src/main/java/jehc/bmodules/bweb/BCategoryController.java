package jehc.bmodules.bweb;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jehc.bmodules.bmodel.BCategory;
import jehc.bmodules.bservice.BCategoryService;
import jehc.xtmodules.xtcore.base.BaseAction;

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
	* 获取对象
	* @param b_category_id 
	* @param request 
	*/
	@RequestMapping(value="/getBCategoryById",method={RequestMethod.POST,RequestMethod.GET})
	public void getBCategoryById(String b_category_id,HttpServletRequest request){
		if(null != b_category_id && !"".equals(b_category_id)){
			BCategory b_Category = bCategoryService.getBCategoryById(b_category_id);
			outDataStr(b_Category);
		}
	}
}
