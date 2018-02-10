package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtNoticeDao;
import jehc.xtmodules.xtmodel.XtNotice;
import jehc.xtmodules.xtservice.XtNoticeService;

/**
* 平台公告; InnoDB free: 6144 kB 
* 2015-08-23 17:27:58  邓纯杰
*/
@Service("xtNoticeService")
public class XtNoticeServiceImpl extends BaseService implements XtNoticeService{
	@Autowired
	private XtNoticeDao xtNoticeDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtNotice> getXtNoticeListByCondition(Map<String,Object> condition){
		try{
			return xtNoticeDao.getXtNoticeListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_notice_id 
	* @return
	*/
	public XtNotice getXtNoticeById(String xt_notice_id){
		try{
			return xtNoticeDao.getXtNoticeById(xt_notice_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_notice 
	* @return
	*/
	public int addXtNotice(XtNotice xt_Notice){
		int i = 0;
		try {
			i = xtNoticeDao.addXtNotice(xt_Notice);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_notice 
	* @return
	*/
	public int updateXtNotice(XtNotice xt_Notice){
		int i = 0;
		try {
			i = xtNoticeDao.updateXtNotice(xt_Notice);
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
	public int delXtNotice(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtNoticeDao.delXtNotice(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	 * 统计
	 * @param condition
	 * @return
	 */
	public int getXtNoticeCountByCondition(Map<String,Object> condition){
		try{
			return xtNoticeDao.getXtNoticeCountByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
