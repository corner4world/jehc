package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtLoadinfoDao;
import jehc.xtmodules.xtmodel.XtLoadinfo;
import jehc.xtmodules.xtservice.XtLoadinfoService;

/**
* 页面加载信息 
* 2015-05-13 21:20:57  邓纯杰
*/
@Service("xtLoadinfoService")
public class XtLoadinfoServiceImpl extends BaseService implements XtLoadinfoService{
	@Autowired
	private XtLoadinfoDao xtLoadinfoDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtLoadinfo> getXtLoadinfoListByCondition(Map<String,Object> condition){
		try {
			return xtLoadinfoDao.getXtLoadinfoListByCondition(condition);
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
	public XtLoadinfo getXtLoadinfoById(String xt_loadinfo_id){
		try {
			return xtLoadinfoDao.getXtLoadinfoById(xt_loadinfo_id);
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
	public int addXtLoadinfo(XtLoadinfo xt_Loadinfo){
		int i = 0;
		try {
			i = xtLoadinfoDao.addXtLoadinfo(xt_Loadinfo);
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
	public int updateXtLoadinfo(XtLoadinfo xt_Loadinfo){
		int i = 0;
		try {
			i = xtLoadinfoDao.updateXtLoadinfo(xt_Loadinfo);
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
			i = xtLoadinfoDao.delXtLoadinfo(condition);
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
	public List<XtLoadinfo> getXtLoadingGroupList(){
		try {
			return xtLoadinfoDao.getXtLoadingGroupList();
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
