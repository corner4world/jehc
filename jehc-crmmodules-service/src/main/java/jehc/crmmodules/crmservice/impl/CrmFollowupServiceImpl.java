package jehc.crmmodules.crmservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.crmmodules.crmservice.CrmFollowupService;
import jehc.crmmodules.crmdao.CrmFollowupDao;
import jehc.crmmodules.crmmodel.CrmFollowup;

/**
* 客户跟进日志 
* 2018-06-27 16:55:12  邓纯杰
*/
@Service("crmFollowupService")
public class CrmFollowupServiceImpl extends BaseService implements CrmFollowupService{
	@Autowired
	private CrmFollowupDao crmFollowupDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CrmFollowup> getCrmFollowupListByCondition(Map<String,Object> condition){
		try{
			return crmFollowupDao.getCrmFollowupListByCondition(condition);
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param followupId 
	* @return
	*/
	public CrmFollowup getCrmFollowupById(String followupId){
		try{
			CrmFollowup crmFollowup = crmFollowupDao.getCrmFollowupById(followupId);
			return crmFollowup;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param crm_followup 
	* @return
	*/
	public int addCrmFollowup(CrmFollowup crmFollowup){
		int i = 0;
		try {
			i = crmFollowupDao.addCrmFollowup(crmFollowup);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param crm_followup 
	* @return
	*/
	public int updateCrmFollowup(CrmFollowup crmFollowup){
		int i = 0;
		try {
			i = crmFollowupDao.updateCrmFollowup(crmFollowup);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param crm_followup 
	* @return
	*/
	public int updateCrmFollowupBySelective(CrmFollowup crmFollowup){
		int i = 0;
		try {
			i = crmFollowupDao.updateCrmFollowupBySelective(crmFollowup);
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
	public int delCrmFollowup(Map<String,Object> condition){
		int i = 0;
		try {
			i = crmFollowupDao.delCrmFollowup(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param crm_followupList 
	* @return
	*/
	public int addBatchCrmFollowup(List<CrmFollowup> crmFollowupList){
		int i = 0;
		try {
			i = crmFollowupDao.addBatchCrmFollowup(crmFollowupList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param crm_followupList 
	* @return
	*/
	public int updateBatchCrmFollowup(List<CrmFollowup> crmFollowupList){
		int i = 0;
		try {
			i = crmFollowupDao.updateBatchCrmFollowup(crmFollowupList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param crm_followupList 
	* @return
	*/
	public int updateBatchCrmFollowupBySelective(List<CrmFollowup> crmFollowupList){
		int i = 0;
		try {
			i = crmFollowupDao.updateBatchCrmFollowupBySelective(crmFollowupList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
