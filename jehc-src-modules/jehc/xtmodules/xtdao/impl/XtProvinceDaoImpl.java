package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtProvinceDao;
import jehc.xtmodules.xtmodel.XtProvince;

/**
* 共用省份表; InnoDB free: 7168 kB 
* 2015-07-27 19:27:08  邓纯杰
*/
@Repository("xtProvinceDao")
public class XtProvinceDaoImpl  extends BaseDaoImpl implements XtProvinceDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtProvince> getXtProvinceListByCondition(Map<String,Object> condition){
		return (List<XtProvince>)this.getList("getXtProvinceListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_provinceID 
	* @return
	*/
	public XtProvince getXtProvinceById(String xt_provinceID){
		return (XtProvince)this.get("getXtProvinceById", xt_provinceID);
	}
	/**
	* 添加
	* @param xt_province 
	* @return
	*/
	public int addXtProvince(XtProvince xt_Province){
		return this.add("addXtProvince", xt_Province);
	}
	/**
	* 修改
	* @param xt_province 
	* @return
	*/
	public int updateXtProvince(XtProvince xt_Province){
		return this.update("updateXtProvince", xt_Province);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtProvince(Map<String,Object> condition){
		return this.del("delXtProvince", condition);
	}
}
