package jehc.lcmodules.lcservice;
import java.util.List;
import java.util.Map;

import jehc.lcmodules.lcmodel.LcApply;

/**
* 流程申请 
* 2017-01-08 14:55:11  邓纯杰
*/
public interface LcApplyService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<LcApply> getLcApplyListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param lc_apply_id 
	* @return
	*/
	public LcApply getLcApplyById(String lc_apply_id);
	/**
	* 添加
	* @param lc_apply 
	* @return
	*/
	public int addLcApply(LcApply lc_Apply);
	/**
	* 修改
	* @param lc_apply 
	* @return
	*/
	public int updateLcApply(LcApply lc_Apply);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delLcApply(Map<String,Object> condition);
	/**
	* 批量添加
	* @param lc_applyList 
	* @return
	*/
	public int addBatchLcApply(List<LcApply> lc_ApplyList);
	/**
	* 批量修改
	* @param lc_applyList 
	* @return
	*/
	public int updateBatchLcApply(List<LcApply> lc_ApplyList);
	/**
	 * 根据实例编号查找集合
	 * @param condition
	 * @return
	 */
	public List<LcApply> getLcApplyList(Map<String,Object> condition);
}
