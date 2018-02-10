package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtNotice;

/**
* 平台公告; InnoDB free: 6144 kB 
* 2015-08-23 17:27:58  邓纯杰
*/
public interface XtNoticeService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtNotice> getXtNoticeListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_notice_id 
	* @return
	*/
	public XtNotice getXtNoticeById(String xt_notice_id);
	/**
	* 添加
	* @param xt_notice 
	* @return
	*/
	public int addXtNotice(XtNotice xt_Notice);
	/**
	* 修改
	* @param xt_notice 
	* @return
	*/
	public int updateXtNotice(XtNotice xt_Notice);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtNotice(Map<String,Object> condition);
	/**
	 * 统计
	 * @param condition
	 * @return
	 */
	public int getXtNoticeCountByCondition(Map<String,Object> condition);
}
