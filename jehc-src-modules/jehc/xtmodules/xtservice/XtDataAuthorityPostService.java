package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtmodel.XtDataAuthorityPost;

/**
* 数据权限按岗位设置 
* 2017-06-20 14:37:16  邓纯杰
*/
public interface XtDataAuthorityPostService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtDataAuthorityPost> getXtDataAuthorityPostListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_data_authority_post_id 
	* @return
	*/
	public XtDataAuthorityPost getXtDataAuthorityPostById(String xt_data_authority_post_id);
	/**
	* 添加
	* @param xt_data_authority_post 
	* @return
	*/
	public int addXtDataAuthorityPost(XtDataAuthorityPost xt_Data_Authority_Post);
	/**
	* 修改
	* @param xt_data_authority_post 
	* @return
	*/
	public int updateXtDataAuthorityPost(XtDataAuthorityPost xt_Data_Authority_Post);
	/**
	* 修改（根据动态条件）
	* @param xt_data_authority_post 
	* @return
	*/
	public int updateXtDataAuthorityPostBySelective(XtDataAuthorityPost xt_Data_Authority_Post);
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
	 * 根据情况删除
	 * @param condition
	 * @return
	 */
	public int delXtDataAuthorityPostListByCondition(Map<String,Object> condition);
	/**
	* 批量添加
	* @param xt_data_authority_postList 
	* @return
	*/
	public int addBatchXtDataAuthorityPost(List<XtDataAuthorityPost> xt_Data_Authority_PostList,String xt_post_id,String id,String xt_menuinfo_id);
	/**
	* 批量修改
	* @param xt_data_authority_postList 
	* @return
	*/
	public int updateBatchXtDataAuthorityPost(List<XtDataAuthorityPost> xt_Data_Authority_PostList);
	/**
	* 批量修改（根据动态条件）
	* @param xt_data_authority_postList 
	* @return
	*/
	public int updateBatchXtDataAuthorityPostBySelective(List<XtDataAuthorityPost> xt_Data_Authority_PostList);
}
