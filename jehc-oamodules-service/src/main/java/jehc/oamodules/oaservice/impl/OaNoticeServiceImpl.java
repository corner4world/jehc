package jehc.oamodules.oaservice.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jehc.lcmodules.activitiutil.ActivitiUtil;
import jehc.lcmodules.lcdao.LcDeploymentHisDao;
import jehc.lcmodules.lcmodel.LcApply;
import jehc.oamodules.oadao.OaNoticeDao;
import jehc.oamodules.oamodel.OaNotice;
import jehc.oamodules.oaservice.OaNoticeService;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtcore.util.SysContanst;
import jehc.xtmodules.xtmodel.XtConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 公告
 * @author 邓纯杰
 *
 */
@Service("oaNoticeService")
public class OaNoticeServiceImpl extends BaseService implements OaNoticeService {
	@Autowired
	ActivitiUtil activitiUtil;
	@Autowired
	private LcDeploymentHisDao lc_Deployment_HisDao;
	@Autowired
	private OaNoticeDao oaNoticeDao;

	/**
	 * 初始化公告集合 
	 */
	public List<OaNotice> getOaNoticeListByCondition(Map<String, Object> condition) {
		try {
			return this.oaNoticeDao.getOaNoticeListByCondition(condition);
		} catch (Exception e) {
			throw new ExceptionUtil(e.getMessage(), e.getCause());
		}
	}

	/**
	 * 读取公告明细
	 */
	public OaNotice getOaNoticeById(String oanoticeID) {
		try {
			return this.oaNoticeDao.getOaNoticeById(oanoticeID);
		} catch (Exception e) {
			throw new ExceptionUtil(e.getMessage(), e.getCause());
		}
	}

	/**
	 * 新增公告
	 */
	public int addOaNotice(OaNotice oaNotice) {
		int i = 0;
		try {
			if("1".equals(oaNotice.getSubmitType())){
				// 处理工作流
				XtConstant Xt_Constant = getXtConstantCache(SysContanst.OA_NOTICE_APPLY);
				Map<String, Object> condition = new HashMap<String, Object>();
				condition.put("xt_constant_id", Xt_Constant.getXt_constant_id());
				String id = lc_Deployment_HisDao.getLcDeploymentHisNewUnique(condition).getId();
				LcApply lc_Apply = new LcApply();
				lc_Apply.setLc_apply_title(getXtUname() + "于" + getSimpleDateFormat() + "，提交了一条公告申请流程");
				lc_Apply.setLc_apply_model_biz_id(oaNotice.getOa_noticeID());
				if (activitiUtil.addApply(id, null, null, lc_Apply)) {
					// 处理业务
					oaNotice.setStatus("1");
					i = this.oaNoticeDao.addOaNotice(oaNotice);
				} else {
					i = 0;
				}
			}else{
				i = this.oaNoticeDao.addOaNotice(oaNotice);
			}
		} catch (Exception e) {
			i = 0;

			throw new ExceptionUtil(e.getMessage(), e.getCause());
		}
		return i;
	}

	/**
	 * 修改公告
	 */
	public int updateOaNotice(OaNotice oaNotice) {
		int i = 0;
		try {
			if("1".equals(oaNotice.getSubmitType())){
				oaNotice.setStatus("1");
				// 处理工作流
				XtConstant Xt_Constant = getXtConstantCache(SysContanst.OA_NOTICE_APPLY);
				Map<String, Object> condition = new HashMap<String, Object>();
				condition.put("xt_constant_id", Xt_Constant.getXt_constant_id());
				String id = lc_Deployment_HisDao.getLcDeploymentHisNewUnique(condition).getId();
				LcApply lc_Apply = new LcApply();
				lc_Apply.setLc_apply_title(getXtUname() + "于" + getSimpleDateFormat() + "，提交了一条公告申请流程");
				lc_Apply.setLc_apply_model_biz_id(oaNotice.getOa_noticeID());
				if (activitiUtil.addApply(id, null, null, lc_Apply)) {
					// 处理业务
					i = this.oaNoticeDao.updateOaNotice(oaNotice);
				} else {
					i = 0;
				}
			}else{
				i = this.oaNoticeDao.updateOaNotice(oaNotice);
			}
			
		} catch (Exception e) {
			i = 0;
			throw new ExceptionUtil(e.getMessage(), e.getCause());
		}
		return i;
	}

	/**
	 * 删除公告
	 */
	public int delOaNotice(Map<String, Object> condition) {
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