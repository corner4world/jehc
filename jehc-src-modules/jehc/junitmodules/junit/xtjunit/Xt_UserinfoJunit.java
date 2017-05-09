package jehc.junitmodules.junit.xtjunit;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import jehc.xtmodules.xtcore.base.BaseJunit;
import jehc.xtmodules.xtmodel.Xt_Userinfo;
import jehc.xtmodules.xtservice.Xt_UserinfoService;

public class Xt_UserinfoJunit extends BaseJunit{
	//模拟request,response  
    private MockHttpServletRequest request;  
    private MockHttpServletResponse response;   
    //执行测试方法之前初始化模拟request,response  
    @Before    
    public void setUp(){    
        request = new MockHttpServletRequest();      
        request.setCharacterEncoding("UTF-8");      
        response = new MockHttpServletResponse();      
    } 
	@Autowired
	private Xt_UserinfoService xt_UserinfoService;
	@Test
	public void getXtUserinfoById(){
		Xt_Userinfo xt_Userinfo = xt_UserinfoService.getXtUserinfoById("00f377cd266c4fc38e1575a559bd842d");
		System.out.println(xt_Userinfo.getXt_departinfo_name());
	}
}
