package jehc.cmsmodules.cmsservice;
import java.util.List;
import java.util.Map;
import jehc.cmsmodules.cmsmodel.CmsContact;

/**
* 内容发布平台联系我们 
* 2018-06-10 14:42:49  邓纯杰
*/
public interface CmsContactService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<CmsContact> getCmsContactListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param cms_contact_id 
	* @return
	*/
	public CmsContact getCmsContactById(String cms_contact_id);
	/**
	* 添加
	* @param cms_contact 
	* @return
	*/
	public int addCmsContact(CmsContact cmsContact);
	/**
	* 修改
	* @param cms_contact 
	* @return
	*/
	public int updateCmsContact(CmsContact cmsContact);
	/**
	* 修改（根据动态条件）
	* @param cms_contact 
	* @return
	*/
	public int updateCmsContactBySelective(CmsContact cmsContact);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCmsContact(Map<String,Object> condition);
	/**
	* 批量添加
	* @param cms_contactList 
	* @return
	*/
	public int addBatchCmsContact(List<CmsContact> cmsContactList);
	/**
	* 批量修改
	* @param cms_contactList 
	* @return
	*/
	public int updateBatchCmsContact(List<CmsContact> cmsContactList);
	/**
	* 批量修改（根据动态条件）
	* @param cms_contactList 
	* @return
	*/
	public int updateBatchCmsContactBySelective(List<CmsContact> cmsContactList);
}
