package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_Modify_RecordDao;
import jehc.xtmodules.xtmodel.Xt_Modify_Record;
import jehc.xtmodules.xtservice.Xt_Modify_RecordService;

/**
* 修改记录日志 
* 2017-04-13 12:50:49  邓纯杰
*/
@Service("xt_Modify_RecordService")
public class Xt_Modify_RecordServiceImpl extends BaseService implements Xt_Modify_RecordService{
	@Autowired
	private Xt_Modify_RecordDao xt_Modify_RecordDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Modify_Record> getXtModifyRecordListByCondition(Map<String,Object> condition){
		try{
			return xt_Modify_RecordDao.getXtModifyRecordListByCondition(condition);
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
	public Xt_Modify_Record getXtModifyRecordById(String xt_modify_record_id){
		try{
			Xt_Modify_Record xt_Modify_Record = xt_Modify_RecordDao.getXtModifyRecordById(xt_modify_record_id);
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
	public int addXtModifyRecord(Xt_Modify_Record xt_Modify_Record){
		int i = 0;
		try {
			i = xt_Modify_RecordDao.addXtModifyRecord(xt_Modify_Record);
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
	public int updateXtModifyRecord(Xt_Modify_Record xt_Modify_Record){
		int i = 0;
		try {
			i = xt_Modify_RecordDao.updateXtModifyRecord(xt_Modify_Record);
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
			i = xt_Modify_RecordDao.delXtModifyRecord(condition);
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
	public int addBatchXtModifyRecord(List<Xt_Modify_Record> xt_Modify_RecordList){
		int i = 0;
		try {
			i = xt_Modify_RecordDao.addBatchXtModifyRecord(xt_Modify_RecordList);
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
	public int updateBatchXtModifyRecord(List<Xt_Modify_Record> xt_Modify_RecordList){
		int i = 0;
		try {
			i = xt_Modify_RecordDao.updateBatchXtModifyRecord(xt_Modify_RecordList);
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
	public int putBatchXtModifyRecord(List<Xt_Modify_Record> xt_Modify_RecordList){
		int i = 0;
		try {
			i = xt_Modify_RecordDao.addBatchXtModifyRecord(xt_Modify_RecordList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
