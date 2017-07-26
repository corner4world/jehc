package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtNoticeDao;
import jehc.xtmodules.xtmodel.XtNotice;

/**
* 平台公告; InnoDB free: 6144 kB 
* 2015-08-23 17:27:58  邓纯杰
*/
@Repository("xtNoticeDao")
public class XtNoticeDaoImpl  extends BaseDaoImpl implements XtNoticeDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtNotice> getXtNoticeListByCondition(Map<String,Object> condition){
		return (List<XtNotice>)this.getList("getXtNoticeListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_notice_id 
	* @return
	*/
	public XtNotice getXtNoticeById(String xt_notice_id){
		return (XtNotice)this.get("getXtNoticeById", xt_notice_id);
	}
	/**
	* 添加
	* @param xt_notice 
	* @return
	*/
	public int addXtNotice(XtNotice xt_Notice){
		return this.add("addXtNotice", xt_Notice);
	}
	/**
	* 修改
	* @param xt_notice 
	* @return
	*/
	public int updateXtNotice(XtNotice xt_Notice){
		return this.update("updateXtNotice", xt_Notice);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtNotice(Map<String,Object> condition){
		return this.del("delXtNotice", condition);
	}
}
