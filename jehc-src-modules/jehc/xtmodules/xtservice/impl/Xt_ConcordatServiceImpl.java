package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_ConcordatDao;
import jehc.xtmodules.xtmodel.Xt_Concordat;
import jehc.xtmodules.xtservice.Xt_ConcordatService;

/**
* 合同管理 
* 2015-05-24 08:39:49  邓纯杰
*/
@Service("xt_ConcordatService")
public class Xt_ConcordatServiceImpl extends BaseService implements Xt_ConcordatService{
	@Autowired
	private Xt_ConcordatDao xt_ConcordatDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Concordat> getXtConcordatListByCondition(Map<String,Object> condition){
		try {
			return xt_ConcordatDao.getXtConcordatListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_concordat_id 
	* @return
	*/
	public Xt_Concordat getXtConcordatById(String xt_concordat_id){
		try {
			return xt_ConcordatDao.getXtConcordatById(xt_concordat_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_concordat 
	* @return
	*/
	public int addXtConcordat(Xt_Concordat xt_Concordat){
		int i = 0;
		try {
			i = xt_ConcordatDao.addXtConcordat(xt_Concordat);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_concordat 
	* @return
	*/
	public int updateXtConcordat(Xt_Concordat xt_Concordat){
		int i = 0;
		try {
			i = xt_ConcordatDao.updateXtConcordat(xt_Concordat);
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
	public int delXtConcordat(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_ConcordatDao.delXtConcordat(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
