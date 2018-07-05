package jehc.oamodules.oaservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.oamodules.oaservice.OaNoteService;
import jehc.oamodules.oadao.OaNoteDao;
import jehc.oamodules.oamodel.OaNote;

/**
* 记事本 
* 2018-07-05 19:35:07  邓纯杰
*/
@Service("oaNoteService")
public class OaNoteServiceImpl extends BaseService implements OaNoteService{
	@Autowired
	private OaNoteDao oaNoteDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<OaNote> getOaNoteListByCondition(Map<String,Object> condition){
		try{
			return oaNoteDao.getOaNoteListByCondition(condition);
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param oa_note_id 
	* @return
	*/
	public OaNote getOaNoteById(String oa_note_id){
		try{
			OaNote oaNote = oaNoteDao.getOaNoteById(oa_note_id);
			return oaNote;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param oa_note 
	* @return
	*/
	public int addOaNote(OaNote oaNote){
		int i = 0;
		try {
			i = oaNoteDao.addOaNote(oaNote);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param oa_note 
	* @return
	*/
	public int updateOaNote(OaNote oaNote){
		int i = 0;
		try {
			i = oaNoteDao.updateOaNote(oaNote);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param oa_note 
	* @return
	*/
	public int updateOaNoteBySelective(OaNote oaNote){
		int i = 0;
		try {
			i = oaNoteDao.updateOaNoteBySelective(oaNote);
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
	public int delOaNote(Map<String,Object> condition){
		int i = 0;
		try {
			i = oaNoteDao.delOaNote(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param oa_noteList 
	* @return
	*/
	public int addBatchOaNote(List<OaNote> oaNoteList){
		int i = 0;
		try {
			i = oaNoteDao.addBatchOaNote(oaNoteList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param oa_noteList 
	* @return
	*/
	public int updateBatchOaNote(List<OaNote> oaNoteList){
		int i = 0;
		try {
			i = oaNoteDao.updateBatchOaNote(oaNoteList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param oa_noteList 
	* @return
	*/
	public int updateBatchOaNoteBySelective(List<OaNote> oaNoteList){
		int i = 0;
		try {
			i = oaNoteDao.updateBatchOaNoteBySelective(oaNoteList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
