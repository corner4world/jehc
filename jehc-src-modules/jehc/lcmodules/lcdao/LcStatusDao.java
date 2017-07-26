package jehc.lcmodules.lcdao;
import java.util.List;
import java.util.Map;

import jehc.lcmodules.lcmodel.LcStatus;

/**
* 流程状态 
* 2016-05-04 14:13:34  邓纯杰
*/
public interface LcStatusDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<LcStatus> getLcStatusListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param lc_status_id 
	* @return
	*/
	public LcStatus getLcStatusById(String lc_status_id);
	/**
	* 添加
	* @param lc_status 
	* @return
	*/
	public int addLcStatus(LcStatus lc_Status);
	/**
	* 修改
	* @param lc_status 
	* @return
	*/
	public int updateLcStatus(LcStatus lc_Status);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delLcStatus(Map<String,Object> condition);
}
