package jehc.bmodules.bservice;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BCartDetail;

/**
* 基础购物车明细 
* 2016-01-27 13:52:21  邓纯杰
*/
public interface BCartDetailService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BCartDetail> getBCartDetailListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_cart_detail_id 
	* @return
	*/
	public BCartDetail getBCartDetailById(String b_cart_detail_id);
	/**
	* 添加
	* @param b_cart_detail 
	* @return
	*/
	public int addBCartDetail(BCartDetail b_Cart_Detail);
	/**
	* 修改
	* @param b_cart_detail 
	* @return
	*/
	public int updateBCartDetail(BCartDetail b_Cart_Detail);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBCartDetail(Map<String,Object> condition);
	/**
	 * 根据购物车编号删除
	 * @param condition
	 */
	public int delBCartDetailByBCartId(Map<String,Object> condition);
	/**
	 * 根据购物车明细编号获取购物车集合编号并去重
	 * @param condition
	 * @return
	 */
	public List<String> getBCartIdByCondition(Map<String,Object> condition);
	/**
	 * 根据购物车明细编号查找集合
	 * @param condition
	 * @return
	 */
	public List<BCartDetail> getBCartDetailList(Map<String,Object> condition);
}
