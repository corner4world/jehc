package jehc.cmsmodules.cmsservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.cmsmodules.cmsservice.CmsSlideService;
import jehc.cmsmodules.cmsdao.CmsSlideDao;
import jehc.cmsmodules.cmsmodel.CmsSlide;

/**
* 内容发布平台幻灯片 
* 2018-06-27 12:44:03  邓纯杰
*/
@Service("cmsSlideService")
public class CmsSlideServiceImpl extends BaseService implements CmsSlideService{
	@Autowired
	private CmsSlideDao cmsSlideDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CmsSlide> getCmsSlideListByCondition(Map<String,Object> condition){
		try{
			return cmsSlideDao.getCmsSlideListByCondition(condition);
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param cms_slide_id 
	* @return
	*/
	public CmsSlide getCmsSlideById(String cms_slide_id){
		try{
			CmsSlide cmsSlide = cmsSlideDao.getCmsSlideById(cms_slide_id);
			return cmsSlide;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param cms_slide 
	* @return
	*/
	public int addCmsSlide(CmsSlide cmsSlide){
		int i = 0;
		try {
			i = cmsSlideDao.addCmsSlide(cmsSlide);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param cms_slide 
	* @return
	*/
	public int updateCmsSlide(CmsSlide cmsSlide){
		int i = 0;
		try {
			i = cmsSlideDao.updateCmsSlide(cmsSlide);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param cms_slide 
	* @return
	*/
	public int updateCmsSlideBySelective(CmsSlide cmsSlide){
		int i = 0;
		try {
			i = cmsSlideDao.updateCmsSlideBySelective(cmsSlide);
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
	public int delCmsSlide(Map<String,Object> condition){
		int i = 0;
		try {
			i = cmsSlideDao.delCmsSlide(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param cms_slideList 
	* @return
	*/
	public int addBatchCmsSlide(List<CmsSlide> cmsSlideList){
		int i = 0;
		try {
			i = cmsSlideDao.addBatchCmsSlide(cmsSlideList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param cms_slideList 
	* @return
	*/
	public int updateBatchCmsSlide(List<CmsSlide> cmsSlideList){
		int i = 0;
		try {
			i = cmsSlideDao.updateBatchCmsSlide(cmsSlideList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param cms_slideList 
	* @return
	*/
	public int updateBatchCmsSlideBySelective(List<CmsSlide> cmsSlideList){
		int i = 0;
		try {
			i = cmsSlideDao.updateBatchCmsSlideBySelective(cmsSlideList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
