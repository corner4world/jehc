package jehc.junitmodules.junit.xtjunit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import jehc.xtmodules.xtcore.base.BaseJunit;
import jehc.xtmodules.xtmodel.XtCompany;
import jehc.xtmodules.xtservice.XtCompanyService;
import jehc.xtmodules.xtservice.XtUserinfoService;

public class XtUserinfoJunit extends BaseJunit{
	@Autowired
	private XtUserinfoService xtUserinfoService;
	@Autowired
	private XtCompanyService xtCompanyService;
	@Test
	public void getXtUserinfoList(){
		Map<String, Object> condition = new HashMap<String, Object>();
		XtCompany xt_Company = new XtCompany();
		List<XtCompany> XtCompanyList = xtCompanyService.getXtCompanyListByCondition(condition);
		if(!XtCompanyList.isEmpty()){
			xt_Company = XtCompanyList.get(0);
		}
		xt_Company.setXt_company_ceo("邓纯杰");
		xtCompanyService.updateXtCompany(xt_Company);
		
		condition = new HashMap<String, Object>();
		System.out.println(xtUserinfoService.getXtUserinfoList(condition).size());
	}
}
