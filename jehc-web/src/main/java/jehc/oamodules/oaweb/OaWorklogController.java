package jehc.oamodules.oaweb;

import com.github.pagehelper.PageInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jehc.oamodules.oamodel.OaWorklog;
import jehc.oamodules.oaservice.OaWorklogService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/oaWorklogController"})
public class OaWorklogController extends BaseAction
{

  @Autowired
  private OaWorklogService oaWorklogService;

  @RequestMapping(value={"/loadOaWorklog"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView loadOaWorklog(OaWorklog oaWorklog, HttpServletRequest request)
  {
    return new ModelAndView("pc/oa-view/oa-worklog/oa-worklog-list");
  }

  @ResponseBody
  @RequestMapping(value={"/getOaWorklogListByCondition"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public String getOaWorklogListByCondition(BaseSearch baseSearch, HttpServletRequest request)
  {
    Map condition = baseSearch.convert();
    commonHPager(condition, request);
    List oaWorklogList = this.oaWorklogService.getOaWorklogListByCondition(condition);
    PageInfo page = new PageInfo(oaWorklogList);
    return outPageBootStr(page, request);
  }

  @RequestMapping(value={"/toOaWorklogAdd"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView toOaWorklogAdd(OaWorklog oaWorklog, HttpServletRequest request)
  {
    return new ModelAndView("pc/oa-view/oa-worklog/oa-worklog-add");
  }

  @RequestMapping(value={"/toOaWorklogUpdate"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView toOaWorklogUpdate(String oa_worklogID, Model model)
  {
    OaWorklog oaWorklog = this.oaWorklogService.getOaWorklogById(oa_worklogID);
    model.addAttribute("oaWorklog", oaWorklog);
    return new ModelAndView("pc/oa-view/oa-worklog/oa-worklog-update");
  }

  @RequestMapping(value={"/toOaWorklogDetail"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView toOaWorklogDetail(String oa_worklogID, Model model)
  {
    OaWorklog oaWorklog = this.oaWorklogService.getOaWorklogById(oa_worklogID);
    model.addAttribute("oaWorklog", oaWorklog);
    return new ModelAndView("pc/oa-view/oa-worklog/oa-worklog-detail");
  }

  @ResponseBody
  @RequestMapping(value={"/addOaWorklog"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public String addOaWorklog(OaWorklog oaWorklog, HttpServletRequest request)
  {
    int i = 0;
    if ((oaWorklog != null) && (!"".equals(oaWorklog))) {
      oaWorklog.setOa_worklogID(UUID.toUUID());
      oaWorklog.setXt_userinfo_id(CommonUtils.getXtUid());
      oaWorklog.setOa_worklogCreateTime(CommonUtils.getSimpleDateFormat());
      i = this.oaWorklogService.addOaWorklog(oaWorklog);
    }
    if (i > 0) {
      return outAudStr(true);
    }
    return outAudStr(false);
  }

  @ResponseBody
  @RequestMapping(value={"/updateOaWorklog"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public String updateOaWorklog(OaWorklog oaWorklog, HttpServletRequest request)
  {
    int i = 0;
    if ((oaWorklog != null) && (!"".equals(oaWorklog))) {
      oaWorklog.setXt_userinfo_id(CommonUtils.getXtUid());
      oaWorklog.setOa_worklogCreateTime(CommonUtils.getSimpleDateFormat());
      i = this.oaWorklogService.updateOaWorklog(oaWorklog);
    }
    if (i > 0) {
      return outAudStr(true);
    }
    return outAudStr(false);
  }

  @ResponseBody
  @RequestMapping(value={"/delOaWorklog"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public String delOaWorklog(String oa_worklogID, HttpServletRequest request)
  {
    int i = 0;
    if ((oa_worklogID != null) && (!"".equals(oa_worklogID))) {
      Map condition = new HashMap();
      condition.put("oa_worklogID", oa_worklogID.split(","));
      i = this.oaWorklogService.delOaWorklog(condition);
    }
    if (i > 0) {
      return outAudStr(true);
    }
    return outAudStr(false);
  }

  @ResponseBody
  @RequestMapping(value={"/copyOaWorklog"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public String copyOaWorklog(String oaworklogID, HttpServletRequest request)
  {
    int i = 0;
    OaWorklog oaWorklog = this.oaWorklogService.getOaWorklogById(oaworklogID);
    if ((oaWorklog != null) && (!"".equals(oaWorklog))) {
      oaWorklog.setOa_worklogID(UUID.toUUID());
      i = this.oaWorklogService.addOaWorklog(oaWorklog);
    }
    if (i > 0) {
      return outAudStr(true);
    }
    return outAudStr(false);
  }

  @RequestMapping(value={"/exportOaWorklog"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void exportOaWorklog(String excleData, String excleHeader, String excleText, HttpServletRequest request, HttpServletResponse response)
  {
    ExportExcel exportExcel = new ExportExcel();
    exportExcel.exportExcel(excleData, excleHeader, excleText, response);
  }
}