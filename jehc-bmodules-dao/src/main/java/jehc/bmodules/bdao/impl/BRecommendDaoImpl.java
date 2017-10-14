package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BRecommendDao;
import jehc.bmodules.bmodel.BRecommend;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础推荐 
* 2016-01-10 11:24:05  邓纯杰
*/
@Repository("bRecommendDao")
public class BRecommendDaoImpl  extends BaseDaoImpl implements BRecommendDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BRecommend> getBRecommendListByCondition(Map<String,Object> condition){
		return (List<BRecommend>)this.getList("getBRecommendListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_recommend_id 
	* @return
	*/
	public BRecommend getBRecommendById(String b_recommend_id){
		return (BRecommend)this.get("getBRecommendById", b_recommend_id);
	}
	/**
	* 添加
	* @param b_recommend 
	* @return
	*/
	public int addBRecommend(BRecommend b_Recommend){
		return this.add("addBRecommend", b_Recommend);
	}
	/**
	* 修改
	* @param b_recommend 
	* @return
	*/
	public int updateBRecommend(BRecommend b_Recommend){
		return this.update("updateBRecommend", b_Recommend);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBRecommend(Map<String,Object> condition){
		return this.del("delBRecommend", condition);
	}
}
