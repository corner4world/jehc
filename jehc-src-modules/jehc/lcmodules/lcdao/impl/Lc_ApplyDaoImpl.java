package jehc.lcmodules.lcdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.lcmodules.lcdao.Lc_ApplyDao;
import jehc.lcmodules.lcmodel.Lc_Apply;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 流程申请 
* 2017-01-08 14:55:10  邓纯杰
*/
@Repository("lc_ApplyDao")
public class Lc_ApplyDaoImpl  extends BaseDaoImpl implements Lc_ApplyDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Lc_Apply> getLcApplyListByCondition(Map<String,Object> condition){
		return (List<Lc_Apply>)this.getList("getLcApplyListByCondition",condition);
	}
	/**
	* 查询对象
	* @param lc_apply_id 
	* @return
	*/
	public Lc_Apply getLcApplyById(String lc_apply_id){
		return (Lc_Apply)this.get("getLcApplyById", lc_apply_id);
	}
	/**
	* 添加
	* @param lc_apply 
	* @return
	*/
	public int addLcApply(Lc_Apply lc_Apply){
		return this.add("addLcApply", lc_Apply);
	}
	/**
	* 修改
	* @param lc_apply 
	* @return
	*/
	public int updateLcApply(Lc_Apply lc_Apply){
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
	public int addBatchLcApply(List<Lc_Apply> lc_ApplyList){
		return this.add("addBatchLcApply", lc_ApplyList);
	}
	/**
	* 批量修改
	* @param lc_applyList 
	* @return
	*/
	public int updateBatchLcApply(List<Lc_Apply> lc_ApplyList){
		return this.update("updateBatchLcApply", lc_ApplyList);
	}
}
