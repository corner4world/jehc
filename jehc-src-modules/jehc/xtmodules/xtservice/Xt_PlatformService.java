package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Platform;

/**
* 平台信息发布 
* 2016-08-30 22:18:44  邓纯杰
*/
public interface Xt_PlatformService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Platform> getXtPlatformListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_platform_id 
	* @return
	*/
	public Xt_Platform getXtPlatformById(String xt_platform_id);
	/**
	* 添加
	* @param xt_platform 
	* @return
	*/
	public int addXtPlatform(Xt_Platform xt_Platform);
	/**
	* 修改
	* @param xt_platform 
	* @return
	*/
	public int updateXtPlatform(Xt_Platform xt_Platform);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtPlatform(Map<String,Object> condition);
}
