package jehc.oamodules.oadao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.oamodules.oadao.OaNoteDao;
import jehc.oamodules.oamodel.OaNote;

/**
* 记事本 
* 2018-07-05 19:35:07  邓纯杰
*/
@Repository("oaNoteDao")
public class OaNoteDaoImpl  extends BaseDaoImpl implements OaNoteDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<OaNote> getOaNoteListByCondition(Map<String,Object> condition){
		return (List<OaNote>)this.getList("getOaNoteListByCondition",condition);
	}
	/**
	* 查询对象
	* @param oa_note_id 
	* @return
	*/
	public OaNote getOaNoteById(String oa_note_id){
		return (OaNote)this.get("getOaNoteById", oa_note_id);
	}
	/**
	* 添加
	* @param oa_note 
	* @return
	*/
	public int addOaNote(OaNote oaNote){
		return this.add("addOaNote", oaNote);
	}
	/**
	* 修改
	* @param oa_note 
	* @return
	*/
	public int updateOaNote(OaNote oaNote){
		return this.update("updateOaNote", oaNote);
	}
	/**
	* 修改（根据动态条件）
	* @param oa_note 
	* @return
	*/
	public int updateOaNoteBySelective(OaNote oaNote){
		return this.update("updateOaNoteBySelective", oaNote);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delOaNote(Map<String,Object> condition){
		return this.del("delOaNote", condition);
	}
	/**
	* 批量添加
	* @param oa_noteList 
	* @return
	*/
	public int addBatchOaNote(List<OaNote> oaNoteList){
		return this.add("addBatchOaNote", oaNoteList);
	}
	/**
	* 批量修改
	* @param oa_noteList 
	* @return
	*/
	public int updateBatchOaNote(List<OaNote> oaNoteList){
		return this.update("updateBatchOaNote", oaNoteList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param oa_noteList 
	* @return
	*/
	public int updateBatchOaNoteBySelective(List<OaNote> oaNoteList){
		return this.update("updateBatchOaNoteBySelective", oaNoteList);
	}
}
