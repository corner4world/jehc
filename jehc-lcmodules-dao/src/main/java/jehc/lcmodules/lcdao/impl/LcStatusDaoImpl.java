package jehc.lcmodules.lcdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.lcmodules.lcdao.LcStatusDao;
import jehc.lcmodules.lcmodel.LcStatus;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 流程状态 
* 2016-05-04 14:13:34  邓纯杰
*/
@Repository("lcStatusDao")
public class LcStatusDaoImpl  extends BaseDaoImpl implements LcStatusDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<LcStatus> getLcStatusListByCondition(Map<String,Object> condition){
		return (List<LcStatus>)this.getList("getLcStatusListByCondition",condition);
	}
	/**
	* 查询对象
	* @param lc_status_id 
	* @return
	*/
	public LcStatus getLcStatusById(String lc_status_id){
		return (LcStatus)this.get("getLcStatusById", lc_status_id);
	}
	/**
	* 添加
	* @param lc_status 
	* @return
	*/
	public int addLcStatus(LcStatus lc_Status){
		return this.add("addLcStatus", lc_Status);
	}
	/**
	* 修改
	* @param lc_status 
	* @return
	*/
	public int updateLcStatus(LcStatus lc_Status){
		return this.update("updateLcStatus", lc_Status);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delLcStatus(Map<String,Object> condition){
		return this.del("delLcStatus", condition);
	}
}
