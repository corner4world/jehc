package jehc.bmodules.bweb;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jehc.bmodules.bmodel.BBrand;
import jehc.bmodules.bservice.BBrandService;
import jehc.xtmodules.xtcore.base.BaseAction;

/**
* 基础-品牌 
* 2016-01-05 12:46:23  邓纯杰
*/
@Controller
@RequestMapping("/bBrandController")
@Api(tags="基础（品牌）")
public class BBrandController extends BaseAction{
	@Autowired
	private BBrandService bBrandService;
	/**
	* 获取全部
	* @param request 
	*/
	@ApiOperation(value = "品牌", httpMethod = "GET", notes = "品牌", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/list",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody List<BBrand> list(HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		return bBrandService.getBBrandListByCondition(condition);
	}
}
