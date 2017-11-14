package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtmodel.XtPlatform;

/**
* 平台信息发布 
* 2017-11-13 15:15:38  邓纯杰
*/
public interface XtPlatformService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtPlatform> getXtPlatformListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_platform_id 
	* @return
	*/
	public XtPlatform getXtPlatformById(String xt_platform_id);
	/**
	* 添加
	* @param xt_platform 
	* @return
	*/
	public int addXtPlatform(XtPlatform xtPlatform);
	/**
	* 修改
	* @param xt_platform 
	* @return
	*/
	public int updateXtPlatform(XtPlatform xtPlatform);
	/**
	* 修改（根据动态条件）
	* @param xt_platform 
	* @return
	*/
	public int updateXtPlatformBySelective(XtPlatform xtPlatform);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtPlatform(Map<String,Object> condition);
	/**
	* 批量添加
	* @param xt_platformList 
	* @return
	*/
	public int addBatchXtPlatform(List<XtPlatform> xtPlatformList);
	/**
	* 批量修改
	* @param xt_platformList 
	* @return
	*/
	public int updateBatchXtPlatform(List<XtPlatform> xtPlatformList);
	/**
	* 批量修改（根据动态条件）
	* @param xt_platformList 
	* @return
	*/
	public int updateBatchXtPlatformBySelective(List<XtPlatform> xtPlatformList);
}
