package jehc.cmsmodules.cmsdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.cmsmodules.cmsdao.CmsVideoDao;
import jehc.cmsmodules.cmsmodel.CmsVideo;

/**
* 内容发布平台视频 
* 2018-06-25 21:50:52  邓纯杰
*/
@Repository("cmsVideoDao")
public class CmsVideoDaoImpl  extends BaseDaoImpl implements CmsVideoDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<CmsVideo> getCmsVideoListByCondition(Map<String,Object> condition){
		return (List<CmsVideo>)this.getList("getCmsVideoListByCondition",condition);
	}
	/**
	* 查询对象
	* @param cms_video_id 
	* @return
	*/
	public CmsVideo getCmsVideoById(String cms_video_id){
		return (CmsVideo)this.get("getCmsVideoById", cms_video_id);
	}
	/**
	* 添加
	* @param cms_video 
	* @return
	*/
	public int addCmsVideo(CmsVideo cmsVideo){
		return this.add("addCmsVideo", cmsVideo);
	}
	/**
	* 修改
	* @param cms_video 
	* @return
	*/
	public int updateCmsVideo(CmsVideo cmsVideo){
		return this.update("updateCmsVideo", cmsVideo);
	}
	/**
	* 修改（根据动态条件）
	* @param cms_video 
	* @return
	*/
	public int updateCmsVideoBySelective(CmsVideo cmsVideo){
		return this.update("updateCmsVideoBySelective", cmsVideo);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCmsVideo(Map<String,Object> condition){
		return this.del("delCmsVideo", condition);
	}
	/**
	* 批量添加
	* @param cms_videoList 
	* @return
	*/
	public int addBatchCmsVideo(List<CmsVideo> cmsVideoList){
		return this.add("addBatchCmsVideo", cmsVideoList);
	}
	/**
	* 批量修改
	* @param cms_videoList 
	* @return
	*/
	public int updateBatchCmsVideo(List<CmsVideo> cmsVideoList){
		return this.update("updateBatchCmsVideo", cmsVideoList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param cms_videoList 
	* @return
	*/
	public int updateBatchCmsVideoBySelective(List<CmsVideo> cmsVideoList){
		return this.update("updateBatchCmsVideoBySelective", cmsVideoList);
	}
}
