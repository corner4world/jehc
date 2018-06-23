package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.xtmodules.xtservice.XtMenuinfoModuleService;
import jehc.xtmodules.xtdao.XtMenuinfoModuleDao;
import jehc.xtmodules.xtmodel.XtMenuinfoModule;

/**
* 菜单资源模块 
* 2018-06-21 09:22:18  邓纯杰
*/
@Service("xtMenuinfoModuleService")
public class XtMenuinfoModuleServiceImpl extends BaseService implements XtMenuinfoModuleService{
	@Autowired
	private XtMenuinfoModuleDao xtMenuinfoModuleDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtMenuinfoModule> getXtMenuinfoModuleListByCondition(Map<String,Object> condition){
		try{
			return xtMenuinfoModuleDao.getXtMenuinfoModuleListByCondition(condition);
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_menuinfo_module_id 
	* @return
	*/
	public XtMenuinfoModule getXtMenuinfoModuleById(String xt_menuinfo_module_id){
		try{
			XtMenuinfoModule xtMenuinfoModule = xtMenuinfoModuleDao.getXtMenuinfoModuleById(xt_menuinfo_module_id);
			return xtMenuinfoModule;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_menuinfo_module 
	* @return
	*/
	public int addXtMenuinfoModule(XtMenuinfoModule xtMenuinfoModule){
		int i = 0;
		try {
			i = xtMenuinfoModuleDao.addXtMenuinfoModule(xtMenuinfoModule);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_menuinfo_module 
	* @return
	*/
	public int updateXtMenuinfoModule(XtMenuinfoModule xtMenuinfoModule){
		int i = 0;
		try {
			i = xtMenuinfoModuleDao.updateXtMenuinfoModule(xtMenuinfoModule);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param xt_menuinfo_module 
	* @return
	*/
	public int updateXtMenuinfoModuleBySelective(XtMenuinfoModule xtMenuinfoModule){
		int i = 0;
		try {
			i = xtMenuinfoModuleDao.updateXtMenuinfoModuleBySelective(xtMenuinfoModule);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtMenuinfoModule(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtMenuinfoModuleDao.delXtMenuinfoModule(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param xt_menuinfo_moduleList 
	* @return
	*/
	public int addBatchXtMenuinfoModule(List<XtMenuinfoModule> xtMenuinfoModuleList){
		int i = 0;
		try {
			i = xtMenuinfoModuleDao.addBatchXtMenuinfoModule(xtMenuinfoModuleList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param xt_menuinfo_moduleList 
	* @return
	*/
	public int updateBatchXtMenuinfoModule(List<XtMenuinfoModule> xtMenuinfoModuleList){
		int i = 0;
		try {
			i = xtMenuinfoModuleDao.updateBatchXtMenuinfoModule(xtMenuinfoModuleList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param xt_menuinfo_moduleList 
	* @return
	*/
	public int updateBatchXtMenuinfoModuleBySelective(List<XtMenuinfoModule> xtMenuinfoModuleList){
		int i = 0;
		try {
			i = xtMenuinfoModuleDao.updateBatchXtMenuinfoModuleBySelective(xtMenuinfoModuleList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
