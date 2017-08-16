package jehc.xtmodules.xtcore.util.constant;

/**
 * 路径常量
 * @author 邓纯杰
 *
 */
public class PathConstant {
	
	public static final String XT_SESSION_JSP_PATH ="/WEB-INF/view/pc/xt-view/xt-session/xt-session.jsp";//session失效发送至页面
	
	public static final String XT_NO_ROLE_JSP_PATH ="/WEB-INF/view/pc/xt-view/xt-no-role/xt-no-role.jsp";//没有权限发送至页面
	
	public static final String XT_ILLEGAL_JSP_PATH = "/WEB-INF/view/pc/xt-view/xt-illegal/xt-illegal.jsp";//发送至黑名单拦截页面
	
	public static final String XT_ERROR_JSP_PATH = "pc/xt-view/xt-error/xt-error";//错误页面
	
	public static final String JDBC_PROPERTIES_PATH = "config/jdbc.properties";//jdbc配置路径
	
	public static final String ZN_PROPERTIES_PATH = "WEB-INF/classes/config/properties/zh.properties";//国际化中文
	
	public static final String MESSAGE_PROPERTIES_PATH = "WEB-INF/classes/config/properties/message.properties";//消息资源文件
	
	public static final String CONFIG_PROPERTIES_PATH = "WEB-INF/classes/config/properties/config.properties";//配置文件
	
	public static final String CONFIG_PROPERTIES_PATH_ = "/WEB-INF/classes/config/properties/config.properties";//配置文件
	
	public static final String BASE_JUNIT_PATH = "classpath*:/config/spring/spring.xml";//spring配置文件路径
	
	public static final String LOG4J_PATH = "WEB-INF/classes/config/log4j.properties";//log4j配置路径
	
	public static final String LOGBACK_PATH = "WEB-INF/classes/config/logback.xml";//logback路径
}
