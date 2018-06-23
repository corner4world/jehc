package jehc.cmsmodules.cmsservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.cmsmodules.cmsservice.CmsNewsCategroyService;
import jehc.cmsmodules.cmsdao.CmsNewsCategroyDao;
import jehc.cmsmodules.cmsmodel.CmsNewsCategroy;

/**
* 内容发布平台新闻分类 
* 2018-06-10 15:01:32  邓纯杰
*/
@Service("cmsNewsCategroyService")
public class CmsNewsCategroyServiceImpl extends BaseService implements CmsNewsCategroyService{
	@Autowired
	private CmsNewsCategroyDao cmsNewsCategroyDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CmsNewsCategroy> getCmsNewsCategroyListByCondition(Map<String,Object> condition){
		try{
			return cmsNewsCategroyDao.getCmsNewsCategroyListByCondition(condition);
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param cms_news_categroy_id 
	* @return
	*/
	public CmsNewsCategroy getCmsNewsCategroyById(String cms_news_categroy_id){
		try{
			CmsNewsCategroy cmsNewsCategroy = cmsNewsCategroyDao.getCmsNewsCategroyById(cms_news_categroy_id);
			return cmsNewsCategroy;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param cms_news_categroy 
	* @return
	*/
	public int addCmsNewsCategroy(CmsNewsCategroy cmsNewsCategroy){
		int i = 0;
		try {
			i = cmsNewsCategroyDao.addCmsNewsCategroy(cmsNewsCategroy);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param cms_news_categroy 
	* @return
	*/
	public int updateCmsNewsCategroy(CmsNewsCategroy cmsNewsCategroy){
		int i = 0;
		try {
			i = cmsNewsCategroyDao.updateCmsNewsCategroy(cmsNewsCategroy);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param cms_news_categroy 
	* @return
	*/
	public int updateCmsNewsCategroyBySelective(CmsNewsCategroy cmsNewsCategroy){
		int i = 0;
		try {
			i = cmsNewsCategroyDao.updateCmsNewsCategroyBySelective(cmsNewsCategroy);
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
	public int delCmsNewsCategroy(Map<String,Object> condition){
		int i = 0;
		try {
			i = cmsNewsCategroyDao.delCmsNewsCategroy(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param cms_news_categroyList 
	* @return
	*/
	public int addBatchCmsNewsCategroy(List<CmsNewsCategroy> cmsNewsCategroyList){
		int i = 0;
		try {
			i = cmsNewsCategroyDao.addBatchCmsNewsCategroy(cmsNewsCategroyList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param cms_news_categroyList 
	* @return
	*/
	public int updateBatchCmsNewsCategroy(List<CmsNewsCategroy> cmsNewsCategroyList){
		int i = 0;
		try {
			i = cmsNewsCategroyDao.updateBatchCmsNewsCategroy(cmsNewsCategroyList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param cms_news_categroyList 
	* @return
	*/
	public int updateBatchCmsNewsCategroyBySelective(List<CmsNewsCategroy> cmsNewsCategroyList){
		int i = 0;
		try {
			i = cmsNewsCategroyDao.updateBatchCmsNewsCategroyBySelective(cmsNewsCategroyList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
