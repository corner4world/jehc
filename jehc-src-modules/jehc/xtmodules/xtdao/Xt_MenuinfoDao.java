package jehc.xtmodules.xtdao;

import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Menuinfo;

/**
 * 系统菜单表
 * 
 * @author邓纯杰
 * 
 */
public interface Xt_MenuinfoDao {
	/**
	 * 查询所有集合列表
	 * @param condition
	 * @return
	 */
	public List<Xt_Menuinfo> getXtMenuinfoListByCondition(Map<String, Object> condition);
	
	/**
	 * 查找所有根菜单
	 * @return
	 */
	public List<Xt_Menuinfo> getXtMenuinfoList();
	
	/**
	 * 查找非父菜单集合
	 * @return
	 */
	public List<Xt_Menuinfo> getXtMenuinfoListAllChild();
	
	/**
	 * 查找子菜单
	 * @param condition
	 * @return
	 */
	public List<Xt_Menuinfo> getXtMenuinfoListChild(Map<String, Object> condition);
	
	/**
	 * 查找对象根据主键
	 * @param xt_menuinfo_id
	 * @return
	 */
	public Xt_Menuinfo getXtMenuinfo(String xt_menuinfo_id);
	
	/**
	 * 添加菜单
	 * @param xt_Menuinfo
	 */
	public int addXtMenuinfo(Xt_Menuinfo xt_Menuinfo);
	
	/**
	 * 修改菜单
	 * @param xt_Menuinfo
	 */
	public int updateXtMenuinfo(Xt_Menuinfo xt_Menuinfo);
	
	/**
	 * 查询所有菜单
	 * @param condition
	 * @return
	 */
	public List<Xt_Menuinfo> getXtMenuinfoListAll(Map<String, Object> condition);
	
	/**
	 * 根据xt_menuinfo_parentId查找集合
	 * @param xt_menuinfo_parentId
	 * @return
	 */
	public List<Xt_Menuinfo> getXtMenuinfoByParentID(String xt_menuinfo_parentId);
	
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
	public List<Xt_Menuinfo> getXtMenuinfoRootForRole(Map<String, Object> condition);
	
	/**
	 * 查找孩子为了权限使用
	 * @param condition
	 * @return
	 */
	public List<Xt_Menuinfo> getXtMenuinfoChildForRole(Map<String, Object> condition);
}
