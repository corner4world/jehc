package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_Service_Center_ParameterDao;
import jehc.xtmodules.xtmodel.Xt_Service_Center_Parameter;

/**
* 服务中心参数 
* 2017-03-27 12:32:04  邓纯杰
*/
@Repository("xt_Service_Center_ParameterDao")
public class Xt_Service_Center_ParameterDaoImpl  extends BaseDaoImpl implements Xt_Service_Center_ParameterDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Service_Center_Parameter> getXtServiceCenterParameterListByCondition(Map<String,Object> condition){
		return (List<Xt_Service_Center_Parameter>)this.getList("getXtServiceCenterParameterListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_service_center_parameter_id 
	* @return
	*/
	public Xt_Service_Center_Parameter getXtServiceCenterParameterById(String xt_service_center_parameter_id){
		return (Xt_Service_Center_Parameter)this.get("getXtServiceCenterParameterById", xt_service_center_parameter_id);
	}
	/**
	* 添加
	* @param xt_service_center_parameter 
	* @return
	*/
	public int addXtServiceCenterParameter(Xt_Service_Center_Parameter xt_Service_Center_Parameter){
		return this.add("addXtServiceCenterParameter", xt_Service_Center_Parameter);
	}
	/**
	* 修改
	* @param xt_service_center_parameter 
	* @return
	*/
	public int updateXtServiceCenterParameter(Xt_Service_Center_Parameter xt_Service_Center_Parameter){
		return this.update("updateXtServiceCenterParameter", xt_Service_Center_Parameter);
	}
	/**
	 * 修改（根据动态条件）
	 * @param xt_Service_Center_Parameter
	 * @return
	 */
	public int updateXtServiceCenterParameterBySelective(Xt_Service_Center_Parameter xt_Service_Center_Parameter){
		return this.update("updateXtServiceCenterParameterBySelective", xt_Service_Center_Parameter);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtServiceCenterParameter(Map<String,Object> condition){
		return this.del("delXtServiceCenterParameter", condition);
	}
	/**
	* 批量添加
	* @param xt_service_center_parameterList 
	* @return
	*/
	public int addBatchXtServiceCenterParameter(List<Xt_Service_Center_Parameter> xt_Service_Center_ParameterList){
		return this.add("addBatchXtServiceCenterParameter", xt_Service_Center_ParameterList);
	}
	/**
	* 批量修改
	* @param xt_service_center_parameterList 
	* @return
	*/
	public int updateBatchXtServiceCenterParameter(List<Xt_Service_Center_Parameter> xt_Service_Center_ParameterList){
		return this.update("updateBatchXtServiceCenterParameter", xt_Service_Center_ParameterList);
	}
	/**
	 * 批量修改（根据动态条件）
	 * @param xt_Service_Center_ParameterList
	 * @return
	 */
	public int updateBatchXtServiceCenterParameterBySelective(List<Xt_Service_Center_Parameter> xt_Service_Center_ParameterList){
		return this.update("updateBatchXtServiceCenterParameterBySelective", xt_Service_Center_ParameterList);
	}
	/**
	* 根据外键删除方法
	* @param xt_service_center_id
	* @return
	*/
	public int delXtServiceCenterParameterByForeignKey(String xt_service_center_id){
		return this.del("delXtServiceCenterParameterByForeignKey", xt_service_center_id);
	}
}
