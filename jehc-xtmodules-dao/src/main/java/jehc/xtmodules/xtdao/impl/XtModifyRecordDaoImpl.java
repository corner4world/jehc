package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtModifyRecordDao;
import jehc.xtmodules.xtmodel.XtModifyRecord;

/**
* 修改记录日志 
* 2017-04-13 12:50:49  邓纯杰
*/
@Repository("xtModifyRecordDao")
public class XtModifyRecordDaoImpl  extends BaseDaoImpl implements XtModifyRecordDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtModifyRecord> getXtModifyRecordListByCondition(Map<String,Object> condition){
		return (List<XtModifyRecord>)this.getList("getXtModifyRecordListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_modify_record_id 
	* @return
	*/
	public XtModifyRecord getXtModifyRecordById(String xt_modify_record_id){
		return (XtModifyRecord)this.get("getXtModifyRecordById", xt_modify_record_id);
	}
	/**
	* 添加
	* @param xt_modify_record 
	* @return
	*/
	public int addXtModifyRecord(XtModifyRecord xt_Modify_Record){
		return this.add("addXtModifyRecord", xt_Modify_Record);
	}
	/**
	* 修改
	* @param xt_modify_record 
	* @return
	*/
	public int updateXtModifyRecord(XtModifyRecord xt_Modify_Record){
		return this.update("updateXtModifyRecord", xt_Modify_Record);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtModifyRecord(Map<String,Object> condition){
		return this.del("delXtModifyRecord", condition);
	}
	/**
	* 批量添加
	* @param xt_modify_recordList 
	* @return
	*/
	public int addBatchXtModifyRecord(List<XtModifyRecord> xt_Modify_RecordList){
		return this.add("addBatchXtModifyRecord", xt_Modify_RecordList);
	}
	/**
	* 批量修改
	* @param xt_modify_recordList 
	* @return
	*/
	public int updateBatchXtModifyRecord(List<XtModifyRecord> xt_Modify_RecordList){
		return this.update("updateBatchXtModifyRecord", xt_Modify_RecordList);
	}
}
