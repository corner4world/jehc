package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_Ip_FrozenDao;
import jehc.xtmodules.xtmodel.Xt_Ip_Frozen;
import jehc.xtmodules.xtservice.Xt_Ip_FrozenService;

/**
* 平台IP冻结账户 
* 2016-02-29 10:41:23  邓纯杰
*/
@Service("xt_Ip_FrozenService")
public class Xt_Ip_FrozenServiceImpl extends BaseService implements Xt_Ip_FrozenService{
	@Autowired
	private Xt_Ip_FrozenDao xt_Ip_FrozenDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Ip_Frozen> getXtIpFrozenListByCondition(Map<String,Object> condition){
		try{
			return xt_Ip_FrozenDao.getXtIpFrozenListByCondition(condition);
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
	public Xt_Ip_Frozen getXtIpFrozenById(String xt_ip_frozen_id){
		try{
			return xt_Ip_FrozenDao.getXtIpFrozenById(xt_ip_frozen_id);
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
	public int addXtIpFrozen(Xt_Ip_Frozen xt_Ip_Frozen){
		int i = 0;
		try {
			xt_Ip_FrozenDao.addXtIpFrozen(xt_Ip_Frozen);
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
	public int updateXtIpFrozen(Xt_Ip_Frozen xt_Ip_Frozen){
		int i = 0;
		try {
			xt_Ip_FrozenDao.updateXtIpFrozen(xt_Ip_Frozen);
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
			xt_Ip_FrozenDao.delXtIpFrozen(condition);
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
	public List<Xt_Ip_Frozen> getXtIpFrozenListAllByCondition(Map<String,Object> condition){
		try{
			return xt_Ip_FrozenDao.getXtIpFrozenListAllByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
