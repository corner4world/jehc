package jehc.xtmodules.xtservice.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.xtmodules.xtservice.XtDataAuthorityDefaultService;
import jehc.xtmodules.xtdao.XtDataAuthorityDao;
import jehc.xtmodules.xtdao.XtDataAuthorityDefaultDao;
import jehc.xtmodules.xtmodel.XtDataAuthorityDefault;

/**
* 数权限初始化默认设置 
* 2017-06-20 14:38:52  邓纯杰
*/
@Service("xtDataAuthorityDefaultService")
public class XtDataAuthorityDefaultServiceImpl extends BaseService implements XtDataAuthorityDefaultService{
	@Autowired
	private XtDataAuthorityDefaultDao xtDataAuthorityDefaultDao;
	@Autowired
	private XtDataAuthorityDao xtDataAuthorityDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtDataAuthorityDefault> getXtDataAuthorityDefaultListByCondition(Map<String,Object> condition){
		try{
			return xtDataAuthorityDefaultDao.getXtDataAuthorityDefaultListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_data_authority_default_id 
	* @return
	*/
	public XtDataAuthorityDefault getXtDataAuthorityDefaultById(String xt_data_authority_default_id){
		try{
			XtDataAuthorityDefault xt_Data_Authority_Default = xtDataAuthorityDefaultDao.getXtDataAuthorityDefaultById(xt_data_authority_default_id);
			return xt_Data_Authority_Default;
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_data_authority_default 
	* @return
	*/
	public int addXtDataAuthorityDefault(XtDataAuthorityDefault xt_Data_Authority_Default){
		int i = 0;
		try {
			i = xtDataAuthorityDefaultDao.addXtDataAuthorityDefault(xt_Data_Authority_Default);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_data_authority_default 
	* @return
	*/
	public int updateXtDataAuthorityDefault(XtDataAuthorityDefault xt_Data_Authority_Default){
		int i = 0;
		try {
			i = xtDataAuthorityDefaultDao.updateXtDataAuthorityDefault(xt_Data_Authority_Default);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param xt_data_authority_default 
	* @return
	*/
	public int updateXtDataAuthorityDefaultBySelective(XtDataAuthorityDefault xt_Data_Authority_Default){
		int i = 0;
		try {
			i = xtDataAuthorityDefaultDao.updateXtDataAuthorityDefaultBySelective(xt_Data_Authority_Default);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtDataAuthorityDefault(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtDataAuthorityDefaultDao.delXtDataAuthorityDefault(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	 * 根据情况删除
	 * @param condition
	 * @return
	 */
	public int delXtDataAuthorityDefaultAllByCondition(Map<String,Object> condition){
		int i = 0;
		try {
			xtDataAuthorityDao.delXtDataAuthorityByCondition(condition);
			xtDataAuthorityDefaultDao.delXtDataAuthorityDefaultAllByCondition(condition);
			i = 1;
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param xt_data_authority_defaultList 
	* @return
	*/
	public int addBatchXtDataAuthorityDefault(List<XtDataAuthorityDefault> xt_Data_Authority_DefaultList,String xt_menuinfo_id){
		int i = 0;
		try {
			//1删除 原先
			Map<String,Object> condition = new HashMap<String, Object>();
			condition.put("xt_menuinfo_id", xt_menuinfo_id);
			//2删除 执行表
			condition.put("xt_data_authorityType", "4");
			xtDataAuthorityDao.delXtDataAuthorityByCondition(condition);
			xtDataAuthorityDefaultDao.delXtDataAuthorityDefaultAllByCondition(condition);
			//3添加 最新
			if(null != xt_Data_Authority_DefaultList && xt_Data_Authority_DefaultList.size()>0){
				xtDataAuthorityDefaultDao.addBatchXtDataAuthorityDefault(xt_Data_Authority_DefaultList);
			}
			//4统一推送
			addPushDataAuthority();
			i = 1;
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param xt_data_authority_defaultList 
	* @return
	*/
	public int updateBatchXtDataAuthorityDefault(List<XtDataAuthorityDefault> xt_Data_Authority_DefaultList){
		int i = 0;
		try {
			i = xtDataAuthorityDefaultDao.updateBatchXtDataAuthorityDefault(xt_Data_Authority_DefaultList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param xt_data_authority_defaultList 
	* @return
	*/
	public int updateBatchXtDataAuthorityDefaultBySelective(List<XtDataAuthorityDefault> xt_Data_Authority_DefaultList){
		int i = 0;
		try {
			i = xtDataAuthorityDefaultDao.updateBatchXtDataAuthorityDefaultBySelective(xt_Data_Authority_DefaultList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
