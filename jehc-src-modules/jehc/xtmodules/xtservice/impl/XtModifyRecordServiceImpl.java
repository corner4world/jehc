package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtModifyRecordDao;
import jehc.xtmodules.xtmodel.XtModifyRecord;
import jehc.xtmodules.xtservice.XtModifyRecordService;

/**
* 修改记录日志 
* 2017-04-13 12:50:49  邓纯杰
*/
@Service("xtModifyRecordService")
public class XtModifyRecordServiceImpl extends BaseService implements XtModifyRecordService{
	@Autowired
	private XtModifyRecordDao xtModifyRecordDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtModifyRecord> getXtModifyRecordListByCondition(Map<String,Object> condition){
		try{
			return xtModifyRecordDao.getXtModifyRecordListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_modify_record_id 
	* @return
	*/
	public XtModifyRecord getXtModifyRecordById(String xt_modify_record_id){
		try{
			XtModifyRecord xt_Modify_Record = xtModifyRecordDao.getXtModifyRecordById(xt_modify_record_id);
			return xt_Modify_Record;
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_modify_record 
	* @return
	*/
	public int addXtModifyRecord(XtModifyRecord xt_Modify_Record){
		int i = 0;
		try {
			i = xtModifyRecordDao.addXtModifyRecord(xt_Modify_Record);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_modify_record 
	* @return
	*/
	public int updateXtModifyRecord(XtModifyRecord xt_Modify_Record){
		int i = 0;
		try {
			i = xtModifyRecordDao.updateXtModifyRecord(xt_Modify_Record);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtModifyRecord(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtModifyRecordDao.delXtModifyRecord(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param xt_modify_recordList 
	* @return
	*/
	public int addBatchXtModifyRecord(List<XtModifyRecord> xt_Modify_RecordList){
		int i = 0;
		try {
			i = xtModifyRecordDao.addBatchXtModifyRecord(xt_Modify_RecordList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param xt_modify_recordList 
	* @return
	*/
	public int updateBatchXtModifyRecord(List<XtModifyRecord> xt_Modify_RecordList){
		int i = 0;
		try {
			i = xtModifyRecordDao.updateBatchXtModifyRecord(xt_Modify_RecordList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	/**
	* 批量添加
	* @param xt_modify_recordList 
	* @return
	*/
	public int putBatchXtModifyRecord(List<XtModifyRecord> xt_Modify_RecordList){
		int i = 0;
		try {
			i = xtModifyRecordDao.addBatchXtModifyRecord(xt_Modify_RecordList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
