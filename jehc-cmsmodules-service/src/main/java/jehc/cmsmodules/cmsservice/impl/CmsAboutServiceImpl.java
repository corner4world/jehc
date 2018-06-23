package jehc.cmsmodules.cmsservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.cmsmodules.cmsservice.CmsAboutService;
import jehc.cmsmodules.cmsdao.CmsAboutDao;
import jehc.cmsmodules.cmsmodel.CmsAbout;

/**
* 内容发布平台关于我们 
* 2018-06-10 17:56:24  邓纯杰
*/
@Service("cmsAboutService")
public class CmsAboutServiceImpl extends BaseService implements CmsAboutService{
	@Autowired
	private CmsAboutDao cmsAboutDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CmsAbout> getCmsAboutListByCondition(Map<String,Object> condition){
		try{
			return cmsAboutDao.getCmsAboutListByCondition(condition);
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param cms_about_id 
	* @return
	*/
	public CmsAbout getCmsAboutById(String cms_about_id){
		try{
			CmsAbout cmsAbout = cmsAboutDao.getCmsAboutById(cms_about_id);
			return cmsAbout;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param cms_about 
	* @return
	*/
	public int addCmsAbout(CmsAbout cmsAbout){
		int i = 0;
		try {
			i = cmsAboutDao.addCmsAbout(cmsAbout);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param cms_about 
	* @return
	*/
	public int updateCmsAbout(CmsAbout cmsAbout){
		int i = 0;
		try {
			i = cmsAboutDao.updateCmsAbout(cmsAbout);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param cms_about 
	* @return
	*/
	public int updateCmsAboutBySelective(CmsAbout cmsAbout){
		int i = 0;
		try {
			i = cmsAboutDao.updateCmsAboutBySelective(cmsAbout);
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
	public int delCmsAbout(Map<String,Object> condition){
		int i = 0;
		try {
			i = cmsAboutDao.delCmsAbout(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param cms_aboutList 
	* @return
	*/
	public int addBatchCmsAbout(List<CmsAbout> cmsAboutList){
		int i = 0;
		try {
			i = cmsAboutDao.addBatchCmsAbout(cmsAboutList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param cms_aboutList 
	* @return
	*/
	public int updateBatchCmsAbout(List<CmsAbout> cmsAboutList){
		int i = 0;
		try {
			i = cmsAboutDao.updateBatchCmsAbout(cmsAboutList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param cms_aboutList 
	* @return
	*/
	public int updateBatchCmsAboutBySelective(List<CmsAbout> cmsAboutList){
		int i = 0;
		try {
			i = cmsAboutDao.updateBatchCmsAboutBySelective(cmsAboutList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
