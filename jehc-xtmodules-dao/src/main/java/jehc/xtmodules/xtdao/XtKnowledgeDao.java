package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtKnowledge;

/**
* 平台知识内容 
* 2015-06-07 20:09:38  邓纯杰
*/
public interface XtKnowledgeDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtKnowledge> getXtKnowledgeListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_knowledge_id 
	* @return
	*/
	public XtKnowledge getXtKnowledgeById(String xt_knowledge_id);
	/**
	* 添加
	* @param xt_knowledge 
	* @return
	*/
	public int addXtKnowledge(XtKnowledge xt_Knowledge);
	/**
	* 修改
	* @param xt_knowledge 
	* @return
	*/
	public int updateXtKnowledge(XtKnowledge xt_Knowledge);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtKnowledge(Map<String,Object> condition);
	/**
	 * 统计知识点数
	 * @param condition
	 * @return
	 */
	public int getXtKnowledgeCount(Map<String,Object> condition);
}
