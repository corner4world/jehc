package jehc.bmodules.bservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.bmodules.bdao.BRecommendDao;
import jehc.bmodules.bmodel.BRecommend;
import jehc.bmodules.bservice.BRecommendService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;

/**
* 基础推荐 
* 2016-01-10 11:24:05  邓纯杰
*/
@Service("bRecommendService")
public class BRecommendServiceImpl extends BaseService implements BRecommendService{
	@Autowired
	private BRecommendDao bRecommendDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<BRecommend> getBRecommendListByCondition(Map<String,Object> condition){
		try{
			return bRecommendDao.getBRecommendListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param b_recommend_id 
	* @return
	*/
	public BRecommend getBRecommendById(String b_recommend_id){
		try{
			return bRecommendDao.getBRecommendById(b_recommend_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param b_recommend 
	* @return
	*/
	public int addBRecommend(BRecommend b_Recommend){
		int i = 0;
		try {
			i = bRecommendDao.addBRecommend(b_Recommend);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param b_recommend 
	* @return
	*/
	public int updateBRecommend(BRecommend b_Recommend){
		int i = 0;
		try {
			i = bRecommendDao.updateBRecommend(b_Recommend);
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
	public int delBRecommend(Map<String,Object> condition){
		int i = 0;
		try {
			i = bRecommendDao.delBRecommend(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
