package jehc.xtmodules.xtservice.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtDepartinfoDao;
import jehc.xtmodules.xtmodel.XtDepartinfo;
import jehc.xtmodules.xtservice.XtDepartinfoService;
import jehc.xtmodules.xtservice.XtPostService;

/**
* 部门信息表(departInfo) 
* 2015-05-13 15:46:38  邓纯杰
*/
@Service("xtDepartinfoService")
public class XtDepartinfoServiceImpl extends BaseService implements XtDepartinfoService{
	@Autowired
	private XtDepartinfoDao xtDepartinfoDao;
	@Autowired
	private XtPostService xtPostService;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtDepartinfo> getXtDepartinfoListByCondition(Map<String,Object> condition){
		try {
			return xtDepartinfoDao.getXtDepartinfoListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_departinfo_id 
	* @return
	*/
	public XtDepartinfo getXtDepartinfoById(String xt_departinfo_id){
		try {
			return xtDepartinfoDao.getXtDepartinfoById(xt_departinfo_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_departinfo 
	* @return
	*/
	public int addXtDepartinfo(XtDepartinfo xt_Departinfo){
		int i = 0;
		try {
			i = xtDepartinfoDao.addXtDepartinfo(xt_Departinfo);
			//统一推送
			addPushDataAuthority();
			aBLogs("部门业务类", "添加", "执行添加部门成功");
		} catch (Exception e) {
			i = 0;
			aBLogs("部门业务类", "添加", "执行修改部门失败");
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_departinfo 
	* @return
	*/
	public int updateXtDepartinfo(XtDepartinfo xt_Departinfo){
		int i = 0;
		try {
			i = xtDepartinfoDao.updateXtDepartinfo(xt_Departinfo);
			//统一推送
			addPushDataAuthority();
			aBLogs("部门业务类", "修改", "执行修改部门成功");
		} catch (Exception e) {
			i = 0;
			aBLogs("部门业务类", "修改", "执行修改部门失败");
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
	public int delXtDepartinfo(Map<String,Object> condition){
		int i = 0;
		try {
			String[] xt_departinfo_idList = (String[])condition.get("xt_departinfo_id");
			for(String xt_departinfo_id:xt_departinfo_idList){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("xt_departinfo_isdelete", 0);
				map.put("xt_departinfo_parentId", xt_departinfo_id);
				//验证下级部门是否存在（正常部门）
				if(!xtDepartinfoDao.getXtDepartinfoListChild(map).isEmpty()){
					aBLogs("部门业务类", "删除", "执行删除部门，编号【"+xt_departinfo_id+"】存在下级部门，不能删除");
					return 0;
				}
				//验证是否存在岗位（正常岗位）
				map = new HashMap<String, Object>();
				map.put("xt_post_isdelete", 0);
				map.put("xt_departinfo_id", xt_departinfo_id);
				if(!xtPostService.getXtPostinfoList(map).isEmpty()){
					aBLogs("部门业务类", "删除", "执行删除部门，编号【"+xt_departinfo_id+"】存在被岗位使用中，不能删除");
					return 0;
				}
			}
			i = xtDepartinfoDao.delXtDepartinfo(condition);
			//统一推送
			addPushDataAuthority();
			aBLogs("部门业务类", "删除", "执行删除部门成功");
		} catch (Exception e) {
			i = 0;
			aBLogs("部门业务类", "删除", "执行删除部门失败");
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	/**
	 * 部门根目录集合
	 * @return
	 */
	public List<XtDepartinfo> getXtDepartinfoList(){
		try {
			return xtDepartinfoDao.getXtDepartinfoList();
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 查找子集合
	 * @param condition
	 * @return
	 */
	public List<XtDepartinfo> getXtDepartinfoListChild(Map<String,Object> condition){
		try {
			return xtDepartinfoDao.getXtDepartinfoListChild(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 查找所有集合
	 * @param condition
	 * @return
	 */
	public List<XtDepartinfo> getXtDepartinfoListAll(Map<String,Object> condition){
		try {
			return xtDepartinfoDao.getXtDepartinfoListAll(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 根据各种情况查找集合不分页（流程设计器中处理组 发起组等使用）
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtDepartinfo> queryXtDepartinfoList(Map<String,Object> condition){
		return xtDepartinfoDao.queryXtDepartinfoList(condition);
	}
}
