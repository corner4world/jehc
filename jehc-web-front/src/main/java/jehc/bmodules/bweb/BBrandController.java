package jehc.bmodules.bweb;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jehc.bmodules.bmodel.BBrand;
import jehc.bmodules.bservice.BBrandService;
import jehc.xtmodules.xtcore.base.BaseAction;

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
	* 获取对象
	* @param b_brand_id 
	* @param request 
	*/
	@RequestMapping(value="/getBBrandById",method={RequestMethod.POST,RequestMethod.GET})
	public void getBBrandById(String b_brand_id,HttpServletRequest request){
		if(null != b_brand_id && !"".equals(b_brand_id)){
			BBrand b_Brand = bBrandService.getBBrandById(b_brand_id);
			outDataStr(b_Brand);
		}
	}
}
