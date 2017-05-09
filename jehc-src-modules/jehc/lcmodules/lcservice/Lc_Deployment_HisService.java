package jehc.lcmodules.lcservice;
import java.util.List;
import java.util.Map;

import jehc.lcmodules.lcmodel.Lc_Deployment_His;

/**
* 流程部署历史记录 
* 2016-12-22 13:02:01  邓纯杰
*/
public interface Lc_Deployment_HisService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Lc_Deployment_His> getLcDeploymentHisListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param id 
	* @return
	*/
	public Lc_Deployment_His getLcDeploymentHisById(String id);
	/**
	* 添加
	* @param lc_deployment_his 
	* @return
	*/
	public int addLcDeploymentHis(Lc_Deployment_His lc_Deployment_His);
	/**
	* 修改
	* @param lc_deployment_his 
	* @return
	*/
	public int updateLcDeploymentHis(Lc_Deployment_His lc_Deployment_His);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delLcDeploymentHis(Map<String,Object> condition);
	/**
	* 批量添加
	* @param lc_deployment_hisList 
	* @return
	*/
	public int addBatchLcDeploymentHis(List<Lc_Deployment_His> lc_Deployment_HisList);
	/**
	* 批量修改
	* @param lc_deployment_hisList 
	* @return
	*/
	public int updateBatchLcDeploymentHis(List<Lc_Deployment_His> lc_Deployment_HisList);
	
	/**
	 * 查询唯一一个对象
	 * @param condition
	 * @return
	 */
	public Lc_Deployment_His getLcDeploymentHisUnique(Map<String,Object> condition);
	
	/**
	 * 查询最新唯一一个对象
	 * @param condition
	 * @return
	 */
	public Lc_Deployment_His getLcDeploymentHisNewUnique(Map<String,Object> condition);
}
