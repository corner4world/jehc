package jehc.cmsmodules.cmsdao;
import java.util.List;
import java.util.Map;
import jehc.cmsmodules.cmsmodel.CmsCase;

/**
* 内容发布平台案例 
* 2018-06-10 14:38:40  邓纯杰
*/
public interface CmsCaseDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CmsCase> getCmsCaseListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param cms_case_id 
	* @return
	*/
	public CmsCase getCmsCaseById(String cms_case_id);
	/**
	* 添加
	* @param cms_case 
	* @return
	*/
	public int addCmsCase(CmsCase cmsCase);
	/**
	* 修改
	* @param cms_case 
	* @return
	*/
	public int updateCmsCase(CmsCase cmsCase);
	/**
	* 修改（根据动态条件）
	* @param cms_case 
	* @return
	*/
	public int updateCmsCaseBySelective(CmsCase cmsCase);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCmsCase(Map<String,Object> condition);
	/**
	* 批量添加
	* @param cms_caseList 
	* @return
	*/
	public int addBatchCmsCase(List<CmsCase> cmsCaseList);
	/**
	* 批量修改
	* @param cms_caseList 
	* @return
	*/
	public int updateBatchCmsCase(List<CmsCase> cmsCaseList);
	/**
	* 批量修改（根据动态条件）
	* @param cms_caseList 
	* @return
	*/
	public int updateBatchCmsCaseBySelective(List<CmsCase> cmsCaseList);
}
