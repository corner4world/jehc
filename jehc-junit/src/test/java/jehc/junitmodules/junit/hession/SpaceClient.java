package jehc.junitmodules.junit.hession;

import java.net.MalformedURLException;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.caucho.hessian.client.HessianProxyFactory;

import jehc.xtmodules.xtcore.base.BaseJunit;
import jehc.xtmodules.xtcore.hession.SpaceService;

public class SpaceClient extends BaseJunit{
	@Autowired
	SpaceService spaceServiceBurlap;
	public static void main(String[] args) {
		/////////采用Hessian版本方式调用
	    String url = "http://localhost:8081/jehc/SpaceHessian";
		HessianProxyFactory factory = new HessianProxyFactory();
		try {
			SpaceService spaceService = (SpaceService) factory.create(SpaceService.class, url);
			System.out.println(spaceService.findSpace("testid"));
			System.out.println(spaceService.updateSpaceName("whatever"));
			List spaceNames = spaceService.showSpaceNames();
			System.out.println("\r\nGet space names:");
			for (int i = 0; i < spaceNames.size(); i++) {
				System.out.print(spaceNames.get(i) + ", ");
			}
			System.out.println();
			System.out.println("space names list finished");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}  
	} 
	
	@Test
	public void test(){
		 //////////////采用Spring代理模式调用
		try {
			System.out.println(spaceServiceBurlap.findSpace("testid"));
			System.out.println(spaceServiceBurlap.updateSpaceName("whatever"));
			List spaceNames = spaceServiceBurlap.showSpaceNames();
			System.out.println("\r\nGet space names:");
			for (int i = 0; i < spaceNames.size(); i++) {
				System.out.print(spaceNames.get(i) + ", ");
			}
			System.out.println();
			System.out.println("space names list finished");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
