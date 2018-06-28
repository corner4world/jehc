package jehc.crmmodules.crmdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.crmmodules.crmdao.CrmFollowupDao;
import jehc.crmmodules.crmmodel.CrmFollowup;

/**
* 客户跟进日志 
* 2018-06-27 16:55:12  邓纯杰
*/
@Repository("crmFollowupDao")
public class CrmFollowupDaoImpl  extends BaseDaoImpl implements CrmFollowupDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<CrmFollowup> getCrmFollowupListByCondition(Map<String,Object> condition){
		return (List<CrmFollowup>)this.getList("getCrmFollowupListByCondition",condition);
	}
	/**
	* 查询对象
	* @param followupId 
	* @return
	*/
	public CrmFollowup getCrmFollowupById(String followupId){
		return (CrmFollowup)this.get("getCrmFollowupById", followupId);
	}
	/**
	* 添加
	* @param crm_followup 
	* @return
	*/
	public int addCrmFollowup(CrmFollowup crmFollowup){
		return this.add("addCrmFollowup", crmFollowup);
	}
	/**
	* 修改
	* @param crm_followup 
	* @return
	*/
	public int updateCrmFollowup(CrmFollowup crmFollowup){
		return this.update("updateCrmFollowup", crmFollowup);
	}
	/**
	* 修改（根据动态条件）
	* @param crm_followup 
	* @return
	*/
	public int updateCrmFollowupBySelective(CrmFollowup crmFollowup){
		return this.update("updateCrmFollowupBySelective", crmFollowup);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCrmFollowup(Map<String,Object> condition){
		return this.del("delCrmFollowup", condition);
	}
	/**
	* 批量添加
	* @param crm_followupList 
	* @return
	*/
	public int addBatchCrmFollowup(List<CrmFollowup> crmFollowupList){
		return this.add("addBatchCrmFollowup", crmFollowupList);
	}
	/**
	* 批量修改
	* @param crm_followupList 
	* @return
	*/
	public int updateBatchCrmFollowup(List<CrmFollowup> crmFollowupList){
		return this.update("updateBatchCrmFollowup", crmFollowupList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param crm_followupList 
	* @return
	*/
	public int updateBatchCrmFollowupBySelective(List<CrmFollowup> crmFollowupList){
		return this.update("updateBatchCrmFollowupBySelective", crmFollowupList);
	}
}
