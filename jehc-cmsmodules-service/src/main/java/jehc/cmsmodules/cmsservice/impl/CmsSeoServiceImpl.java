package jehc.cmsmodules.cmsservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.cmsmodules.cmsservice.CmsSeoService;
import jehc.cmsmodules.cmsdao.CmsSeoDao;
import jehc.cmsmodules.cmsmodel.CmsSeo;

/**
* 内容发布平台SEO配置 
* 2018-06-10 15:15:07  邓纯杰
*/
@Service("cmsSeoService")
public class CmsSeoServiceImpl extends BaseService implements CmsSeoService{
	@Autowired
	private CmsSeoDao cmsSeoDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CmsSeo> getCmsSeoListByCondition(Map<String,Object> condition){
		try{
			return cmsSeoDao.getCmsSeoListByCondition(condition);
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param cms_seo_id 
	* @return
	*/
	public CmsSeo getCmsSeoById(String cms_seo_id){
		try{
			CmsSeo cmsSeo = cmsSeoDao.getCmsSeoById(cms_seo_id);
			return cmsSeo;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param cms_seo 
	* @return
	*/
	public int addCmsSeo(CmsSeo cmsSeo){
		int i = 0;
		try {
			i = cmsSeoDao.addCmsSeo(cmsSeo);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param cms_seo 
	* @return
	*/
	public int updateCmsSeo(CmsSeo cmsSeo){
		int i = 0;
		try {
			i = cmsSeoDao.updateCmsSeo(cmsSeo);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param cms_seo 
	* @return
	*/
	public int updateCmsSeoBySelective(CmsSeo cmsSeo){
		int i = 0;
		try {
			i = cmsSeoDao.updateCmsSeoBySelective(cmsSeo);
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
	public int delCmsSeo(Map<String,Object> condition){
		int i = 0;
		try {
			i = cmsSeoDao.delCmsSeo(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param cms_seoList 
	* @return
	*/
	public int addBatchCmsSeo(List<CmsSeo> cmsSeoList){
		int i = 0;
		try {
			i = cmsSeoDao.addBatchCmsSeo(cmsSeoList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param cms_seoList 
	* @return
	*/
	public int updateBatchCmsSeo(List<CmsSeo> cmsSeoList){
		int i = 0;
		try {
			i = cmsSeoDao.updateBatchCmsSeo(cmsSeoList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param cms_seoList 
	* @return
	*/
	public int updateBatchCmsSeoBySelective(List<CmsSeo> cmsSeoList){
		int i = 0;
		try {
			i = cmsSeoDao.updateBatchCmsSeoBySelective(cmsSeoList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
