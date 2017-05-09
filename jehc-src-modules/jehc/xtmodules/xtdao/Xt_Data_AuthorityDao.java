package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Data_Authority;

/**
* 数据权限; InnoDB free: 10240 kB 
* 2015-10-04 14:42:34  邓纯杰
*/
public interface Xt_Data_AuthorityDao{
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
	public int addXtDataAuthority(Xt_Data_Authority xt_Data_Authority);
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
	 * 读取所有数据
	 * @param condition
	 * @return
	 */
	public List<Xt_Data_Authority> getXtDataAuthorityListAllByCondition(Map<String,Object> condition);
	/**
	 * 根据情况删除
	 * @param condition
	 */
	public int delXtDataAuthorityByCondition(Map<String,Object> condition);
}
