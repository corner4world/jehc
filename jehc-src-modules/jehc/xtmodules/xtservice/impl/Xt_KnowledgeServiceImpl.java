package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_KnowledgeDao;
import jehc.xtmodules.xtmodel.Xt_Knowledge;
import jehc.xtmodules.xtservice.Xt_KnowledgeService;

/**
* 平台知识内容 
* 2015-06-07 20:09:38  邓纯杰
*/
@Service("xt_KnowledgeService")
public class Xt_KnowledgeServiceImpl extends BaseService implements Xt_KnowledgeService{
	@Autowired
	private Xt_KnowledgeDao xt_KnowledgeDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Knowledge> getXtKnowledgeListByCondition(Map<String,Object> condition){
		try{
			return xt_KnowledgeDao.getXtKnowledgeListByCondition(condition);
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
	public Xt_Knowledge getXtKnowledgeById(String xt_knowledge_id){
		try{
			return xt_KnowledgeDao.getXtKnowledgeById(xt_knowledge_id);
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
	public int addXtKnowledge(Xt_Knowledge xt_Knowledge){
		int i = 0;
		try {
			xt_Knowledge.setXt_userinfo_id(CommonUtils.getXtUid());
			i = xt_KnowledgeDao.addXtKnowledge(xt_Knowledge);
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
	public int updateXtKnowledge(Xt_Knowledge xt_Knowledge){
		int i = 0;
		try {
			xt_Knowledge.setXt_userinfo_id(CommonUtils.getXtUid());
			i = xt_KnowledgeDao.updateXtKnowledge(xt_Knowledge);
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
			i = xt_KnowledgeDao.delXtKnowledge(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
