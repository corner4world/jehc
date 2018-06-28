package jehc.crmmodules.crmservice.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.crmmodules.crmdao.CrmCustomerAttachDao;
import jehc.crmmodules.crmdao.CrmCustomerDao;
import jehc.crmmodules.crmdao.CrmInvoiceDao;
import jehc.crmmodules.crmmodel.CrmCustomer;
import jehc.crmmodules.crmmodel.CrmCustomerAttach;
import jehc.crmmodules.crmmodel.CrmInvoice;
import jehc.crmmodules.crmservice.CrmCustomerService;
import jehc.lcmodules.activitiutil.ActivitiUtil;
import jehc.lcmodules.lcmodel.LcApply;
import jehc.lcmodules.lcmodel.LcDeploymentHis;
import jehc.lcmodules.lcservice.LcDeploymentHisService;
import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtmodel.XtConstant;

/**
* 客户基础资料 
* 2018-06-27 13:45:48  邓纯杰
*/
@Service("crmCustomerService")
public class CrmCustomerServiceImpl extends BaseService implements CrmCustomerService{
	@Autowired
	private CrmCustomerDao crmCustomerDao;
	@Autowired
	private CrmInvoiceDao crmInvoiceDao;
	@Autowired
	private CrmCustomerAttachDao crmCustomerAttachDao;
	@Autowired
	ActivitiUtil activitiUtil;
	@Autowired
	private LcDeploymentHisService lcDeploymentHisService;
	
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CrmCustomer> getCrmCustomerListByCondition(Map<String,Object> condition){
		try{
			return crmCustomerDao.getCrmCustomerListByCondition(condition);
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param customerId 
	* @return
	*/
	public CrmCustomer getCrmCustomerById(String customerId){
		try{
			CrmCustomer crmCustomer = crmCustomerDao.getCrmCustomerById(customerId);
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("custom_id", customerId);
			List<CrmCustomerAttach> crmCustomerAttach = crmCustomerAttachDao.getCrmCustomerAttachListByCondition(condition);
			crmCustomer.setCrmCustomerAttach(crmCustomerAttach);
			return crmCustomer;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param crm_customer 
	* @return
	*/
	public int addCrmCustomer(CrmCustomer crmCustomer,CrmInvoice crmInvoice){
		int i = 0;
		try {
			i = crmCustomerDao.addCrmCustomer(crmCustomer);
			//保存发票
			crmInvoice.setCustomerId(crmCustomer.getCustomerId());
			crmInvoice.setInvoiceId(toUUID());
			crmInvoiceDao.addCrmInvoice(crmInvoice);
			
			//处理附件
			List<CrmCustomerAttach> crmCustomerAttachTempList = crmCustomer.getCrmCustomerAttach();
			List<CrmCustomerAttach> crmCustomerAttachList = new ArrayList<CrmCustomerAttach>();
			if(null != crmCustomerAttachTempList){
				for(int j = 0; j < crmCustomerAttachTempList.size(); j++){
					if(!StringUtils.isEmpty(crmCustomerAttachTempList.get(j).getXt_attachement_id())){
						crmCustomerAttachTempList.get(j).setCustom_id(crmCustomer.getCustomerId());
						crmCustomerAttachTempList.get(j).setCtime(getSimpleDateFormat());
						crmCustomerAttachTempList.get(j).setCustomer_attach_id(toUUID());
						crmCustomerAttachTempList.get(j).setXt_userinfo_id(getXtUid());
						crmCustomerAttachList.add(crmCustomerAttachTempList.get(j));
					}
				}
				if(!crmCustomerAttachList.isEmpty()&&crmCustomerAttachList.size()>0){
					crmCustomerAttachDao.addBatchCrmCustomerAttach(crmCustomerAttachList);
				}
			}
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param crm_customer 
	* @return
	*/
	public int updateCrmCustomer(CrmCustomer crmCustomer,CrmInvoice crmInvoice){
		int i = 0;
		try {
			i = crmCustomerDao.updateCrmCustomer(crmCustomer);
			
			crmInvoiceDao.updateCrmInvoice(crmInvoice);
			List<CrmCustomerAttach> crmCustomerAttachList = crmCustomer.getCrmCustomerAttach();
			List<CrmCustomerAttach> crmCustomerAttachAddList = new ArrayList<CrmCustomerAttach>();
			List<CrmCustomerAttach> crmCustomerAttachUpdateList = new ArrayList<CrmCustomerAttach>();
			if(null != crmCustomerAttachList){
				for(int j = 0; j < crmCustomerAttachList.size(); j++){
					if(crmCustomer.getCrmCustomerAttach_removed_flag().indexOf(","+j+",") == -1){
						crmCustomerAttachList.get(j).setCustom_id(crmCustomer.getCustomerId());
						if(StringUtil.isEmpty(crmCustomerAttachList.get(j).getCustomer_attach_id())){
							if(!StringUtil.isEmpty(crmCustomerAttachList.get(j).getXt_attachement_id())){
								crmCustomerAttachList.get(j).setCustomer_attach_id(toUUID());
								crmCustomerAttachList.get(j).setCtime(getSimpleDateFormat());
								crmCustomerAttachList.get(j).setXt_userinfo_id(getXtUid());
								crmCustomerAttachAddList.add(crmCustomerAttachList.get(j));
							}
						}else{
							crmCustomerAttachUpdateList.add(crmCustomerAttachList.get(j));
						}
					}
				}
			}
			if(!crmCustomerAttachAddList.isEmpty()&&crmCustomerAttachAddList.size()>0){
				crmCustomerAttachDao.addBatchCrmCustomerAttach(crmCustomerAttachAddList);
			}
			if(!crmCustomerAttachUpdateList.isEmpty()&&crmCustomerAttachUpdateList.size()>0){
				crmCustomerAttachDao.updateBatchCrmCustomerAttach(crmCustomerAttachUpdateList);
			}
			
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param crm_customer 
	* @return
	*/
	public int updateCrmCustomerBySelective(CrmCustomer crmCustomer){
		int i = 0;
		try {
			i = crmCustomerDao.updateCrmCustomerBySelective(crmCustomer);
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
	public int delCrmCustomer(Map<String,Object> condition){
		int i = 0;
		try {
			i = crmCustomerDao.delCrmCustomer(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param crm_customerList 
	* @return
	*/
	public int addBatchCrmCustomer(List<CrmCustomer> crmCustomerList){
		int i = 0;
		try {
			i = crmCustomerDao.addBatchCrmCustomer(crmCustomerList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param crm_customerList 
	* @return
	*/
	public int updateBatchCrmCustomer(List<CrmCustomer> crmCustomerList){
		int i = 0;
		try {
			i = crmCustomerDao.updateBatchCrmCustomer(crmCustomerList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param crm_customerList 
	* @return
	*/
	public int updateBatchCrmCustomerBySelective(List<CrmCustomer> crmCustomerList){
		int i = 0;
		try {
			i = crmCustomerDao.updateBatchCrmCustomerBySelective(crmCustomerList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	/**
	 * 批量分配客户至客户所属人
	 * @param crmCustomer
	 * @return
	 */
	public int updateCToUser(Map<String, Object> map){
		int i = 0;
		try {
			i = crmCustomerDao.updateCToUser(map);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	/**
	 * 客户等级申请
	 * @param crmCustomer
	 * @param lcApply
	 * @return
	 */
	public int addCrmLevelApply(CrmCustomer crmCustomer,LcApply lcApply){
		int i = 0;
		try {
			if(StringUtil.isEmpty(crmCustomer.getCustomerId())){
				throw new ExceptionUtil("未能获取到客户编号！");
			}
			//1.操作客户等级表字段
			crmCustomer = crmCustomerDao.getCrmCustomerById(crmCustomer.getCustomerId());
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("levelStatus", "1");
			condition.put("crmCustomerId", crmCustomer.getCustomerId());
			i = crmCustomerDao.updateCrmLevelStatus(condition);
			
			//2.操作工作流（发起流程实例）
			XtConstant xtConstant = getXtConstantCache("CRM_LEVEL_APPLY");
			if(null == xtConstant){
				throw new ExceptionUtil("未能获取到工作流常量！");
			}
			condition = new HashMap<String, Object>();
			condition.put("xt_constant_id", xtConstant.getXt_constant_id());
			LcDeploymentHis lcDeploymentHis = lcDeploymentHisService.getLcDeploymentHisNewUnique(condition);
			if(null == lcDeploymentHis){
				throw new ExceptionUtil("未能获取到流程部署！");
			}
			lcApply.setLc_apply_title("用户："+getXtUname()+"发起了一条客户等级申请，客户是："+crmCustomer.getName()+"。");
			activitiUtil.addApplySetAssignee(lcDeploymentHis.getId(), crmCustomer.getCustomerId(), null, lcApply);
		} catch (Exception e) {
			i = 0;
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
