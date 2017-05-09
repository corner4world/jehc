package jehc.lcmodules.lcdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.lcmodules.lcdao.Lc_Deployment_HisDao;
import jehc.lcmodules.lcmodel.Lc_Deployment_His;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 流程部署历史记录 
* 2016-12-22 13:02:01  邓纯杰
*/
@Repository("lc_Deployment_HisDao")
public class Lc_Deployment_HisDaoImpl  extends BaseDaoImpl implements Lc_Deployment_HisDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Lc_Deployment_His> getLcDeploymentHisListByCondition(Map<String,Object> condition){
		return (List<Lc_Deployment_His>)this.getList("getLcDeploymentHisListByCondition",condition);
	}
	/**
	* 查询对象
	* @param id 
	* @return
	*/
	public Lc_Deployment_His getLcDeploymentHisById(String id){
		return (Lc_Deployment_His)this.get("getLcDeploymentHisById", id);
	}
	/**
	* 添加
	* @param lc_deployment_his 
	* @return
	*/
	public int addLcDeploymentHis(Lc_Deployment_His lc_Deployment_His){
		return this.add("addLcDeploymentHis", lc_Deployment_His);
	}
	/**
	* 修改
	* @param lc_deployment_his 
	* @return
	*/
	public int updateLcDeploymentHis(Lc_Deployment_His lc_Deployment_His){
		return this.update("updateLcDeploymentHis", lc_Deployment_His);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delLcDeploymentHis(Map<String,Object> condition){
		return this.del("delLcDeploymentHis", condition);
	}
	/**
	* 批量添加
	* @param lc_deployment_hisList 
	* @return
	*/
	public int addBatchLcDeploymentHis(List<Lc_Deployment_His> lc_Deployment_HisList){
		return this.add("addBatchLcDeploymentHis", lc_Deployment_HisList);
	}
	/**
	* 批量修改
	* @param lc_deployment_hisList 
	* @return
	*/
	public int updateBatchLcDeploymentHis(List<Lc_Deployment_His> lc_Deployment_HisList){
		return this.update("updateBatchLcDeploymentHis", lc_Deployment_HisList);
	}
	
	/**
	 * 查询唯一一个对象
	 * @param condition
	 * @return
	 */
	public Lc_Deployment_His getLcDeploymentHisUnique(Map<String,Object> condition){
		return (Lc_Deployment_His)this.get("getLcDeploymentHisUnique", condition);
	}
	
	/**
	 * 查询最新唯一一个对象
	 * @param condition
	 * @return
	 */
	public Lc_Deployment_His getLcDeploymentHisNewUnique(Map<String,Object> condition){
		return (Lc_Deployment_His)this.getList("getLcDeploymentHisUnique",condition).get(0);
	}
}
