package jehc.oamodules.oaservice;

import java.util.List;
import java.util.Map;
import jehc.oamodules.oamodel.OaWorklog;

public abstract interface OaWorklogService
{
  public abstract List<OaWorklog> getOaWorklogListByCondition(Map<String, Object> paramMap);

  public abstract int getOaWorklogListCountByCondition(Map<String, Object> paramMap);

  public abstract OaWorklog getOaWorklogById(String paramString);

  public abstract int addOaWorklog(OaWorklog paramOaWorklog);

  public abstract int updateOaWorklog(OaWorklog paramOaWorklog);

  public abstract int delOaWorklog(Map<String, Object> paramMap);
}