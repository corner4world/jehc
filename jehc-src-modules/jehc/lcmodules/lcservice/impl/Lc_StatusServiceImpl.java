package jehc.lcmodules.lcservice.impl;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.lcmodules.lcdao.Lc_StatusDao;
import jehc.lcmodules.lcmodel.Lc_Status;
import jehc.lcmodules.lcservice.Lc_StatusService;
import jehc.xtmodules.xtcore.base.BaseService;

/**
* 流程状态 
* 2016-05-04 14:13:34  邓纯杰
*/
@Service("lc_StatusService")
public class Lc_StatusServiceImpl extends BaseService implements Lc_StatusService{
	@Autowired
	private Lc_StatusDao lc_StatusDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Lc_Status> getLcStatusListByCondition(Map<String,Object> condition){
		try{
			return lc_StatusDao.getLcStatusListByCondition(condition);
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
	public Lc_Status getLcStatusById(String lc_status_id){
		try{
			return lc_StatusDao.getLcStatusById(lc_status_id);
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
	public int addLcStatus(Lc_Status lc_Status){
		int i = 0;
		try {
			i = lc_StatusDao.addLcStatus(lc_Status);
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
	public int updateLcStatus(Lc_Status lc_Status){
		int i = 0;
		try {
			i = lc_StatusDao.updateLcStatus(lc_Status);
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
			i = lc_StatusDao.delLcStatus(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new RuntimeException(e.getMessage(),e.getCause());
		}
		return i;
	}
}
