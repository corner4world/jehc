package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtModifyRecord;

/**
* 修改记录日志 
* 2017-04-13 12:50:49  邓纯杰
*/
public interface XtModifyRecordService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtModifyRecord> getXtModifyRecordListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_modify_record_id 
	* @return
	*/
	public XtModifyRecord getXtModifyRecordById(String xt_modify_record_id);
	/**
	* 添加
	* @param xt_modify_record 
	* @return
	*/
	public int addXtModifyRecord(XtModifyRecord xt_Modify_Record);
	/**
	* 修改
	* @param xt_modify_record 
	* @return
	*/
	public int updateXtModifyRecord(XtModifyRecord xt_Modify_Record);
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
	public int addBatchXtModifyRecord(List<XtModifyRecord> xt_Modify_RecordList);
	/**
	* 批量修改
	* @param xt_modify_recordList 
	* @return
	*/
	public int updateBatchXtModifyRecord(List<XtModifyRecord> xt_Modify_RecordList);
	
	/**
	* 批量添加
	* @param xt_modify_recordList 
	* @return
	*/
	public int putBatchXtModifyRecord(List<XtModifyRecord> xt_Modify_RecordList);
}
