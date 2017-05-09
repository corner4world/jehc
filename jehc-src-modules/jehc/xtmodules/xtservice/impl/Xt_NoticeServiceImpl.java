package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_NoticeDao;
import jehc.xtmodules.xtmodel.Xt_Notice;
import jehc.xtmodules.xtservice.Xt_NoticeService;

/**
* 平台公告; InnoDB free: 6144 kB 
* 2015-08-23 17:27:58  邓纯杰
*/
@Service("xt_NoticeService")
public class Xt_NoticeServiceImpl extends BaseService implements Xt_NoticeService{
	@Autowired
	private Xt_NoticeDao xt_NoticeDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Notice> getXtNoticeListByCondition(Map<String,Object> condition){
		try{
			return xt_NoticeDao.getXtNoticeListByCondition(condition);
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
	public Xt_Notice getXtNoticeById(String xt_notice_id){
		try{
			return xt_NoticeDao.getXtNoticeById(xt_notice_id);
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
	public int addXtNotice(Xt_Notice xt_Notice){
		int i = 0;
		try {
			i = xt_NoticeDao.addXtNotice(xt_Notice);
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
	public int updateXtNotice(Xt_Notice xt_Notice){
		int i = 0;
		try {
			i = xt_NoticeDao.updateXtNotice(xt_Notice);
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
			i = xt_NoticeDao.delXtNotice(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
