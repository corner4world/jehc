package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_VersionDao;
import jehc.xtmodules.xtmodel.Xt_Version;
import jehc.xtmodules.xtservice.Xt_VersionService;

/**
* 平台版本 
* 2017-04-16 20:05:24  邓纯杰
*/
@Service("xt_VersionService")
public class Xt_VersionServiceImpl extends BaseService implements Xt_VersionService{
	@Autowired
	private Xt_VersionDao xt_VersionDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Version> getXtVersionListByCondition(Map<String,Object> condition){
		try{
			return xt_VersionDao.getXtVersionListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_version_id 
	* @return
	*/
	public Xt_Version getXtVersionById(String xt_version_id){
		try{
			Xt_Version xt_Version = xt_VersionDao.getXtVersionById(xt_version_id);
			return xt_Version;
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_version 
	* @return
	*/
	public int addXtVersion(Xt_Version xt_Version){
		int i = 0;
		try {
			i = xt_VersionDao.addXtVersion(xt_Version);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_version 
	* @return
	*/
	public int updateXtVersion(Xt_Version xt_Version){
		int i = 0;
		try {
			i = xt_VersionDao.updateXtVersion(xt_Version);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param xt_version 
	* @return
	*/
	public int updateXtVersionBySelective(Xt_Version xt_Version){
		int i = 0;
		try {
			i = xt_VersionDao.updateXtVersionBySelective(xt_Version);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtVersion(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_VersionDao.delXtVersion(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param xt_versionList 
	* @return
	*/
	public int addBatchXtVersion(List<Xt_Version> xt_VersionList){
		int i = 0;
		try {
			i = xt_VersionDao.addBatchXtVersion(xt_VersionList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param xt_versionList 
	* @return
	*/
	public int updateBatchXtVersion(List<Xt_Version> xt_VersionList){
		int i = 0;
		try {
			i = xt_VersionDao.updateBatchXtVersion(xt_VersionList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param xt_versionList 
	* @return
	*/
	public int updateBatchXtVersionBySelective(List<Xt_Version> xt_VersionList){
		int i = 0;
		try {
			i = xt_VersionDao.updateBatchXtVersionBySelective(xt_VersionList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
