package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_NoticeDao;
import jehc.xtmodules.xtmodel.Xt_Notice;

/**
* 平台公告; InnoDB free: 6144 kB 
* 2015-08-23 17:27:58  邓纯杰
*/
@Repository("xt_NoticeDao")
public class Xt_NoticeDaoImpl  extends BaseDaoImpl implements Xt_NoticeDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Notice> getXtNoticeListByCondition(Map<String,Object> condition){
		return (List<Xt_Notice>)this.getList("getXtNoticeListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_notice_id 
	* @return
	*/
	public Xt_Notice getXtNoticeById(String xt_notice_id){
		return (Xt_Notice)this.get("getXtNoticeById", xt_notice_id);
	}
	/**
	* 添加
	* @param xt_notice 
	* @return
	*/
	public int addXtNotice(Xt_Notice xt_Notice){
		return this.add("addXtNotice", xt_Notice);
	}
	/**
	* 修改
	* @param xt_notice 
	* @return
	*/
	public int updateXtNotice(Xt_Notice xt_Notice){
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
