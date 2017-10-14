package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtKnowledgeDao;
import jehc.xtmodules.xtmodel.XtKnowledge;

/**
* 平台知识内容 
* 2015-06-07 20:09:38  邓纯杰
*/
@Repository("xtKnowledgeDao")
public class XtKnowledgeDaoImpl  extends BaseDaoImpl implements XtKnowledgeDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtKnowledge> getXtKnowledgeListByCondition(Map<String,Object> condition){
		return (List<XtKnowledge>)this.getList("getXtKnowledgeListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_knowledge_id 
	* @return
	*/
	public XtKnowledge getXtKnowledgeById(String xt_knowledge_id){
		return (XtKnowledge)this.get("getXtKnowledgeById", xt_knowledge_id);
	}
	/**
	* 添加
	* @param xt_knowledge 
	* @return
	*/
	public int addXtKnowledge(XtKnowledge xt_Knowledge){
		return this.add("addXtKnowledge", xt_Knowledge);
	}
	/**
	* 修改
	* @param xt_knowledge 
	* @return
	*/
	public int updateXtKnowledge(XtKnowledge xt_Knowledge){
		return this.update("updateXtKnowledge", xt_Knowledge);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtKnowledge(Map<String,Object> condition){
		return this.del("delXtKnowledge", condition);
	}
}
