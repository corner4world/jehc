package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtSourcesDao;
import jehc.xtmodules.xtmodel.XtSources;
import jehc.xtmodules.xtservice.XtSourcesService;

/**
* 平台静态资源 
* 2016-06-16 10:34:06  邓纯杰
*/
@Service("xtSourcesService")
public class XtSourcesServiceImpl extends BaseService implements XtSourcesService{
	@Autowired
	private XtSourcesDao xtSourcesDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtSources> getXtSourcesListByCondition(Map<String,Object> condition){
		try{
			return xtSourcesDao.getXtSourcesListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_sources_id 
	* @return
	*/
	public XtSources getXtSourcesById(String xt_sources_id){
		try{
			return xtSourcesDao.getXtSourcesById(xt_sources_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_sources 
	* @return
	*/
	public int addXtSources(XtSources xt_Sources){
		int i = 0;
		try {
			i = xtSourcesDao.addXtSources(xt_Sources);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_sources 
	* @return
	*/
	public int updateXtSources(XtSources xt_Sources){
		int i = 0;
		try {
			i = xtSourcesDao.updateXtSources(xt_Sources);
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
	public int delXtSources(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtSourcesDao.delXtSources(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
