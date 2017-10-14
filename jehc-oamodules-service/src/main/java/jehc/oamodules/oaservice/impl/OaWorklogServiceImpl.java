package jehc.oamodules.oaservice.impl;

import java.util.List;
import java.util.Map;
import jehc.oamodules.oadao.OaWorklogDao;
import jehc.oamodules.oamodel.OaWorklog;
import jehc.oamodules.oaservice.OaWorklogService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("oaWorklogService")
public class OaWorklogServiceImpl extends BaseService
  implements OaWorklogService
{

  @Autowired
  private OaWorklogDao oaWorklogDao;

  public List<OaWorklog> getOaWorklogListByCondition(Map<String, Object> condition)
  {
    try
    {
      return this.oaWorklogDao.getOaWorklogListByCondition(condition);
    }
    catch (Exception e) {
      throw new ExceptionUtil(e.getMessage(), e.getCause());
    }
  }

  public int getOaWorklogListCountByCondition(Map<String, Object> condition)
  {
    try
    {
      return this.oaWorklogDao.getOaWorklogListCountByCondition(condition);
    }
    catch (Exception e) {
      throw new ExceptionUtil(e.getMessage(), e.getCause());
    }
  }

  public OaWorklog getOaWorklogById(String oaworklogID)
  {
    try
    {
      return this.oaWorklogDao.getOaWorklogById(oaworklogID);
    }
    catch (Exception e) {
      throw new ExceptionUtil(e.getMessage(), e.getCause());
    }
  }

  public int addOaWorklog(OaWorklog oaWorklog)
  {
    int i = 0;
    try {
      i = this.oaWorklogDao.addOaWorklog(oaWorklog);
    } catch (Exception e) {
      i = 0;

      throw new ExceptionUtil(e.getMessage(), e.getCause());
    }
    return i;
  }

  public int updateOaWorklog(OaWorklog oaWorklog)
  {
    int i = 0;
    try {
      i = this.oaWorklogDao.updateOaWorklog(oaWorklog);
    } catch (Exception e) {
      i = 0;

      throw new ExceptionUtil(e.getMessage(), e.getCause());
    }
    return i;
  }

  public int delOaWorklog(Map<String, Object> condition)
  {
    int i = 0;
    try {
      i = this.oaWorklogDao.delOaWorklog(condition);
    } catch (Exception e) {
      i = 0;

      throw new ExceptionUtil(e.getMessage(), e.getCause());
    }
    return i;
  }
}