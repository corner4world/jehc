package jehc.cmsmodules.cmsservice.impl;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jehc.cmsmodules.cmsservice.CmsContactService;
import jehc.cmsmodules.cmsdao.CmsContactDao;
import jehc.cmsmodules.cmsmodel.CmsContact;

/**
* 内容发布平台联系我们 
* 2018-06-10 14:42:49  邓纯杰
*/
@Service("cmsContactService")
public class CmsContactServiceImpl extends BaseService implements CmsContactService{
	@Autowired
	private CmsContactDao cmsContactDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CmsContact> getCmsContactListByCondition(Map<String,Object> condition){
		try{
			return cmsContactDao.getCmsContactListByCondition(condition);
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param cms_contact_id 
	* @return
	*/
	public CmsContact getCmsContactById(String cms_contact_id){
		try{
			CmsContact cmsContact = cmsContactDao.getCmsContactById(cms_contact_id);
			return cmsContact;
		} catch (Exception e) {
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param cms_contact 
	* @return
	*/
	public int addCmsContact(CmsContact cmsContact){
		int i = 0;
		try {
			i = cmsContactDao.addCmsContact(cmsContact);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param cms_contact 
	* @return
	*/
	public int updateCmsContact(CmsContact cmsContact){
		int i = 0;
		try {
			i = cmsContactDao.updateCmsContact(cmsContact);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param cms_contact 
	* @return
	*/
	public int updateCmsContactBySelective(CmsContact cmsContact){
		int i = 0;
		try {
			i = cmsContactDao.updateCmsContactBySelective(cmsContact);
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
	public int delCmsContact(Map<String,Object> condition){
		int i = 0;
		try {
			i = cmsContactDao.delCmsContact(condition);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param cms_contactList 
	* @return
	*/
	public int addBatchCmsContact(List<CmsContact> cmsContactList){
		int i = 0;
		try {
			i = cmsContactDao.addBatchCmsContact(cmsContactList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param cms_contactList 
	* @return
	*/
	public int updateBatchCmsContact(List<CmsContact> cmsContactList){
		int i = 0;
		try {
			i = cmsContactDao.updateBatchCmsContact(cmsContactList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param cms_contactList 
	* @return
	*/
	public int updateBatchCmsContactBySelective(List<CmsContact> cmsContactList){
		int i = 0;
		try {
			i = cmsContactDao.updateBatchCmsContactBySelective(cmsContactList);
		} catch (Exception e) {
			i = 0;
			/**捕捉异常并回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
