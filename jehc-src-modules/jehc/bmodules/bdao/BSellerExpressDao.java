package jehc.bmodules.bdao;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BSellerExpress;

/**
* 基础卖家快递 
* 2016-02-18 17:14:52  邓纯杰
*/
public interface BSellerExpressDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BSellerExpress> getBSellerExpressListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_seller_express_id 
	* @return
	*/
	public BSellerExpress getBSellerExpressById(String b_seller_express_id);
	/**
	* 添加
	* @param b_seller_express 
	* @return
	*/
	public int addBSellerExpress(BSellerExpress b_Seller_Express);
	/**
	* 修改
	* @param b_seller_express 
	* @return
	*/
	public int updateBSellerExpress(BSellerExpress b_Seller_Express);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBSellerExpress(Map<String,Object> condition);
}
