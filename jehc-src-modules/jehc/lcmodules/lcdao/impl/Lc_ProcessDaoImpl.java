package jehc.lcmodules.lcdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.lcmodules.lcdao.Lc_ProcessDao;
import jehc.lcmodules.lcmodel.Lc_Process;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 流程详细信息即流程部署内容,BPMN文件,ZIP路径,IMG路径,MXGraph内容等等（流程表） 
* 2016-11-22 10:16:39  邓纯杰
*/
@Repository("lc_ProcessDao")
public class Lc_ProcessDaoImpl  extends BaseDaoImpl implements Lc_ProcessDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Lc_Process> getLcProcessListByCondition(Map<String,Object> condition){
		return (List<Lc_Process>)this.getList("getLcProcessListByCondition",condition);
	}
	/**
	* 查询对象
	* @param lc_process_id 
	* @return
	*/
	public Lc_Process getLcProcessById(String lc_process_id){
		return (Lc_Process)this.get("getLcProcessById", lc_process_id);
	}
	/**
	* 添加
	* @param lc_process 
	* @return
	*/
	public int addLcProcess(Lc_Process lc_Process){
		return this.add("addLcProcess", lc_Process);
	}
	/**
	* 修改
	* @param lc_process 
	* @return
	*/
	public int updateLcProcess(Lc_Process lc_Process){
		return this.update("updateLcProcess", lc_Process);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delLcProcess(Map<String,Object> condition){
		return this.update("delLcProcess", condition);
	}
	/**
	* 批量添加
	* @param lc_processList 
	* @return
	*/
	public int addBatchLcProcess(List<Lc_Process> lc_ProcessList){
		return this.add("addBatchLcProcess", lc_ProcessList);
	}
	/**
	* 批量修改
	* @param lc_processList 
	* @return
	*/
	public int updateBatchLcProcess(List<Lc_Process> lc_ProcessList){
		return this.update("updateBatchLcProcess", lc_ProcessList);
	}
	/**
	 * 发布或关闭流程
	 * @param condition
	 * @return
	 */
	public int updateLcProcessStatus(Map<String,Object> condition){
		return this.update("updateLcProcessStatus", condition);
	}
}
