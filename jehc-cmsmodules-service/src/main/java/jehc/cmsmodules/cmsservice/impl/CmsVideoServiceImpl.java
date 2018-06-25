package jehc.cmsmodules.cmsservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.cmsmodules.cmsservice.CmsVideoService;
import jehc.cmsmodules.cmsdao.CmsVideoDao;
import jehc.cmsmodules.cmsmodel.CmsVideo;

/**
* 内容发布平台视频 
* 2018-06-25 21:50:52  邓纯杰
*/
@Service("cmsVideoService")
public class CmsVideoServiceImpl extends BaseService implements CmsVideoService{
	@Autowired
	private CmsVideoDao cmsVideoDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CmsVideo> getCmsVideoListByCondition(Map<String,Object> condition){
		try{
			return cmsVideoDao.getCmsVideoListByCondition(condition);
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param cms_video_id 
	* @return
	*/
	public CmsVideo getCmsVideoById(String cms_video_id){
		try{
			CmsVideo cmsVideo = cmsVideoDao.getCmsVideoById(cms_video_id);
			return cmsVideo;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param cms_video 
	* @return
	*/
	public int addCmsVideo(CmsVideo cmsVideo){
		int i = 0;
		try {
			i = cmsVideoDao.addCmsVideo(cmsVideo);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param cms_video 
	* @return
	*/
	public int updateCmsVideo(CmsVideo cmsVideo){
		int i = 0;
		try {
			i = cmsVideoDao.updateCmsVideo(cmsVideo);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param cms_video 
	* @return
	*/
	public int updateCmsVideoBySelective(CmsVideo cmsVideo){
		int i = 0;
		try {
			i = cmsVideoDao.updateCmsVideoBySelective(cmsVideo);
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
	public int delCmsVideo(Map<String,Object> condition){
		int i = 0;
		try {
			i = cmsVideoDao.delCmsVideo(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param cms_videoList 
	* @return
	*/
	public int addBatchCmsVideo(List<CmsVideo> cmsVideoList){
		int i = 0;
		try {
			i = cmsVideoDao.addBatchCmsVideo(cmsVideoList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param cms_videoList 
	* @return
	*/
	public int updateBatchCmsVideo(List<CmsVideo> cmsVideoList){
		int i = 0;
		try {
			i = cmsVideoDao.updateBatchCmsVideo(cmsVideoList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param cms_videoList 
	* @return
	*/
	public int updateBatchCmsVideoBySelective(List<CmsVideo> cmsVideoList){
		int i = 0;
		try {
			i = cmsVideoDao.updateBatchCmsVideoBySelective(cmsVideoList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
