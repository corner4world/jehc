package jehc.cmsmodules.cmsdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.cmsmodules.cmsdao.CmsSeoDao;
import jehc.cmsmodules.cmsmodel.CmsSeo;

/**
* 内容发布平台SEO配置 
* 2018-06-10 15:15:07  邓纯杰
*/
@Repository("cmsSeoDao")
public class CmsSeoDaoImpl  extends BaseDaoImpl implements CmsSeoDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<CmsSeo> getCmsSeoListByCondition(Map<String,Object> condition){
		return (List<CmsSeo>)this.getList("getCmsSeoListByCondition",condition);
	}
	/**
	* 查询对象
	* @param cms_seo_id 
	* @return
	*/
	public CmsSeo getCmsSeoById(String cms_seo_id){
		return (CmsSeo)this.get("getCmsSeoById", cms_seo_id);
	}
	/**
	* 添加
	* @param cms_seo 
	* @return
	*/
	public int addCmsSeo(CmsSeo cmsSeo){
		return this.add("addCmsSeo", cmsSeo);
	}
	/**
	* 修改
	* @param cms_seo 
	* @return
	*/
	public int updateCmsSeo(CmsSeo cmsSeo){
		return this.update("updateCmsSeo", cmsSeo);
	}
	/**
	* 修改（根据动态条件）
	* @param cms_seo 
	* @return
	*/
	public int updateCmsSeoBySelective(CmsSeo cmsSeo){
		return this.update("updateCmsSeoBySelective", cmsSeo);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCmsSeo(Map<String,Object> condition){
		return this.del("delCmsSeo", condition);
	}
	/**
	* 批量添加
	* @param cms_seoList 
	* @return
	*/
	public int addBatchCmsSeo(List<CmsSeo> cmsSeoList){
		return this.add("addBatchCmsSeo", cmsSeoList);
	}
	/**
	* 批量修改
	* @param cms_seoList 
	* @return
	*/
	public int updateBatchCmsSeo(List<CmsSeo> cmsSeoList){
		return this.update("updateBatchCmsSeo", cmsSeoList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param cms_seoList 
	* @return
	*/
	public int updateBatchCmsSeoBySelective(List<CmsSeo> cmsSeoList){
		return this.update("updateBatchCmsSeoBySelective", cmsSeoList);
	}
}
