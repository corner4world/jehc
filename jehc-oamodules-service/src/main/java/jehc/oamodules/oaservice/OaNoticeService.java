package jehc.oamodules.oaservice;
import java.util.List;
import java.util.Map;
import jehc.oamodules.oamodel.OaNotice;

/**
* 公告 
* 2017-11-16 13:23:07  邓纯杰
*/
public interface OaNoticeService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<OaNotice> getOaNoticeListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param oa_noticeID 
	* @return
	*/
	public OaNotice getOaNoticeById(String oa_noticeID);
	/**
	* 添加
	* @param oa_notice 
	* @return
	*/
	public int addOaNotice(OaNotice oaNotice);
	/**
	* 修改
	* @param oa_notice 
	* @return
	*/
	public int updateOaNotice(OaNotice oaNotice);
	/**
	* 修改（根据动态条件）
	* @param oa_notice 
	* @return
	*/
	public int updateOaNoticeBySelective(OaNotice oaNotice);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delOaNotice(Map<String,Object> condition);
	/**
	* 批量添加
	* @param oa_noticeList 
	* @return
	*/
	public int addBatchOaNotice(List<OaNotice> oaNoticeList);
	/**
	* 批量修改
	* @param oa_noticeList 
	* @return
	*/
	public int updateBatchOaNotice(List<OaNotice> oaNoticeList);
	/**
	* 批量修改（根据动态条件）
	* @param oa_noticeList 
	* @return
	*/
	public int updateBatchOaNoticeBySelective(List<OaNotice> oaNoticeList);
}
