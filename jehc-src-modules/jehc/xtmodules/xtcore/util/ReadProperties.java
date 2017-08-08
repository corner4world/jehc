package jehc.xtmodules.xtcore.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContextEvent;

import jehc.xtmodules.xtcore.allutils.AllUtils;

/**
 * 读取properties文件
 * @author邓纯杰
 *
 */
public class ReadProperties {
	/**
	 * 读取平台配置文件
	 * @param event
	 * @return
	 */
	public static Map<String, Object> readProperties(ServletContextEvent event){
		Properties prop = new Properties();
		Map<String, Object> map = new HashMap<String, Object>();
        FileInputStream fis;
		try {
			String path = event.getServletContext().getRealPath("/")+"WEB-INF/classes/sources/properties/zh.properties";
			fis = new FileInputStream(path);
			try {
				prop.load(fis);
				fis.close();
		        map.put("sys_pt_login", prop.getProperty("sys_pt_login"));
		        map.put("sys_pt_index", prop.getProperty("sys_pt_index"));
		        map.put("sys_pt_index_foot", prop.getProperty("sys_pt_index_foot"));
		        map.put("sys_pt_index_top", prop.getProperty("sys_pt_index_top"));
		        map.put("sys_pt_session", prop.getProperty("sys_pt_session"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 读取提示信息文件
	 * @param event
	 * @return
	 */
	public static Map<String, Object> readMessageProperties(ServletContextEvent event){
		Properties prop = new Properties();
		Map<String, Object> map = new HashMap<String, Object>();
        FileInputStream fis;
		try {
			String path = event.getServletContext().getRealPath("/")+"WEB-INF/classes/sources/properties/message.properties";
			fis = new FileInputStream(path);
			try {
				prop.load(fis);
				fis.close();
		        map.put("sys_add_sucess", prop.getProperty("sys_add_sucess"));
		        map.put("sys_add_error", prop.getProperty("sys_add_error"));
		        map.put("sys_update_sucess", prop.getProperty("sys_update_sucess"));
		        map.put("sys_update_error", prop.getProperty("sys_update_error"));
		        map.put("sys_del_sucess", prop.getProperty("sys_del_sucess"));
		        map.put("sys_del_error", prop.getProperty("sys_del_error"));
		        map.put("sys_get_sucess", prop.getProperty("sys_get_sucess"));
		        map.put("sys_get_error", prop.getProperty("sys_get_error"));
		        map.put("sys_operate_sucess", prop.getProperty("sys_operate_sucess"));
		        map.put("sys_operate_error", prop.getProperty("sys_operate_error"));
		        map.put("sys_import_user_sucess", prop.getProperty("sys_import_user_sucess"));
		        map.put("sys_import_user_error", prop.getProperty("sys_import_user_error"));
		        map.put("sys_remove_user_sucess", prop.getProperty("sys_remove_user_sucess"));
		        map.put("sys_remove_user_error", prop.getProperty("sys_remove_user_error"));
		        map.put("sys_import_sources_sucess", prop.getProperty("sys_import_sources_sucess"));
		        map.put("sys_improt_sourcess_error", prop.getProperty("sys_improt_sourcess_error"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 读取平台Config配置文件
	 * @param event
	 * @return
	 */
	public static Map<String, Object> readConfigProperties(ServletContextEvent event){
		Properties prop = new Properties();
		Map<String, Object> map = new HashMap<String, Object>();
        FileInputStream fis;
		try {
			String path = event.getServletContext().getRealPath("/")+"WEB-INF/classes/sources/properties/config.properties";
			fis = new FileInputStream(path);
			try {
				prop.load(fis);
				fis.close();
		        map.put("grid_toolbar_text_is_view", prop.getProperty("grid_toolbar_text_is_view"));
		        map.put("moretext", prop.getProperty("moretext"));
		        map.put("moretexttooltip", prop.getProperty("moretexttooltip"));
		        map.put("grid_toolbar_gaps", prop.getProperty("grid_toolbar_gaps"));
		        map.put("grid_toolbar_moretext_gaps", prop.getProperty("grid_toolbar_moretext_gaps"));
		        map.put("solr_home_path", prop.getProperty("solr_home_path"));
		        map.put("lc_design_displaywin_for_edit", prop.getProperty("lc_design_displaywin_for_edit"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 读取平台Config配置文件
	 * @param event
	 * @return
	 */
	public static String readConfigProperties(String key){
		Properties prop = new Properties();
        FileInputStream fis;
		try {
			String path = AllUtils.getWebRootAbsolutePath()+"/WEB-INF/classes/sources/properties/config.properties";
			fis = new FileInputStream(path);
			try {
				prop.load(fis);
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}
}
