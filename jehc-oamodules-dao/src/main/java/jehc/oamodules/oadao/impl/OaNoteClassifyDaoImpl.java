package jehc.oamodules.oadao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.oamodules.oadao.OaNoteClassifyDao;
import jehc.oamodules.oamodel.OaNoteClassify;

/**
* 记事本分类 
* 2018-07-04 21:06:37  邓纯杰
*/
@Repository("oaNoteClassifyDao")
public class OaNoteClassifyDaoImpl  extends BaseDaoImpl implements OaNoteClassifyDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<OaNoteClassify> getOaNoteClassifyListByCondition(Map<String,Object> condition){
		return (List<OaNoteClassify>)this.getList("getOaNoteClassifyListByCondition",condition);
	}
	/**
	* 查询对象
	* @param oa_note_classify_id 
	* @return
	*/
	public OaNoteClassify getOaNoteClassifyById(String oa_note_classify_id){
		return (OaNoteClassify)this.get("getOaNoteClassifyById", oa_note_classify_id);
	}
	/**
	* 添加
	* @param oa_note_classify 
	* @return
	*/
	public int addOaNoteClassify(OaNoteClassify oaNoteClassify){
		return this.add("addOaNoteClassify", oaNoteClassify);
	}
	/**
	* 修改
	* @param oa_note_classify 
	* @return
	*/
	public int updateOaNoteClassify(OaNoteClassify oaNoteClassify){
		return this.update("updateOaNoteClassify", oaNoteClassify);
	}
	/**
	* 修改（根据动态条件）
	* @param oa_note_classify 
	* @return
	*/
	public int updateOaNoteClassifyBySelective(OaNoteClassify oaNoteClassify){
		return this.update("updateOaNoteClassifyBySelective", oaNoteClassify);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delOaNoteClassify(Map<String,Object> condition){
		return this.update("delOaNoteClassify", condition);
	}
	/**
	* 批量添加
	* @param oa_note_classifyList 
	* @return
	*/
	public int addBatchOaNoteClassify(List<OaNoteClassify> oaNoteClassifyList){
		return this.add("addBatchOaNoteClassify", oaNoteClassifyList);
	}
	/**
	* 批量修改
	* @param oa_note_classifyList 
	* @return
	*/
	public int updateBatchOaNoteClassify(List<OaNoteClassify> oaNoteClassifyList){
		return this.update("updateBatchOaNoteClassify", oaNoteClassifyList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param oa_note_classifyList 
	* @return
	*/
	public int updateBatchOaNoteClassifyBySelective(List<OaNoteClassify> oaNoteClassifyList){
		return this.update("updateBatchOaNoteClassifyBySelective", oaNoteClassifyList);
	}
}
