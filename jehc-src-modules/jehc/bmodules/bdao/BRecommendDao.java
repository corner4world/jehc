package jehc.bmodules.bdao;
import java.util.List;
import java.util.Map;

import jehc.bmodules.bmodel.BRecommend;

/**
* 基础推荐 
* 2016-01-10 11:24:05  邓纯杰
*/
public interface BRecommendDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BRecommend> getBRecommendListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param b_recommend_id 
	* @return
	*/
	public BRecommend getBRecommendById(String b_recommend_id);
	/**
	* 添加
	* @param b_recommend 
	* @return
	*/
	public int addBRecommend(BRecommend b_Recommend);
	/**
	* 修改
	* @param b_recommend 
	* @return
	*/
	public int updateBRecommend(BRecommend b_Recommend);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBRecommend(Map<String,Object> condition);
}
