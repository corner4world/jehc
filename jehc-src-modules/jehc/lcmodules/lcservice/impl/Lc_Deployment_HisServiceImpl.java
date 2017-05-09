package jehc.lcmodules.lcservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.lcmodules.lcdao.Lc_Deployment_HisDao;
import jehc.lcmodules.lcmodel.Lc_Deployment_His;
import jehc.lcmodules.lcservice.Lc_Deployment_HisService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 流程部署历史记录 
* 2016-12-22 13:02:01  邓纯杰
*/
@Service("lc_Deployment_HisService")
public class Lc_Deployment_HisServiceImpl extends BaseService implements Lc_Deployment_HisService{
	@Autowired
	private Lc_Deployment_HisDao lc_Deployment_HisDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Lc_Deployment_His> getLcDeploymentHisListByCondition(Map<String,Object> condition){
		try{
			return lc_Deployment_HisDao.getLcDeploymentHisListByCondition(condition);
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
	public Lc_Deployment_His getLcDeploymentHisById(String id){
		try{
			return lc_Deployment_HisDao.getLcDeploymentHisById(id);
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
	public int addLcDeploymentHis(Lc_Deployment_His lc_Deployment_His){
		int i = 0;
		try {
			i = lc_Deployment_HisDao.addLcDeploymentHis(lc_Deployment_His);
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
	public int updateLcDeploymentHis(Lc_Deployment_His lc_Deployment_His){
		int i = 0;
		try {
			i = lc_Deployment_HisDao.updateLcDeploymentHis(lc_Deployment_His);
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
			i = lc_Deployment_HisDao.delLcDeploymentHis(condition);
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
	public int addBatchLcDeploymentHis(List<Lc_Deployment_His> lc_Deployment_HisList){
		int i = 0;
		try {
			i = lc_Deployment_HisDao.addBatchLcDeploymentHis(lc_Deployment_HisList);
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
	public int updateBatchLcDeploymentHis(List<Lc_Deployment_His> lc_Deployment_HisList){
		int i = 0;
		try {
			i = lc_Deployment_HisDao.updateBatchLcDeploymentHis(lc_Deployment_HisList);
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
	public Lc_Deployment_His getLcDeploymentHisUnique(Map<String,Object> condition){
		try{
			return lc_Deployment_HisDao.getLcDeploymentHisUnique(condition);
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
	public Lc_Deployment_His getLcDeploymentHisNewUnique(Map<String,Object> condition){
		try{
			return lc_Deployment_HisDao.getLcDeploymentHisNewUnique(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
