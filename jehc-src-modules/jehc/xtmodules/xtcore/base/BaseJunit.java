package jehc.xtmodules.xtcore.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 基础注解测试
 * @author 邓纯杰
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)   
@ContextConfiguration(
locations = {
	"classpath*:xtCore/sources/spring/spring.xml"
})
public class BaseJunit {
	
}
