package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtServiceCenterDao;
import jehc.xtmodules.xtmodel.XtServiceCenter;

/**
* 服务中心 
* 2017-03-27 12:32:04  邓纯杰
*/
@Repository("xtServiceCenterDao")
public class XtServiceCenterDaoImpl  extends BaseDaoImpl implements XtServiceCenterDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtServiceCenter> getXtServiceCenterListByCondition(Map<String,Object> condition){
		return (List<XtServiceCenter>)this.getList("getXtServiceCenterListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_service_center_id 
	* @return
	*/
	public XtServiceCenter getXtServiceCenterById(String xt_service_center_id){
		return (XtServiceCenter)this.get("getXtServiceCenterById", xt_service_center_id);
	}
	/**
	* 添加
	* @param xt_service_center 
	* @return
	*/
	public int addXtServiceCenter(XtServiceCenter xt_Service_Center){
		return this.add("addXtServiceCenter", xt_Service_Center);
	}
	/**
	* 修改
	* @param xt_service_center 
	* @return
	*/
	public int updateXtServiceCenter(XtServiceCenter xt_Service_Center){
		return this.update("updateXtServiceCenter", xt_Service_Center);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtServiceCenter(Map<String,Object> condition){
		return this.del("delXtServiceCenter", condition);
	}
	/**
	* 批量添加
	* @param xt_service_centerList 
	* @return
	*/
	public int addBatchXtServiceCenter(List<XtServiceCenter> xt_Service_CenterList){
		return this.add("addBatchXtServiceCenter", xt_Service_CenterList);
	}
	/**
	* 批量修改
	* @param xt_service_centerList 
	* @return
	*/
	public int updateBatchXtServiceCenter(List<XtServiceCenter> xt_Service_CenterList){
		return this.update("updateBatchXtServiceCenter", xt_Service_CenterList);
	}
}
