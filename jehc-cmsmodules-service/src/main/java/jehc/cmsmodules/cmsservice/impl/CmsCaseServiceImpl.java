package jehc.cmsmodules.cmsservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.cmsmodules.cmsservice.CmsCaseService;
import jehc.cmsmodules.cmsdao.CmsCaseDao;
import jehc.cmsmodules.cmsmodel.CmsCase;

/**
* 内容发布平台案例 
* 2018-06-10 14:38:40  邓纯杰
*/
@Service("cmsCaseService")
public class CmsCaseServiceImpl extends BaseService implements CmsCaseService{
	@Autowired
	private CmsCaseDao cmsCaseDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CmsCase> getCmsCaseListByCondition(Map<String,Object> condition){
		try{
			return cmsCaseDao.getCmsCaseListByCondition(condition);
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param cms_case_id 
	* @return
	*/
	public CmsCase getCmsCaseById(String cms_case_id){
		try{
			CmsCase cmsCase = cmsCaseDao.getCmsCaseById(cms_case_id);
			return cmsCase;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param cms_case 
	* @return
	*/
	public int addCmsCase(CmsCase cmsCase){
		int i = 0;
		try {
			i = cmsCaseDao.addCmsCase(cmsCase);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param cms_case 
	* @return
	*/
	public int updateCmsCase(CmsCase cmsCase){
		int i = 0;
		try {
			i = cmsCaseDao.updateCmsCase(cmsCase);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param cms_case 
	* @return
	*/
	public int updateCmsCaseBySelective(CmsCase cmsCase){
		int i = 0;
		try {
			i = cmsCaseDao.updateCmsCaseBySelective(cmsCase);
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
	public int delCmsCase(Map<String,Object> condition){
		int i = 0;
		try {
			i = cmsCaseDao.delCmsCase(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param cms_caseList 
	* @return
	*/
	public int addBatchCmsCase(List<CmsCase> cmsCaseList){
		int i = 0;
		try {
			i = cmsCaseDao.addBatchCmsCase(cmsCaseList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param cms_caseList 
	* @return
	*/
	public int updateBatchCmsCase(List<CmsCase> cmsCaseList){
		int i = 0;
		try {
			i = cmsCaseDao.updateBatchCmsCase(cmsCaseList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param cms_caseList 
	* @return
	*/
	public int updateBatchCmsCaseBySelective(List<CmsCase> cmsCaseList){
		int i = 0;
		try {
			i = cmsCaseDao.updateBatchCmsCaseBySelective(cmsCaseList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
