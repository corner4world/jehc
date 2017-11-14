package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtPlatformDao;
import jehc.xtmodules.xtmodel.XtPlatform;

/**
* 平台信息发布 
* 2017-11-13 15:15:38  邓纯杰
*/
@Repository("xtPlatformDao")
public class XtPlatformDaoImpl  extends BaseDaoImpl implements XtPlatformDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtPlatform> getXtPlatformListByCondition(Map<String,Object> condition){
		return (List<XtPlatform>)this.getList("getXtPlatformListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_platform_id 
	* @return
	*/
	public XtPlatform getXtPlatformById(String xt_platform_id){
		return (XtPlatform)this.get("getXtPlatformById", xt_platform_id);
	}
	/**
	* 添加
	* @param xt_platform 
	* @return
	*/
	public int addXtPlatform(XtPlatform xtPlatform){
		return this.add("addXtPlatform", xtPlatform);
	}
	/**
	* 修改
	* @param xt_platform 
	* @return
	*/
	public int updateXtPlatform(XtPlatform xtPlatform){
		return this.update("updateXtPlatform", xtPlatform);
	}
	/**
	* 修改（根据动态条件）
	* @param xt_platform 
	* @return
	*/
	public int updateXtPlatformBySelective(XtPlatform xtPlatform){
		return this.update("updateXtPlatformBySelective", xtPlatform);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtPlatform(Map<String,Object> condition){
		return this.del("delXtPlatform", condition);
	}
	/**
	* 批量添加
	* @param xt_platformList 
	* @return
	*/
	public int addBatchXtPlatform(List<XtPlatform> xtPlatformList){
		return this.add("addBatchXtPlatform", xtPlatformList);
	}
	/**
	* 批量修改
	* @param xt_platformList 
	* @return
	*/
	public int updateBatchXtPlatform(List<XtPlatform> xtPlatformList){
		return this.update("updateBatchXtPlatform", xtPlatformList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param xt_platformList 
	* @return
	*/
	public int updateBatchXtPlatformBySelective(List<XtPlatform> xtPlatformList){
		return this.update("updateBatchXtPlatformBySelective", xtPlatformList);
	}
}
