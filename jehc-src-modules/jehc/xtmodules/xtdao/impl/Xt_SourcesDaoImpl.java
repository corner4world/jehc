package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_SourcesDao;
import jehc.xtmodules.xtmodel.Xt_Sources;

/**
* 平台静态资源 
* 2016-06-16 10:34:06  邓纯杰
*/
@Repository("xt_SourcesDao")
public class Xt_SourcesDaoImpl  extends BaseDaoImpl implements Xt_SourcesDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Sources> getXtSourcesListByCondition(Map<String,Object> condition){
		return (List<Xt_Sources>)this.getList("getXtSourcesListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_sources_id 
	* @return
	*/
	public Xt_Sources getXtSourcesById(String xt_sources_id){
		return (Xt_Sources)this.get("getXtSourcesById", xt_sources_id);
	}
	/**
	* 添加
	* @param xt_sources 
	* @return
	*/
	public int addXtSources(Xt_Sources xt_Sources){
		return this.add("addXtSources", xt_Sources);
	}
	/**
	* 修改
	* @param xt_sources 
	* @return
	*/
	public int updateXtSources(Xt_Sources xt_Sources){
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
