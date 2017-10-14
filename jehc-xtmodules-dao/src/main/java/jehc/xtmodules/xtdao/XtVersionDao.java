package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtVersion;

/**
* 平台版本 
* 2017-04-16 20:05:24  邓纯杰
*/
public interface XtVersionDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtVersion> getXtVersionListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_version_id 
	* @return
	*/
	public XtVersion getXtVersionById(String xt_version_id);
	/**
	* 添加
	* @param xt_version 
	* @return
	*/
	public int addXtVersion(XtVersion xt_Version);
	/**
	* 修改
	* @param xt_version 
	* @return
	*/
	public int updateXtVersion(XtVersion xt_Version);
	/**
	* 修改（根据动态条件）
	* @param xt_version 
	* @return
	*/
	public int updateXtVersionBySelective(XtVersion xt_Version);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtVersion(Map<String,Object> condition);
	/**
	* 批量添加
	* @param xt_versionList 
	* @return
	*/
	public int addBatchXtVersion(List<XtVersion> xt_VersionList);
	/**
	* 批量修改
	* @param xt_versionList 
	* @return
	*/
	public int updateBatchXtVersion(List<XtVersion> xt_VersionList);
	/**
	* 批量修改（根据动态条件）
	* @param xt_versionList 
	* @return
	*/
	public int updateBatchXtVersionBySelective(List<XtVersion> xt_VersionList);
}
