package jehc.cmsmodules.cmsservice;
import java.util.List;
import java.util.Map;
import jehc.cmsmodules.cmsmodel.CmsNews;

/**
* 内容发布平台新闻 
* 2018-06-10 14:56:47  邓纯杰
*/
public interface CmsNewsService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CmsNews> getCmsNewsListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param cms_news_id 
	* @return
	*/
	public CmsNews getCmsNewsById(String cms_news_id);
	/**
	* 添加
	* @param cms_news 
	* @return
	*/
	public int addCmsNews(CmsNews cmsNews);
	/**
	* 修改
	* @param cms_news 
	* @return
	*/
	public int updateCmsNews(CmsNews cmsNews);
	/**
	* 修改（根据动态条件）
	* @param cms_news 
	* @return
	*/
	public int updateCmsNewsBySelective(CmsNews cmsNews);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCmsNews(Map<String,Object> condition);
	/**
	* 批量添加
	* @param cms_newsList 
	* @return
	*/
	public int addBatchCmsNews(List<CmsNews> cmsNewsList);
	/**
	* 批量修改
	* @param cms_newsList 
	* @return
	*/
	public int updateBatchCmsNews(List<CmsNews> cmsNewsList);
	/**
	* 批量修改（根据动态条件）
	* @param cms_newsList 
	* @return
	*/
	public int updateBatchCmsNewsBySelective(List<CmsNews> cmsNewsList);
}
