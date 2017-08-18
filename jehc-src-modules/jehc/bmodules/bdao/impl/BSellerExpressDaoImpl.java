package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BSellerExpressDao;
import jehc.bmodules.bmodel.BSellerExpress;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础卖家快递 
* 2016-02-18 17:14:52  邓纯杰
*/
@Repository("bSellerExpressDao")
public class BSellerExpressDaoImpl  extends BaseDaoImpl implements BSellerExpressDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BSellerExpress> getBSellerExpressListByCondition(Map<String,Object> condition){
		return (List<BSellerExpress>)this.getList("getBSellerExpressListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_seller_express_id 
	* @return
	*/
	public BSellerExpress getBSellerExpressById(String b_seller_express_id){
		return (BSellerExpress)this.get("getBSellerExpressById", b_seller_express_id);
	}
	/**
	* 添加
	* @param b_seller_express 
	* @return
	*/
	public int addBSellerExpress(BSellerExpress b_Seller_Express){
		return this.add("addBSellerExpress", b_Seller_Express);
	}
	/**
	* 修改
	* @param b_seller_express 
	* @return
	*/
	public int updateBSellerExpress(BSellerExpress b_Seller_Express){
		return this.update("updateBSellerExpress", b_Seller_Express);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBSellerExpress(Map<String,Object> condition){
		return this.del("delBSellerExpress", condition);
	}
}
