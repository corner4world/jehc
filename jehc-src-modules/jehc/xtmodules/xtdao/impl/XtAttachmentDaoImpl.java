package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtAttachmentDao;
import jehc.xtmodules.xtmodel.XtAttachment;

/**
* 附件管理 
* 2015-05-24 08:36:53  邓纯杰
*/
@Repository("xtAttachmentDao")
public class XtAttachmentDaoImpl  extends BaseDaoImpl implements XtAttachmentDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtAttachment> getXtAttachmentListByCondition(Map<String,Object> condition){
		return (List<XtAttachment>)this.getList("getXtAttachmentListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_attachment_id 
	* @return
	*/
	public XtAttachment getXtAttachmentById(String xt_attachment_id){
		return (XtAttachment)this.get("getXtAttachmentById", xt_attachment_id);
	}
	/**
	* 添加
	* @param xt_attachment 
	* @return
	*/
	public int addXtAttachment(XtAttachment xt_Attachment){
		return this.add("addXtAttachment", xt_Attachment);
	}
	/**
	* 修改
	* @param xt_attachment 
	* @return
	*/
	public int updateXtAttachment(XtAttachment xt_Attachment){
		return this.update("updateXtAttachment", xt_Attachment);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtAttachment(Map<String,Object> condition){
		return this.del("delXtAttachment", condition);
	}
	/**
	 * 根据如编号数组批量查询集合
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtAttachment> getXtAttachmentList(Map<String,Object> condition){
		return (List<XtAttachment>)this.getList("getXtAttachmentList",condition);
	}
}
