package jehc.lcmodules.lcdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.lcmodules.lcdao.LcDeploymentHisDao;
import jehc.lcmodules.lcmodel.LcDeploymentHis;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 流程部署历史记录 
* 2016-12-22 13:02:01  邓纯杰
*/
@Repository("lcDeploymentHisDao")
public class LcDeployment_HisDaoImpl  extends BaseDaoImpl implements LcDeploymentHisDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<LcDeploymentHis> getLcDeploymentHisListByCondition(Map<String,Object> condition){
		return (List<LcDeploymentHis>)this.getList("getLcDeploymentHisListByCondition",condition);
	}
	/**
	* 查询对象
	* @param id 
	* @return
	*/
	public LcDeploymentHis getLcDeploymentHisById(String id){
		return (LcDeploymentHis)this.get("getLcDeploymentHisById", id);
	}
	/**
	* 添加
	* @param lc_deployment_his 
	* @return
	*/
	public int addLcDeploymentHis(LcDeploymentHis lc_Deployment_His){
		return this.add("addLcDeploymentHis", lc_Deployment_His);
	}
	/**
	* 修改
	* @param lc_deployment_his 
	* @return
	*/
	public int updateLcDeploymentHis(LcDeploymentHis lc_Deployment_His){
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
	public int addBatchLcDeploymentHis(List<LcDeploymentHis> lc_Deployment_HisList){
		return this.add("addBatchLcDeploymentHis", lc_Deployment_HisList);
	}
	/**
	* 批量修改
	* @param lc_deployment_hisList 
	* @return
	*/
	public int updateBatchLcDeploymentHis(List<LcDeploymentHis> lc_Deployment_HisList){
		return this.update("updateBatchLcDeploymentHis", lc_Deployment_HisList);
	}
	
	/**
	 * 查询唯一一个对象
	 * @param condition
	 * @return
	 */
	public LcDeploymentHis getLcDeploymentHisUnique(Map<String,Object> condition){
		return (LcDeploymentHis)this.get("getLcDeploymentHisUnique", condition);
	}
	
	/**
	 * 查询最新唯一一个对象
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public LcDeploymentHis getLcDeploymentHisNewUnique(Map<String,Object> condition){
		List<LcDeploymentHis> his = (List<LcDeploymentHis>)this.getList("getLcDeploymentHisNewUnique",condition);
		if(null != his && !his.isEmpty()){
			return his.get(0);
		}else{
			return new LcDeploymentHis();
		}
	}
}
