package jehc.cmsmodules.cmsdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.cmsmodules.cmsdao.CmsContactDao;
import jehc.cmsmodules.cmsmodel.CmsContact;

/**
* 内容发布平台联系我们 
* 2018-06-10 14:42:49  邓纯杰
*/
@Repository("cmsContactDao")
public class CmsContactDaoImpl  extends BaseDaoImpl implements CmsContactDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<CmsContact> getCmsContactListByCondition(Map<String,Object> condition){
		return (List<CmsContact>)this.getList("getCmsContactListByCondition",condition);
	}
	/**
	* 查询对象
	* @param cms_contact_id 
	* @return
	*/
	public CmsContact getCmsContactById(String cms_contact_id){
		return (CmsContact)this.get("getCmsContactById", cms_contact_id);
	}
	/**
	* 添加
	* @param cms_contact 
	* @return
	*/
	public int addCmsContact(CmsContact cmsContact){
		return this.add("addCmsContact", cmsContact);
	}
	/**
	* 修改
	* @param cms_contact 
	* @return
	*/
	public int updateCmsContact(CmsContact cmsContact){
		return this.update("updateCmsContact", cmsContact);
	}
	/**
	* 修改（根据动态条件）
	* @param cms_contact 
	* @return
	*/
	public int updateCmsContactBySelective(CmsContact cmsContact){
		return this.update("updateCmsContactBySelective", cmsContact);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delCmsContact(Map<String,Object> condition){
		return this.del("delCmsContact", condition);
	}
	/**
	* 批量添加
	* @param cms_contactList 
	* @return
	*/
	public int addBatchCmsContact(List<CmsContact> cmsContactList){
		return this.add("addBatchCmsContact", cmsContactList);
	}
	/**
	* 批量修改
	* @param cms_contactList 
	* @return
	*/
	public int updateBatchCmsContact(List<CmsContact> cmsContactList){
		return this.update("updateBatchCmsContact", cmsContactList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param cms_contactList 
	* @return
	*/
	public int updateBatchCmsContactBySelective(List<CmsContact> cmsContactList){
		return this.update("updateBatchCmsContactBySelective", cmsContactList);
	}
}
