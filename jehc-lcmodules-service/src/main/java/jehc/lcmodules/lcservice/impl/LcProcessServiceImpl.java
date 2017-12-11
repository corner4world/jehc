package jehc.lcmodules.lcservice.impl;
import java.io.File;
import java.util.List;
import java.util.Map;

import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.lcmodules.activitiutil.ActivitiUtil;
import jehc.lcmodules.lcdao.LcDeploymentHisDao;
import jehc.lcmodules.lcdao.LcProcessDao;
import jehc.lcmodules.lcmodel.LcDeploymentHis;
import jehc.lcmodules.lcmodel.LcProcess;
import jehc.lcmodules.lcservice.LcProcessService;
import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.allutils.file.FileUtil;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtmodel.XtAttachment;
import jehc.xtmodules.xtservice.XtAttachmentService;

/**
* 流程详细信息即流程部署内容,BPMN文件,ZIP路径,IMG路径,MXGraph内容等等（流程表） 
* 2016-11-22 10:16:39  邓纯杰
*/
@Service("lcProcessService")
public class LcProcessServiceImpl extends BaseService implements LcProcessService{
	@Autowired
	private LcProcessDao lcProcessDao;
	@Autowired
	private LcDeploymentHisDao lcDeploymentHisDao;
	@Autowired
	ActivitiUtil activitiUtil;
	@Autowired
	private XtAttachmentService xtAttachmentService;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<LcProcess> getLcProcessListByCondition(Map<String,Object> condition){
		try{
			return lcProcessDao.getLcProcessListByCondition(condition);
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
	public LcProcess getLcProcessById(String lc_process_id){
		try{
			return lcProcessDao.getLcProcessById(lc_process_id);
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
	public int addLcProcess(LcProcess lc_Process){
		int i = 0;
		try {
			i = lcProcessDao.addLcProcess(lc_Process);
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
	public int updateLcProcess(LcProcess lc_Process){
		int i = 0;
		try {
			i = lcProcessDao.updateLcProcess(lc_Process);
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
			i = lcProcessDao.delLcProcess(condition);
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
	public int addBatchLcProcess(List<LcProcess> lc_ProcessList){
		int i = 0;
		try {
			i = lcProcessDao.addBatchLcProcess(lc_ProcessList);
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
	public int updateBatchLcProcess(List<LcProcess> lc_ProcessList){
		int i = 0;
		try {
			i = lcProcessDao.updateBatchLcProcess(lc_ProcessList);
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
			LcProcess lc_Process = lcProcessDao.getLcProcessById(lc_process_id);
			if(null== lc_Process){
				throw new ExceptionUtil("该流程为空，无法发布");
			}
			if(1==lc_Process.getLc_process_status()){
				throw new ExceptionUtil("该流程已启动中，无法发布");
			}
			if(1==lc_Process.getLc_process_flag()){
				XtAttachment attachment = xtAttachmentService.getXtAttachmentById(lc_Process.getXt_attachment());
				attachPath = FileUtil.validOrCreateFile(getXtPathCache("ActivitiLc").get(0).getXt_path()+attachment.getXt_attachmentName());
			}else{
				attachPath = FileUtil.validOrCreateFile(getXtPathCache("ActivitiLc").get(0).getXt_path()+lc_Process.getLc_process_title()+"/"+lc_Process.getLc_process_path());
			}
			File file = new File(attachPath);
			if(!file.exists()){
				throw new ExceptionUtil("流程文件不存在，部署失败");
			}else{
				Deployment deployment = activitiUtil.createDeployment(file);
				if(null != deployment){
					LcDeploymentHis lc_Deployment_His = new LcDeploymentHis();
					lc_Deployment_His.setId(UUID.toUUID());
					lc_Deployment_His.setLc_deployment_his_id(deployment.getId());
					lc_Deployment_His.setLc_deployment_his_name(lc_Process.getLc_process_title()+"_"+deployment.getId());
					lc_Deployment_His.setLc_deployment_his_tenantId(deployment.getTenantId());
					lc_Deployment_His.setLc_deployment_his_time(getDate());
					lc_Deployment_His.setLc_process_id(lc_process_id);
					lc_Deployment_His.setLc_deployment_his_status(0);
					lcDeploymentHisDao.addLcDeploymentHis(lc_Deployment_His);
				}else{
					throw new ExceptionUtil("返回流程部署信息为空");
				}
			}
			i = lcProcessDao.updateLcProcessStatus(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
