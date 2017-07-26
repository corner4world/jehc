package jehc.xtmodules.xtservice.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.lcmodules.activitiutil.ActivitiUtil;
import jehc.lcmodules.lcmodel.LcApply;
import jehc.lcmodules.lcservice.LcDeploymentHisService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtChangePwdDao;
import jehc.xtmodules.xtmodel.XtChangePwd;
import jehc.xtmodules.xtmodel.XtConstant;
import jehc.xtmodules.xtservice.XtChangePwdService;

/**
* 密码找回中心 
* 2016-10-21 16:41:55  邓纯杰
*/
@Service("xtChangePwdService")
public class XtChangePwdServiceImpl extends BaseService implements XtChangePwdService{
	@Autowired
	private XtChangePwdDao xtChangePwdDao;
	
	@Autowired
	ActivitiUtil activitiUtil;
	@Autowired
	private LcDeploymentHisService lc_Deployment_HisService;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtChangePwd> getXtChangePwdListByCondition(Map<String,Object> condition){
		try{
			return xtChangePwdDao.getXtChangePwdListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_change_pwd_id 
	* @return
	*/
	public XtChangePwd getXtChangePwdById(String xt_change_pwd_id){
		try{
			return xtChangePwdDao.getXtChangePwdById(xt_change_pwd_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_change_pwd 
	* @return
	*/
	public int addXtChangePwd(XtChangePwd xt_Change_Pwd){
		int i = 0;
		try {
			//处理工作流
			XtConstant Xt_Constant = getXtConstantCache("XtChangePwd");
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_constant_id", Xt_Constant.getXt_constant_id());
			String id = lc_Deployment_HisService.getLcDeploymentHisNewUnique(condition).getId();
			LcApply lc_Apply = new LcApply();
			lc_Apply.setLc_apply_title(xt_Change_Pwd.getUser_name()+"于"+getSimpleDateFormat()+"，提交了一条密码找回申请流程");
			lc_Apply.setLc_apply_model_biz_id(xt_Change_Pwd.getXt_change_pwd_id());
			if(activitiUtil.addApply(id, null, null,lc_Apply)){
				//处理业务
				i = xtChangePwdDao.addXtChangePwd(xt_Change_Pwd);
			}else{
				i = 0;
			}
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_change_pwd 
	* @return
	*/
	public int updateXtChangePwd(XtChangePwd xt_Change_Pwd){
		int i = 0;
		try {
			i = xtChangePwdDao.updateXtChangePwd(xt_Change_Pwd);
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
	public int delXtChangePwd(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtChangePwdDao.delXtChangePwd(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
