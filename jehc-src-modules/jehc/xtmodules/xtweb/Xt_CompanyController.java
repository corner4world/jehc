package jehc.xtmodules.xtweb;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtmodel.Xt_Company;
import jehc.xtmodules.xtservice.Xt_CompanyService;

/**
* 平台公司信息表 
* 2015-05-12 22:59:42  邓纯杰
*/
@Controller
@RequestMapping("/xtCompanyController")
public class Xt_CompanyController extends BaseAction{
	@Autowired
	private Xt_CompanyService xt_CompanyService;
	/**
	* 载入初始化页面
	* @param xt_company 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtCompany",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtCompany(Xt_Company xt_Company,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-company/xt-company-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param xt_company 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtCompanyListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtCompanyListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<Xt_Company> xtCompanyList = xt_CompanyService.getXtCompanyListByCondition(condition);
		PageInfo<Xt_Company> page = new PageInfo<Xt_Company>(xtCompanyList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_company_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtCompanyById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtCompanyById(String xt_company_id,HttpServletRequest request){
		Xt_Company xt_Company = xt_CompanyService.getXtCompanyById(xt_company_id);
		return outDataStr(xt_Company);
	}
	/**
	* 获取对象
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtCompany",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtCompany(HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		commonPager(condition,request);
		List<Xt_Company> XtCompanyList = xt_CompanyService.getXtCompanyListByCondition(condition);
		Xt_Company xt_Company = new Xt_Company();
		if(!XtCompanyList.isEmpty()){
			xt_Company = XtCompanyList.get(0);
		}
		return outDataStr(xt_Company);
	}
	
	/**
	* 添加或修改
	* @param xt_company 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addOrUpdateXtCompany",method={RequestMethod.POST,RequestMethod.GET})
	public String addOrUpdateXtCompany(Xt_Company xt_Company,HttpServletRequest request){
		int i = 0;
		if(null != xt_Company && !"".equals(xt_Company)){
			if(null != xt_Company.getXt_company_id() && !"".equals(xt_Company.getXt_company_id())){
				i = xt_CompanyService.updateXtCompany(xt_Company);
			}else{
				xt_Company.setXt_company_id(UUID.toUUID());
				i=xt_CompanyService.addXtCompany(xt_Company);
			}
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	
	/**
	* 添加
	* @param xt_company 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtCompany",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtCompany(Xt_Company xt_Company,HttpServletRequest request){
		int i = 0;
		if(null != xt_Company && !"".equals(xt_Company)){
			xt_Company.setXt_company_id(UUID.toUUID());
			i=xt_CompanyService.addXtCompany(xt_Company);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_company 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtCompany",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtCompany(Xt_Company xt_Company,HttpServletRequest request){
		int i = 0;
		if(null != xt_Company && !"".equals(xt_Company)){
			i=xt_CompanyService.updateXtCompany(xt_Company);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_company_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtCompany",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtCompany(String xt_company_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_company_id && !"".equals(xt_company_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_company_id",xt_company_id);
			i=xt_CompanyService.delXtCompany(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
}
