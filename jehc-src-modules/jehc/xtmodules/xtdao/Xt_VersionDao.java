package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Version;

/**
* 平台版本 
* 2017-04-16 20:05:24  邓纯杰
*/
public interface Xt_VersionDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Version> getXtVersionListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_version_id 
	* @return
	*/
	public Xt_Version getXtVersionById(String xt_version_id);
	/**
	* 添加
	* @param xt_version 
	* @return
	*/
	public int addXtVersion(Xt_Version xt_Version);
	/**
	* 修改
	* @param xt_version 
	* @return
	*/
	public int updateXtVersion(Xt_Version xt_Version);
	/**
	* 修改（根据动态条件）
	* @param xt_version 
	* @return
	*/
	public int updateXtVersionBySelective(Xt_Version xt_Version);
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
	public int addBatchXtVersion(List<Xt_Version> xt_VersionList);
	/**
	* 批量修改
	* @param xt_versionList 
	* @return
	*/
	public int updateBatchXtVersion(List<Xt_Version> xt_VersionList);
	/**
	* 批量修改（根据动态条件）
	* @param xt_versionList 
	* @return
	*/
	public int updateBatchXtVersionBySelective(List<Xt_Version> xt_VersionList);
}
