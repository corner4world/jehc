package jehc.cmsmodules.cmsservice;
import java.util.List;
import java.util.Map;
import jehc.cmsmodules.cmsmodel.CmsVideo;

/**
* 内容发布平台视频 
* 2018-06-25 21:50:52  邓纯杰
*/
public interface CmsVideoService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CmsVideo> getCmsVideoListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param cms_video_id 
	* @return
	*/
	public CmsVideo getCmsVideoById(String cms_video_id);
	/**
	* 添加
	* @param cms_video 
	* @return
	*/
	public int addCmsVideo(CmsVideo cmsVideo);
	/**
	* 修改
	* @param cms_video 
	* @return
	*/
	public int updateCmsVideo(CmsVideo cmsVideo);
	/**
	* 修改（根据动态条件）
	* @param cms_video 
	* @return
	*/
	public int updateCmsVideoBySelective(CmsVideo cmsVideo);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCmsVideo(Map<String,Object> condition);
	/**
	* 批量添加
	* @param cms_videoList 
	* @return
	*/
	public int addBatchCmsVideo(List<CmsVideo> cmsVideoList);
	/**
	* 批量修改
	* @param cms_videoList 
	* @return
	*/
	public int updateBatchCmsVideo(List<CmsVideo> cmsVideoList);
	/**
	* 批量修改（根据动态条件）
	* @param cms_videoList 
	* @return
	*/
	public int updateBatchCmsVideoBySelective(List<CmsVideo> cmsVideoList);
}
