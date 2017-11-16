package jehc.oamodules.oadao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.oamodules.oadao.OaNoticeDao;
import jehc.oamodules.oamodel.OaNotice;

/**
* 公告 
* 2017-11-16 13:23:07  邓纯杰
*/
@Repository("oaNoticeDao")
public class OaNoticeDaoImpl  extends BaseDaoImpl implements OaNoticeDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<OaNotice> getOaNoticeListByCondition(Map<String,Object> condition){
		return (List<OaNotice>)this.getList("getOaNoticeListByCondition",condition);
	}
	/**
	* 查询对象
	* @param oa_noticeID 
	* @return
	*/
	public OaNotice getOaNoticeById(String oa_noticeID){
		return (OaNotice)this.get("getOaNoticeById", oa_noticeID);
	}
	/**
	* 添加
	* @param oa_notice 
	* @return
	*/
	public int addOaNotice(OaNotice oaNotice){
		return this.add("addOaNotice", oaNotice);
	}
	/**
	* 修改
	* @param oa_notice 
	* @return
	*/
	public int updateOaNotice(OaNotice oaNotice){
		return this.update("updateOaNotice", oaNotice);
	}
	/**
	* 修改（根据动态条件）
	* @param oa_notice 
	* @return
	*/
	public int updateOaNoticeBySelective(OaNotice oaNotice){
		return this.update("updateOaNoticeBySelective", oaNotice);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delOaNotice(Map<String,Object> condition){
		return this.del("delOaNotice", condition);
	}
	/**
	* 批量添加
	* @param oa_noticeList 
	* @return
	*/
	public int addBatchOaNotice(List<OaNotice> oaNoticeList){
		return this.add("addBatchOaNotice", oaNoticeList);
	}
	/**
	* 批量修改
	* @param oa_noticeList 
	* @return
	*/
	public int updateBatchOaNotice(List<OaNotice> oaNoticeList){
		return this.update("updateBatchOaNotice", oaNoticeList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param oa_noticeList 
	* @return
	*/
	public int updateBatchOaNoticeBySelective(List<OaNotice> oaNoticeList){
		return this.update("updateBatchOaNoticeBySelective", oaNoticeList);
	}
}
