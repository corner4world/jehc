package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtVersionDao;
import jehc.xtmodules.xtmodel.XtVersion;
import jehc.xtmodules.xtservice.XtVersionService;

/**
* 平台版本 
* 2017-04-16 20:05:24  邓纯杰
*/
@Service("xtVersionService")
public class XtVersionServiceImpl extends BaseService implements XtVersionService{
	@Autowired
	private XtVersionDao xtVersionDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtVersion> getXtVersionListByCondition(Map<String,Object> condition){
		try{
			return xtVersionDao.getXtVersionListByCondition(condition);
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
	public XtVersion getXtVersionById(String xt_version_id){
		try{
			XtVersion xt_Version = xtVersionDao.getXtVersionById(xt_version_id);
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
	public int addXtVersion(XtVersion xt_Version){
		int i = 0;
		try {
			i = xtVersionDao.addXtVersion(xt_Version);
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
	public int updateXtVersion(XtVersion xt_Version){
		int i = 0;
		try {
			i = xtVersionDao.updateXtVersion(xt_Version);
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
	public int updateXtVersionBySelective(XtVersion xt_Version){
		int i = 0;
		try {
			i = xtVersionDao.updateXtVersionBySelective(xt_Version);
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
			i = xtVersionDao.delXtVersion(condition);
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
	public int addBatchXtVersion(List<XtVersion> xt_VersionList){
		int i = 0;
		try {
			i = xtVersionDao.addBatchXtVersion(xt_VersionList);
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
	public int updateBatchXtVersion(List<XtVersion> xt_VersionList){
		int i = 0;
		try {
			i = xtVersionDao.updateBatchXtVersion(xt_VersionList);
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
	public int updateBatchXtVersionBySelective(List<XtVersion> xt_VersionList){
		int i = 0;
		try {
			i = xtVersionDao.updateBatchXtVersionBySelective(xt_VersionList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
