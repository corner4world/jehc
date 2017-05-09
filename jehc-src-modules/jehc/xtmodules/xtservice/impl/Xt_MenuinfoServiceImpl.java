package jehc.xtmodules.xtservice.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_MenuinfoDao;
import jehc.xtmodules.xtmodel.Xt_Menuinfo;
import jehc.xtmodules.xtservice.Xt_MenuinfoService;
/**
 * 系统菜单表
 * 
 * @author邓纯杰
 * 
 */
@Service("xt_MenuinfoService")
public class Xt_MenuinfoServiceImpl extends BaseService implements Xt_MenuinfoService {
	@Autowired
	private Xt_MenuinfoDao xt_MenuinfoDao;
	/**
	 * 查询所有集合列表
	 * @param condition
	 * @return
	 */
	public List<Xt_Menuinfo> getXtMenuinfoListByCondition(Map<String, Object> condition){
		try {
			return xt_MenuinfoDao.getXtMenuinfoListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 查找所有根菜单
	 * @return
	 */
	public List<Xt_Menuinfo> getXtMenuinfoList(){
		try {
			return xt_MenuinfoDao.getXtMenuinfoList();
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 查找非父菜单集合
	 * @return
	 */
	public List<Xt_Menuinfo> getXtMenuinfoListAllChild(){
		try {
			return xt_MenuinfoDao.getXtMenuinfoListAllChild();
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
	public List<Xt_Menuinfo> getXtMenuinfoListChild(Map<String, Object> condition){
		try {
			return xt_MenuinfoDao.getXtMenuinfoListChild(condition);
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
	public Xt_Menuinfo getXtMenuinfo(String xt_menuinfo_id){
		try {
			return xt_MenuinfoDao.getXtMenuinfo(xt_menuinfo_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 添加菜单
	 * @param xt_Menuinfo
	 */
	public int addXtMenuinfo(Xt_Menuinfo xt_Menuinfo){
		int i = 0;
		try {
			i = xt_MenuinfoDao.addXtMenuinfo(xt_Menuinfo);
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
	public int updateXtMenuinfo(Xt_Menuinfo xt_Menuinfo){
		int i = 0;
		try {
			i = xt_MenuinfoDao.updateXtMenuinfo(xt_Menuinfo);
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
	public List<Xt_Menuinfo> getXtMenuinfoListAll(Map<String, Object> condition){
		try {
			return xt_MenuinfoDao.getXtMenuinfoListAll(condition);
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
	public List<Xt_Menuinfo> getXtMenuinfoByParentID(String xt_menuinfo_parentId){
		try {
			return xt_MenuinfoDao.getXtMenuinfoByParentID(xt_menuinfo_parentId);
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
			i = xt_MenuinfoDao.delXtMenuinfo(condition);
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
			i = xt_MenuinfoDao.updateXtMenuinfoSort(condition);
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
	public List<Xt_Menuinfo> getXtMenuinfoRootForRole(Map<String, Object> condition){
		try {
			return xt_MenuinfoDao.getXtMenuinfoRootForRole(condition);
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
	public List<Xt_Menuinfo> getXtMenuinfoChildForRole(Map<String, Object> condition){
		try {
			return xt_MenuinfoDao.getXtMenuinfoChildForRole(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
