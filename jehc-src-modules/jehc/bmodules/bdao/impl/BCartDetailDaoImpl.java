package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BCartDetailDao;
import jehc.bmodules.bmodel.BCartDetail;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础购物车明细 
* 2016-01-27 13:52:21  邓纯杰
*/
@Repository("bCartDetailDao")
public class BCartDetailDaoImpl  extends BaseDaoImpl implements BCartDetailDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BCartDetail> getBCartDetailListByCondition(Map<String,Object> condition){
		return (List<BCartDetail>)this.getList("getBCartDetailListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_cart_detail_id 
	* @return
	*/
	public BCartDetail getBCartDetailById(String b_cart_detail_id){
		return (BCartDetail)this.get("getBCartDetailById", b_cart_detail_id);
	}
	/**
	* 添加
	* @param b_cart_detail 
	* @return
	*/
	public int addBCartDetail(BCartDetail b_Cart_Detail){
		return this.add("addBCartDetail", b_Cart_Detail);
	}
	/**
	* 修改
	* @param b_cart_detail 
	* @return
	*/
	public int updateBCartDetail(BCartDetail b_Cart_Detail){
		return this.update("updateBCartDetail", b_Cart_Detail);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBCartDetail(Map<String,Object> condition){
		return this.del("delBCartDetail", condition);
	}
	/**
	 * 根据购物车编号删除
	 * @param condition
	 */
	public int delBCartDetailByBCartId(Map<String,Object> condition){
		return this.del("delBCartDetailByBCartId", condition);
	}
	/**
	 * 根据购物车明细编号获取购物车集合编号并去重
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getBCartIdByCondition(Map<String,Object> condition){
		return (List<String>)this.getList("getBCartIdByCondition",condition);
	}
	/**
	 * 根据购物车明细编号查找集合
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<BCartDetail> getBCartDetailList(Map<String,Object> condition){
		return (List<BCartDetail>)this.getList("getBCartDetailList",condition);
	}
}
