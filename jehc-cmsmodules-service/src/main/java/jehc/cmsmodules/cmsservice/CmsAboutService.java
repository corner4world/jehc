package jehc.cmsmodules.cmsservice;
import java.util.List;
import java.util.Map;
import jehc.cmsmodules.cmsmodel.CmsAbout;

/**
* 内容发布平台关于我们 
* 2018-06-10 17:56:24  邓纯杰
*/
public interface CmsAboutService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CmsAbout> getCmsAboutListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param cms_about_id 
	* @return
	*/
	public CmsAbout getCmsAboutById(String cms_about_id);
	/**
	* 添加
	* @param cms_about 
	* @return
	*/
	public int addCmsAbout(CmsAbout cmsAbout);
	/**
	* 修改
	* @param cms_about 
	* @return
	*/
	public int updateCmsAbout(CmsAbout cmsAbout);
	/**
	* 修改（根据动态条件）
	* @param cms_about 
	* @return
	*/
	public int updateCmsAboutBySelective(CmsAbout cmsAbout);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCmsAbout(Map<String,Object> condition);
	/**
	* 批量添加
	* @param cms_aboutList 
	* @return
	*/
	public int addBatchCmsAbout(List<CmsAbout> cmsAboutList);
	/**
	* 批量修改
	* @param cms_aboutList 
	* @return
	*/
	public int updateBatchCmsAbout(List<CmsAbout> cmsAboutList);
	/**
	* 批量修改（根据动态条件）
	* @param cms_aboutList 
	* @return
	*/
	public int updateBatchCmsAboutBySelective(List<CmsAbout> cmsAboutList);
}
