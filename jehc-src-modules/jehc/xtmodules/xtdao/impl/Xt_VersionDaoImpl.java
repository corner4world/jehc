package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_VersionDao;
import jehc.xtmodules.xtmodel.Xt_Version;

/**
* 平台版本 
* 2017-04-16 20:05:24  邓纯杰
*/
@Repository("xt_VersionDao")
public class Xt_VersionDaoImpl  extends BaseDaoImpl implements Xt_VersionDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Version> getXtVersionListByCondition(Map<String,Object> condition){
		return (List<Xt_Version>)this.getList("getXtVersionListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_version_id 
	* @return
	*/
	public Xt_Version getXtVersionById(String xt_version_id){
		return (Xt_Version)this.get("getXtVersionById", xt_version_id);
	}
	/**
	* 添加
	* @param xt_version 
	* @return
	*/
	public int addXtVersion(Xt_Version xt_Version){
		return this.add("addXtVersion", xt_Version);
	}
	/**
	* 修改
	* @param xt_version 
	* @return
	*/
	public int updateXtVersion(Xt_Version xt_Version){
		return this.update("updateXtVersion", xt_Version);
	}
	/**
	* 修改（根据动态条件）
	* @param xt_version 
	* @return
	*/
	public int updateXtVersionBySelective(Xt_Version xt_Version){
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
	public int addBatchXtVersion(List<Xt_Version> xt_VersionList){
		return this.add("addBatchXtVersion", xt_VersionList);
	}
	/**
	* 批量修改
	* @param xt_versionList 
	* @return
	*/
	public int updateBatchXtVersion(List<Xt_Version> xt_VersionList){
		return this.update("updateBatchXtVersion", xt_VersionList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param xt_versionList 
	* @return
	*/
	public int updateBatchXtVersionBySelective(List<Xt_Version> xt_VersionList){
		return this.update("updateBatchXtVersionBySelective", xt_VersionList);
	}
}
