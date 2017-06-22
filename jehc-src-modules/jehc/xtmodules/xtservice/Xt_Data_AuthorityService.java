package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Data_Authority;

/**
* 数据权限; InnoDB free: 10240 kB 
* 2015-10-04 14:42:34  邓纯杰
*/
public interface Xt_Data_AuthorityService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Data_Authority> getXtDataAuthorityListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_data_authority_id 
	* @return
	*/
	public Xt_Data_Authority getXtDataAuthorityById(String xt_data_authority_id);
	/**
	* 添加
	* @param xt_data_authority 
	* @return
	*/
	public int addXtDataAuthority(List<Xt_Data_Authority> xt_Data_AuthorityList,String xt_userinfo_id,String id,String xt_menuinfo_id,String xt_data_authorityType);
	/**
	* 修改
	* @param xt_data_authority 
	* @return
	*/
	public int updateXtDataAuthority(Xt_Data_Authority xt_Data_Authority);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtDataAuthority(Map<String,Object> condition);
	
	/**
	* 根据条件删除（用于清空）
	* @param condition 
	* @return
	*/
	public int delXtDataAuthorityByCondition(Map<String,Object> condition);
	
	/**
	 * 读取所有数据
	 * @param condition
	 * @return
	 */
	public List<Xt_Data_Authority> getXtDataAuthorityListAllByCondition(Map<String,Object> condition);
	/**
	 * 获取所有为登录使用 
	 * @param condition
	 * @return
	 */
	public List<Xt_Data_Authority> getXtDataAuthorityListForLogin(Map<String,Object> condition);
	/**
	 * 批量添加
	 * @param xt_Data_AuthorityList
	 * @return
	 */
	public int addBatchXtDataAuthority(List<Xt_Data_Authority> xt_Data_AuthorityList);
}
