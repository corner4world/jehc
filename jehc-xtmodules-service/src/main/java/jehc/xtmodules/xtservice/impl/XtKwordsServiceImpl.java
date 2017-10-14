package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtKwordsDao;
import jehc.xtmodules.xtmodel.XtKwords;
import jehc.xtmodules.xtservice.XtKwordsService;

/**
* 关键词（敏感词） 
* 2016-10-08 15:03:41  邓纯杰
*/
@Service("xtKwordsService")
public class XtKwordsServiceImpl extends BaseService implements XtKwordsService{
	@Autowired
	private XtKwordsDao xtKwordsDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtKwords> getXtKwordsListByCondition(Map<String,Object> condition){
		try{
			return xtKwordsDao.getXtKwordsListByCondition(condition);
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
	public XtKwords getXtKwordsById(String xt_kwords_id){
		try{
			return xtKwordsDao.getXtKwordsById(xt_kwords_id);
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
	public int addXtKwords(XtKwords xt_Kwords){
		int i = 0;
		try {
			i = xtKwordsDao.addXtKwords(xt_Kwords);
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
	public int updateXtKwords(XtKwords xt_Kwords){
		int i = 0;
		try {
			i = xtKwordsDao.updateXtKwords(xt_Kwords);
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
			i = xtKwordsDao.delXtKwords(condition);
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
	public int addBatchXtKwords(List<XtKwords> xt_KwordsList){
		int i = 0;
		try {
			i = xtKwordsDao.addBatchXtKwords(xt_KwordsList);
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
	public int updateBatchXtKwords(List<XtKwords> xt_KwordsList){
		int i = 0;
		try {
			i = xtKwordsDao.updateBatchXtKwords(xt_KwordsList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
