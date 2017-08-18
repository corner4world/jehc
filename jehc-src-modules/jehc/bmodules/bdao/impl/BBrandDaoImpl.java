package jehc.bmodules.bdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.bmodules.bdao.BBrandDao;
import jehc.bmodules.bmodel.BBrand;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;

/**
* 基础-品牌 
* 2016-01-05 12:46:23  邓纯杰
*/
@Repository("bBrandDao")
public class BBrandDaoImpl  extends BaseDaoImpl implements BBrandDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BBrand> getBBrandListByCondition(Map<String,Object> condition){
		return (List<BBrand>)this.getList("getBBrandListByCondition",condition);
	}
	/**
	* 查询对象
	* @param b_brand_id 
	* @return
	*/
	public BBrand getBBrandById(String b_brand_id){
		return (BBrand)this.get("getBBrandById", b_brand_id);
	}
	/**
	* 添加
	* @param b_brand 
	* @return
	*/
	public int addBBrand(BBrand b_Brand){
		return this.add("addBBrand", b_Brand);
	}
	/**
	* 修改
	* @param b_brand 
	* @return
	*/
	public int updateBBrand(BBrand b_Brand){
		return this.update("updateBBrand", b_Brand);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delBBrand(Map<String,Object> condition){
		return this.del("delBBrand", condition);
	}
}
