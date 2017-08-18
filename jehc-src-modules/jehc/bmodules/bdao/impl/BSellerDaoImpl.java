package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BSellerDao;
import jehc.bmodules.bmodel.BSeller;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础卖家 
* 2016-01-08 22:54:00  邓纯杰
*/
@Repository("bSellerDao")
public class BSellerDaoImpl  extends BaseDaoImpl implements BSellerDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BSeller> getBSellerListByCondition(Map<String,Object> condition){
		return (List<BSeller>)this.getList("getBSellerListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_seller_id 
	* @return
	*/
	public BSeller getBSellerById(String b_seller_id){
		return (BSeller)this.get("getBSellerById", b_seller_id);
	}
	/**
	* 添加
	* @param b_seller 
	* @return
	*/
	public int addBSeller(BSeller b_Seller){
		return this.add("addBSeller", b_Seller);
	}
	/**
	* 修改
	* @param b_seller 
	* @return
	*/
	public int updateBSeller(BSeller b_Seller){
		return this.update("updateBSeller", b_Seller);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBSeller(Map<String,Object> condition){
		return this.del("delBSeller", condition);
	}
}
