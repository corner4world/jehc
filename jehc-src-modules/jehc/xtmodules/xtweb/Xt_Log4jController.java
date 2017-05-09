package jehc.xtmodules.xtweb;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jehc.xtmodules.xtcore.base.BaseAction;

/**
 * Log4j动态修改 重启服务
 * @author邓纯杰
 *
 */
@Controller
@RequestMapping("/xtLog4jController")
public class Xt_Log4jController extends BaseAction {
	static Logger log=Logger.getLogger(Xt_Log4jController.class);
	
	/**
	* 载入初始化页面
	* @param request 
	* @return
	 * @throws IOException 
	*/
	@RequestMapping(value="/loadLog4j",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtLog4j(HttpServletRequest request,Model model) throws IOException{
		return new ModelAndView("pc/xt-view/xt-log4j/xt-log4j-list");
	}
	
	/**
	* 载入编辑页面
	* @param request 
	* @return
	 * @throws IOException 
	*/
	@RequestMapping(value="/loadLog4jEditor",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadLog4jEditor(HttpServletRequest request,Model model) throws IOException{
		StringBuffer sbf = new StringBuffer();
        //获得类加载器，然后把文件作为一个流获取
        String path = request.getSession().getServletContext().getRealPath("/")+"WEB-INF/classes/jehc/xtmodules/xtcore/sources/log4j.properties";
        FileInputStream fis = new FileInputStream(path);
        //创建Properties实例
        Properties prop = new Properties();
        //将Properties和流关联
        prop.load(fis);
        //获取所有的名称
        Enumeration<?> allName = prop.propertyNames();
        //遍历
        while(allName.hasMoreElements()){
            //获得每一个名称
            String name = (String)allName.nextElement();
            //利用已得到的名称通过Properties对象获得相应的值
            String value = (String) prop.get(name);
            sbf.append(name+ "=" +value+"\r\n");
        }
        //关闭资源
        fis.close();
        model.addAttribute("xtLog4jContent", sbf.toString().substring(0,sbf.toString().length()-4));
		return new ModelAndView("pc/xt-view/xt-log4j/xt-log4j-editor");
	} 
	
	/**
	 * 自动重新加载
	 */
	@ResponseBody
	@RequestMapping(value="/autoLoadLog4j",method={RequestMethod.POST,RequestMethod.GET})
	public String autoLoadLog4j(HttpServletRequest request){
		try {
			String path = request.getSession().getServletContext().getRealPath("/")+"WEB-INF/classes/jehc/xtmodules/xtcore/sources/log4j.properties";
			PropertyConfigurator.configure(path);
			return outAudStr(true);
		} catch (Exception e) {
			return outAudStr(false);
		}
	}
}
