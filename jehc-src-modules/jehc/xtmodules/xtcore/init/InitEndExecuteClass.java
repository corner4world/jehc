package jehc.xtmodules.xtcore.init;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


/**
 * 容器启动结束之后执行方法
 * 
 * @author邓纯杰
 * 
 */
public class InitEndExecuteClass extends HttpServlet {
	private static final long serialVersionUID = -9045451275234606838L;
	//Servlet的init方法会在Tomcat启动的时候执行
	public void init() throws ServletException {
	//System.out.println("工程启动结束之后执行的类开始");
	}
}
