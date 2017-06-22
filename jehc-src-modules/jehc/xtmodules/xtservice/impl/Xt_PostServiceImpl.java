package jehc.xtmodules.xtservice.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_PostDao;
import jehc.xtmodules.xtmodel.Xt_Post;
import jehc.xtmodules.xtservice.Xt_PostService;

/**
* 用户岗位表(xt_post) 
* 2015-05-13 16:48:11  邓纯杰
*/
@Service("xt_PostService")
public class Xt_PostServiceImpl extends BaseService implements Xt_PostService{
	@Autowired
	private Xt_PostDao xt_PostDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Post> getXtPostListByCondition(Map<String,Object> condition){
		try {
			return xt_PostDao.getXtPostListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_post_id 
	* @return
	*/
	public Xt_Post getXtPostById(String xt_post_id){
		try {
			return xt_PostDao.getXtPostById(xt_post_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_post 
	* @return
	*/
	public int addXtPost(Xt_Post xt_Post){
		int i = 0;
		try {
			i = xt_PostDao.addXtPost(xt_Post);
			//统一推送
			addPushDataAuthority();
			aBLogs("岗位业务类", "添加", "添加岗位成功");
		} catch (Exception e) {
			i = 0;
			aBLogs("岗位业务类", "添加", "添加岗位失败");
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_post 
	* @return
	*/
	public int updateXtPost(Xt_Post xt_Post){
		int i = 0;
		try {
			i = xt_PostDao.updateXtPost(xt_Post);
			//统一推送
			addPushDataAuthority();
			aBLogs("岗位业务类", "修改", "修改岗位成功");
		} catch (Exception e) {
			i = 0;
			aBLogs("岗位业务类", "修改", "修改岗位失败");
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
	public int delXtPost(Map<String,Object> condition){
		int i = 0;
		try {
			String[] xt_post_idList = (String[])condition.get("xt_post_id");
			//验证下级岗位是否存在
			for(String xt_post_id:xt_post_idList){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("xt_post_parentId", xt_post_id);
				map.put("xt_post_isdelete", 0);
				if(!xt_PostDao.getXtPostListChild(map).isEmpty()){
					aBLogs("岗位业务类", "删除", "执行删除岗位，编号【"+xt_post_id+"】存在下级岗位，不能删除");
					return 0;
				}
			}
			i = xt_PostDao.delXtPost(condition);
			//统一推送
			addPushDataAuthority();
			aBLogs("岗位业务类", "删除", "删除岗位成功");
		} catch (Exception e) {
			i = 0;
			aBLogs("岗位业务类", "删除", "删除岗位失败");
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	
	/**
	 * 岗位根目录集合
	 * @return
	 */
	public List<Xt_Post> getXtPostinfoList(Map<String,Object> condition){
		return xt_PostDao.getXtPostinfoList(condition);
	}
	
	/**
	 * 查找子集合
	 * @return
	 */
	public List<Xt_Post> getXtPostListChild(Map<String,Object> condition){
		try {
			return xt_PostDao.getXtPostListChild(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	
	/**
	 * 查找所有集合
	 * @return
	 */
	public List<Xt_Post> getXtPostListAll(Map<String,Object> condition){
		try {
			return xt_PostDao.getXtPostListAll(condition);
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
	public List<Xt_Post> getXtPostList(Map<String,Object> condition){
		return xt_PostDao.getXtPostList(condition);
	}
}
