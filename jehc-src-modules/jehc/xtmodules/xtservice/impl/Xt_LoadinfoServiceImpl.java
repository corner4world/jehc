package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_LoadinfoDao;
import jehc.xtmodules.xtmodel.Xt_Loadinfo;
import jehc.xtmodules.xtservice.Xt_LoadinfoService;

/**
* 页面加载信息 
* 2015-05-13 21:20:57  邓纯杰
*/
@Service("xt_LoadinfoService")
public class Xt_LoadinfoServiceImpl extends BaseService implements Xt_LoadinfoService{
	@Autowired
	private Xt_LoadinfoDao xt_LoadinfoDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Loadinfo> getXtLoadinfoListByCondition(Map<String,Object> condition){
		try {
			return xt_LoadinfoDao.getXtLoadinfoListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_loadinfo_id 
	* @return
	*/
	public Xt_Loadinfo getXtLoadinfoById(String xt_loadinfo_id){
		try {
			return xt_LoadinfoDao.getXtLoadinfoById(xt_loadinfo_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_loadinfo 
	* @return
	*/
	public int addXtLoadinfo(Xt_Loadinfo xt_Loadinfo){
		int i = 0;
		try {
			i = xt_LoadinfoDao.addXtLoadinfo(xt_Loadinfo);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_loadinfo 
	* @return
	*/
	public int updateXtLoadinfo(Xt_Loadinfo xt_Loadinfo){
		int i = 0;
		try {
			i = xt_LoadinfoDao.updateXtLoadinfo(xt_Loadinfo);
		} catch (Exception e) {
			i = 0;
			/**方案二加上下面这句话的意思是手动回滚:如果该方法中有多个方法只要有一个发生异常则全部回滚**/
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtLoadinfo(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_LoadinfoDao.delXtLoadinfo(condition);
		} catch (Exception e) {
			i = 0;
			e.printStackTrace();
			/**方案二加上下面这句话的意思是手动回滚:如果该方法中有多个方法只要有一个发生异常则全部回滚**/
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return i;
	}
	/**
	 * 分组统计并平均值算法
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Loadinfo> getXtLoadingGroupList(){
		try {
			return xt_LoadinfoDao.getXtLoadingGroupList();
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
