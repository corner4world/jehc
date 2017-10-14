package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtPlatformDao;
import jehc.xtmodules.xtmodel.XtPlatform;

/**
* 平台信息发布 
* 2016-08-30 22:18:44  邓纯杰
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
	public int addXtPlatform(XtPlatform xt_Platform){
		return this.add("addXtPlatform", xt_Platform);
	}
	/**
	* 修改
	* @param xt_platform 
	* @return
	*/
	public int updateXtPlatform(XtPlatform xt_Platform){
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
