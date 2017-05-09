package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Attachment;

/**
* 附件管理 
* 2015-05-24 08:36:53  邓纯杰
*/
public interface Xt_AttachmentService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Attachment> getXtAttachmentListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_attachment_id 
	* @return
	*/
	public Xt_Attachment getXtAttachmentById(String xt_attachment_id);
	/**
	* 添加
	* @param xt_attachment 
	* @return
	*/
	public int addXtAttachment(Xt_Attachment xt_Attachment);
	/**
	* 修改
	* @param xt_attachment 
	* @return
	*/
	public int updateXtAttachment(Xt_Attachment xt_Attachment);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtAttachment(Map<String,Object> condition);
	/**
	 * 根据如编号数组批量查询集合
	 * @param condition
	 * @return
	 */
	public List<Xt_Attachment> getXtAttachmentList(Map<String,Object> condition);
}
