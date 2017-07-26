package jehc.junitmodules.junit.xtjunit;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import jehc.xtmodules.xtcore.base.BaseJunit;
import jehc.xtmodules.xtcore.util.springutil.SpringUtil;
import jehc.xtmodules.xtmodel.XtUserinfo;
import jehc.xtmodules.xtservice.XtUserinfoService;

public class Xt_UserinfoJunit extends BaseJunit{
//	//模拟request,response  
//    private MockHttpServletRequest request;  
//    private MockHttpServletResponse response;   
//    //执行测试方法之前初始化模拟request,response  
//    @Before    
//    public void setUp(){    
//        request = new MockHttpServletRequest();      
//        request.setCharacterEncoding("UTF-8");      
//        response = new MockHttpServletResponse();      
//    } 
	@Autowired
	private XtUserinfoService xt_UserinfoService;
	@Test
	public void getXtUserinfoList(){
		Map<String, Object> condition = new HashMap<String, Object>();
		System.out.println(xt_UserinfoService.getXtUserinfoList(condition).size());
	}
}
