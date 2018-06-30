package jehc.cmsmodules.cmsservice;
import java.util.List;
import java.util.Map;
import jehc.cmsmodules.cmsmodel.CmsSlide;

/**
* 内容发布平台幻灯片 
* 2018-06-27 12:44:03  邓纯杰
*/
public interface CmsSlideService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CmsSlide> getCmsSlideListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param cms_slide_id 
	* @return
	*/
	public CmsSlide getCmsSlideById(String cms_slide_id);
	/**
	* 添加
	* @param cms_slide 
	* @return
	*/
	public int addCmsSlide(CmsSlide cmsSlide);
	/**
	* 修改
	* @param cms_slide 
	* @return
	*/
	public int updateCmsSlide(CmsSlide cmsSlide);
	/**
	* 修改（根据动态条件）
	* @param cms_slide 
	* @return
	*/
	public int updateCmsSlideBySelective(CmsSlide cmsSlide);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCmsSlide(Map<String,Object> condition);
	/**
	* 批量添加
	* @param cms_slideList 
	* @return
	*/
	public int addBatchCmsSlide(List<CmsSlide> cmsSlideList);
	/**
	* 批量修改
	* @param cms_slideList 
	* @return
	*/
	public int updateBatchCmsSlide(List<CmsSlide> cmsSlideList);
	/**
	* 批量修改（根据动态条件）
	* @param cms_slideList 
	* @return
	*/
	public int updateBatchCmsSlideBySelective(List<CmsSlide> cmsSlideList);
}
