package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtKnowledgeDao;
import jehc.xtmodules.xtmodel.XtKnowledge;
import jehc.xtmodules.xtservice.XtKnowledgeService;

/**
* 平台知识内容 
* 2015-06-07 20:09:38  邓纯杰
*/
@Service("xtKnowledgeService")
public class XtKnowledgeServiceImpl extends BaseService implements XtKnowledgeService{
	@Autowired
	private XtKnowledgeDao xtKnowledgeDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtKnowledge> getXtKnowledgeListByCondition(Map<String,Object> condition){
		try{
			return xtKnowledgeDao.getXtKnowledgeListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_knowledge_id 
	* @return
	*/
	public XtKnowledge getXtKnowledgeById(String xt_knowledge_id){
		try{
			return xtKnowledgeDao.getXtKnowledgeById(xt_knowledge_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_knowledge 
	* @return
	*/
	public int addXtKnowledge(XtKnowledge xt_Knowledge){
		int i = 0;
		try {
			xt_Knowledge.setXt_userinfo_id(getXtUid());
			i = xtKnowledgeDao.addXtKnowledge(xt_Knowledge);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_knowledge 
	* @return
	*/
	public int updateXtKnowledge(XtKnowledge xt_Knowledge){
		int i = 0;
		try {
			xt_Knowledge.setXt_userinfo_id(getXtUid());
			i = xtKnowledgeDao.updateXtKnowledge(xt_Knowledge);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtKnowledge(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtKnowledgeDao.delXtKnowledge(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	 * 统计知识点数
	 * @param condition
	 * @return
	 */
	public int getXtKnowledgeCount(Map<String,Object> condition){
		return xtKnowledgeDao.getXtKnowledgeCount(condition);
	}
}
