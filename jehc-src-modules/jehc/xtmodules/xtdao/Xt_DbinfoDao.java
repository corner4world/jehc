package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Dbinfo;

/**
* 数据库信息表 
* 2015-05-24 08:13:15  邓纯杰
*/
public interface Xt_DbinfoDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Dbinfo> getXtDbinfoListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_dbinfo_id 
	* @return
	*/
	public Xt_Dbinfo getXtDbinfoById(String xt_dbinfo_id);
	/**
	* 添加
	* @param xt_dbinfo 
	* @return
	*/
	public int addXtDbinfo(Xt_Dbinfo xt_Dbinfo);
	/**
	* 修改
	* @param xt_dbinfo 
	* @return
	*/
	public int updateXtDbinfo(Xt_Dbinfo xt_Dbinfo);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtDbinfo(Map<String,Object> condition);
}
