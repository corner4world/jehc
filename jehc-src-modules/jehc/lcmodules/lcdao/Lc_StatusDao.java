package jehc.lcmodules.lcdao;
import java.util.List;
import java.util.Map;

import jehc.lcmodules.lcmodel.Lc_Status;

/**
* 流程状态 
* 2016-05-04 14:13:34  邓纯杰
*/
public interface Lc_StatusDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Lc_Status> getLcStatusListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param lc_status_id 
	* @return
	*/
	public Lc_Status getLcStatusById(String lc_status_id);
	/**
	* 添加
	* @param lc_status 
	* @return
	*/
	public int addLcStatus(Lc_Status lc_Status);
	/**
	* 修改
	* @param lc_status 
	* @return
	*/
	public int updateLcStatus(Lc_Status lc_Status);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delLcStatus(Map<String,Object> condition);
}
