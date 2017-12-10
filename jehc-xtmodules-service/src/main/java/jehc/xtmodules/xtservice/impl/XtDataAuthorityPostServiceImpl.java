package jehc.xtmodules.xtservice.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.xtmodules.xtservice.XtDataAuthorityPostService;
import jehc.xtmodules.xtdao.XtDataAuthorityDao;
import jehc.xtmodules.xtdao.XtDataAuthorityPostDao;
import jehc.xtmodules.xtmodel.XtDataAuthorityPost;

/**
* 数据权限按岗位设置 
* 2017-06-20 14:37:16  邓纯杰
*/
@Service("xtDataAuthorityPostService")
public class XtDataAuthorityPostServiceImpl extends BaseService implements XtDataAuthorityPostService{
	@Autowired
	private XtDataAuthorityPostDao xtDataAuthorityPostDao;
	@Autowired
	private XtDataAuthorityDao xtDataAuthorityDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtDataAuthorityPost> getXtDataAuthorityPostListByCondition(Map<String,Object> condition){
		try{
			return xtDataAuthorityPostDao.getXtDataAuthorityPostListByCondition(condition);
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
	public XtDataAuthorityPost getXtDataAuthorityPostById(String xt_data_authority_post_id){
		try{
			XtDataAuthorityPost xt_Data_Authority_Post = xtDataAuthorityPostDao.getXtDataAuthorityPostById(xt_data_authority_post_id);
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
	public int addXtDataAuthorityPost(XtDataAuthorityPost xt_Data_Authority_Post){
		int i = 0;
		try {
			i = xtDataAuthorityPostDao.addXtDataAuthorityPost(xt_Data_Authority_Post);
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
	public int updateXtDataAuthorityPost(XtDataAuthorityPost xt_Data_Authority_Post){
		int i = 0;
		try {
			i = xtDataAuthorityPostDao.updateXtDataAuthorityPost(xt_Data_Authority_Post);
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
	public int updateXtDataAuthorityPostBySelective(XtDataAuthorityPost xt_Data_Authority_Post){
		int i = 0;
		try {
			i = xtDataAuthorityPostDao.updateXtDataAuthorityPostBySelective(xt_Data_Authority_Post);
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
			i = xtDataAuthorityPostDao.delXtDataAuthorityPost(condition);
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
			i = xtDataAuthorityPostDao.delXtDataAuthorityPostList(condition);
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
			xtDataAuthorityDao.delXtDataAuthorityByCondition(condition);
			xtDataAuthorityPostDao.delXtDataAuthorityPostList(condition);
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
	public int addBatchXtDataAuthorityPost(List<XtDataAuthorityPost> xt_Data_Authority_PostList,String xt_post_id,String id,String xt_menuinfo_id){
		int i = 0;
		try {
			//1删除 原表
			Map<String, Object> condition = new HashMap<String, Object>();
			if(StringUtil.isEmpty(xt_post_id)){
				throw new ExceptionUtil("未能获取到岗位编号----xt_post_id");
			}
			if(StringUtil.isEmpty(xt_menuinfo_id)){
				throw new ExceptionUtil("未能获取到菜单编号----xt_menuinfo_id");
			}
			condition.put("xt_post_id", xt_post_id);
			condition.put("xt_menuinfo_id", xt_menuinfo_id);
			xtDataAuthorityPostDao.delXtDataAuthorityPostList(condition);
			//2删除 执行表
			condition = new HashMap<String, Object>();
			condition.put("xt_data_authorityType", "3");
			condition.put("xt_menuinfo_id", xt_menuinfo_id);
			xtDataAuthorityDao.delXtDataAuthorityByCondition(condition);
			//3添加新数据
			if(null != xt_Data_Authority_PostList && xt_Data_Authority_PostList.size() > 0){
				for(XtDataAuthorityPost xt_Data_Authority_Post:xt_Data_Authority_PostList){
					xtDataAuthorityPostDao.addXtDataAuthorityPost(xt_Data_Authority_Post);
				}
				//兼容oracle与mysql语法 废弃批量插入
//				i = xtDataAuthorityPostDao.addBatchXtDataAuthorityPost(xt_Data_Authority_PostList);
			}
			i = 1;
			//4统一推送
			addPushDataAuthority();
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
	public int updateBatchXtDataAuthorityPost(List<XtDataAuthorityPost> xt_Data_Authority_PostList){
		int i = 0;
		try {
			i = xtDataAuthorityPostDao.updateBatchXtDataAuthorityPost(xt_Data_Authority_PostList);
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
	public int updateBatchXtDataAuthorityPostBySelective(List<XtDataAuthorityPost> xt_Data_Authority_PostList){
		int i = 0;
		try {
			i = xtDataAuthorityPostDao.updateBatchXtDataAuthorityPostBySelective(xt_Data_Authority_PostList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
