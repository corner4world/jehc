package jehc.cmsmodules.cmsdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.cmsmodules.cmsdao.CmsNewsCategroyDao;
import jehc.cmsmodules.cmsmodel.CmsNewsCategroy;

/**
* 内容发布平台新闻分类 
* 2018-06-10 15:01:32  邓纯杰
*/
@Repository("cmsNewsCategroyDao")
public class CmsNewsCategroyDaoImpl  extends BaseDaoImpl implements CmsNewsCategroyDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<CmsNewsCategroy> getCmsNewsCategroyListByCondition(Map<String,Object> condition){
		return (List<CmsNewsCategroy>)this.getList("getCmsNewsCategroyListByCondition",condition);
	}
	/**
	* 查询对象
	* @param cms_news_categroy_id 
	* @return
	*/
	public CmsNewsCategroy getCmsNewsCategroyById(String cms_news_categroy_id){
		return (CmsNewsCategroy)this.get("getCmsNewsCategroyById", cms_news_categroy_id);
	}
	/**
	* 添加
	* @param cms_news_categroy 
	* @return
	*/
	public int addCmsNewsCategroy(CmsNewsCategroy cmsNewsCategroy){
		return this.add("addCmsNewsCategroy", cmsNewsCategroy);
	}
	/**
	* 修改
	* @param cms_news_categroy 
	* @return
	*/
	public int updateCmsNewsCategroy(CmsNewsCategroy cmsNewsCategroy){
		return this.update("updateCmsNewsCategroy", cmsNewsCategroy);
	}
	/**
	* 修改（根据动态条件）
	* @param cms_news_categroy 
	* @return
	*/
	public int updateCmsNewsCategroyBySelective(CmsNewsCategroy cmsNewsCategroy){
		return this.update("updateCmsNewsCategroyBySelective", cmsNewsCategroy);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCmsNewsCategroy(Map<String,Object> condition){
		return this.del("delCmsNewsCategroy", condition);
	}
	/**
	* 批量添加
	* @param cms_news_categroyList 
	* @return
	*/
	public int addBatchCmsNewsCategroy(List<CmsNewsCategroy> cmsNewsCategroyList){
		return this.add("addBatchCmsNewsCategroy", cmsNewsCategroyList);
	}
	/**
	* 批量修改
	* @param cms_news_categroyList 
	* @return
	*/
	public int updateBatchCmsNewsCategroy(List<CmsNewsCategroy> cmsNewsCategroyList){
		return this.update("updateBatchCmsNewsCategroy", cmsNewsCategroyList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param cms_news_categroyList 
	* @return
	*/
	public int updateBatchCmsNewsCategroyBySelective(List<CmsNewsCategroy> cmsNewsCategroyList){
		return this.update("updateBatchCmsNewsCategroyBySelective", cmsNewsCategroyList);
	}
}
