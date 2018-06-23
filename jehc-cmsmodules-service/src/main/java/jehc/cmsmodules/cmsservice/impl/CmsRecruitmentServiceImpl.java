package jehc.cmsmodules.cmsservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.cmsmodules.cmsservice.CmsRecruitmentService;
import jehc.cmsmodules.cmsdao.CmsRecruitmentDao;
import jehc.cmsmodules.cmsmodel.CmsRecruitment;

/**
* 内容发布平台招贤纳士 
* 2018-06-10 15:11:58  邓纯杰
*/
@Service("cmsRecruitmentService")
public class CmsRecruitmentServiceImpl extends BaseService implements CmsRecruitmentService{
	@Autowired
	private CmsRecruitmentDao cmsRecruitmentDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CmsRecruitment> getCmsRecruitmentListByCondition(Map<String,Object> condition){
		try{
			return cmsRecruitmentDao.getCmsRecruitmentListByCondition(condition);
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param cms_recruitment_id 
	* @return
	*/
	public CmsRecruitment getCmsRecruitmentById(String cms_recruitment_id){
		try{
			CmsRecruitment cmsRecruitment = cmsRecruitmentDao.getCmsRecruitmentById(cms_recruitment_id);
			return cmsRecruitment;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param cms_recruitment 
	* @return
	*/
	public int addCmsRecruitment(CmsRecruitment cmsRecruitment){
		int i = 0;
		try {
			i = cmsRecruitmentDao.addCmsRecruitment(cmsRecruitment);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param cms_recruitment 
	* @return
	*/
	public int updateCmsRecruitment(CmsRecruitment cmsRecruitment){
		int i = 0;
		try {
			i = cmsRecruitmentDao.updateCmsRecruitment(cmsRecruitment);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param cms_recruitment 
	* @return
	*/
	public int updateCmsRecruitmentBySelective(CmsRecruitment cmsRecruitment){
		int i = 0;
		try {
			i = cmsRecruitmentDao.updateCmsRecruitmentBySelective(cmsRecruitment);
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
	public int delCmsRecruitment(Map<String,Object> condition){
		int i = 0;
		try {
			i = cmsRecruitmentDao.delCmsRecruitment(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param cms_recruitmentList 
	* @return
	*/
	public int addBatchCmsRecruitment(List<CmsRecruitment> cmsRecruitmentList){
		int i = 0;
		try {
			i = cmsRecruitmentDao.addBatchCmsRecruitment(cmsRecruitmentList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param cms_recruitmentList 
	* @return
	*/
	public int updateBatchCmsRecruitment(List<CmsRecruitment> cmsRecruitmentList){
		int i = 0;
		try {
			i = cmsRecruitmentDao.updateBatchCmsRecruitment(cmsRecruitmentList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param cms_recruitmentList 
	* @return
	*/
	public int updateBatchCmsRecruitmentBySelective(List<CmsRecruitment> cmsRecruitmentList){
		int i = 0;
		try {
			i = cmsRecruitmentDao.updateBatchCmsRecruitmentBySelective(cmsRecruitmentList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
