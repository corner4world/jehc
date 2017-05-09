package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_KwordsDao;
import jehc.xtmodules.xtmodel.Xt_Kwords;
import jehc.xtmodules.xtservice.Xt_KwordsService;

/**
* 关键词（敏感词） 
* 2016-10-08 15:03:41  邓纯杰
*/
@Service("xt_KwordsService")
public class Xt_KwordsServiceImpl extends BaseService implements Xt_KwordsService{
	@Autowired
	private Xt_KwordsDao xt_KwordsDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Kwords> getXtKwordsListByCondition(Map<String,Object> condition){
		try{
			return xt_KwordsDao.getXtKwordsListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_kwords_id 
	* @return
	*/
	public Xt_Kwords getXtKwordsById(String xt_kwords_id){
		try{
			return xt_KwordsDao.getXtKwordsById(xt_kwords_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_kwords 
	* @return
	*/
	public int addXtKwords(Xt_Kwords xt_Kwords){
		int i = 0;
		try {
			i = xt_KwordsDao.addXtKwords(xt_Kwords);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_kwords 
	* @return
	*/
	public int updateXtKwords(Xt_Kwords xt_Kwords){
		int i = 0;
		try {
			i = xt_KwordsDao.updateXtKwords(xt_Kwords);
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
	public int delXtKwords(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_KwordsDao.delXtKwords(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param xt_kwordsList 
	* @return
	*/
	public int addBatchXtKwords(List<Xt_Kwords> xt_KwordsList){
		int i = 0;
		try {
			i = xt_KwordsDao.addBatchXtKwords(xt_KwordsList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param xt_kwordsList 
	* @return
	*/
	public int updateBatchXtKwords(List<Xt_Kwords> xt_KwordsList){
		int i = 0;
		try {
			i = xt_KwordsDao.updateBatchXtKwords(xt_KwordsList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
