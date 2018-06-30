package jehc.cmsmodules.cmsdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.cmsmodules.cmsdao.CmsSlideDao;
import jehc.cmsmodules.cmsmodel.CmsSlide;

/**
* 内容发布平台幻灯片 
* 2018-06-27 12:44:03  邓纯杰
*/
@Repository("cmsSlideDao")
public class CmsSlideDaoImpl  extends BaseDaoImpl implements CmsSlideDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<CmsSlide> getCmsSlideListByCondition(Map<String,Object> condition){
		return (List<CmsSlide>)this.getList("getCmsSlideListByCondition",condition);
	}
	/**
	* 查询对象
	* @param cms_slide_id 
	* @return
	*/
	public CmsSlide getCmsSlideById(String cms_slide_id){
		return (CmsSlide)this.get("getCmsSlideById", cms_slide_id);
	}
	/**
	* 添加
	* @param cms_slide 
	* @return
	*/
	public int addCmsSlide(CmsSlide cmsSlide){
		return this.add("addCmsSlide", cmsSlide);
	}
	/**
	* 修改
	* @param cms_slide 
	* @return
	*/
	public int updateCmsSlide(CmsSlide cmsSlide){
		return this.update("updateCmsSlide", cmsSlide);
	}
	/**
	* 修改（根据动态条件）
	* @param cms_slide 
	* @return
	*/
	public int updateCmsSlideBySelective(CmsSlide cmsSlide){
		return this.update("updateCmsSlideBySelective", cmsSlide);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCmsSlide(Map<String,Object> condition){
		return this.del("delCmsSlide", condition);
	}
	/**
	* 批量添加
	* @param cms_slideList 
	* @return
	*/
	public int addBatchCmsSlide(List<CmsSlide> cmsSlideList){
		return this.add("addBatchCmsSlide", cmsSlideList);
	}
	/**
	* 批量修改
	* @param cms_slideList 
	* @return
	*/
	public int updateBatchCmsSlide(List<CmsSlide> cmsSlideList){
		return this.update("updateBatchCmsSlide", cmsSlideList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param cms_slideList 
	* @return
	*/
	public int updateBatchCmsSlideBySelective(List<CmsSlide> cmsSlideList){
		return this.update("updateBatchCmsSlideBySelective", cmsSlideList);
	}
}
