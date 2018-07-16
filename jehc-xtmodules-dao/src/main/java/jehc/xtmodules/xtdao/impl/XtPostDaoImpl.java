package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtPostDao;
import jehc.xtmodules.xtmodel.XtPost;

/**
* 用户岗位表(xt_post) 
* 2015-05-13 16:48:11  邓纯杰
*/
@Repository("xtPostDao")
public class XtPostDaoImpl  extends BaseDaoImpl implements XtPostDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtPost> getXtPostListByCondition(Map<String,Object> condition){
		return (List<XtPost>)this.getList("getXtPostListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_post_id 
	* @return
	*/
	public XtPost getXtPostById(String xt_post_id){
		return (XtPost)this.get("getXtPostById", xt_post_id);
	}
	/**
	* 添加
	* @param xt_post 
	* @return
	*/
	public int addXtPost(XtPost xt_Post){
		return this.add("addXtPost", xt_Post);
	}
	/**
	* 修改
	* @param xt_post 
	* @return
	*/
	public int updateXtPost(XtPost xt_Post){
		return this.update("updateXtPost", xt_Post);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtPost(Map<String,Object> condition){
		return this.del("delXtPost", condition);
	}
	
	
	/**
	 * 岗位根目录集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtPost> getXtPostinfoList(Map<String,Object> condition){
		return (List<XtPost>)this.getList("getXtPostinfoList", condition);
	}
	
	/**
	 * 查找子集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtPost> getXtPostListChild(Map<String,Object> condition){
		return (List<XtPost>)this.getList("getXtPostListChild", condition);
	}
	
	/**
	 * 查找所有集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtPost> getXtPostListAll(Map<String,Object> condition){
		return (List<XtPost>)this.getList("getXtPostListAll", condition);
	}
	
	/**
	 * 根据各种情况查找集合不分页（流程设计器中处理组 发起组等使用）
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtPost> getXtPostList(Map<String,Object> condition){
		return (List<XtPost>)this.getList("getXtPostList", condition);
	}
	
	/**
	 * 非根岗位全部集合
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtPost> getXtPostinfoUnRootList(Map<String,Object> condition){
		return (List<XtPost>)this.getList("getXtPostinfoUnRootList", condition);
	}
}
