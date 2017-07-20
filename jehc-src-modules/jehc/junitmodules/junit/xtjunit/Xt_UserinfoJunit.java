package jehc.junitmodules.junit.xtjunit;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import jehc.xtmodules.xtcore.base.BaseJunit;
import jehc.xtmodules.xtcore.util.springutil.GetApplicationContext;
import jehc.xtmodules.xtmodel.Xt_Userinfo;
import jehc.xtmodules.xtservice.Xt_UserinfoService;

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
	private Xt_UserinfoService xt_UserinfoService;
	@Test
	public void getXtUserinfoById(){
//		Xt_UserinfoService service =(Xt_UserinfoService)GetApplicationContext.getBean("xt_UserinfoService");
		Map<String, Object> condition = new HashMap<String, Object>();
		System.out.println(xt_UserinfoService.getXtUserinfoList(condition).size());
	}
}
