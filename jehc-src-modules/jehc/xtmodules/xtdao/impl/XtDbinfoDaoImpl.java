package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtDbinfoDao;
import jehc.xtmodules.xtmodel.XtDbinfo;

/**
* 数据库信息表 
* 2015-05-24 08:13:15  邓纯杰
*/
@Repository("xtDbinfoDao")
public class XtDbinfoDaoImpl  extends BaseDaoImpl implements XtDbinfoDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtDbinfo> getXtDbinfoListByCondition(Map<String,Object> condition){
		return (List<XtDbinfo>)this.getList("getXtDbinfoListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_dbinfo_id 
	* @return
	*/
	public XtDbinfo getXtDbinfoById(String xt_dbinfo_id){
		return (XtDbinfo)this.get("getXtDbinfoById", xt_dbinfo_id);
	}
	/**
	* 添加
	* @param xt_dbinfo 
	* @return
	*/
	public int addXtDbinfo(XtDbinfo xt_Dbinfo){
		return this.add("addXtDbinfo", xt_Dbinfo);
	}
	/**
	* 修改
	* @param xt_dbinfo 
	* @return
	*/
	public int updateXtDbinfo(XtDbinfo xt_Dbinfo){
		return this.update("updateXtDbinfo", xt_Dbinfo);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtDbinfo(Map<String,Object> condition){
		return this.del("delXtDbinfo", condition);
	}
}
