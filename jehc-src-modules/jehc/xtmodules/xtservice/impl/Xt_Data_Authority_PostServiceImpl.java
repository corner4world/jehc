package jehc.xtmodules.xtservice.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.xtmodules.xtservice.Xt_Data_Authority_PostService;
import jehc.xtmodules.xtdao.Xt_Data_AuthorityDao;
import jehc.xtmodules.xtdao.Xt_Data_Authority_PostDao;
import jehc.xtmodules.xtmodel.Xt_Data_Authority_Post;

/**
* 数据权限按岗位设置 
* 2017-06-20 14:37:16  邓纯杰
*/
@Service("xt_Data_Authority_PostService")
public class Xt_Data_Authority_PostServiceImpl extends BaseService implements Xt_Data_Authority_PostService{
	@Autowired
	private Xt_Data_Authority_PostDao xt_Data_Authority_PostDao;
	@Autowired
	private Xt_Data_AuthorityDao xt_Data_AuthorityDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Data_Authority_Post> getXtDataAuthorityPostListByCondition(Map<String,Object> condition){
		try{
			return xt_Data_Authority_PostDao.getXtDataAuthorityPostListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_data_authority_post_id 
	* @return
	*/
	public Xt_Data_Authority_Post getXtDataAuthorityPostById(String xt_data_authority_post_id){
		try{
			Xt_Data_Authority_Post xt_Data_Authority_Post = xt_Data_Authority_PostDao.getXtDataAuthorityPostById(xt_data_authority_post_id);
			return xt_Data_Authority_Post;
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_data_authority_post 
	* @return
	*/
	public int addXtDataAuthorityPost(Xt_Data_Authority_Post xt_Data_Authority_Post){
		int i = 0;
		try {
			i = xt_Data_Authority_PostDao.addXtDataAuthorityPost(xt_Data_Authority_Post);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_data_authority_post 
	* @return
	*/
	public int updateXtDataAuthorityPost(Xt_Data_Authority_Post xt_Data_Authority_Post){
		int i = 0;
		try {
			i = xt_Data_Authority_PostDao.updateXtDataAuthorityPost(xt_Data_Authority_Post);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param xt_data_authority_post 
	* @return
	*/
	public int updateXtDataAuthorityPostBySelective(Xt_Data_Authority_Post xt_Data_Authority_Post){
		int i = 0;
		try {
			i = xt_Data_Authority_PostDao.updateXtDataAuthorityPostBySelective(xt_Data_Authority_Post);
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
	public int delXtDataAuthorityPost(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_Data_Authority_PostDao.delXtDataAuthorityPost(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	 * 删除集合根据拥有者及菜单编号
	 * @param condition
	 * @return
	 */
	public int delXtDataAuthorityPostList(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_Data_Authority_PostDao.delXtDataAuthorityPostList(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	 * 根据情况删除
	 * @param condition
	 * @return
	 */
	public int delXtDataAuthorityPostListByCondition(Map<String,Object> condition){
		int i = 0;
		try {
			xt_Data_AuthorityDao.delXtDataAuthorityByCondition(condition);
			xt_Data_Authority_PostDao.delXtDataAuthorityPostList(condition);
			i = 1;
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param xt_data_authority_postList 
	* @return
	*/
	public int addBatchXtDataAuthorityPost(List<Xt_Data_Authority_Post> xt_Data_Authority_PostList,String xt_post_id,String id,String xt_menuinfo_id){
		int i = 0;
		try {
			//1先删除原来数据
			Map<String, Object> condition = new HashMap<String, Object>();
			if(StringUtil.isEmpty(xt_post_id)){
				throw new ExceptionUtil("未能获取到岗位编号----xt_post_id");
			}
			if(StringUtil.isEmpty(xt_menuinfo_id)){
				throw new ExceptionUtil("未能获取到菜单编号----xt_menuinfo_id");
			}
			condition.put("xt_post_id", xt_post_id);
			condition.put("xt_menuinfo_id", xt_menuinfo_id);
			xt_Data_Authority_PostDao.delXtDataAuthorityPostList(condition);
			//2添加新数据
			if(null != xt_Data_Authority_PostList && xt_Data_Authority_PostList.size() > 0){
				i = xt_Data_Authority_PostDao.addBatchXtDataAuthorityPost(xt_Data_Authority_PostList);
			}else{
				i = 1;
			}
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param xt_data_authority_postList 
	* @return
	*/
	public int updateBatchXtDataAuthorityPost(List<Xt_Data_Authority_Post> xt_Data_Authority_PostList){
		int i = 0;
		try {
			i = xt_Data_Authority_PostDao.updateBatchXtDataAuthorityPost(xt_Data_Authority_PostList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param xt_data_authority_postList 
	* @return
	*/
	public int updateBatchXtDataAuthorityPostBySelective(List<Xt_Data_Authority_Post> xt_Data_Authority_PostList){
		int i = 0;
		try {
			i = xt_Data_Authority_PostDao.updateBatchXtDataAuthorityPostBySelective(xt_Data_Authority_PostList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
