package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtmodel.XtMenuinfoModule;

/**
* 菜单资源模块 
* 2018-06-21 09:22:18  邓纯杰
*/
public interface XtMenuinfoModuleService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtMenuinfoModule> getXtMenuinfoModuleListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_menuinfo_module_id 
	* @return
	*/
	public XtMenuinfoModule getXtMenuinfoModuleById(String xt_menuinfo_module_id);
	/**
	* 添加
	* @param xt_menuinfo_module 
	* @return
	*/
	public int addXtMenuinfoModule(XtMenuinfoModule xtMenuinfoModule);
	/**
	* 修改
	* @param xt_menuinfo_module 
	* @return
	*/
	public int updateXtMenuinfoModule(XtMenuinfoModule xtMenuinfoModule);
	/**
	* 修改（根据动态条件）
	* @param xt_menuinfo_module 
	* @return
	*/
	public int updateXtMenuinfoModuleBySelective(XtMenuinfoModule xtMenuinfoModule);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtMenuinfoModule(Map<String,Object> condition);
	/**
	* 批量添加
	* @param xt_menuinfo_moduleList 
	* @return
	*/
	public int addBatchXtMenuinfoModule(List<XtMenuinfoModule> xtMenuinfoModuleList);
	/**
	* 批量修改
	* @param xt_menuinfo_moduleList 
	* @return
	*/
	public int updateBatchXtMenuinfoModule(List<XtMenuinfoModule> xtMenuinfoModuleList);
	/**
	* 批量修改（根据动态条件）
	* @param xt_menuinfo_moduleList 
	* @return
	*/
	public int updateBatchXtMenuinfoModuleBySelective(List<XtMenuinfoModule> xtMenuinfoModuleList);
}
