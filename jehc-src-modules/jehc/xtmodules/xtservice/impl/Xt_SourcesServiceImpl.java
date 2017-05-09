package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_SourcesDao;
import jehc.xtmodules.xtmodel.Xt_Sources;
import jehc.xtmodules.xtservice.Xt_SourcesService;

/**
* 平台静态资源 
* 2016-06-16 10:34:06  邓纯杰
*/
@Service("xt_SourcesService")
public class Xt_SourcesServiceImpl extends BaseService implements Xt_SourcesService{
	@Autowired
	private Xt_SourcesDao xt_SourcesDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Sources> getXtSourcesListByCondition(Map<String,Object> condition){
		try{
			return xt_SourcesDao.getXtSourcesListByCondition(condition);
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
	public Xt_Sources getXtSourcesById(String xt_sources_id){
		try{
			return xt_SourcesDao.getXtSourcesById(xt_sources_id);
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
	public int addXtSources(Xt_Sources xt_Sources){
		int i = 0;
		try {
			i = xt_SourcesDao.addXtSources(xt_Sources);
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
	public int updateXtSources(Xt_Sources xt_Sources){
		int i = 0;
		try {
			i = xt_SourcesDao.updateXtSources(xt_Sources);
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
			i = xt_SourcesDao.delXtSources(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
