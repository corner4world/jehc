package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtAttachmentDao;
import jehc.xtmodules.xtmodel.XtAttachment;
import jehc.xtmodules.xtservice.XtAttachmentService;

/**
* 附件管理 
* 2015-05-24 08:36:53  邓纯杰
*/
@Service("xtAttachmentService")
public class XtAttachmentServiceImpl extends BaseService implements XtAttachmentService{
	@Autowired
	private XtAttachmentDao xtAttachmentDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtAttachment> getXtAttachmentListByCondition(Map<String,Object> condition){
		try {
			return xtAttachmentDao.getXtAttachmentListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_attachment_id 
	* @return
	*/
	public XtAttachment getXtAttachmentById(String xt_attachment_id){
		try {
			return xtAttachmentDao.getXtAttachmentById(xt_attachment_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_attachment 
	* @return
	*/
	public int addXtAttachment(XtAttachment xt_Attachment){
		int i = 0;
		try {
			i = xtAttachmentDao.addXtAttachment(xt_Attachment);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_attachment 
	* @return
	*/
	public int updateXtAttachment(XtAttachment xt_Attachment){
		int i = 0;
		try {
			i = xtAttachmentDao.updateXtAttachment(xt_Attachment);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtAttachment(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtAttachmentDao.delXtAttachment(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	 * 根据如编号数组批量查询集合
	 * @param condition
	 * @return
	 */
	public List<XtAttachment> getXtAttachmentList(Map<String,Object> condition){
		try {
			return xtAttachmentDao.getXtAttachmentList(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
