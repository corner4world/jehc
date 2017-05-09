package jehc.lcmodules.lcdao;
import java.util.List;
import java.util.Map;

import jehc.lcmodules.lcmodel.Lc_Apply;

/**
* 流程申请 
* 2017-01-08 14:55:10  邓纯杰
*/
public interface Lc_ApplyDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Lc_Apply> getLcApplyListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param lc_apply_id 
	* @return
	*/
	public Lc_Apply getLcApplyById(String lc_apply_id);
	/**
	* 添加
	* @param lc_apply 
	* @return
	*/
	public int addLcApply(Lc_Apply lc_Apply);
	/**
	* 修改
	* @param lc_apply 
	* @return
	*/
	public int updateLcApply(Lc_Apply lc_Apply);
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
	public int addBatchLcApply(List<Lc_Apply> lc_ApplyList);
	/**
	* 批量修改
	* @param lc_applyList 
	* @return
	*/
	public int updateBatchLcApply(List<Lc_Apply> lc_ApplyList);
}
