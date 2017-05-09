package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Notice;

/**
* 平台公告; InnoDB free: 6144 kB 
* 2015-08-23 17:27:58  邓纯杰
*/
public interface Xt_NoticeService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Notice> getXtNoticeListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_notice_id 
	* @return
	*/
	public Xt_Notice getXtNoticeById(String xt_notice_id);
	/**
	* 添加
	* @param xt_notice 
	* @return
	*/
	public int addXtNotice(Xt_Notice xt_Notice);
	/**
	* 修改
	* @param xt_notice 
	* @return
	*/
	public int updateXtNotice(Xt_Notice xt_Notice);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtNotice(Map<String,Object> condition);
}
