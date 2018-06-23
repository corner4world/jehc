package jehc.cmsmodules.cmsdao;
import java.util.List;
import java.util.Map;
import jehc.cmsmodules.cmsmodel.CmsRecruitment;

/**
* 内容发布平台招贤纳士 
* 2018-06-10 15:11:58  邓纯杰
*/
public interface CmsRecruitmentDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CmsRecruitment> getCmsRecruitmentListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param cms_recruitment_id 
	* @return
	*/
	public CmsRecruitment getCmsRecruitmentById(String cms_recruitment_id);
	/**
	* 添加
	* @param cms_recruitment 
	* @return
	*/
	public int addCmsRecruitment(CmsRecruitment cmsRecruitment);
	/**
	* 修改
	* @param cms_recruitment 
	* @return
	*/
	public int updateCmsRecruitment(CmsRecruitment cmsRecruitment);
	/**
	* 修改（根据动态条件）
	* @param cms_recruitment 
	* @return
	*/
	public int updateCmsRecruitmentBySelective(CmsRecruitment cmsRecruitment);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCmsRecruitment(Map<String,Object> condition);
	/**
	* 批量添加
	* @param cms_recruitmentList 
	* @return
	*/
	public int addBatchCmsRecruitment(List<CmsRecruitment> cmsRecruitmentList);
	/**
	* 批量修改
	* @param cms_recruitmentList 
	* @return
	*/
	public int updateBatchCmsRecruitment(List<CmsRecruitment> cmsRecruitmentList);
	/**
	* 批量修改（根据动态条件）
	* @param cms_recruitmentList 
	* @return
	*/
	public int updateBatchCmsRecruitmentBySelective(List<CmsRecruitment> cmsRecruitmentList);
}
