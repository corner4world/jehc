package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_AttachmentDao;
import jehc.xtmodules.xtmodel.Xt_Attachment;

/**
* 附件管理 
* 2015-05-24 08:36:53  邓纯杰
*/
@Repository("xt_AttachmentDao")
public class Xt_AttachmentDaoImpl  extends BaseDaoImpl implements Xt_AttachmentDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Attachment> getXtAttachmentListByCondition(Map<String,Object> condition){
		return (List<Xt_Attachment>)this.getList("getXtAttachmentListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_attachment_id 
	* @return
	*/
	public Xt_Attachment getXtAttachmentById(String xt_attachment_id){
		return (Xt_Attachment)this.get("getXtAttachmentById", xt_attachment_id);
	}
	/**
	* 添加
	* @param xt_attachment 
	* @return
	*/
	public int addXtAttachment(Xt_Attachment xt_Attachment){
		return this.add("addXtAttachment", xt_Attachment);
	}
	/**
	* 修改
	* @param xt_attachment 
	* @return
	*/
	public int updateXtAttachment(Xt_Attachment xt_Attachment){
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
	public List<Xt_Attachment> getXtAttachmentList(Map<String,Object> condition){
		return (List<Xt_Attachment>)this.getList("getXtAttachmentList",condition);
	}
}
