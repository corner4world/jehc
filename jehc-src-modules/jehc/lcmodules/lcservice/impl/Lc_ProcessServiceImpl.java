package jehc.lcmodules.lcservice.impl;
import java.io.File;
import java.util.List;
import java.util.Map;

import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.lcmodules.activitiutil.ActivitiUtil;
import jehc.lcmodules.lcdao.Lc_Deployment_HisDao;
import jehc.lcmodules.lcdao.Lc_ProcessDao;
import jehc.lcmodules.lcmodel.Lc_Deployment_His;
import jehc.lcmodules.lcmodel.Lc_Process;
import jehc.lcmodules.lcservice.Lc_ProcessService;
import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.allutils.file.FileUtil;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtservice.Xt_AttachmentService;

/**
* 流程详细信息即流程部署内容,BPMN文件,ZIP路径,IMG路径,MXGraph内容等等（流程表） 
* 2016-11-22 10:16:39  邓纯杰
*/
@Service("lc_ProcessService")
public class Lc_ProcessServiceImpl extends BaseService implements Lc_ProcessService{
	@Autowired
	private Lc_ProcessDao lc_ProcessDao;
	@Autowired
	private Lc_Deployment_HisDao lc_Deployment_HisDao;
	@Autowired
	ActivitiUtil activitiUtil;
	@Autowired
	private Xt_AttachmentService xt_AttachmentService;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Lc_Process> getLcProcessListByCondition(Map<String,Object> condition){
		try{
			return lc_ProcessDao.getLcProcessListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param lc_process_id 
	* @return
	*/
	public Lc_Process getLcProcessById(String lc_process_id){
		try{
			return lc_ProcessDao.getLcProcessById(lc_process_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param lc_process 
	* @return
	*/
	public int addLcProcess(Lc_Process lc_Process){
		int i = 0;
		try {
			i = lc_ProcessDao.addLcProcess(lc_Process);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param lc_process 
	* @return
	*/
	public int updateLcProcess(Lc_Process lc_Process){
		int i = 0;
		try {
			i = lc_ProcessDao.updateLcProcess(lc_Process);
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
	public int delLcProcess(Map<String,Object> condition){
		int i = 0;
		try {
			i = lc_ProcessDao.delLcProcess(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param lc_processList 
	* @return
	*/
	public int addBatchLcProcess(List<Lc_Process> lc_ProcessList){
		int i = 0;
		try {
			i = lc_ProcessDao.addBatchLcProcess(lc_ProcessList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param lc_processList 
	* @return
	*/
	public int updateBatchLcProcess(List<Lc_Process> lc_ProcessList){
		int i = 0;
		try {
			i = lc_ProcessDao.updateBatchLcProcess(lc_ProcessList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	 * 发布或关闭流程
	 * @param condition
	 * @return
	 */
	public int updateLcProcessStatus(String lc_process_id,Map<String,Object> condition){
		int i = 0;
		try {
			String attachPath;
			if(StringUtil.isEmpty(lc_process_id)){
				throw new ExceptionUtil("流程编号为空");
			}
			Lc_Process lc_Process = lc_ProcessDao.getLcProcessById(lc_process_id);
			if(null== lc_Process){
				throw new ExceptionUtil("该流程为空，无法发布");
			}
			if(lc_Process.getLc_process_status().equals("1")){
				throw new ExceptionUtil("该流程已启动中，无法发布");
			}
			if(lc_Process.getLc_process_flag().equals("1")){
				attachPath = FileUtil.validOrCreateFile(getXtPathCache("ActivitiLc").get(0).getXt_path());
			}else{
				attachPath = FileUtil.validOrCreateFile(getXtPathCache("ActivitiLc").get(0).getXt_path()+lc_Process.getLc_process_title()+"/");
			}
			File file = new File(attachPath+lc_Process.getLc_process_path());
			if(!file.exists()){
				throw new ExceptionUtil("流程文件不存在，部署失败");
			}else{
				Deployment deployment = activitiUtil.createDeployment(file);
				if(null != deployment){
					Lc_Deployment_His lc_Deployment_His = new Lc_Deployment_His();
					lc_Deployment_His.setId(UUID.toUUID());
					lc_Deployment_His.setLc_deployment_his_id(deployment.getId());
					lc_Deployment_His.setLc_deployment_his_name(lc_Process.getLc_process_title()+"_"+deployment.getId());
					lc_Deployment_His.setLc_deployment_his_tenantId(deployment.getTenantId());
					lc_Deployment_His.setLc_deployment_his_time(getSimpleDateFormat());
					lc_Deployment_His.setLc_process_id(lc_process_id);
					lc_Deployment_His.setLc_deployment_his_status("0");
					lc_Deployment_HisDao.addLcDeploymentHis(lc_Deployment_His);
				}else{
					throw new ExceptionUtil("返回流程部署信息为空");
				}
			}
			i = lc_ProcessDao.updateLcProcessStatus(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
