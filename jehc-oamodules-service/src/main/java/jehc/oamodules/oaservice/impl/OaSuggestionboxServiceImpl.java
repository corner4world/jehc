package jehc.oamodules.oaservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.oamodules.oaservice.OaSuggestionboxService;
import jehc.oamodules.oadao.OaSuggestionboxDao;
import jehc.oamodules.oamodel.OaSuggestionbox;

/**
* 意见申诉 
* 2018-07-06 20:11:52  邓纯杰
*/
@Service("oaSuggestionboxService")
public class OaSuggestionboxServiceImpl extends BaseService implements OaSuggestionboxService{
	@Autowired
	private OaSuggestionboxDao oaSuggestionboxDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<OaSuggestionbox> getOaSuggestionboxListByCondition(Map<String,Object> condition){
		try{
			return oaSuggestionboxDao.getOaSuggestionboxListByCondition(condition);
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param oa_suggestionbox_id 
	* @return
	*/
	public OaSuggestionbox getOaSuggestionboxById(String oa_suggestionbox_id){
		try{
			OaSuggestionbox oaSuggestionbox = oaSuggestionboxDao.getOaSuggestionboxById(oa_suggestionbox_id);
			return oaSuggestionbox;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param oa_suggestionbox 
	* @return
	*/
	public int addOaSuggestionbox(OaSuggestionbox oaSuggestionbox){
		int i = 0;
		try {
			i = oaSuggestionboxDao.addOaSuggestionbox(oaSuggestionbox);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param oa_suggestionbox 
	* @return
	*/
	public int updateOaSuggestionbox(OaSuggestionbox oaSuggestionbox){
		int i = 0;
		try {
			i = oaSuggestionboxDao.updateOaSuggestionbox(oaSuggestionbox);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param oa_suggestionbox 
	* @return
	*/
	public int updateOaSuggestionboxBySelective(OaSuggestionbox oaSuggestionbox){
		int i = 0;
		try {
			i = oaSuggestionboxDao.updateOaSuggestionboxBySelective(oaSuggestionbox);
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
	public int delOaSuggestionbox(Map<String,Object> condition){
		int i = 0;
		try {
			i = oaSuggestionboxDao.delOaSuggestionbox(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param oa_suggestionboxList 
	* @return
	*/
	public int addBatchOaSuggestionbox(List<OaSuggestionbox> oaSuggestionboxList){
		int i = 0;
		try {
			i = oaSuggestionboxDao.addBatchOaSuggestionbox(oaSuggestionboxList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param oa_suggestionboxList 
	* @return
	*/
	public int updateBatchOaSuggestionbox(List<OaSuggestionbox> oaSuggestionboxList){
		int i = 0;
		try {
			i = oaSuggestionboxDao.updateBatchOaSuggestionbox(oaSuggestionboxList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param oa_suggestionboxList 
	* @return
	*/
	public int updateBatchOaSuggestionboxBySelective(List<OaSuggestionbox> oaSuggestionboxList){
		int i = 0;
		try {
			i = oaSuggestionboxDao.updateBatchOaSuggestionboxBySelective(oaSuggestionboxList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
