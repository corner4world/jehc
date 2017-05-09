package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Modify_Record;

/**
* 修改记录日志 
* 2017-04-13 12:50:49  邓纯杰
*/
public interface Xt_Modify_RecordDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Modify_Record> getXtModifyRecordListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_modify_record_id 
	* @return
	*/
	public Xt_Modify_Record getXtModifyRecordById(String xt_modify_record_id);
	/**
	* 添加
	* @param xt_modify_record 
	* @return
	*/
	public int addXtModifyRecord(Xt_Modify_Record xt_Modify_Record);
	/**
	* 修改
	* @param xt_modify_record 
	* @return
	*/
	public int updateXtModifyRecord(Xt_Modify_Record xt_Modify_Record);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtModifyRecord(Map<String,Object> condition);
	/**
	* 批量添加
	* @param xt_modify_recordList 
	* @return
	*/
	public int addBatchXtModifyRecord(List<Xt_Modify_Record> xt_Modify_RecordList);
	/**
	* 批量修改
	* @param xt_modify_recordList 
	* @return
	*/
	public int updateBatchXtModifyRecord(List<Xt_Modify_Record> xt_Modify_RecordList);
}
