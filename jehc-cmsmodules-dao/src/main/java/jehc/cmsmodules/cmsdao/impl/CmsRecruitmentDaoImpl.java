package jehc.cmsmodules.cmsdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.cmsmodules.cmsdao.CmsRecruitmentDao;
import jehc.cmsmodules.cmsmodel.CmsRecruitment;

/**
* 内容发布平台招贤纳士 
* 2018-06-10 15:11:58  邓纯杰
*/
@Repository("cmsRecruitmentDao")
public class CmsRecruitmentDaoImpl  extends BaseDaoImpl implements CmsRecruitmentDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<CmsRecruitment> getCmsRecruitmentListByCondition(Map<String,Object> condition){
		return (List<CmsRecruitment>)this.getList("getCmsRecruitmentListByCondition",condition);
	}
	/**
	* 查询对象
	* @param cms_recruitment_id 
	* @return
	*/
	public CmsRecruitment getCmsRecruitmentById(String cms_recruitment_id){
		return (CmsRecruitment)this.get("getCmsRecruitmentById", cms_recruitment_id);
	}
	/**
	* 添加
	* @param cms_recruitment 
	* @return
	*/
	public int addCmsRecruitment(CmsRecruitment cmsRecruitment){
		return this.add("addCmsRecruitment", cmsRecruitment);
	}
	/**
	* 修改
	* @param cms_recruitment 
	* @return
	*/
	public int updateCmsRecruitment(CmsRecruitment cmsRecruitment){
		return this.update("updateCmsRecruitment", cmsRecruitment);
	}
	/**
	* 修改（根据动态条件）
	* @param cms_recruitment 
	* @return
	*/
	public int updateCmsRecruitmentBySelective(CmsRecruitment cmsRecruitment){
		return this.update("updateCmsRecruitmentBySelective", cmsRecruitment);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCmsRecruitment(Map<String,Object> condition){
		return this.del("delCmsRecruitment", condition);
	}
	/**
	* 批量添加
	* @param cms_recruitmentList 
	* @return
	*/
	public int addBatchCmsRecruitment(List<CmsRecruitment> cmsRecruitmentList){
		return this.add("addBatchCmsRecruitment", cmsRecruitmentList);
	}
	/**
	* 批量修改
	* @param cms_recruitmentList 
	* @return
	*/
	public int updateBatchCmsRecruitment(List<CmsRecruitment> cmsRecruitmentList){
		return this.update("updateBatchCmsRecruitment", cmsRecruitmentList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param cms_recruitmentList 
	* @return
	*/
	public int updateBatchCmsRecruitmentBySelective(List<CmsRecruitment> cmsRecruitmentList){
		return this.update("updateBatchCmsRecruitmentBySelective", cmsRecruitmentList);
	}
}
