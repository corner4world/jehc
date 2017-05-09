package jehc.xtmodules.xtservice.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_DepartinfoDao;
import jehc.xtmodules.xtmodel.Xt_Departinfo;
import jehc.xtmodules.xtservice.Xt_DepartinfoService;
import jehc.xtmodules.xtservice.Xt_PostService;

/**
* 部门信息表(departInfo) 
* 2015-05-13 15:46:38  邓纯杰
*/
@Service("xt_DepartinfoService")
public class Xt_DepartinfoServiceImpl extends BaseService implements Xt_DepartinfoService{
	@Autowired
	private Xt_DepartinfoDao xt_DepartinfoDao;
	@Autowired
	private Xt_PostService xt_PostService;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Departinfo> getXtDepartinfoListByCondition(Map<String,Object> condition){
		try {
			return xt_DepartinfoDao.getXtDepartinfoListByCondition(condition);
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
	public Xt_Departinfo getXtDepartinfoById(String xt_departinfo_id){
		try {
			return xt_DepartinfoDao.getXtDepartinfoById(xt_departinfo_id);
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
	public int addXtDepartinfo(Xt_Departinfo xt_Departinfo){
		int i = 0;
		try {
			i = xt_DepartinfoDao.addXtDepartinfo(xt_Departinfo);
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
	public int updateXtDepartinfo(Xt_Departinfo xt_Departinfo){
		int i = 0;
		try {
			i = xt_DepartinfoDao.updateXtDepartinfo(xt_Departinfo);
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
				if(!xt_DepartinfoDao.getXtDepartinfoListChild(map).isEmpty()){
					aBLogs("部门业务类", "删除", "执行删除部门，编号【"+xt_departinfo_id+"】存在下级部门，不能删除");
					return 0;
				}
				//验证是否存在岗位（正常岗位）
				map = new HashMap<String, Object>();
				map.put("xt_post_isdelete", 0);
				map.put("xt_departinfo_id", xt_departinfo_id);
				if(!xt_PostService.getXtPostinfoList(map).isEmpty()){
					aBLogs("部门业务类", "删除", "执行删除部门，编号【"+xt_departinfo_id+"】存在被岗位使用中，不能删除");
					return 0;
				}
			}
			i = xt_DepartinfoDao.delXtDepartinfo(condition);
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
	public List<Xt_Departinfo> getXtDepartinfoList(){
		try {
			return xt_DepartinfoDao.getXtDepartinfoList();
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
	public List<Xt_Departinfo> getXtDepartinfoListChild(Map<String,Object> condition){
		try {
			return xt_DepartinfoDao.getXtDepartinfoListChild(condition);
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
	public List<Xt_Departinfo> getXtDepartinfoListAll(Map<String,Object> condition){
		try {
			return xt_DepartinfoDao.getXtDepartinfoListAll(condition);
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
	public List<Xt_Departinfo> queryXtDepartinfoList(Map<String,Object> condition){
		return xt_DepartinfoDao.queryXtDepartinfoList(condition);
	}
}
