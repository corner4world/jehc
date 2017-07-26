package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtConcordat;

/**
* 合同管理 
* 2015-05-24 08:39:49  邓纯杰
*/
public interface XtConcordatService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtConcordat> getXtConcordatListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_concordat_id 
	* @return
	*/
	public XtConcordat getXtConcordatById(String xt_concordat_id);
	/**
	* 添加
	* @param xt_concordat 
	* @return
	*/
	public int addXtConcordat(XtConcordat xt_Concordat);
	/**
	* 修改
	* @param xt_concordat 
	* @return
	*/
	public int updateXtConcordat(XtConcordat xt_Concordat);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtConcordat(Map<String,Object> condition);
}
