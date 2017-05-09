package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_Service_CenterDao;
import jehc.xtmodules.xtmodel.Xt_Service_Center;

/**
* 服务中心 
* 2017-03-27 12:32:04  邓纯杰
*/
@Repository("xt_Service_CenterDao")
public class Xt_Service_CenterDaoImpl  extends BaseDaoImpl implements Xt_Service_CenterDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Service_Center> getXtServiceCenterListByCondition(Map<String,Object> condition){
		return (List<Xt_Service_Center>)this.getList("getXtServiceCenterListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_service_center_id 
	* @return
	*/
	public Xt_Service_Center getXtServiceCenterById(String xt_service_center_id){
		return (Xt_Service_Center)this.get("getXtServiceCenterById", xt_service_center_id);
	}
	/**
	* 添加
	* @param xt_service_center 
	* @return
	*/
	public int addXtServiceCenter(Xt_Service_Center xt_Service_Center){
		return this.add("addXtServiceCenter", xt_Service_Center);
	}
	/**
	* 修改
	* @param xt_service_center 
	* @return
	*/
	public int updateXtServiceCenter(Xt_Service_Center xt_Service_Center){
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
	public int addBatchXtServiceCenter(List<Xt_Service_Center> xt_Service_CenterList){
		return this.add("addBatchXtServiceCenter", xt_Service_CenterList);
	}
	/**
	* 批量修改
	* @param xt_service_centerList 
	* @return
	*/
	public int updateBatchXtServiceCenter(List<Xt_Service_Center> xt_Service_CenterList){
		return this.update("updateBatchXtServiceCenter", xt_Service_CenterList);
	}
}
