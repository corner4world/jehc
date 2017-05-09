package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_Modify_RecordDao;
import jehc.xtmodules.xtmodel.Xt_Modify_Record;

/**
* 修改记录日志 
* 2017-04-13 12:50:49  邓纯杰
*/
@Repository("xt_Modify_RecordDao")
public class Xt_Modify_RecordDaoImpl  extends BaseDaoImpl implements Xt_Modify_RecordDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Modify_Record> getXtModifyRecordListByCondition(Map<String,Object> condition){
		return (List<Xt_Modify_Record>)this.getList("getXtModifyRecordListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_modify_record_id 
	* @return
	*/
	public Xt_Modify_Record getXtModifyRecordById(String xt_modify_record_id){
		return (Xt_Modify_Record)this.get("getXtModifyRecordById", xt_modify_record_id);
	}
	/**
	* 添加
	* @param xt_modify_record 
	* @return
	*/
	public int addXtModifyRecord(Xt_Modify_Record xt_Modify_Record){
		return this.add("addXtModifyRecord", xt_Modify_Record);
	}
	/**
	* 修改
	* @param xt_modify_record 
	* @return
	*/
	public int updateXtModifyRecord(Xt_Modify_Record xt_Modify_Record){
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
	public int addBatchXtModifyRecord(List<Xt_Modify_Record> xt_Modify_RecordList){
		return this.add("addBatchXtModifyRecord", xt_Modify_RecordList);
	}
	/**
	* 批量修改
	* @param xt_modify_recordList 
	* @return
	*/
	public int updateBatchXtModifyRecord(List<Xt_Modify_Record> xt_Modify_RecordList){
		return this.update("updateBatchXtModifyRecord", xt_Modify_RecordList);
	}
}
