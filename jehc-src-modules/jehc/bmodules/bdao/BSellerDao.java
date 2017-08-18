package jehc.bmodules.bdao;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BSeller;

/**
* 基础卖家 
* 2016-01-08 22:54:00  邓纯杰
*/
public interface BSellerDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BSeller> getBSellerListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_seller_id 
	* @return
	*/
	public BSeller getBSellerById(String b_seller_id);
	/**
	* 添加
	* @param b_seller 
	* @return
	*/
	public int addBSeller(BSeller b_Seller);
	/**
	* 修改
	* @param b_seller 
	* @return
	*/
	public int updateBSeller(BSeller b_Seller);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBSeller(Map<String,Object> condition);
}
