package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtMenuinfoModuleDao;
import jehc.xtmodules.xtmodel.XtMenuinfoModule;

/**
* 菜单资源模块 
* 2018-06-21 09:22:18  邓纯杰
*/
@Repository("xtMenuinfoModuleDao")
public class XtMenuinfoModuleDaoImpl  extends BaseDaoImpl implements XtMenuinfoModuleDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtMenuinfoModule> getXtMenuinfoModuleListByCondition(Map<String,Object> condition){
		return (List<XtMenuinfoModule>)this.getList("getXtMenuinfoModuleListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_menuinfo_module_id 
	* @return
	*/
	public XtMenuinfoModule getXtMenuinfoModuleById(String xt_menuinfo_module_id){
		return (XtMenuinfoModule)this.get("getXtMenuinfoModuleById", xt_menuinfo_module_id);
	}
	/**
	* 添加
	* @param xt_menuinfo_module 
	* @return
	*/
	public int addXtMenuinfoModule(XtMenuinfoModule xtMenuinfoModule){
		return this.add("addXtMenuinfoModule", xtMenuinfoModule);
	}
	/**
	* 修改
	* @param xt_menuinfo_module 
	* @return
	*/
	public int updateXtMenuinfoModule(XtMenuinfoModule xtMenuinfoModule){
		return this.update("updateXtMenuinfoModule", xtMenuinfoModule);
	}
	/**
	* 修改（根据动态条件）
	* @param xt_menuinfo_module 
	* @return
	*/
	public int updateXtMenuinfoModuleBySelective(XtMenuinfoModule xtMenuinfoModule){
		return this.update("updateXtMenuinfoModuleBySelective", xtMenuinfoModule);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtMenuinfoModule(Map<String,Object> condition){
		return this.del("delXtMenuinfoModule", condition);
	}
	/**
	* 批量添加
	* @param xt_menuinfo_moduleList 
	* @return
	*/
	public int addBatchXtMenuinfoModule(List<XtMenuinfoModule> xtMenuinfoModuleList){
		return this.add("addBatchXtMenuinfoModule", xtMenuinfoModuleList);
	}
	/**
	* 批量修改
	* @param xt_menuinfo_moduleList 
	* @return
	*/
	public int updateBatchXtMenuinfoModule(List<XtMenuinfoModule> xtMenuinfoModuleList){
		return this.update("updateBatchXtMenuinfoModule", xtMenuinfoModuleList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param xt_menuinfo_moduleList 
	* @return
	*/
	public int updateBatchXtMenuinfoModuleBySelective(List<XtMenuinfoModule> xtMenuinfoModuleList){
		return this.update("updateBatchXtMenuinfoModuleBySelective", xtMenuinfoModuleList);
	}
}
