package jehc.xtmodules.xtservice.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_Functioninfo_RightDao;
import jehc.xtmodules.xtdao.Xt_M_RDao;
import jehc.xtmodules.xtmodel.Xt_Functioninfo_Right;
import jehc.xtmodules.xtmodel.Xt_M_R;
import jehc.xtmodules.xtservice.Xt_M_RService;

/**
* 资源角色; InnoDB free: 6144 kB 
* 2015-08-04 16:27:46  邓纯杰
*/
@Service("xt_M_RService")
public class Xt_M_RServiceImpl extends BaseService implements Xt_M_RService{
	@Autowired
	private Xt_M_RDao xt_M_RDao;
	@Autowired
	private Xt_Functioninfo_RightDao xt_Functioninfo_RightDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_M_R> getXtMRListByCondition(Map<String,Object> condition){
		try{
			return xt_M_RDao.getXtMRListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_m_r_id 
	* @return
	*/
	public Xt_M_R getXtMRById(String xt_m_r_id){
		try{
			return xt_M_RDao.getXtMRById(xt_m_r_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_m_r 
	* @return
	*/
	public int addXtMR(List<Xt_M_R> xt_M_RList,List<Xt_Functioninfo_Right> xt_Functioninfo_RightList,String xt_role_id){
		int i = 0;
		try {
			if(null != xt_role_id && !"".equals(xt_role_id)){
				Map<String, Object> condition = new HashMap<String, Object>();
				condition.put("xt_role_id", xt_role_id);
				i = xt_M_RDao.delXtMR(condition);
				i = xt_Functioninfo_RightDao.delXtFunctioninfoRight(condition);
			}
			i = xt_M_RDao.addBatchXtMR(xt_M_RList);
			if(null != xt_Functioninfo_RightList && !xt_Functioninfo_RightList.isEmpty()){
				i = xt_Functioninfo_RightDao.addBatchXtFunctioninfoRight(xt_Functioninfo_RightList);
			}
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_m_r 
	* @return
	*/
	public int updateXtMR(Xt_M_R xt_M_R){
		int i = 0;
		try {
			i = xt_M_RDao.updateXtMR(xt_M_R);
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
	public int delXtMR(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_M_RDao.delXtMR(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
