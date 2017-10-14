package jehc.oamodules.oaservice.impl;

import java.util.List;
import java.util.Map;
import jehc.oamodules.oadao.OaNoticeDao;
import jehc.oamodules.oamodel.OaNotice;
import jehc.oamodules.oaservice.OaNoticeService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("oaNoticeService")
public class OaNoticeServiceImpl extends BaseService
  implements OaNoticeService
{

  @Autowired
  private OaNoticeDao oaNoticeDao;

  public List<OaNotice> getOaNoticeListByCondition(Map<String, Object> condition)
  {
    try
    {
      return this.oaNoticeDao.getOaNoticeListByCondition(condition);
    }
    catch (Exception e) {
      throw new ExceptionUtil(e.getMessage(), e.getCause());
    }
  }

  public OaNotice getOaNoticeById(String oanoticeID)
  {
    try
    {
      return this.oaNoticeDao.getOaNoticeById(oanoticeID);
    }
    catch (Exception e) {
      throw new ExceptionUtil(e.getMessage(), e.getCause());
    }
  }

  public int addOaNotice(OaNotice oaNotice)
  {
    int i = 0;
    try {
      i = this.oaNoticeDao.addOaNotice(oaNotice);
    } catch (Exception e) {
      i = 0;

      throw new ExceptionUtil(e.getMessage(), e.getCause());
    }
    return i;
  }

  public int updateOaNotice(OaNotice oaNotice)
  {
    int i = 0;
    try {
      i = this.oaNoticeDao.updateOaNotice(oaNotice);
    } catch (Exception e) {
      i = 0;

      throw new ExceptionUtil(e.getMessage(), e.getCause());
    }
    return i;
  }

  public int delOaNotice(Map<String, Object> condition)
  {
    int i = 0;
    try {
      i = this.oaNoticeDao.delOaNotice(condition);
    } catch (Exception e) {
      i = 0;

      throw new ExceptionUtil(e.getMessage(), e.getCause());
    }
    return i;
  }
}