package jehc.oamodules.oadao;

import java.util.List;
import java.util.Map;
import jehc.oamodules.oamodel.OaNotice;

public abstract interface OaNoticeDao
{
  public abstract List<OaNotice> getOaNoticeListByCondition(Map<String, Object> paramMap);

  public abstract OaNotice getOaNoticeById(String paramString);

  public abstract int addOaNotice(OaNotice paramOaNotice);

  public abstract int updateOaNotice(OaNotice paramOaNotice);

  public abstract int delOaNotice(Map<String, Object> paramMap);
}