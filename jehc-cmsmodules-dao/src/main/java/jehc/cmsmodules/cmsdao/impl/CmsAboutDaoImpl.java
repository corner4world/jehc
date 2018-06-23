package jehc.cmsmodules.cmsdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.cmsmodules.cmsdao.CmsAboutDao;
import jehc.cmsmodules.cmsmodel.CmsAbout;

/**
* 内容发布平台关于我们 
* 2018-06-10 17:56:24  邓纯杰
*/
@Repository("cmsAboutDao")
public class CmsAboutDaoImpl  extends BaseDaoImpl implements CmsAboutDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<CmsAbout> getCmsAboutListByCondition(Map<String,Object> condition){
		return (List<CmsAbout>)this.getList("getCmsAboutListByCondition",condition);
	}
	/**
	* 查询对象
	* @param cms_about_id 
	* @return
	*/
	public CmsAbout getCmsAboutById(String cms_about_id){
		return (CmsAbout)this.get("getCmsAboutById", cms_about_id);
	}
	/**
	* 添加
	* @param cms_about 
	* @return
	*/
	public int addCmsAbout(CmsAbout cmsAbout){
		return this.add("addCmsAbout", cmsAbout);
	}
	/**
	* 修改
	* @param cms_about 
	* @return
	*/
	public int updateCmsAbout(CmsAbout cmsAbout){
		return this.update("updateCmsAbout", cmsAbout);
	}
	/**
	* 修改（根据动态条件）
	* @param cms_about 
	* @return
	*/
	public int updateCmsAboutBySelective(CmsAbout cmsAbout){
		return this.update("updateCmsAboutBySelective", cmsAbout);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCmsAbout(Map<String,Object> condition){
		return this.del("delCmsAbout", condition);
	}
	/**
	* 批量添加
	* @param cms_aboutList 
	* @return
	*/
	public int addBatchCmsAbout(List<CmsAbout> cmsAboutList){
		return this.add("addBatchCmsAbout", cmsAboutList);
	}
	/**
	* 批量修改
	* @param cms_aboutList 
	* @return
	*/
	public int updateBatchCmsAbout(List<CmsAbout> cmsAboutList){
		return this.update("updateBatchCmsAbout", cmsAboutList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param cms_aboutList 
	* @return
	*/
	public int updateBatchCmsAboutBySelective(List<CmsAbout> cmsAboutList){
		return this.update("updateBatchCmsAboutBySelective", cmsAboutList);
	}
}
