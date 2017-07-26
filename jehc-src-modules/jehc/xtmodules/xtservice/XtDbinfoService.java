package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtDbinfo;

/**
* 数据库信息表 
* 2015-05-24 08:13:15  邓纯杰
*/
public interface XtDbinfoService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtDbinfo> getXtDbinfoListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_dbinfo_id 
	* @return
	*/
	public XtDbinfo getXtDbinfoById(String xt_dbinfo_id);
	/**
	* 添加
	* @param xt_dbinfo 
	* @return
	*/
	public int addXtDbinfo(XtDbinfo xt_Dbinfo);
	/**
	* 修改
	* @param xt_dbinfo 
	* @return
	*/
	public int updateXtDbinfo(XtDbinfo xt_Dbinfo);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtDbinfo(Map<String,Object> condition);
}
