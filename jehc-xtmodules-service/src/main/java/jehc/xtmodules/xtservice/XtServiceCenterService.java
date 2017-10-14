package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtServiceCenter;

/**
* 服务中心 
* 2017-03-27 12:32:04  邓纯杰
*/
public interface XtServiceCenterService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtServiceCenter> getXtServiceCenterListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_service_center_id 
	* @return
	*/
	public XtServiceCenter getXtServiceCenterById(String xt_service_center_id);
	/**
	* 添加
	* @param xt_service_center 
	* @return
	*/
	public int addXtServiceCenter(XtServiceCenter xt_Service_Center);
	/**
	* 修改
	* @param xt_service_center 
	* @return
	*/
	public int updateXtServiceCenter(XtServiceCenter xt_Service_Center);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtServiceCenter(Map<String,Object> condition);
	/**
	* 批量添加
	* @param xt_service_centerList 
	* @return
	*/
	public int addBatchXtServiceCenter(List<XtServiceCenter> xt_Service_CenterList);
	/**
	* 批量修改
	* @param xt_service_centerList 
	* @return
	*/
	public int updateBatchXtServiceCenter(List<XtServiceCenter> xt_Service_CenterList);
}
