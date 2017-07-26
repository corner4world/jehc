package jehc.lcmodules.lcservice.impl;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.lcmodules.lcdao.LcStatusDao;
import jehc.lcmodules.lcmodel.LcStatus;
import jehc.lcmodules.lcservice.LcStatusService;
import jehc.xtmodules.xtcore.base.BaseService;

/**
* 流程状态 
* 2016-05-04 14:13:34  邓纯杰
*/
@Service("lcStatusService")
public class LcStatusServiceImpl extends BaseService implements LcStatusService{
	@Autowired
	private LcStatusDao lcStatusDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<LcStatus> getLcStatusListByCondition(Map<String,Object> condition){
		try{
			return lcStatusDao.getLcStatusListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new RuntimeException(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param lc_status_id 
	* @return
	*/
	public LcStatus getLcStatusById(String lc_status_id){
		try{
			return lcStatusDao.getLcStatusById(lc_status_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new RuntimeException(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param lc_status 
	* @return
	*/
	public int addLcStatus(LcStatus lc_Status){
		int i = 0;
		try {
			i = lcStatusDao.addLcStatus(lc_Status);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new RuntimeException(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param lc_status 
	* @return
	*/
	public int updateLcStatus(LcStatus lc_Status){
		int i = 0;
		try {
			i = lcStatusDao.updateLcStatus(lc_Status);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new RuntimeException(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delLcStatus(Map<String,Object> condition){
		int i = 0;
		try {
			i = lcStatusDao.delLcStatus(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new RuntimeException(e.getMessage(),e.getCause());
		}
		return i;
	}
}
