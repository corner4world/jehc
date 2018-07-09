package jehc.oamodules.oaservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.oamodules.oaservice.OaMplanClassifyService;
import jehc.oamodules.oadao.OaMplanClassifyDao;
import jehc.oamodules.oamodel.OaMplanClassify;

/**
* 个人计划分类 
* 2018-07-09 20:18:35  邓纯杰
*/
@Service("oaMplanClassifyService")
public class OaMplanClassifyServiceImpl extends BaseService implements OaMplanClassifyService{
	@Autowired
	private OaMplanClassifyDao oaMplanClassifyDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<OaMplanClassify> getOaMplanClassifyListByCondition(Map<String,Object> condition){
		try{
			return oaMplanClassifyDao.getOaMplanClassifyListByCondition(condition);
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param oa_mplan_classify_id 
	* @return
	*/
	public OaMplanClassify getOaMplanClassifyById(String oa_mplan_classify_id){
		try{
			OaMplanClassify oaMplanClassify = oaMplanClassifyDao.getOaMplanClassifyById(oa_mplan_classify_id);
			return oaMplanClassify;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param oa_mplan_classify 
	* @return
	*/
	public int addOaMplanClassify(OaMplanClassify oaMplanClassify){
		int i = 0;
		try {
			i = oaMplanClassifyDao.addOaMplanClassify(oaMplanClassify);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param oa_mplan_classify 
	* @return
	*/
	public int updateOaMplanClassify(OaMplanClassify oaMplanClassify){
		int i = 0;
		try {
			i = oaMplanClassifyDao.updateOaMplanClassify(oaMplanClassify);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param oa_mplan_classify 
	* @return
	*/
	public int updateOaMplanClassifyBySelective(OaMplanClassify oaMplanClassify){
		int i = 0;
		try {
			i = oaMplanClassifyDao.updateOaMplanClassifyBySelective(oaMplanClassify);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delOaMplanClassify(Map<String,Object> condition){
		int i = 0;
		try {
			i = oaMplanClassifyDao.delOaMplanClassify(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param oa_mplan_classifyList 
	* @return
	*/
	public int addBatchOaMplanClassify(List<OaMplanClassify> oaMplanClassifyList){
		int i = 0;
		try {
			i = oaMplanClassifyDao.addBatchOaMplanClassify(oaMplanClassifyList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param oa_mplan_classifyList 
	* @return
	*/
	public int updateBatchOaMplanClassify(List<OaMplanClassify> oaMplanClassifyList){
		int i = 0;
		try {
			i = oaMplanClassifyDao.updateBatchOaMplanClassify(oaMplanClassifyList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param oa_mplan_classifyList 
	* @return
	*/
	public int updateBatchOaMplanClassifyBySelective(List<OaMplanClassify> oaMplanClassifyList){
		int i = 0;
		try {
			i = oaMplanClassifyDao.updateBatchOaMplanClassifyBySelective(oaMplanClassifyList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
