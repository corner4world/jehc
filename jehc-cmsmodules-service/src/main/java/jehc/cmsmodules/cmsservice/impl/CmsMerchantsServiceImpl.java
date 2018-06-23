package jehc.cmsmodules.cmsservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.cmsmodules.cmsservice.CmsMerchantsService;
import jehc.cmsmodules.cmsdao.CmsMerchantsDao;
import jehc.cmsmodules.cmsmodel.CmsMerchants;

/**
* 内容发布平台招商加盟 
* 2018-06-10 14:47:12  邓纯杰
*/
@Service("cmsMerchantsService")
public class CmsMerchantsServiceImpl extends BaseService implements CmsMerchantsService{
	@Autowired
	private CmsMerchantsDao cmsMerchantsDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CmsMerchants> getCmsMerchantsListByCondition(Map<String,Object> condition){
		try{
			return cmsMerchantsDao.getCmsMerchantsListByCondition(condition);
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param cms_merchants_id 
	* @return
	*/
	public CmsMerchants getCmsMerchantsById(String cms_merchants_id){
		try{
			CmsMerchants cmsMerchants = cmsMerchantsDao.getCmsMerchantsById(cms_merchants_id);
			return cmsMerchants;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param cms_merchants 
	* @return
	*/
	public int addCmsMerchants(CmsMerchants cmsMerchants){
		int i = 0;
		try {
			i = cmsMerchantsDao.addCmsMerchants(cmsMerchants);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param cms_merchants 
	* @return
	*/
	public int updateCmsMerchants(CmsMerchants cmsMerchants){
		int i = 0;
		try {
			i = cmsMerchantsDao.updateCmsMerchants(cmsMerchants);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param cms_merchants 
	* @return
	*/
	public int updateCmsMerchantsBySelective(CmsMerchants cmsMerchants){
		int i = 0;
		try {
			i = cmsMerchantsDao.updateCmsMerchantsBySelective(cmsMerchants);
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
	public int delCmsMerchants(Map<String,Object> condition){
		int i = 0;
		try {
			i = cmsMerchantsDao.delCmsMerchants(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param cms_merchantsList 
	* @return
	*/
	public int addBatchCmsMerchants(List<CmsMerchants> cmsMerchantsList){
		int i = 0;
		try {
			i = cmsMerchantsDao.addBatchCmsMerchants(cmsMerchantsList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param cms_merchantsList 
	* @return
	*/
	public int updateBatchCmsMerchants(List<CmsMerchants> cmsMerchantsList){
		int i = 0;
		try {
			i = cmsMerchantsDao.updateBatchCmsMerchants(cmsMerchantsList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param cms_merchantsList 
	* @return
	*/
	public int updateBatchCmsMerchantsBySelective(List<CmsMerchants> cmsMerchantsList){
		int i = 0;
		try {
			i = cmsMerchantsDao.updateBatchCmsMerchantsBySelective(cmsMerchantsList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
