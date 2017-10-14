package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtServiceCenterParameter;

/**
* 服务中心参数 
* 2017-03-27 12:32:04  邓纯杰
*/
public interface XtServiceCenterParameterService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtServiceCenterParameter> getXtServiceCenterParameterListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_service_center_parameter_id 
	* @return
	*/
	public XtServiceCenterParameter getXtServiceCenterParameterById(String xt_service_center_parameter_id);
	/**
	* 添加
	* @param xt_service_center_parameter 
	* @return
	*/
	public int addXtServiceCenterParameter(XtServiceCenterParameter xt_Service_Center_Parameter);
	/**
	* 修改
	* @param xt_service_center_parameter 
	* @return
	*/
	public int updateXtServiceCenterParameter(XtServiceCenterParameter xt_Service_Center_Parameter);
	/**
	 * 修改（根据动态条件）
	 * @param xt_Service_Center_Parameter
	 * @return
	 */
	public int updateXtServiceCenterParameterBySelective(XtServiceCenterParameter xt_Service_Center_Parameter);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtServiceCenterParameter(Map<String,Object> condition);
	/**
	* 批量添加
	* @param xt_service_center_parameterList 
	* @return
	*/
	public int addBatchXtServiceCenterParameter(List<XtServiceCenterParameter> xt_Service_Center_ParameterList);
	/**
	* 批量修改
	* @param xt_service_center_parameterList 
	* @return
	*/
	public int updateBatchXtServiceCenterParameter(List<XtServiceCenterParameter> xt_Service_Center_ParameterList);
	/**
	 * 批量修改（根据动态条件）
	 * @param xt_Service_Center_ParameterList
	 * @return
	 */
	public int updateBatchXtServiceCenterParameterBySelective(List<XtServiceCenterParameter> xt_Service_Center_ParameterList);
	/**
	* 根据外键删除
	* @param xt_service_center_id
	* @return
	*/
	public int delXtServiceCenterParameterByForeignKey(String xt_service_center_id);
}
