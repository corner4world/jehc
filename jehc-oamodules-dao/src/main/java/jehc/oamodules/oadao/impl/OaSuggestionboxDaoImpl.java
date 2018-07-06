package jehc.oamodules.oadao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.oamodules.oadao.OaSuggestionboxDao;
import jehc.oamodules.oamodel.OaSuggestionbox;

/**
* 意见申诉 
* 2018-07-06 20:11:52  邓纯杰
*/
@Repository("oaSuggestionboxDao")
public class OaSuggestionboxDaoImpl  extends BaseDaoImpl implements OaSuggestionboxDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<OaSuggestionbox> getOaSuggestionboxListByCondition(Map<String,Object> condition){
		return (List<OaSuggestionbox>)this.getList("getOaSuggestionboxListByCondition",condition);
	}
	/**
	* 查询对象
	* @param oa_suggestionbox_id 
	* @return
	*/
	public OaSuggestionbox getOaSuggestionboxById(String oa_suggestionbox_id){
		return (OaSuggestionbox)this.get("getOaSuggestionboxById", oa_suggestionbox_id);
	}
	/**
	* 添加
	* @param oa_suggestionbox 
	* @return
	*/
	public int addOaSuggestionbox(OaSuggestionbox oaSuggestionbox){
		return this.add("addOaSuggestionbox", oaSuggestionbox);
	}
	/**
	* 修改
	* @param oa_suggestionbox 
	* @return
	*/
	public int updateOaSuggestionbox(OaSuggestionbox oaSuggestionbox){
		return this.update("updateOaSuggestionbox", oaSuggestionbox);
	}
	/**
	* 修改（根据动态条件）
	* @param oa_suggestionbox 
	* @return
	*/
	public int updateOaSuggestionboxBySelective(OaSuggestionbox oaSuggestionbox){
		return this.update("updateOaSuggestionboxBySelective", oaSuggestionbox);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delOaSuggestionbox(Map<String,Object> condition){
		return this.update("delOaSuggestionbox", condition);
	}
	/**
	* 批量添加
	* @param oa_suggestionboxList 
	* @return
	*/
	public int addBatchOaSuggestionbox(List<OaSuggestionbox> oaSuggestionboxList){
		return this.add("addBatchOaSuggestionbox", oaSuggestionboxList);
	}
	/**
	* 批量修改
	* @param oa_suggestionboxList 
	* @return
	*/
	public int updateBatchOaSuggestionbox(List<OaSuggestionbox> oaSuggestionboxList){
		return this.update("updateBatchOaSuggestionbox", oaSuggestionboxList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param oa_suggestionboxList 
	* @return
	*/
	public int updateBatchOaSuggestionboxBySelective(List<OaSuggestionbox> oaSuggestionboxList){
		return this.update("updateBatchOaSuggestionboxBySelective", oaSuggestionboxList);
	}
}
