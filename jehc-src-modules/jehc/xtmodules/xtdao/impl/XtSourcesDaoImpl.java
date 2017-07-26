package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtSourcesDao;
import jehc.xtmodules.xtmodel.XtSources;

/**
* 平台静态资源 
* 2016-06-16 10:34:06  邓纯杰
*/
@Repository("xtSourcesDao")
public class XtSourcesDaoImpl  extends BaseDaoImpl implements XtSourcesDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtSources> getXtSourcesListByCondition(Map<String,Object> condition){
		return (List<XtSources>)this.getList("getXtSourcesListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_sources_id 
	* @return
	*/
	public XtSources getXtSourcesById(String xt_sources_id){
		return (XtSources)this.get("getXtSourcesById", xt_sources_id);
	}
	/**
	* 添加
	* @param xt_sources 
	* @return
	*/
	public int addXtSources(XtSources xt_Sources){
		return this.add("addXtSources", xt_Sources);
	}
	/**
	* 修改
	* @param xt_sources 
	* @return
	*/
	public int updateXtSources(XtSources xt_Sources){
		return this.update("updateXtSources", xt_Sources);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtSources(Map<String,Object> condition){
		return this.del("delXtSources", condition);
	}
}
