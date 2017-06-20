package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtmodel.Xt_Data_Authority_Post;

/**
* 数据权限按岗位设置 
* 2017-06-20 14:37:16  邓纯杰
*/
public interface Xt_Data_Authority_PostDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Data_Authority_Post> getXtDataAuthorityPostListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_data_authority_post_id 
	* @return
	*/
	public Xt_Data_Authority_Post getXtDataAuthorityPostById(String xt_data_authority_post_id);
	/**
	* 添加
	* @param xt_data_authority_post 
	* @return
	*/
	public int addXtDataAuthorityPost(Xt_Data_Authority_Post xt_Data_Authority_Post);
	/**
	* 修改
	* @param xt_data_authority_post 
	* @return
	*/
	public int updateXtDataAuthorityPost(Xt_Data_Authority_Post xt_Data_Authority_Post);
	/**
	* 修改（根据动态条件）
	* @param xt_data_authority_post 
	* @return
	*/
	public int updateXtDataAuthorityPostBySelective(Xt_Data_Authority_Post xt_Data_Authority_Post);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtDataAuthorityPost(Map<String,Object> condition);
	/**
	 * 删除集合根据拥有者及菜单编号
	 * @param condition
	 * @return
	 */
	public int delXtDataAuthorityPostList(Map<String,Object> condition);
	/**
	* 批量添加
	* @param xt_data_authority_postList 
	* @return
	*/
	public int addBatchXtDataAuthorityPost(List<Xt_Data_Authority_Post> xt_Data_Authority_PostList);
	/**
	* 批量修改
	* @param xt_data_authority_postList 
	* @return
	*/
	public int updateBatchXtDataAuthorityPost(List<Xt_Data_Authority_Post> xt_Data_Authority_PostList);
	/**
	* 批量修改（根据动态条件）
	* @param xt_data_authority_postList 
	* @return
	*/
	public int updateBatchXtDataAuthorityPostBySelective(List<Xt_Data_Authority_Post> xt_Data_Authority_PostList);
}
