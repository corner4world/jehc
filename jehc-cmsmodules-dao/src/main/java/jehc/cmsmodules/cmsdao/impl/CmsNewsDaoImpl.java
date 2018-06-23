package jehc.cmsmodules.cmsdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.cmsmodules.cmsdao.CmsNewsDao;
import jehc.cmsmodules.cmsmodel.CmsNews;

/**
* 内容发布平台新闻 
* 2018-06-10 14:56:47  邓纯杰
*/
@Repository("cmsNewsDao")
public class CmsNewsDaoImpl  extends BaseDaoImpl implements CmsNewsDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<CmsNews> getCmsNewsListByCondition(Map<String,Object> condition){
		return (List<CmsNews>)this.getList("getCmsNewsListByCondition",condition);
	}
	/**
	* 查询对象
	* @param cms_news_id 
	* @return
	*/
	public CmsNews getCmsNewsById(String cms_news_id){
		return (CmsNews)this.get("getCmsNewsById", cms_news_id);
	}
	/**
	* 添加
	* @param cms_news 
	* @return
	*/
	public int addCmsNews(CmsNews cmsNews){
		return this.add("addCmsNews", cmsNews);
	}
	/**
	* 修改
	* @param cms_news 
	* @return
	*/
	public int updateCmsNews(CmsNews cmsNews){
		return this.update("updateCmsNews", cmsNews);
	}
	/**
	* 修改（根据动态条件）
	* @param cms_news 
	* @return
	*/
	public int updateCmsNewsBySelective(CmsNews cmsNews){
		return this.update("updateCmsNewsBySelective", cmsNews);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCmsNews(Map<String,Object> condition){
		return this.del("delCmsNews", condition);
	}
	/**
	* 批量添加
	* @param cms_newsList 
	* @return
	*/
	public int addBatchCmsNews(List<CmsNews> cmsNewsList){
		return this.add("addBatchCmsNews", cmsNewsList);
	}
	/**
	* 批量修改
	* @param cms_newsList 
	* @return
	*/
	public int updateBatchCmsNews(List<CmsNews> cmsNewsList){
		return this.update("updateBatchCmsNews", cmsNewsList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param cms_newsList 
	* @return
	*/
	public int updateBatchCmsNewsBySelective(List<CmsNews> cmsNewsList){
		return this.update("updateBatchCmsNewsBySelective", cmsNewsList);
	}
}
