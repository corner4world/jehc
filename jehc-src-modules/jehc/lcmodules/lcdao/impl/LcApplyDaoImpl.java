package jehc.lcmodules.lcdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.lcmodules.lcdao.LcApplyDao;
import jehc.lcmodules.lcmodel.LcApply;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 流程申请 
* 2017-01-08 14:55:10  邓纯杰
*/
@Repository("lcApplyDao")
public class LcApplyDaoImpl  extends BaseDaoImpl implements LcApplyDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<LcApply> getLcApplyListByCondition(Map<String,Object> condition){
		return (List<LcApply>)this.getList("getLcApplyListByCondition",condition);
	}
	/**
	* 查询对象
	* @param lc_apply_id 
	* @return
	*/
	public LcApply getLcApplyById(String lc_apply_id){
		return (LcApply)this.get("getLcApplyById", lc_apply_id);
	}
	/**
	* 添加
	* @param lc_apply 
	* @return
	*/
	public int addLcApply(LcApply lc_Apply){
		return this.add("addLcApply", lc_Apply);
	}
	/**
	* 修改
	* @param lc_apply 
	* @return
	*/
	public int updateLcApply(LcApply lc_Apply){
		return this.update("updateLcApply", lc_Apply);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delLcApply(Map<String,Object> condition){
		return this.del("delLcApply", condition);
	}
	/**
	* 批量添加
	* @param lc_applyList 
	* @return
	*/
	public int addBatchLcApply(List<LcApply> lc_ApplyList){
		return this.add("addBatchLcApply", lc_ApplyList);
	}
	/**
	* 批量修改
	* @param lc_applyList 
	* @return
	*/
	public int updateBatchLcApply(List<LcApply> lc_ApplyList){
		return this.update("updateBatchLcApply", lc_ApplyList);
	}
	/**
	 * 根据实例编号查找集合
	 * @param condition
	 * @return
	 */
	public List<LcApply> getLcApplyList(Map<String,Object> condition){
		return (List<LcApply>)this.getList("getLcApplyList", condition);
	}
}
