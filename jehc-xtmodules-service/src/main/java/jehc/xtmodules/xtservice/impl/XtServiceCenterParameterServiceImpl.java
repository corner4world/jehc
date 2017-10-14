package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtServiceCenterParameterDao;
import jehc.xtmodules.xtmodel.XtServiceCenterParameter;
import jehc.xtmodules.xtservice.XtServiceCenterParameterService;

/**
* 服务中心参数 
* 2017-03-27 12:32:04  邓纯杰
*/
@Service("xtServiceCenterParameterService")
public class XtServiceCenterParameterServiceImpl extends BaseService implements XtServiceCenterParameterService{
	@Autowired
	private XtServiceCenterParameterDao xtServiceCenterParameterDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtServiceCenterParameter> getXtServiceCenterParameterListByCondition(Map<String,Object> condition){
		try{
			return xtServiceCenterParameterDao.getXtServiceCenterParameterListByCondition(condition);
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
	public XtServiceCenterParameter getXtServiceCenterParameterById(String xt_service_center_parameter_id){
		try{
			XtServiceCenterParameter xt_Service_Center_Parameter = xtServiceCenterParameterDao.getXtServiceCenterParameterById(xt_service_center_parameter_id);
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
	public int addXtServiceCenterParameter(XtServiceCenterParameter xt_Service_Center_Parameter){
		int i = 0;
		try {
			i = xtServiceCenterParameterDao.addXtServiceCenterParameter(xt_Service_Center_Parameter);
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
	public int updateXtServiceCenterParameter(XtServiceCenterParameter xt_Service_Center_Parameter){
		int i = 0;
		try {
			i = xtServiceCenterParameterDao.updateXtServiceCenterParameter(xt_Service_Center_Parameter);
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
	public int updateXtServiceCenterParameterBySelective(XtServiceCenterParameter xt_Service_Center_Parameter){
		int i = 0;
		try {
			i = xtServiceCenterParameterDao.updateXtServiceCenterParameterBySelective(xt_Service_Center_Parameter);
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
			i = xtServiceCenterParameterDao.delXtServiceCenterParameter(condition);
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
	public int addBatchXtServiceCenterParameter(List<XtServiceCenterParameter> xt_Service_Center_ParameterList){
		int i = 0;
		try {
			i = xtServiceCenterParameterDao.addBatchXtServiceCenterParameter(xt_Service_Center_ParameterList);
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
	public int updateBatchXtServiceCenterParameter(List<XtServiceCenterParameter> xt_Service_Center_ParameterList){
		int i = 0;
		try {
			i = xtServiceCenterParameterDao.updateBatchXtServiceCenterParameter(xt_Service_Center_ParameterList);
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
	public int updateBatchXtServiceCenterParameterBySelective(List<XtServiceCenterParameter> xt_Service_Center_ParameterList){
		int i = 0;
		try {
			i = xtServiceCenterParameterDao.updateBatchXtServiceCenterParameterBySelective(xt_Service_Center_ParameterList);
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
			i = xtServiceCenterParameterDao.delXtServiceCenterParameterByForeignKey(xt_service_center_id);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
