package jehc.xtmodules.xtdao;

import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtNotify;

/**
 * 通知
 * @author 邓纯杰
 *
 */
public interface XtNotifyDao {
	
	/**
	 * 初始化分页
	 * @param condition
	 * @return
	 */
	public List<XtNotify> getXtNotifyListByCondition(Map<String, Object> condition);
	
	/**
	 * 查询对象
	 * @param xt_notify_id
	 * @return
	 */
	public XtNotify getXtNotifyById(String xt_notify_id);
	
	/**
	 * 插入对象
	 * @param xtNotify
	 * @return
	 */
	public int addXtNotify(XtNotify xtNotify);
	
	/***
	 * 删除
	 * @param condition
	 * @return
	 */
	public int delXtNotify(Map<String, Object> condition);
}
