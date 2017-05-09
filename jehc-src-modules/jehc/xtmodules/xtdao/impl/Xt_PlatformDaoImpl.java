package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_PlatformDao;
import jehc.xtmodules.xtmodel.Xt_Platform;

/**
* 平台信息发布 
* 2016-08-30 22:18:44  邓纯杰
*/
@Repository("xt_PlatformDao")
public class Xt_PlatformDaoImpl  extends BaseDaoImpl implements Xt_PlatformDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Platform> getXtPlatformListByCondition(Map<String,Object> condition){
		return (List<Xt_Platform>)this.getList("getXtPlatformListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_platform_id 
	* @return
	*/
	public Xt_Platform getXtPlatformById(String xt_platform_id){
		return (Xt_Platform)this.get("getXtPlatformById", xt_platform_id);
	}
	/**
	* 添加
	* @param xt_platform 
	* @return
	*/
	public int addXtPlatform(Xt_Platform xt_Platform){
		return this.add("addXtPlatform", xt_Platform);
	}
	/**
	* 修改
	* @param xt_platform 
	* @return
	*/
	public int updateXtPlatform(Xt_Platform xt_Platform){
		return this.update("updateXtPlatform", xt_Platform);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtPlatform(Map<String,Object> condition){
		return this.del("delXtPlatform", condition);
	}
}
