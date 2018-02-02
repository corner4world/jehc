package jehc.junitmodules.junit.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import jehc.xtmodules.xtcore.util.constant.PathConstant;

/**
 * 基础注解测试
 * @author 邓纯杰
 *
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath*:/jehc/xtmodules/xtcore/sources/spring/spring.xml" })
//@WebAppConfiguration

@RunWith(SpringJUnit4ClassRunner.class)   
@WebAppConfiguration
@ContextConfiguration(
locations = {
		PathConstant.BASE_JUNIT_PATH
})
public class BaseJunit {
	
}
