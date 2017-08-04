package jehc.junitmodules.junit.xtjunit;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import jehc.xtmodules.xtcore.base.BaseJunit;
import jehc.xtmodules.xtservice.XtUserinfoService;

public class XtUserinfoJunit extends BaseJunit{
	@Autowired
	private XtUserinfoService xtUserinfoService;
	@Test
	public void getXtUserinfoList(){
		Map<String, Object> condition = new HashMap<String, Object>();
		System.out.println(xtUserinfoService.getXtUserinfoList(condition).size());
	}
}
