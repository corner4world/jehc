package jehc.lcmodules.lcdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.lcmodules.lcdao.Lc_StatusDao;
import jehc.lcmodules.lcmodel.Lc_Status;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 流程状态 
* 2016-05-04 14:13:34  邓纯杰
*/
@Repository("lc_StatusDao")
public class Lc_StatusDaoImpl  extends BaseDaoImpl implements Lc_StatusDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Lc_Status> getLcStatusListByCondition(Map<String,Object> condition){
		return (List<Lc_Status>)this.getList("getLcStatusListByCondition",condition);
	}
	/**
	* 查询对象
	* @param lc_status_id 
	* @return
	*/
	public Lc_Status getLcStatusById(String lc_status_id){
		return (Lc_Status)this.get("getLcStatusById", lc_status_id);
	}
	/**
	* 添加
	* @param lc_status 
	* @return
	*/
	public int addLcStatus(Lc_Status lc_Status){
		return this.add("addLcStatus", lc_Status);
	}
	/**
	* 修改
	* @param lc_status 
	* @return
	*/
	public int updateLcStatus(Lc_Status lc_Status){
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
