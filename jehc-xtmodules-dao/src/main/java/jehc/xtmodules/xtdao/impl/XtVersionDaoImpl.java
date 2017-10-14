package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtVersionDao;
import jehc.xtmodules.xtmodel.XtVersion;

/**
* 平台版本 
* 2017-04-16 20:05:24  邓纯杰
*/
@Repository("xtVersionDao")
public class XtVersionDaoImpl  extends BaseDaoImpl implements XtVersionDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtVersion> getXtVersionListByCondition(Map<String,Object> condition){
		return (List<XtVersion>)this.getList("getXtVersionListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_version_id 
	* @return
	*/
	public XtVersion getXtVersionById(String xt_version_id){
		return (XtVersion)this.get("getXtVersionById", xt_version_id);
	}
	/**
	* 添加
	* @param xt_version 
	* @return
	*/
	public int addXtVersion(XtVersion xt_Version){
		return this.add("addXtVersion", xt_Version);
	}
	/**
	* 修改
	* @param xt_version 
	* @return
	*/
	public int updateXtVersion(XtVersion xt_Version){
		return this.update("updateXtVersion", xt_Version);
	}
	/**
	* 修改（根据动态条件）
	* @param xt_version 
	* @return
	*/
	public int updateXtVersionBySelective(XtVersion xt_Version){
		return this.update("updateXtVersionBySelective", xt_Version);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtVersion(Map<String,Object> condition){
		return this.del("delXtVersion", condition);
	}
	/**
	* 批量添加
	* @param xt_versionList 
	* @return
	*/
	public int addBatchXtVersion(List<XtVersion> xt_VersionList){
		return this.add("addBatchXtVersion", xt_VersionList);
	}
	/**
	* 批量修改
	* @param xt_versionList 
	* @return
	*/
	public int updateBatchXtVersion(List<XtVersion> xt_VersionList){
		return this.update("updateBatchXtVersion", xt_VersionList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param xt_versionList 
	* @return
	*/
	public int updateBatchXtVersionBySelective(List<XtVersion> xt_VersionList){
		return this.update("updateBatchXtVersionBySelective", xt_VersionList);
	}
}
