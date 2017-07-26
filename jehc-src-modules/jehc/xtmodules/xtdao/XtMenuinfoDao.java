package jehc.xtmodules.xtdao;

import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtMenuinfo;

/**
 * 系统菜单表
 * 
 * @author邓纯杰
 * 
 */
public interface XtMenuinfoDao {
	/**
	 * 查询所有集合列表
	 * @param condition
	 * @return
	 */
	public List<XtMenuinfo> getXtMenuinfoListByCondition(Map<String, Object> condition);
	
	/**
	 * 查找所有根菜单
	 * @return
	 */
	public List<XtMenuinfo> getXtMenuinfoList();
	
	/**
	 * 查找非父菜单集合
	 * @return
	 */
	public List<XtMenuinfo> getXtMenuinfoListAllChild();
	
	/**
	 * 查找子菜单
	 * @param condition
	 * @return
	 */
	public List<XtMenuinfo> getXtMenuinfoListChild(Map<String, Object> condition);
	
	/**
	 * 查找对象根据主键
	 * @param xt_menuinfo_id
	 * @return
	 */
	public XtMenuinfo getXtMenuinfo(String xt_menuinfo_id);
	
	/**
	 * 添加菜单
	 * @param xt_Menuinfo
	 */
	public int addXtMenuinfo(XtMenuinfo xt_Menuinfo);
	
	/**
	 * 修改菜单
	 * @param xt_Menuinfo
	 */
	public int updateXtMenuinfo(XtMenuinfo xt_Menuinfo);
	
	/**
	 * 查询所有菜单
	 * @param condition
	 * @return
	 */
	public List<XtMenuinfo> getXtMenuinfoListAll(Map<String, Object> condition);
	
	/**
	 * 根据xt_menuinfo_parentId查找集合
	 * @param xt_menuinfo_parentId
	 * @return
	 */
	public List<XtMenuinfo> getXtMenuinfoByParentID(String xt_menuinfo_parentId);
	
	/**
	 * 删除菜单
	 * @param condition
	 */
	public int delXtMenuinfo(Map<String, Object> condition);
	
	/**
	 * 排序
	 * @param condition
	 */
	public int updateXtMenuinfoSort(Map<String, Object> condition);
	
	/**
	 * 查找根目录为了权限使用
	 * @param condition
	 * @return
	 */
	public List<XtMenuinfo> getXtMenuinfoRootForRole(Map<String, Object> condition);
	
	/**
	 * 查找孩子为了权限使用
	 * @param condition
	 * @return
	 */
	public List<XtMenuinfo> getXtMenuinfoChildForRole(Map<String, Object> condition);
}
