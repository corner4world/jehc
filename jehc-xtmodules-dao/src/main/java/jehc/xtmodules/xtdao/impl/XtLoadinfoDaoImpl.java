package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtLoadinfoDao;
import jehc.xtmodules.xtmodel.XtLoadinfo;

/**
* 页面加载信息 
* 2015-05-13 21:20:57  邓纯杰
*/
@Repository("xtLoadinfoDao")
public class XtLoadinfoDaoImpl  extends BaseDaoImpl implements XtLoadinfoDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtLoadinfo> getXtLoadinfoListByCondition(Map<String,Object> condition){
		return (List<XtLoadinfo>)this.getList("getXtLoadinfoListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_loadinfo_id 
	* @return
	*/
	public XtLoadinfo getXtLoadinfoById(String xt_loadinfo_id){
		return (XtLoadinfo)this.get("getXtLoadinfoById", xt_loadinfo_id);
	}
	/**
	* 添加
	* @param xt_loadinfo 
	* @return
	*/
	public int addXtLoadinfo(XtLoadinfo xt_Loadinfo){
		return this.add("addXtLoadinfo", xt_Loadinfo);
	}
	/**
	* 修改
	* @param xt_loadinfo 
	* @return
	*/
	public int updateXtLoadinfo(XtLoadinfo xt_Loadinfo){
		return this.update("updateXtLoadinfo", xt_Loadinfo);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtLoadinfo(Map<String,Object> condition){
		return this.del("delXtLoadinfo", condition);
	}
	
	/**
	 * 分组统计并平均值算法
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtLoadinfo> getXtLoadingGroupList(){
		return (List<XtLoadinfo>)this.getList("getXtLoadingGroupList", null);
	}
}
