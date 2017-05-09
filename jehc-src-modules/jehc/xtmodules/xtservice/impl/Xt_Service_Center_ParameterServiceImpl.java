package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_Service_Center_ParameterDao;
import jehc.xtmodules.xtmodel.Xt_Service_Center_Parameter;
import jehc.xtmodules.xtservice.Xt_Service_Center_ParameterService;

/**
* 服务中心参数 
* 2017-03-27 12:32:04  邓纯杰
*/
@Service("xt_Service_Center_ParameterService")
public class Xt_Service_Center_ParameterServiceImpl extends BaseService implements Xt_Service_Center_ParameterService{
	@Autowired
	private Xt_Service_Center_ParameterDao xt_Service_Center_ParameterDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Service_Center_Parameter> getXtServiceCenterParameterListByCondition(Map<String,Object> condition){
		try{
			return xt_Service_Center_ParameterDao.getXtServiceCenterParameterListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_service_center_parameter_id 
	* @return
	*/
	public Xt_Service_Center_Parameter getXtServiceCenterParameterById(String xt_service_center_parameter_id){
		try{
			Xt_Service_Center_Parameter xt_Service_Center_Parameter = xt_Service_Center_ParameterDao.getXtServiceCenterParameterById(xt_service_center_parameter_id);
			return xt_Service_Center_Parameter;
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_service_center_parameter 
	* @return
	*/
	public int addXtServiceCenterParameter(Xt_Service_Center_Parameter xt_Service_Center_Parameter){
		int i = 0;
		try {
			i = xt_Service_Center_ParameterDao.addXtServiceCenterParameter(xt_Service_Center_Parameter);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_service_center_parameter 
	* @return
	*/
	public int updateXtServiceCenterParameter(Xt_Service_Center_Parameter xt_Service_Center_Parameter){
		int i = 0;
		try {
			i = xt_Service_Center_ParameterDao.updateXtServiceCenterParameter(xt_Service_Center_Parameter);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	 * 修改（根据动态条件）
	 * @param xt_Service_Center_Parameter
	 * @return
	 */
	public int updateXtServiceCenterParameterBySelective(Xt_Service_Center_Parameter xt_Service_Center_Parameter){
		int i = 0;
		try {
			i = xt_Service_Center_ParameterDao.updateXtServiceCenterParameterBySelective(xt_Service_Center_Parameter);
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
	public int delXtServiceCenterParameter(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_Service_Center_ParameterDao.delXtServiceCenterParameter(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param xt_service_center_parameterList 
	* @return
	*/
	public int addBatchXtServiceCenterParameter(List<Xt_Service_Center_Parameter> xt_Service_Center_ParameterList){
		int i = 0;
		try {
			i = xt_Service_Center_ParameterDao.addBatchXtServiceCenterParameter(xt_Service_Center_ParameterList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param xt_service_center_parameterList 
	* @return
	*/
	public int updateBatchXtServiceCenterParameter(List<Xt_Service_Center_Parameter> xt_Service_Center_ParameterList){
		int i = 0;
		try {
			i = xt_Service_Center_ParameterDao.updateBatchXtServiceCenterParameter(xt_Service_Center_ParameterList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	 * 批量修改（根据动态条件）
	 * @param xt_Service_Center_ParameterList
	 * @return
	 */
	public int updateBatchXtServiceCenterParameterBySelective(List<Xt_Service_Center_Parameter> xt_Service_Center_ParameterList){
		int i = 0;
		try {
			i = xt_Service_Center_ParameterDao.updateBatchXtServiceCenterParameterBySelective(xt_Service_Center_ParameterList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 根据外键删除方法
	* @param xt_service_center_id
	* @return
	*/
	public int delXtServiceCenterParameterByForeignKey(String xt_service_center_id){
		int i = 0;
		try {
			i = xt_Service_Center_ParameterDao.delXtServiceCenterParameterByForeignKey(xt_service_center_id);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
