package jehc.xtmodules.xtdao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_MenuinfoDao;
import jehc.xtmodules.xtmodel.Xt_Menuinfo;
/**
 * 系统菜单表
 * 
 * @author邓纯杰
 * 
 */
@Repository("xt_MenuinfoDao")
public class Xt_MenuinfoDaoImpl extends BaseDaoImpl implements Xt_MenuinfoDao {
	/**
	 * 查询所有集合列表
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Menuinfo> getXtMenuinfoListByCondition(Map<String, Object> condition){
		return (List<Xt_Menuinfo>)this.getList("getXtMenuinfoListByCondition", condition);
	}
	
	/**
	 * 查找所有根菜单
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Menuinfo> getXtMenuinfoList(){
		return (List<Xt_Menuinfo>)this.getList("getXtMenuinfoList", null);
	}
	
	/**
	 * 查找非父菜单集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Menuinfo> getXtMenuinfoListAllChild(){
		return (List<Xt_Menuinfo>)this.getList("getXtMenuinfoListAllChild", null);
	}
	
	/**
	 * 查找子菜单
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Menuinfo> getXtMenuinfoListChild(Map<String, Object> condition){
		return (List<Xt_Menuinfo>)this.getList("getXtMenuinfoListChild", condition);
	}
	
	/**
	 * 查找对象根据主键
	 * @param xt_menuinfo_id
	 * @return
	 */
	public Xt_Menuinfo getXtMenuinfo(String xt_menuinfo_id){
		return (Xt_Menuinfo)this.get("getXtMenuinfo", xt_menuinfo_id);
	}
	
	/**
	 * 添加菜单
	 * @param xt_Menuinfo
	 */
	public int addXtMenuinfo(Xt_Menuinfo xt_Menuinfo){
		return this.add("addXtMenuinfo", xt_Menuinfo);
	}
	
	/**
	 * 修改菜单
	 * @param xt_Menuinfo
	 */
	public int updateXtMenuinfo(Xt_Menuinfo xt_Menuinfo){
		return this.update("updateXtMenuinfo", xt_Menuinfo);
	}
	
	/**
	 * 查询所有菜单
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Menuinfo> getXtMenuinfoListAll(Map<String, Object> condition){
		return (List<Xt_Menuinfo>)this.getList("getXtMenuinfoListAll", condition);
	}
	
	/**
	 * 根据xt_menuinfo_parentId查找集合
	 * @param xt_menuinfo_parentId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Menuinfo> getXtMenuinfoByParentID(String xt_menuinfo_parentId){
		return (List<Xt_Menuinfo>)this.getList("getXtMenuinfoByParentID", xt_menuinfo_parentId);
	}
	
	/**
	 * 删除菜单
	 * @param condition
	 */
	public int delXtMenuinfo(Map<String, Object> condition){
		return this.del("delXtMenuinfo", condition);
	}
	
	/**
	 * 排序
	 * @param condition
	 */
	public int updateXtMenuinfoSort(Map<String, Object> condition){
		return this.update("updateXtMenuinfoSort", condition);
	}
	
	/**
	 * 查找根目录为了权限使用
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Menuinfo> getXtMenuinfoRootForRole(Map<String, Object> condition){
		return (List<Xt_Menuinfo>)this.getList("getXtMenuinfoRootForRole", condition);
	}
	
	/**
	 * 查找孩子为了权限使用
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Menuinfo> getXtMenuinfoChildForRole(Map<String, Object> condition){
		return (List<Xt_Menuinfo>)this.getList("getXtMenuinfoChildForRole", condition);
	}
}
