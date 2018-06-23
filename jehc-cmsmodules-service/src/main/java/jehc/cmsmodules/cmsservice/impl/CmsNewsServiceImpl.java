package jehc.cmsmodules.cmsservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.cmsmodules.cmsservice.CmsNewsService;
import jehc.cmsmodules.cmsdao.CmsNewsDao;
import jehc.cmsmodules.cmsmodel.CmsNews;

/**
* 内容发布平台新闻 
* 2018-06-10 14:56:47  邓纯杰
*/
@Service("cmsNewsService")
public class CmsNewsServiceImpl extends BaseService implements CmsNewsService{
	@Autowired
	private CmsNewsDao cmsNewsDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CmsNews> getCmsNewsListByCondition(Map<String,Object> condition){
		try{
			return cmsNewsDao.getCmsNewsListByCondition(condition);
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param cms_news_id 
	* @return
	*/
	public CmsNews getCmsNewsById(String cms_news_id){
		try{
			CmsNews cmsNews = cmsNewsDao.getCmsNewsById(cms_news_id);
			return cmsNews;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param cms_news 
	* @return
	*/
	public int addCmsNews(CmsNews cmsNews){
		int i = 0;
		try {
			i = cmsNewsDao.addCmsNews(cmsNews);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param cms_news 
	* @return
	*/
	public int updateCmsNews(CmsNews cmsNews){
		int i = 0;
		try {
			i = cmsNewsDao.updateCmsNews(cmsNews);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param cms_news 
	* @return
	*/
	public int updateCmsNewsBySelective(CmsNews cmsNews){
		int i = 0;
		try {
			i = cmsNewsDao.updateCmsNewsBySelective(cmsNews);
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
	public int delCmsNews(Map<String,Object> condition){
		int i = 0;
		try {
			i = cmsNewsDao.delCmsNews(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param cms_newsList 
	* @return
	*/
	public int addBatchCmsNews(List<CmsNews> cmsNewsList){
		int i = 0;
		try {
			i = cmsNewsDao.addBatchCmsNews(cmsNewsList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param cms_newsList 
	* @return
	*/
	public int updateBatchCmsNews(List<CmsNews> cmsNewsList){
		int i = 0;
		try {
			i = cmsNewsDao.updateBatchCmsNews(cmsNewsList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param cms_newsList 
	* @return
	*/
	public int updateBatchCmsNewsBySelective(List<CmsNews> cmsNewsList){
		int i = 0;
		try {
			i = cmsNewsDao.updateBatchCmsNewsBySelective(cmsNewsList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
