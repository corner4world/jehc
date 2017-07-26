package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtSources;

/**
* 平台静态资源 
* 2016-06-16 10:34:06  邓纯杰
*/
public interface XtSourcesService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtSources> getXtSourcesListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_sources_id 
	* @return
	*/
	public XtSources getXtSourcesById(String xt_sources_id);
	/**
	* 添加
	* @param xt_sources 
	* @return
	*/
	public int addXtSources(XtSources xt_Sources);
	/**
	* 修改
	* @param xt_sources 
	* @return
	*/
	public int updateXtSources(XtSources xt_Sources);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtSources(Map<String,Object> condition);
}
