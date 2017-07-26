package jehc.lcmodules.lcdao;
import java.util.List;
import java.util.Map;

import jehc.lcmodules.lcmodel.LcDeploymentHis;

/**
* 流程部署历史记录 
* 2016-12-22 13:02:01  邓纯杰
*/
public interface LcDeploymentHisDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<LcDeploymentHis> getLcDeploymentHisListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param id 
	* @return
	*/
	public LcDeploymentHis getLcDeploymentHisById(String id);
	/**
	* 添加
	* @param lc_deployment_his 
	* @return
	*/
	public int addLcDeploymentHis(LcDeploymentHis lc_Deployment_His);
	/**
	* 修改
	* @param lc_deployment_his 
	* @return
	*/
	public int updateLcDeploymentHis(LcDeploymentHis lc_Deployment_His);
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
	public int addBatchLcDeploymentHis(List<LcDeploymentHis> lc_Deployment_HisList);
	/**
	* 批量修改
	* @param lc_deployment_hisList 
	* @return
	*/
	public int updateBatchLcDeploymentHis(List<LcDeploymentHis> lc_Deployment_HisList);
	
	/**
	 * 查询唯一一个对象
	 * @param condition
	 * @return
	 */
	public LcDeploymentHis getLcDeploymentHisUnique(Map<String,Object> condition);
	
	/**
	 * 查询最新唯一一个对象
	 * @param condition
	 * @return
	 */
	public LcDeploymentHis getLcDeploymentHisNewUnique(Map<String,Object> condition);
}
