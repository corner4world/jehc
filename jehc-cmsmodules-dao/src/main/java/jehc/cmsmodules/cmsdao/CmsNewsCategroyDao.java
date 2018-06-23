package jehc.cmsmodules.cmsdao;
import java.util.List;
import java.util.Map;
import jehc.cmsmodules.cmsmodel.CmsNewsCategroy;

/**
* 内容发布平台新闻分类 
* 2018-06-10 15:01:32  邓纯杰
*/
public interface CmsNewsCategroyDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CmsNewsCategroy> getCmsNewsCategroyListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param cms_news_categroy_id 
	* @return
	*/
	public CmsNewsCategroy getCmsNewsCategroyById(String cms_news_categroy_id);
	/**
	* 添加
	* @param cms_news_categroy 
	* @return
	*/
	public int addCmsNewsCategroy(CmsNewsCategroy cmsNewsCategroy);
	/**
	* 修改
	* @param cms_news_categroy 
	* @return
	*/
	public int updateCmsNewsCategroy(CmsNewsCategroy cmsNewsCategroy);
	/**
	* 修改（根据动态条件）
	* @param cms_news_categroy 
	* @return
	*/
	public int updateCmsNewsCategroyBySelective(CmsNewsCategroy cmsNewsCategroy);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCmsNewsCategroy(Map<String,Object> condition);
	/**
	* 批量添加
	* @param cms_news_categroyList 
	* @return
	*/
	public int addBatchCmsNewsCategroy(List<CmsNewsCategroy> cmsNewsCategroyList);
	/**
	* 批量修改
	* @param cms_news_categroyList 
	* @return
	*/
	public int updateBatchCmsNewsCategroy(List<CmsNewsCategroy> cmsNewsCategroyList);
	/**
	* 批量修改（根据动态条件）
	* @param cms_news_categroyList 
	* @return
	*/
	public int updateBatchCmsNewsCategroyBySelective(List<CmsNewsCategroy> cmsNewsCategroyList);
}
