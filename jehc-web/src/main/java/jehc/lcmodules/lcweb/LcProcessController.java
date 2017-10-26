package jehc.lcmodules.lcweb;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import jehc.lcmodules.activitiutil.ActivitiUtil;
import jehc.lcmodules.lcmodel.LcApply;
import jehc.lcmodules.lcmodel.LcDeploymentHis;
import jehc.lcmodules.lcmodel.LcProcess;
import jehc.lcmodules.lcservice.LcApplyService;
import jehc.lcmodules.lcservice.LcDeploymentHisService;
import jehc.lcmodules.lcservice.LcProcessService;
import jehc.lcmodules.mxgraph.MxGraphModel;
import jehc.lcmodules.mxgraph.MxGraphToBPMN;
import jehc.lcmodules.mxgraph.MxGraphToPng;
import jehc.xtmodules.xtcore.allutils.file.FileUtil;
import jehc.xtmodules.xtcore.annotation.AuthUneedLogin;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import net.sf.json.JSONArray;

/**
* 流程详细信息即流程部署内容,BPMN文件,ZIP路径,IMG路径,MXGraph内容等等（流程表） 
* 2016-11-22 10:16:39  邓纯杰
*/
@Controller
@RequestMapping("/lcProcessController")
public class LcProcessController  extends BaseAction{
	@Autowired
	private LcProcessService lcProcessService;
	@Autowired
	ActivitiUtil activitiUtil;
	@Autowired
	private LcApplyService lcApplyService;
	@Autowired
	private LcDeploymentHisService lcDeploymentHisService;
	/**
	* 载入初始化页面
	* @param lc_process 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadLcProcess",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadLcProcess(LcProcess lc_Process,HttpServletRequest request){
		return new ModelAndView("pc/lc-view/lc-process/lc-process-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param lc_process 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getLcProcessListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getLcProcessListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<LcProcess> lc_ProcessList = lcProcessService.getLcProcessListByCondition(condition);
		PageInfo<LcProcess> page = new PageInfo<LcProcess>(lc_ProcessList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param lc_process_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getLcProcessById",method={RequestMethod.POST,RequestMethod.GET})
	public String getLcProcessById(String lc_process_id,HttpServletRequest request){
		LcProcess lc_Process = lcProcessService.getLcProcessById(lc_process_id);
		return outDataStr(lc_Process);
	}
	/**
	* 添加
	* @param lc_process 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addLcProcess",method={RequestMethod.POST,RequestMethod.GET})
	public String addLcProcess(LcProcess lc_Process,HttpServletRequest request){
		int i = 0;
		if(null != lc_Process && !"".equals(lc_Process)){
			lc_Process.setLc_process_id(UUID.toUUID());
			lc_Process.setLc_process_ctime(getSimpleDateFormat());
			lc_Process.setXt_userinfo_id(getXtUid());
			i=lcProcessService.addLcProcess(lc_Process);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param lc_process 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateLcProcess",method={RequestMethod.POST,RequestMethod.GET})
	public String updateLcProcess(LcProcess lc_Process,HttpServletRequest request){
		int i = 0;
		if(null != lc_Process && !"".equals(lc_Process)){
			lc_Process.setLc_process_mtime(getSimpleDateFormat());
			lc_Process.setXt_userinfo_id(getXtUid());
			i=lcProcessService.updateLcProcess(lc_Process);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param lc_process_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delLcProcess",method={RequestMethod.POST,RequestMethod.GET})
	public String delLcProcess(String lc_process_id,HttpServletRequest request){
		int i = 0;
		if(null != lc_process_id && !"".equals(lc_process_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("lc_process_id",lc_process_id.split(","));
			i=lcProcessService.delLcProcess(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param lc_process_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyLcProcess",method={RequestMethod.POST,RequestMethod.GET})
	public String copyLcProcess(String lc_process_id,HttpServletRequest request){
		int i = 0;
		LcProcess lc_Process = lcProcessService.getLcProcessById(lc_process_id);
		if(null != lc_Process && !"".equals(lc_Process)){
			lc_Process.setLc_process_id(UUID.toUUID());
			i=lcProcessService.addLcProcess(lc_Process);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 导出
	* @param excleData 
	* @param excleHeader 
	* @param excleText 
	* @param request 
	* @param request 
	*/
	@RequestMapping(value="/exportLcProcess",method={RequestMethod.POST,RequestMethod.GET})
	public void exportLcProcess(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	
	
	/**
	 * 载入到设计工作流页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/loadLcDesign",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadLcDesign(String lc_process_id,HttpServletRequest request,Model model){
		model.addAttribute("lc_process_id", lc_process_id);
		return new ModelAndView("pc/lc-view/lc-design/lc-design");
	}
	
	/**
	 * 通过在线设计生成流程信息
	 * @param request
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping(value="/createBPMN",method={RequestMethod.POST,RequestMethod.GET})
	public String createBPMN(LcProcess lc_Process,MxGraphModel mxGraphModel,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
//		String mxgraphxml = URLDecoder.decode(request.getParameter("mxgraphxml"), "UTF-8");
//		String imgxml = URLDecoder.decode(request.getParameter("imgxml"), "UTF-8");
		String mxgraphxml = lc_Process.getMxgraphxml();
		String imgxml = lc_Process.getImgxml();
		mxGraphModel.setMxgraphxml(mxgraphxml);
		mxGraphModel.setImgxml(imgxml);
		MxGraphToBPMN MxGraphToBPMN = new MxGraphToBPMN();
		mxGraphModel = MxGraphToBPMN.createBPMN(mxGraphModel);
		//////////添加或修改流程信息///////////////
		lc_Process.setLc_process_flag("0");
		lc_Process.setLc_process_bpmn(mxGraphModel.getBpmn());
		lc_Process.setLc_process_uid(mxGraphModel.getProcessId());
		lc_Process.setLc_process_uk(mxGraphModel.getProcessId());
		lc_Process.setLc_process_remark(mxGraphModel.getRemark());
		lc_Process.setLc_process_mxgraphxml(mxGraphModel.getMxgraphxml());
		lc_Process.setLc_process_status("0");
		lc_Process.setXt_userinfo_id(getXtUid());
		lc_Process.setLc_process_title(mxGraphModel.getProcessName());
		LcProcess lcProcess = generateBpmnAndImg(request.getRequestURL().toString(), imgxml, mxGraphModel.getBpmn(), mxGraphModel.getProcessName(), mxGraphModel.getW(), mxGraphModel.getH(), response, lc_Process);
		lc_Process.setLc_process_bpmn_path(lcProcess.getLc_process_bpmn_path());
		lc_Process.setLc_process_path(lcProcess.getLc_process_path());
		lc_Process.setLc_process_img_path(lcProcess.getLc_process_img_path());
		int i = 0;
		if(StringUtils.isEmpty(lc_Process.getLc_process_id())){
			lc_Process.setLc_process_id(UUID.toUUID());
			lc_Process.setLc_process_ctime(getSimpleDateFormat());
			i = lcProcessService.addLcProcess(lc_Process);
		}else{
			lc_Process.setLc_process_mtime(getSimpleDateFormat());
			i = lcProcessService.updateLcProcess(lc_Process);
		}
		if(i > 0){
			return "{success:true,msg:'保存流程成功',lc_Process_id:'"+lc_Process.getLc_process_id()+"'}";
		}
		return "{success:false,msg:'保存流程失败',lc_Process_id:'0'}";
	}
	
	/**
	 * 生成bpmn,图片两个文件及压缩文件
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public LcProcess generateBpmnAndImg(String url,String imgxml,String bpmn,String processName,String w,String h,HttpServletResponse response,LcProcess lc_Process) throws UnsupportedEncodingException{
		//生成BPMN文件
		String attachPath = FileUtil.validOrCreateFile(getXtPathCache("ActivitiLc").get(0).getXt_path()+processName+"/");
		String bpmnAttachPath = attachPath+processName+".bpmn";
		String imgAttachPath =  attachPath+processName+".png";
		try {
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(bpmnAttachPath),"UTF8");
			out.write(bpmn);
			out.flush();
			out.close();
		} catch (IOException e) {
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		//通过mxgraphxml字符串生成PNG图片
		MxGraphToPng MxGraphToPng = new MxGraphToPng();
		int j = MxGraphToPng.mxgraphxml_to_png(url,imgxml,imgAttachPath,w,h,response);
		if(j <= 0){
			throw new ExceptionUtil("由bpmn文件生成图片失败");
		}
		//生成ZIP文件
		try{
			File jpdlAttachPathFile = new File(bpmnAttachPath);
			File imgAttachPathFile = new File(imgAttachPath);
			File zipFile = new File(attachPath+processName+".zip");
			File[] srcfile={jpdlAttachPathFile,imgAttachPathFile};
			FileUtil.zipFiles(srcfile,zipFile);
			lc_Process.setLc_process_bpmn_path(processName+".bpmn");
			lc_Process.setLc_process_path(processName+".zip");
			lc_Process.setLc_process_img_path(processName+".png");
		}catch (Exception e) {
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return lc_Process;
	}
	
	/**
	 * 部署流程
	 */
	@ResponseBody
	@RequestMapping(value="/createDeployment",method={RequestMethod.POST,RequestMethod.GET})
	public String createDeployment(String lc_process_id,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("lc_process_id", lc_process_id);
		condition.put("lc_process_status", 1);
		int i = lcProcessService.updateLcProcessStatus(lc_process_id, condition);
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	
	/**
	 * 发起流程实例
	 * @param lc_deployment_his_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/startProcessInstance",method={RequestMethod.POST,RequestMethod.GET})
	public String startProcessInstance(String lc_deployment_his_id){
		ProcessDefinition processDefinition = activitiUtil.getProcessDefinition(lc_deployment_his_id);
		ProcessInstance procesInstance = activitiUtil.startProcessInstanceByKey(processDefinition.getKey(), null, null);
		System.out.println(procesInstance.getActivityId());
		System.out.println(procesInstance.getBusinessKey());
		System.out.println(procesInstance.getDeploymentId());
		System.out.println(procesInstance.getId());
		System.out.println(procesInstance.getProcessDefinitionId());
		System.out.println(procesInstance.getProcessDefinitionKey());
		System.out.println(procesInstance.getProcessDefinitionName());
		System.out.println(procesInstance.getProcessDefinitionVersion());
		System.out.println(procesInstance.getProcessInstanceId());
		return outAudStr(true);
	}
	
	/**
	 * 获取所有实例
	 * @param lc_deployment_his_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getProcessInstance",method={RequestMethod.POST,RequestMethod.GET})
	public String getProcessInstance(String lc_deployment_his_id){
		ProcessDefinition processDefinition = activitiUtil.getProcessDefinition(lc_deployment_his_id);
		List<ProcessInstance> list = activitiUtil.getProcessInstaceListById(processDefinition.getId());
		JSONArray jsonArray = new JSONArray();  
		Map<String, Object> model = new HashMap<String, Object>();
		for(int i = 0; i < list.size(); i++){
			ProcessInstance processInstance = list.get(i);
			model.put("id", processInstance.getId());
			model.put("name",processInstance.getName()) ;
			model.put("processDefinitionName", processInstance.getProcessDefinitionName());
			model.put("processDefinitionVersion", processInstance.getProcessDefinitionVersion());
			if(processInstance.isSuspended()){
				model.put("suspended", "是");
			}else{
				model.put("suspended", "否");
			}
			if(activitiUtil.isEnded(processInstance.getId())){
				model.put("pstatus", "已结束");
			}else{
				model.put("pstatus", "运行中");
			}
			model.put("description", processInstance.getDescription());
			model.put("processDefinitionId", processInstance.getProcessDefinitionId());
			model.put("processDefinitionKey", processInstance.getProcessDefinitionKey());
			jsonArray.add(model);
		}
		return outItemsStr(jsonArray);
	}
	
	/**
	* 载入实例初始化页面
	* @param lc_process 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadLcProcessInstance",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadLcProcessInstance(String lc_deployment_his_id,String lc_process_id,HttpServletRequest request){
		request.setAttribute("lc_deployment_his_id", lc_deployment_his_id);
		request.setAttribute("lc_process_id", lc_process_id);
		return new ModelAndView("pc/lc-view/lc-process-instance/lc-process-instance-list");
	} 
		  
	/**
	* 载入实例图初始化页面
	* @param lc_process 
	* @param request 
	* @return
	 * @throws IOException 
	*/
	@RequestMapping(value="/loadLcProcessInstanceImg",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadLcProcessInstanceImg(String processInstanceId,Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		model.addAttribute("processInstanceId", processInstanceId);
	    Map<String, Object> map = activitiUtil.getProcessInstanceImg(processInstanceId);
	    if(map.get("processInstance") == null){
	    	String lc_apply_id = request.getParameter("lc_apply_id");
	    	model.addAttribute("lc_apply_id", lc_apply_id);
//	    	Lc_Apply lc_Apply = lc_ApplyService.getLcApplyById(lc_apply_id);
//	    	if(lc_Apply != null){
//	    		Map<String, Object> condition = new HashMap<String, Object>();
//	    		condition.put("xt_constant_id", lc_Apply.getLc_apply_model_id());
//	    		Lc_Deployment_His lcDeploymentHis = lc_Deployment_HisService.getLcDeploymentHisNewUnique(condition);
//	    		model.addAttribute("deploymentId", lcDeploymentHis.getLc_deployment_his_id());
//	    		Lc_Process lc_Process = lcProcessService.getLcProcessById(lcDeploymentHis.getLc_process_id());
//	    		String attachPath = FileUtil.validOrCreateFile(getXtPathCache("ActivitiLc").get(0).getXt_path()+lc_Process.getLc_process_title()+"/"+lc_Process.getLc_process_img_path());
//	    		File file = new File(attachPath); 
////	    		model.addAttribute("img",ImageAnd64Binary.getImageStr(attachPath));
//	    		String xt_attachment_name=lc_Process.getLc_process_bpmn_path();
//	    		int len=0;
//				byte []buffers = new byte[1024];
//				BufferedInputStream br = null; 
//		        OutputStream ut = null;  
//				response.reset();
//				response.setContentType("application/x-msdownload");
//				response.setHeader("Content-Disposition","attachment;filename=" +java.net.URLEncoder.encode(xt_attachment_name,"UTF-8"));
//				br = new BufferedInputStream(new FileInputStream(file));  
//		        ut = response.getOutputStream();  
//		        while((len=br.read(buffers))!=-1){  
//		            ut.write(buffers, 0, len);  
//		        } 
//		        ut.flush();
//		        ut.close();
//		        br.close();
//	    	}
	    	return new ModelAndView("pc/lc-view/lc-process-instance/lc-process-instance-imged");
	    }
	    model.addAttribute("x", map.get("x"));
	    model.addAttribute("y", map.get("y"));
	    model.addAttribute("width", map.get("width"));
	    model.addAttribute("height", map.get("height"));
	    model.addAttribute("img", map.get("img"));
	    model.addAttribute("ActivityImpl", map.get("ActivityImpl"));
	    model.addAttribute("activitiList", map.get("activitiList"));
		return new ModelAndView("pc/lc-view/lc-process-instance/lc-process-instance-img");
	}  
	
//	/**
//	 * 只查看流程图
//	 * @param deploymentId
//	 * @param request
//	 * @param response
//	 * @throws Exception
//	 */
//	@ResponseBody
//	@RequestMapping(value="/viewImage",method={RequestMethod.POST,RequestMethod.GET})
//	public void viewImage(String deploymentId,HttpServletRequest request,HttpServletResponse response) throws Exception {
//		// 从仓库中找需要展示的文件
//		List<String> names = activitiUtil.getRepositoryService().getDeploymentResourceNames(deploymentId);
//		String imageName = null;
//		for (String name : names) {
//			if(name.indexOf(".png")>=0){
//				imageName = name;
//			}
//		}
//		if(imageName!=null){
//			String lc_apply_id = request.getParameter("lc_apply_id");
//			Lc_Apply lc_Apply = lc_ApplyService.getLcApplyById(lc_apply_id);
//			if(lc_Apply != null){
//				Map<String, Object> condition = new HashMap<String, Object>();
//				condition.put("xt_constant_id", lc_Apply.getLc_apply_model_id());
//				Lc_Deployment_His lcDeploymentHis = lc_Deployment_HisService.getLcDeploymentHisNewUnique(condition);
//				Lc_Process lc_Process = lcProcessService.getLcProcessById(lcDeploymentHis.getLc_process_id());
//				String attachPath = FileUtil.validOrCreateFile(getXtPathCache("ActivitiLc").get(0).getXt_path()+lc_Process.getLc_process_title()+"/"+lc_Process.getLc_process_img_path());
//				File file = new File(attachPath); 
//				//通过部署ID和文件名称得到文件的输入流
//				InputStream in =  activitiUtil.getRepositoryService().getResourceAsStream(deploymentId, imageName);
//				FileUtils.copyInputStreamToFile(in, file);
//	//	   		String xt_attachment_name=lc_Process.getLc_process_bpmn_path();
//	//	   		int len=0;
//	//			byte []buffers = new byte[1024];
//	//			BufferedInputStream br = null; 
//	//	        OutputStream ut = null;  
//	//			response.reset();
//	//			response.setContentType("application/x-msdownload");
//	//			response.setHeader("Content-Disposition","attachment;filename=" +java.net.URLEncoder.encode(xt_attachment_name,"UTF-8"));
//	//			br = new BufferedInputStream(new FileInputStream(file));  
//	//	        ut = response.getOutputStream();  
//	//	        while((len=br.read(buffers))!=-1){  
//	//	            ut.write(buffers, 0, len);  
//	//	        } 
//	//	        ut.flush();
//	//	        ut.close();
//	//	        br.close();
//			}
//		}
//	}
	
	/**
	 * 只查看流程图
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@AuthUneedLogin
	@ResponseBody
	@RequestMapping(value="/viewImage",method={RequestMethod.POST,RequestMethod.GET})
	public void viewImage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String lc_apply_id = request.getParameter("lc_apply_id");
		LcApply lc_Apply = lcApplyService.getLcApplyById(lc_apply_id);
		if(lc_Apply != null){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_constant_id", lc_Apply.getLc_apply_model_id());
			LcDeploymentHis lcDeploymentHis = lcDeploymentHisService.getLcDeploymentHisNewUnique(condition);
			LcProcess lc_Process = lcProcessService.getLcProcessById(lcDeploymentHis.getLc_process_id());
			String attachPath = FileUtil.validOrCreateFile(getXtPathCache("ActivitiLc").get(0).getXt_path()+lc_Process.getLc_process_title()+"/"+lc_Process.getLc_process_img_path());
			File file = new File(attachPath); 
	   		String xt_attachment_name=lc_Process.getLc_process_bpmn_path();
	   		int len=0;
			byte []buffers = new byte[1024];
			BufferedInputStream br = null; 
	        OutputStream ut = null;  
			response.reset();
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition","attachment;filename=" +java.net.URLEncoder.encode(xt_attachment_name,"UTF-8"));
			br = new BufferedInputStream(new FileInputStream(file));  
	        ut = response.getOutputStream();  
	        while((len=br.read(buffers))!=-1){  
	            ut.write(buffers, 0, len);  
	        } 
	        ut.flush();
	        ut.close();
	        br.close();
		}
    }
		  
	/**
	 * 挂起流程实例（可作为撤销流程）
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/suspendProcessInstanceById",method={RequestMethod.POST,RequestMethod.GET})
	public String suspendProcessInstanceById(String id){
		boolean flag = activitiUtil.suspendProcessInstanceById(id);
		return outAudStr(flag);
	}
	
	/**
	 * 激活流程实例(开启流程实例)
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/activateProcessInstanceById",method={RequestMethod.POST,RequestMethod.GET})
	public String activateProcessInstanceById(String id){
		boolean flag = activitiUtil.activateProcessInstanceById(id);
		return outAudStr(flag);
	}
	
	/**
	 * 完成任务
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/completeTask",method={RequestMethod.POST,RequestMethod.GET})
	public String completeTask(String taskId){
		return outAudStr(activitiUtil.completeTask(taskId));
	}
	
	/**
	 * 下载Bpmn文件
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/downFileBpmn",method={RequestMethod.POST,RequestMethod.GET})
	public void downFile(String lc_process_id,HttpServletRequest request,HttpServletResponse response){
		LcProcess lc_Process = lcProcessService.getLcProcessById(lc_process_id);
		response.setContentType("text/html;charset=utf-8");  
		String attachPath = FileUtil.validOrCreateFile(getXtPathCache("ActivitiLc").get(0).getXt_path()+lc_Process.getLc_process_title()+"/"+lc_Process.getLc_process_bpmn_path());
		File file = new File(attachPath); 
		String xt_attachment_name=lc_Process.getLc_process_bpmn_path();
		try {
			if(!file.exists()){
				response.getWriter().println("<script>top.Ext.Msg.alert('提示','<font color=red>您访问的文件已经不存在，请联系管理员！</font>');</script>");
			}else{
				int len=0;
				byte []buffers = new byte[1024];
				BufferedInputStream br = null; 
		        OutputStream ut = null;  
				response.reset();
				response.setContentType("application/x-msdownload");
				response.setHeader("Content-Disposition","attachment;filename=" +java.net.URLEncoder.encode(xt_attachment_name,"UTF-8"));
				br = new BufferedInputStream(new FileInputStream(file));  
		        ut = response.getOutputStream();  
		        while((len=br.read(buffers))!=-1){  
		            ut.write(buffers, 0, len);  
		        } 
		        ut.flush();
		        ut.close();
		        br.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 下载Img文件
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/downFileImg",method={RequestMethod.POST,RequestMethod.GET})
	public void downFileImg(String lc_process_id,HttpServletRequest request,HttpServletResponse response){
		LcProcess lc_Process = lcProcessService.getLcProcessById(lc_process_id);
		response.setContentType("text/html;charset=utf-8");  
		String attachPath = FileUtil.validOrCreateFile(getXtPathCache("ActivitiLc").get(0).getXt_path()+lc_Process.getLc_process_title()+"/"+lc_Process.getLc_process_img_path());
		File file = new File(attachPath); 
		String xt_attachment_name=lc_Process.getLc_process_img_path();
		try {
			if(!file.exists()){
				response.getWriter().println("<script>top.Ext.Msg.alert('提示','<font color=red>您访问的文件已经不存在，请联系管理员！</font>');</script>");
			}else{
				int len=0;
				byte []buffers = new byte[1024];
				BufferedInputStream br = null; 
		        OutputStream ut = null;  
				response.reset();
				response.setContentType("application/x-msdownload");
				response.setHeader("Content-Disposition","attachment;filename=" +java.net.URLEncoder.encode(xt_attachment_name,"UTF-8"));
				br = new BufferedInputStream(new FileInputStream(file));  
		        ut = response.getOutputStream();  
		        while((len=br.read(buffers))!=-1){  
		            ut.write(buffers, 0, len);  
		        } 
		        ut.flush();
		        ut.close();
		        br.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
