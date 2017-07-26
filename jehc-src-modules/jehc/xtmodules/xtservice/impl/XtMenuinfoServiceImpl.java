package jehc.xtmodules.xtservice.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtMenuinfoDao;
import jehc.xtmodules.xtmodel.XtMenuinfo;
import jehc.xtmodules.xtservice.XtMenuinfoService;
/**
 * 系统菜单表
 * 
 * @author邓纯杰
 * 
 */
@Service("xtMenuinfoService")
public class XtMenuinfoServiceImpl extends BaseService implements XtMenuinfoService {
	@Autowired
	private XtMenuinfoDao xtMenuinfoDao;
	/**
	 * 查询所有集合列表
	 * @param condition
	 * @return
	 */
	public List<XtMenuinfo> getXtMenuinfoListByCondition(Map<String, Object> condition){
		try {
			return xtMenuinfoDao.getXtMenuinfoListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 查找所有根菜单
	 * @return
	 */
	public List<XtMenuinfo> getXtMenuinfoList(){
		try {
			return xtMenuinfoDao.getXtMenuinfoList();
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 查找非父菜单集合
	 * @return
	 */
	public List<XtMenuinfo> getXtMenuinfoListAllChild(){
		try {
			return xtMenuinfoDao.getXtMenuinfoListAllChild();
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	
	/**
	 * 查找子菜单
	 * @param condition
	 * @return
	 */
	public List<XtMenuinfo> getXtMenuinfoListChild(Map<String, Object> condition){
		try {
			return xtMenuinfoDao.getXtMenuinfoListChild(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 查找对象根据主键
	 * @param xt_menuinfo_id
	 * @return
	 */
	public XtMenuinfo getXtMenuinfo(String xt_menuinfo_id){
		try {
			return xtMenuinfoDao.getXtMenuinfo(xt_menuinfo_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 添加菜单
	 * @param xt_Menuinfo
	 */
	public int addXtMenuinfo(XtMenuinfo xt_Menuinfo){
		int i = 0;
		try {
			i = xtMenuinfoDao.addXtMenuinfo(xt_Menuinfo);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	/**
	 * 修改菜单
	 * @param xt_Menuinfo
	 */
	public int updateXtMenuinfo(XtMenuinfo xt_Menuinfo){
		int i = 0;
		try {
			i = xtMenuinfoDao.updateXtMenuinfo(xt_Menuinfo);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	/**
	 * 查询所有菜单
	 * @param condition
	 * @return
	 */
	public List<XtMenuinfo> getXtMenuinfoListAll(Map<String, Object> condition){
		try {
			return xtMenuinfoDao.getXtMenuinfoListAll(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 根据xt_menuinfo_parentId查找集合
	 * @param xt_menuinfo_parentId
	 * @return
	 */
	public List<XtMenuinfo> getXtMenuinfoByParentID(String xt_menuinfo_parentId){
		try {
			return xtMenuinfoDao.getXtMenuinfoByParentID(xt_menuinfo_parentId);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 删除菜单
	 * @param condition
	 */
	public int delXtMenuinfo(Map<String, Object> condition){
		int i = 0;
		try {
			i = xtMenuinfoDao.delXtMenuinfo(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	/**
	 * 排序
	 * @param condition
	 */
	public int updateXtMenuinfoSort(Map<String, Object> condition){
		int i = 0;
		try {
			i = xtMenuinfoDao.updateXtMenuinfoSort(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	/**
	 * 查找根目录为了权限使用
	 * @param condition
	 * @return
	 */
	public List<XtMenuinfo> getXtMenuinfoRootForRole(Map<String, Object> condition){
		try {
			return xtMenuinfoDao.getXtMenuinfoRootForRole(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 查找孩子为了权限使用
	 * @param condition
	 * @return
	 */
	public List<XtMenuinfo> getXtMenuinfoChildForRole(Map<String, Object> condition){
		try {
			return xtMenuinfoDao.getXtMenuinfoChildForRole(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
