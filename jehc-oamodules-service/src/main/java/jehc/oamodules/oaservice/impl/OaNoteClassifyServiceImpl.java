package jehc.oamodules.oaservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.oamodules.oaservice.OaNoteClassifyService;
import jehc.oamodules.oadao.OaNoteClassifyDao;
import jehc.oamodules.oamodel.OaNoteClassify;

/**
* 记事本分类 
* 2018-07-04 21:06:37  邓纯杰
*/
@Service("oaNoteClassifyService")
public class OaNoteClassifyServiceImpl extends BaseService implements OaNoteClassifyService{
	@Autowired
	private OaNoteClassifyDao oaNoteClassifyDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<OaNoteClassify> getOaNoteClassifyListByCondition(Map<String,Object> condition){
		try{
			return oaNoteClassifyDao.getOaNoteClassifyListByCondition(condition);
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param oa_note_classify_id 
	* @return
	*/
	public OaNoteClassify getOaNoteClassifyById(String oa_note_classify_id){
		try{
			OaNoteClassify oaNoteClassify = oaNoteClassifyDao.getOaNoteClassifyById(oa_note_classify_id);
			return oaNoteClassify;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param oa_note_classify 
	* @return
	*/
	public int addOaNoteClassify(OaNoteClassify oaNoteClassify){
		int i = 0;
		try {
			i = oaNoteClassifyDao.addOaNoteClassify(oaNoteClassify);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param oa_note_classify 
	* @return
	*/
	public int updateOaNoteClassify(OaNoteClassify oaNoteClassify){
		int i = 0;
		try {
			i = oaNoteClassifyDao.updateOaNoteClassify(oaNoteClassify);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param oa_note_classify 
	* @return
	*/
	public int updateOaNoteClassifyBySelective(OaNoteClassify oaNoteClassify){
		int i = 0;
		try {
			i = oaNoteClassifyDao.updateOaNoteClassifyBySelective(oaNoteClassify);
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
	public int delOaNoteClassify(Map<String,Object> condition){
		int i = 0;
		try {
			i = oaNoteClassifyDao.delOaNoteClassify(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param oa_note_classifyList 
	* @return
	*/
	public int addBatchOaNoteClassify(List<OaNoteClassify> oaNoteClassifyList){
		int i = 0;
		try {
			i = oaNoteClassifyDao.addBatchOaNoteClassify(oaNoteClassifyList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param oa_note_classifyList 
	* @return
	*/
	public int updateBatchOaNoteClassify(List<OaNoteClassify> oaNoteClassifyList){
		int i = 0;
		try {
			i = oaNoteClassifyDao.updateBatchOaNoteClassify(oaNoteClassifyList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param oa_note_classifyList 
	* @return
	*/
	public int updateBatchOaNoteClassifyBySelective(List<OaNoteClassify> oaNoteClassifyList){
		int i = 0;
		try {
			i = oaNoteClassifyDao.updateBatchOaNoteClassifyBySelective(oaNoteClassifyList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
