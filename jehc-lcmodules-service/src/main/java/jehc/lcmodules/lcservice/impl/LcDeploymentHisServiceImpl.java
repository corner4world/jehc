package jehc.lcmodules.lcservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.lcmodules.lcdao.LcDeploymentHisDao;
import jehc.lcmodules.lcmodel.LcDeploymentHis;
import jehc.lcmodules.lcservice.LcDeploymentHisService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 流程部署历史记录 
* 2016-12-22 13:02:01  邓纯杰
*/
@Service("lcDeploymentHisService")
public class LcDeploymentHisServiceImpl extends BaseService implements LcDeploymentHisService{
	@Autowired
	private LcDeploymentHisDao lcDeploymentHisDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<LcDeploymentHis> getLcDeploymentHisListByCondition(Map<String,Object> condition){
		try{
			return lcDeploymentHisDao.getLcDeploymentHisListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param id 
	* @return
	*/
	public LcDeploymentHis getLcDeploymentHisById(String id){
		try{
			return lcDeploymentHisDao.getLcDeploymentHisById(id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param lc_deployment_his 
	* @return
	*/
	public int addLcDeploymentHis(LcDeploymentHis lc_Deployment_His){
		int i = 0;
		try {
			i = lcDeploymentHisDao.addLcDeploymentHis(lc_Deployment_His);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param lc_deployment_his 
	* @return
	*/
	public int updateLcDeploymentHis(LcDeploymentHis lc_Deployment_His){
		int i = 0;
		try {
			i = lcDeploymentHisDao.updateLcDeploymentHis(lc_Deployment_His);
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
	public int delLcDeploymentHis(Map<String,Object> condition){
		int i = 0;
		try {
			i = lcDeploymentHisDao.delLcDeploymentHis(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param lc_deployment_hisList 
	* @return
	*/
	public int addBatchLcDeploymentHis(List<LcDeploymentHis> lc_Deployment_HisList){
		int i = 0;
		try {
			i = lcDeploymentHisDao.addBatchLcDeploymentHis(lc_Deployment_HisList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param lc_deployment_hisList 
	* @return
	*/
	public int updateBatchLcDeploymentHis(List<LcDeploymentHis> lc_Deployment_HisList){
		int i = 0;
		try {
			i = lcDeploymentHisDao.updateBatchLcDeploymentHis(lc_Deployment_HisList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	/**
	 * 查询唯一一个对象
	 * @param condition
	 * @return
	 */
	public LcDeploymentHis getLcDeploymentHisUnique(Map<String,Object> condition){
		try{
			return lcDeploymentHisDao.getLcDeploymentHisUnique(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 查询最新唯一一个对象
	 * @param condition
	 * @return
	 */
	public LcDeploymentHis getLcDeploymentHisNewUnique(Map<String,Object> condition){
		try{
			return lcDeploymentHisDao.getLcDeploymentHisNewUnique(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
