package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_LoadinfoDao;
import jehc.xtmodules.xtmodel.Xt_Loadinfo;

/**
* 页面加载信息 
* 2015-05-13 21:20:57  邓纯杰
*/
@Repository("xt_LoadinfoDao")
public class Xt_LoadinfoDaoImpl  extends BaseDaoImpl implements Xt_LoadinfoDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Loadinfo> getXtLoadinfoListByCondition(Map<String,Object> condition){
		return (List<Xt_Loadinfo>)this.getList("getXtLoadinfoListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_loadinfo_id 
	* @return
	*/
	public Xt_Loadinfo getXtLoadinfoById(String xt_loadinfo_id){
		return (Xt_Loadinfo)this.get("getXtLoadinfoById", xt_loadinfo_id);
	}
	/**
	* 添加
	* @param xt_loadinfo 
	* @return
	*/
	public int addXtLoadinfo(Xt_Loadinfo xt_Loadinfo){
		return this.add("addXtLoadinfo", xt_Loadinfo);
	}
	/**
	* 修改
	* @param xt_loadinfo 
	* @return
	*/
	public int updateXtLoadinfo(Xt_Loadinfo xt_Loadinfo){
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
	public List<Xt_Loadinfo> getXtLoadingGroupList(){
		return (List<Xt_Loadinfo>)this.getList("getXtLoadingGroupList", null);
	}
}
