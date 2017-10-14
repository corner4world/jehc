package jehc.oamodules.oadao.impl;

import java.util.List;
import java.util.Map;
import jehc.oamodules.oadao.OaNoticeDao;
import jehc.oamodules.oamodel.OaNotice;
import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("oaNoticeDao")
public class OaNoticeDaoImpl extends BaseDaoImpl implements OaNoticeDao{
	
  @SuppressWarnings("unchecked")
public List<OaNotice> getOaNoticeListByCondition(Map<String, Object> condition)
  {
    return (List<OaNotice>) getList("getOaNoticeListByCondition", condition);
  }

  public OaNotice getOaNoticeById(String oanoticeID)
  {
    return (OaNotice)get("getOaNoticeById", oanoticeID);
  }

  public int addOaNotice(OaNotice oaNotice)
  {
    return add("addOaNotice", oaNotice);
  }

  public int updateOaNotice(OaNotice oaNotice)
  {
    return update("updateOaNotice", oaNotice);
  }

  public int delOaNotice(Map<String, Object> condition)
  {
    return del("delOaNotice", condition);
  }
}