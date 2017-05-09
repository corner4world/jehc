package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Post;

/**
* 用户岗位表(xt_post) 
* 2015-05-13 16:48:11  邓纯杰
*/
public interface Xt_PostService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Post> getXtPostListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_post_id 
	* @return
	*/
	public Xt_Post getXtPostById(String xt_post_id);
	/**
	* 添加
	* @param xt_post 
	* @return
	*/
	public int addXtPost(Xt_Post xt_Post);
	/**
	* 修改
	* @param xt_post 
	* @return
	*/
	public int updateXtPost(Xt_Post xt_Post);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtPost(Map<String,Object> condition);
	
	/**
	 * 岗位根目录集合
	 * @return
	 */
	public List<Xt_Post> getXtPostinfoList(Map<String,Object> condition);
	
	/**
	 * 查找子集合
	 * @return
	 */
	public List<Xt_Post> getXtPostListChild(Map<String,Object> condition);
	
	/**
	 * 查找所有集合
	 * @return
	 */
	public List<Xt_Post> getXtPostListAll(Map<String,Object> condition);
	
	/**
	 * 根据各种情况查找集合不分页（流程设计器中处理组 发起组等使用）
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Post> getXtPostList(Map<String,Object> condition);
}
