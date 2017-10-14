package jehc.xtmodules.xtservice.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtFunctioninfoRightDao;
import jehc.xtmodules.xtdao.XtMRDao;
import jehc.xtmodules.xtmodel.XtFunctioninfoRight;
import jehc.xtmodules.xtmodel.XtMR;
import jehc.xtmodules.xtservice.XtMRService;

/**
* 资源角色; InnoDB free: 6144 kB 
* 2015-08-04 16:27:46  邓纯杰
*/
@Service("xtMRService")
public class XtMRServiceImpl extends BaseService implements XtMRService{
	@Autowired
	private XtMRDao xtMRDao;
	@Autowired
	private XtFunctioninfoRightDao xtFunctioninfoRightDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtMR> getXtMRListByCondition(Map<String,Object> condition){
		try{
			return xtMRDao.getXtMRListByCondition(condition);
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
	public XtMR getXtMRById(String xt_m_r_id){
		try{
			return xtMRDao.getXtMRById(xt_m_r_id);
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
	public int addXtMR(List<XtMR> xt_M_RList,List<XtFunctioninfoRight> xt_Functioninfo_RightList,String xt_role_id){
		int i = 0;
		try {
			if(null != xt_role_id && !"".equals(xt_role_id)){
				Map<String, Object> condition = new HashMap<String, Object>();
				condition.put("xt_role_id", xt_role_id);
				i = xtMRDao.delXtMR(condition);
				i = xtFunctioninfoRightDao.delXtFunctioninfoRight(condition);
			}
			i = xtMRDao.addBatchXtMR(xt_M_RList);
			if(null != xt_Functioninfo_RightList && !xt_Functioninfo_RightList.isEmpty()){
				i = xtFunctioninfoRightDao.addBatchXtFunctioninfoRight(xt_Functioninfo_RightList);
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
	public int updateXtMR(XtMR xt_M_R){
		int i = 0;
		try {
			i = xtMRDao.updateXtMR(xt_M_R);
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
			i = xtMRDao.delXtMR(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
