package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtDataAuthority;

/**
* 数据权限; InnoDB free: 10240 kB 
* 2015-10-04 14:42:34  邓纯杰
*/
public interface XtDataAuthorityDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtDataAuthority> getXtDataAuthorityListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_data_authority_id 
	* @return
	*/
	public XtDataAuthority getXtDataAuthorityById(String xt_data_authority_id);
	/**
	* 添加
	* @param xt_data_authority 
	* @return
	*/
	public int addXtDataAuthority(XtDataAuthority xt_Data_Authority);
	/**
	* 修改
	* @param xt_data_authority 
	* @return
	*/
	public int updateXtDataAuthority(XtDataAuthority xt_Data_Authority);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtDataAuthority(Map<String,Object> condition);
	/**
	 * 读取所有数据
	 * @param condition
	 * @return
	 */
	public List<XtDataAuthority> getXtDataAuthorityListAllByCondition(Map<String,Object> condition);
	/**
	 * 根据情况删除
	 * @param condition
	 */
	public int delXtDataAuthorityByCondition(Map<String,Object> condition);
	/**
	 * 获取所有为登录使用 
	 * @param condition
	 * @return
	 */
	public List<XtDataAuthority> getXtDataAuthorityListForLogin(Map<String,Object> condition);
	/**
	 * 批量添加
	 * @param xt_Data_AuthorityList
	 * @return
	 */
	public int addBatchXtDataAuthority(List<XtDataAuthority> xt_Data_AuthorityList);
}
