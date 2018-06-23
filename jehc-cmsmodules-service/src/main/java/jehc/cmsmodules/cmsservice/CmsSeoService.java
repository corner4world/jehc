package jehc.cmsmodules.cmsservice;
import java.util.List;
import java.util.Map;
import jehc.cmsmodules.cmsmodel.CmsSeo;

/**
* 内容发布平台SEO配置 
* 2018-06-10 15:15:07  邓纯杰
*/
public interface CmsSeoService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CmsSeo> getCmsSeoListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param cms_seo_id 
	* @return
	*/
	public CmsSeo getCmsSeoById(String cms_seo_id);
	/**
	* 添加
	* @param cms_seo 
	* @return
	*/
	public int addCmsSeo(CmsSeo cmsSeo);
	/**
	* 修改
	* @param cms_seo 
	* @return
	*/
	public int updateCmsSeo(CmsSeo cmsSeo);
	/**
	* 修改（根据动态条件）
	* @param cms_seo 
	* @return
	*/
	public int updateCmsSeoBySelective(CmsSeo cmsSeo);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCmsSeo(Map<String,Object> condition);
	/**
	* 批量添加
	* @param cms_seoList 
	* @return
	*/
	public int addBatchCmsSeo(List<CmsSeo> cmsSeoList);
	/**
	* 批量修改
	* @param cms_seoList 
	* @return
	*/
	public int updateBatchCmsSeo(List<CmsSeo> cmsSeoList);
	/**
	* 批量修改（根据动态条件）
	* @param cms_seoList 
	* @return
	*/
	public int updateBatchCmsSeoBySelective(List<CmsSeo> cmsSeoList);
}
