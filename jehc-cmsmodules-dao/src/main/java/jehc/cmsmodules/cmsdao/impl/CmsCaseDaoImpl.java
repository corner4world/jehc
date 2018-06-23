package jehc.cmsmodules.cmsdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.cmsmodules.cmsdao.CmsCaseDao;
import jehc.cmsmodules.cmsmodel.CmsCase;

/**
* 内容发布平台案例 
* 2018-06-10 14:38:40  邓纯杰
*/
@Repository("cmsCaseDao")
public class CmsCaseDaoImpl  extends BaseDaoImpl implements CmsCaseDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<CmsCase> getCmsCaseListByCondition(Map<String,Object> condition){
		return (List<CmsCase>)this.getList("getCmsCaseListByCondition",condition);
	}
	/**
	* 查询对象
	* @param cms_case_id 
	* @return
	*/
	public CmsCase getCmsCaseById(String cms_case_id){
		return (CmsCase)this.get("getCmsCaseById", cms_case_id);
	}
	/**
	* 添加
	* @param cms_case 
	* @return
	*/
	public int addCmsCase(CmsCase cmsCase){
		return this.add("addCmsCase", cmsCase);
	}
	/**
	* 修改
	* @param cms_case 
	* @return
	*/
	public int updateCmsCase(CmsCase cmsCase){
		return this.update("updateCmsCase", cmsCase);
	}
	/**
	* 修改（根据动态条件）
	* @param cms_case 
	* @return
	*/
	public int updateCmsCaseBySelective(CmsCase cmsCase){
		return this.update("updateCmsCaseBySelective", cmsCase);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCmsCase(Map<String,Object> condition){
		return this.del("delCmsCase", condition);
	}
	/**
	* 批量添加
	* @param cms_caseList 
	* @return
	*/
	public int addBatchCmsCase(List<CmsCase> cmsCaseList){
		return this.add("addBatchCmsCase", cmsCaseList);
	}
	/**
	* 批量修改
	* @param cms_caseList 
	* @return
	*/
	public int updateBatchCmsCase(List<CmsCase> cmsCaseList){
		return this.update("updateBatchCmsCase", cmsCaseList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param cms_caseList 
	* @return
	*/
	public int updateBatchCmsCaseBySelective(List<CmsCase> cmsCaseList){
		return this.update("updateBatchCmsCaseBySelective", cmsCaseList);
	}
}
