package jehc.oamodules.oaweb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import jehc.oamodules.oamodel.OaNotice;
import jehc.oamodules.oaservice.OaNoticeService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

@Controller
@RequestMapping({"/oaNoticeController"})
public class OaNoticeController extends BaseAction{

  @Autowired
  private OaNoticeService oaNoticeService;

  @RequestMapping(value={"/loadOaNotice"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView loadOaNotice(OaNotice oaNotice, HttpServletRequest request)
  {
    return new ModelAndView("pc/oa-view/oa-notice/oa-notice-list");
  }

  @ResponseBody
  @RequestMapping(value={"/getOaNoticeListByCondition"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public String getOaNoticeListByCondition(BaseSearch baseSearch, HttpServletRequest request){
    Map<String,Object> condition = baseSearch.convert();
    commonHPager(condition, request);
    List<OaNotice> oaNoticeList = this.oaNoticeService.getOaNoticeListByCondition(condition);
    PageInfo<OaNotice> page = new PageInfo<OaNotice>(oaNoticeList);
    return outPageBootStr(page, request);
  }
  
  /**
   * 发送中新增页面
   * @param request
   * @return
   */
  @RequestMapping(value={"/toOaNoticeAdd"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView toOaNoticeAdd(HttpServletRequest request){
    return new ModelAndView("pc/oa-view/oa-notice/oa-notice-add");
  }

  /**
   * 发送至编辑页面
   * @param oanoticeID
   * @param model
   * @return
   */
  @RequestMapping(value={"/toOaNoticeUpdate"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView toOaNoticeUpdate(String oanoticeID, Model model){
	OaNotice oaNotice = oaNoticeService.getOaNoticeById(oanoticeID);
    model.addAttribute("oaNotice", oaNotice);
    return new ModelAndView("pc/oa-view/oa-notice/oa-notice-update");
  }

  /**
   * 发送至详情
   * @param oanoticeID
   * @param model
   * @return
   */
  @RequestMapping(value={"/toOaNoticeDetail"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView toOaNoticeDetail(String oanoticeID, Model model){
	  OaNotice oaNotice = oaNoticeService.getOaNoticeById(oanoticeID);
	  model.addAttribute("oaNotice", oaNotice);
	  return new ModelAndView("pc/oa-view/oa-notice/oa-notice-detail");
  }

  
  @ResponseBody
  @RequestMapping(value={"/getOaNoticeById"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public String getOaNoticeById(String oanoticeID, HttpServletRequest request){
    OaNotice oaNotice = this.oaNoticeService.getOaNoticeById(oanoticeID);
    return outDataStr(oaNotice);
  }

  /**
   * 新增
   * @param oaNotice
   * @param request
   * @return
   */
  @ResponseBody
  @RequestMapping(value={"/addOaNotice"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public String addOaNotice(OaNotice oaNotice, HttpServletRequest request) {
    int i = 0;
    if ((oaNotice != null) && (!"".equals(oaNotice))) {
      oaNotice.setOa_noticeID(UUID.toUUID());
      oaNotice.setOa_noticeCreateTime(getSimpleDateFormat());
      oaNotice.setXt_userinfo_id(getXtUid());
      i = this.oaNoticeService.addOaNotice(oaNotice);
    }
    if (i > 0) {
      return outAudStr(true);
    }
    return outAudStr(false);
  }

  /**
   * 修改
   * @param oaNotice
   * @param request
   * @return
   */
  @ResponseBody
  @RequestMapping(value={"/updateOaNotice"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public String updateOaNotice(OaNotice oaNotice, HttpServletRequest request)
  {
    int i = 0;
    if ((oaNotice != null) && (!"".equals(oaNotice))) {
      i = this.oaNoticeService.updateOaNotice(oaNotice);
    }
    if (i > 0) {
      return outAudStr(true);
    }
    return outAudStr(false);
  }

  /**
   * 删除
   * @param oanoticeID
   * @param request
   * @return
   */
  @ResponseBody
  @RequestMapping(value={"/delOaNotice"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public String delOaNotice(String oanoticeID, HttpServletRequest request)
  {
    int i = 0;
    if ((oanoticeID != null) && (!"".equals(oanoticeID))) {
      Map<String,Object> condition = new HashMap<String,Object>();
      condition.put("oa_noticeID", oanoticeID.split(","));
      i = this.oaNoticeService.delOaNotice(condition);
    }
    if (i > 0) {
      return outAudStr(true);
    }
    return outAudStr(false);
  }

  /**
   * 复制一行数据并生成新纪录
   * @param oanoticeID
   * @param request
   * @return
   */
  @RequestMapping(value={"/copyOaNotice"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public String copyOaNotice(String oanoticeID, HttpServletRequest request)
  {
    int i = 0;
    OaNotice oaNotice = this.oaNoticeService.getOaNoticeById(oanoticeID);
    if ((oaNotice != null) && (!"".equals(oaNotice))) {
      oaNotice.setOa_noticeID(UUID.toUUID());
      i = this.oaNoticeService.addOaNotice(oaNotice);
    }
    if (i > 0) {
      return outAudStr(true);
    }
    return outAudStr(false);
  }

  /**
   * 导出
   * @param excleData
   * @param excleHeader
   * @param excleText
   * @param request
   * @param response
   */
  @RequestMapping(value={"/exportOaNotice"}, method={org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.GET})
  public void exportOaNotice(String excleData, String excleHeader, String excleText, HttpServletRequest request, HttpServletResponse response)
  {
    ExportExcel exportExcel = new ExportExcel();
    exportExcel.exportExcel(excleData, excleHeader, excleText, response);
  }
}