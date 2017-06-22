package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_Data_AuthorityDao;
import jehc.xtmodules.xtmodel.Xt_Data_Authority;

/**
* 数据权限; InnoDB free: 10240 kB 
* 2015-10-04 14:42:34  邓纯杰
*/
@Repository("xt_Data_AuthorityDao")
public class Xt_Data_AuthorityDaoImpl  extends BaseDaoImpl implements Xt_Data_AuthorityDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Data_Authority> getXtDataAuthorityListByCondition(Map<String,Object> condition){
		return (List<Xt_Data_Authority>)this.getList("getXtDataAuthorityListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_data_authority_id 
	* @return
	*/
	public Xt_Data_Authority getXtDataAuthorityById(String xt_data_authority_id){
		return (Xt_Data_Authority)this.get("getXtDataAuthorityById", xt_data_authority_id);
	}
	/**
	* 添加
	* @param xt_data_authority 
	* @return
	*/
	public int addXtDataAuthority(Xt_Data_Authority xt_Data_Authority){
		return this.add("addXtDataAuthority", xt_Data_Authority);
	}
	/**
	* 修改
	* @param xt_data_authority 
	* @return
	*/
	public int updateXtDataAuthority(Xt_Data_Authority xt_Data_Authority){
		return this.update("updateXtDataAuthority", xt_Data_Authority);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtDataAuthority(Map<String,Object> condition){
		return this.del("delXtDataAuthority", condition);
	}
	/**
	 * 读取所有数据
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Data_Authority> getXtDataAuthorityListAllByCondition(Map<String,Object> condition){
		return (List<Xt_Data_Authority>)this.getList("getXtDataAuthorityListAllByCondition",condition);
	}
	/**
	 * 根据情况删除
	 * @param condition
	 */
	public int delXtDataAuthorityByCondition(Map<String,Object> condition){
		return this.del("delXtDataAuthorityByCondition", condition);
	}
	/**
	 * 获取所有为登录使用 
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Data_Authority> getXtDataAuthorityListForLogin(Map<String,Object> condition){
		return (List<Xt_Data_Authority>)this.getList("getXtDataAuthorityListForLogin",condition);
	}
	/**
	 * 批量添加
	 * @param xt_Data_AuthorityList
	 * @return
	 */
	public int addBatchXtDataAuthority(List<Xt_Data_Authority> xt_Data_AuthorityList){
		return this.add("addBatchXtDataAuthority", xt_Data_AuthorityList); 
	}
}
