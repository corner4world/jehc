package jehc.xtmodules.xtdao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtMenuinfoDao;
import jehc.xtmodules.xtmodel.XtMenuinfo;
/**
 * 系统菜单表
 * 
 * @author邓纯杰
 * 
 */
@Repository("xtMenuinfoDao")
public class XtMenuinfoDaoImpl extends BaseDaoImpl implements XtMenuinfoDao {
	/**
	 * 查询所有集合列表
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtMenuinfo> getXtMenuinfoListByCondition(Map<String, Object> condition){
		return (List<XtMenuinfo>)this.getList("getXtMenuinfoListByCondition", condition);
	}
	
	/**
	 * 查找所有根菜单
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtMenuinfo> getXtMenuinfoList(){
		return (List<XtMenuinfo>)this.getList("getXtMenuinfoList", null);
	}
	
	/**
	 * 查找非父菜单集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtMenuinfo> getXtMenuinfoListAllChild(){
		return (List<XtMenuinfo>)this.getList("getXtMenuinfoListAllChild", null);
	}
	
	/**
	 * 查找子菜单
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtMenuinfo> getXtMenuinfoListChild(Map<String, Object> condition){
		return (List<XtMenuinfo>)this.getList("getXtMenuinfoListChild", condition);
	}
	
	/**
	 * 查找对象根据主键
	 * @param xt_menuinfo_id
	 * @return
	 */
	public XtMenuinfo getXtMenuinfo(String xt_menuinfo_id){
		return (XtMenuinfo)this.get("getXtMenuinfo", xt_menuinfo_id);
	}
	
	/**
	 * 添加菜单
	 * @param xt_Menuinfo
	 */
	public int addXtMenuinfo(XtMenuinfo xt_Menuinfo){
		return this.add("addXtMenuinfo", xt_Menuinfo);
	}
	
	/**
	 * 修改菜单
	 * @param xt_Menuinfo
	 */
	public int updateXtMenuinfo(XtMenuinfo xt_Menuinfo){
		return this.update("updateXtMenuinfo", xt_Menuinfo);
	}
	
	/**
	 * 查询所有菜单
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtMenuinfo> getXtMenuinfoListAll(Map<String, Object> condition){
		return (List<XtMenuinfo>)this.getList("getXtMenuinfoListAll", condition);
	}
	
	/**
	 * 根据xt_menuinfo_parentId查找集合
	 * @param xt_menuinfo_parentId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtMenuinfo> getXtMenuinfoByParentID(String xt_menuinfo_parentId){
		return (List<XtMenuinfo>)this.getList("getXtMenuinfoByParentID", xt_menuinfo_parentId);
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
	public List<XtMenuinfo> getXtMenuinfoRootForRole(Map<String, Object> condition){
		return (List<XtMenuinfo>)this.getList("getXtMenuinfoRootForRole", condition);
	}
	
	/**
	 * 查找孩子为了权限使用
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtMenuinfo> getXtMenuinfoChildForRole(Map<String, Object> condition){
		return (List<XtMenuinfo>)this.getList("getXtMenuinfoChildForRole", condition);
	}
}
