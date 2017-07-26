package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtProvince;

/**
* 共用省份表; InnoDB free: 7168 kB 
* 2015-07-27 19:27:08  邓纯杰
*/
public interface XtProvinceService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtProvince> getXtProvinceListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_provinceID 
	* @return
	*/
	public XtProvince getXtProvinceById(String xt_provinceID);
	/**
	* 添加
	* @param xt_province 
	* @return
	*/
	public int addXtProvince(XtProvince xt_Province);
	/**
	* 修改
	* @param xt_province 
	* @return
	*/
	public int updateXtProvince(XtProvince xt_Province);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtProvince(Map<String,Object> condition);
}
