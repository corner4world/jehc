package jehc.xtmodules.xtdao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtNotifyDao;
import jehc.xtmodules.xtmodel.XtNotify;
/**
 * 通知
 * @author 邓纯杰
 *
 */
@Repository("xtNotifyDao")
public class XtNotifyDaoImpl extends BaseDaoImpl implements XtNotifyDao {
	/**
	 * 初始化分页
	 * @param condition
	 * @return
	 */
	public List<XtNotify> getXtNotifyListByCondition(Map<String, Object> condition){
		return (List<XtNotify>)this.getList("getXtNotifyListByCondition", condition);
	}
	
	/**
	 * 查询对象
	 * @param xt_notify_id
	 * @return
	 */
	public XtNotify getXtNotifyById(String xt_notify_id){
		return (XtNotify)this.get("getXtNotifyById", xt_notify_id);
	}
	
	/**
	 * 插入对象
	 * @param xtNotify
	 * @return
	 */
	public int addXtNotify(XtNotify xtNotify){
		return this.add("addXtNotify", xtNotify);
	}
	
	/***
	 * 删除
	 * @param condition
	 * @return
	 */
	public int delXtNotify(Map<String, Object> condition){
		return this.update("delXtNotify", condition);
	}
}
