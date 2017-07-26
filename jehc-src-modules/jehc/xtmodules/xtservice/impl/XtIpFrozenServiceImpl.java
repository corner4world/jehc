package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtIpFrozenDao;
import jehc.xtmodules.xtmodel.XtIpFrozen;
import jehc.xtmodules.xtservice.XtIpFrozenService;

/**
* 平台IP冻结账户 
* 2016-02-29 10:41:23  邓纯杰
*/
@Service("xtIpFrozenService")
public class XtIpFrozenServiceImpl extends BaseService implements XtIpFrozenService{
	@Autowired
	private XtIpFrozenDao xtIpFrozenDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtIpFrozen> getXtIpFrozenListByCondition(Map<String,Object> condition){
		try{
			return xtIpFrozenDao.getXtIpFrozenListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_ip_frozen_id 
	* @return
	*/
	public XtIpFrozen getXtIpFrozenById(String xt_ip_frozen_id){
		try{
			return xtIpFrozenDao.getXtIpFrozenById(xt_ip_frozen_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_ip_frozen 
	* @return
	*/
	public int addXtIpFrozen(XtIpFrozen xt_Ip_Frozen){
		int i = 0;
		try {
			xtIpFrozenDao.addXtIpFrozen(xt_Ip_Frozen);
			i = 1;
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_ip_frozen 
	* @return
	*/
	public int updateXtIpFrozen(XtIpFrozen xt_Ip_Frozen){
		int i = 0;
		try {
			xtIpFrozenDao.updateXtIpFrozen(xt_Ip_Frozen);
			i = 1;
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
	public int delXtIpFrozen(Map<String,Object> condition){
		int i = 0;
		try {
			xtIpFrozenDao.delXtIpFrozen(condition);
			i = 1;
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	/**
	 * 获取所有集合
	 * @param condition
	 * @return
	 */
	public List<XtIpFrozen> getXtIpFrozenListAllByCondition(Map<String,Object> condition){
		try{
			return xtIpFrozenDao.getXtIpFrozenListAllByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
