package jehc.xtmodules.xtweb;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.base.BaseTreeGridEntity;
import jehc.xtmodules.xtcore.md5.MD5;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;
import jehc.xtmodules.xtmodel.Xt_Data_Dictionary;
import jehc.xtmodules.xtmodel.Xt_U_R;
import jehc.xtmodules.xtmodel.Xt_Userinfo;
import jehc.xtmodules.xtservice.Xt_U_RService;
import jehc.xtmodules.xtservice.Xt_UserinfoService;

/**
* 员工信息表; InnoDB free: 6144 kB 
* 2015-07-30 21:41:20  邓纯杰
*/
@Controller
@RequestMapping("/xtUserinfoController")
@Scope("prototype")
public class Xt_UserinfoController extends BaseAction{
	@Autowired
	private Xt_UserinfoService xt_UserinfoService;
	@Autowired
	private Xt_U_RService xt_U_RService;
	/**
	* 载入初始化页面
	* @param xt_userinfo 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtUserinfo",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtUserinfo(Xt_Userinfo xt_Userinfo,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-userinfo/xt-userinfo-list");
	}
	/**
	* 载入上传头像页面
	* @param xt_userinfo 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadXtUserinfoUpLoad",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtUserinfoUpLoad(Xt_Userinfo xt_Userinfo,HttpServletRequest request){
		return new ModelAndView("pc/xt-view/xt-userinfo/xt-userinfo-upload");
	}
	
	/**
	* 加载初始化列表数据并分页
	* @param xt_userinfo 
	* @param request 
	 * @throws UnsupportedEncodingException 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtUserinfoListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtUserinfoListByCondition(BaseSearch baseSearch,HttpServletRequest request) throws UnsupportedEncodingException{
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		List<Xt_Userinfo>XtUserinfoList = xt_UserinfoService.getXtUserinfoListByCondition(condition);
		PageInfo<Xt_Userinfo> page = new PageInfo<Xt_Userinfo>(XtUserinfoList);
		return outPageStr(page,request);
	}
	
	/**
	 * 已删除用户
	 * @param xt_Userinfo
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value="/getXtUserinfoDeletedListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtUserinfoDeletedListByCondition(Xt_Userinfo xt_Userinfo,HttpServletRequest request) throws UnsupportedEncodingException{
		Map<String, Object> condition = new HashMap<String, Object>();
		if(null != xt_Userinfo.getXt_departinfo_name()){
			condition.put("xt_departinfo_name", URLDecoder.decode(xt_Userinfo.getXt_departinfo_name(), "UTF-8"));
		}
		commonHPager(condition,request);
		List<Xt_Userinfo>XtUserinfoList = xt_UserinfoService.getXtUserinfoDeletedListByCondition(condition);
		PageInfo<Xt_Userinfo> page = new PageInfo<Xt_Userinfo>(XtUserinfoList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param xt_userinfo_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getXtUserinfoById",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtUserinfoById(String xt_userinfo_id,HttpServletRequest request){
		Xt_Userinfo xt_Userinfo = xt_UserinfoService.getXtUserinfoById(xt_userinfo_id);
		return outDataStr(xt_Userinfo);
	}
	/**
	* 添加
	* @param xt_userinfo 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addXtUserinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String addXtUserinfo(Xt_Userinfo xt_Userinfo,HttpServletRequest request){
		int i = 0;
		MD5 md5 = new MD5();
		if(null != xt_Userinfo && !"".equals(xt_Userinfo)){
			xt_Userinfo.setXt_userinfo_id(UUID.toUUID());
			xt_Userinfo.setXt_userinfo_passWord(md5.getMD5ofStr(getXtConstantCache("XtUserinfoDefaultPwd").getXt_constantValue()));
			i=xt_UserinfoService.addXtUserinfo(xt_Userinfo);
		}
		if(i>0){
			aBLogs("用户控制层", "添加", "添加用户成功");
			return outAudStr(true);
		}else{
			aBLogs("用户控制层", "添加", "添加用户失败");
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param xt_userinfo 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateXtUserinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String updateXtUserinfo(Xt_Userinfo xt_Userinfo,HttpServletRequest request){
		int i = 0;
		if(null != xt_Userinfo && !"".equals(xt_Userinfo)){
			i=xt_UserinfoService.updateXtUserinfo(xt_Userinfo);
		}
		if(i>0){
			aBLogs("用户控制层", "修改", "修改用户成功");
			return outAudStr(true);
		}else{
			aBLogs("用户控制层", "修改", "修改用户失败");
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param xt_userinfo_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delXtUserinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String delXtUserinfo(String xt_userinfo_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_userinfo_id && !"".equals(xt_userinfo_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_userinfo_id",xt_userinfo_id.split(","));
			i=xt_UserinfoService.delXtUserinfo(condition);
		}
		if(i>0){
			aBLogs("用户控制层", "删除", "删除用户成功");
			return outAudStr(true);
		}else{
			aBLogs("用户控制层", "删除", "删除用户失败");
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param xt_userinfo_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyXtUserinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String copyXtUserinfo(String xt_userinfo_id,HttpServletRequest request){
		int i = 0;
		Xt_Userinfo xt_Userinfo = xt_UserinfoService.getXtUserinfoById(xt_userinfo_id);
		if(null != xt_Userinfo && !"".equals(xt_Userinfo)){
			xt_Userinfo.setXt_userinfo_id(UUID.toUUID());
			i=xt_UserinfoService.addXtUserinfo(xt_Userinfo);
		}
		if(i>0){
			aBLogs("用户控制层", "复制一行并生成记录", "复制一行并生成记录用户成功");
			return outAudStr(true);
		}else{
			aBLogs("用户控制层", "复制一行并生成记录", "复制一行并生成记录用户失败");
			return outAudStr(false);
		}
	}
	/**
	* 导出
	* @param excleData 
	* @param excleHeader 
	* @param excleText 
	* @param request 
	* @param request 
	*/
	@RequestMapping(value="/exportXtUserinfo",method={RequestMethod.POST,RequestMethod.GET})
	public void exportXtUserinfo(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
	
	/**
	 * 获取名族数据字典
	 * @param xt_userinfo_nation
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getXtUserinfoNationList",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtUserinfoNationList(HttpServletRequest request,HttpServletResponse response){
		List<Xt_Data_Dictionary> xtDataDictionaryList = CommonUtils.getXtDataDictionaryCache("xt_userinfo_nation");
		return outComboDataStr(xtDataDictionaryList);
	}
	
	/**
	 * 获取性别数据字典
	 * @param xt_userinfo_sex
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getXtUserinfoSexList",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtUserinfoSexList(HttpServletRequest request,HttpServletResponse response){
		List<Xt_Data_Dictionary> xtDataDictionaryList = CommonUtils.getXtDataDictionaryCache("gender");
		return outComboDataStr(xtDataDictionaryList);
	}
	
	/**
	 * 获取文化程度数据字典
	 * @param xt_userinfo_highestDegree
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getXtUserinfoHighestDegreeList",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtUserinfoHighestDegreeList(HttpServletRequest request,HttpServletResponse response){
		List<Xt_Data_Dictionary> xtDataDictionaryList = CommonUtils.getXtDataDictionaryCache("xt_userinfo_highestDegree");
		return outComboDataStr(xtDataDictionaryList);
	}
	
	/**
	 * 获取工作年限数据字典
	 * @param xt_userinfo_nation
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getXtUserinfoWorkYearList",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtUserinfoWorkYearList(HttpServletRequest request,HttpServletResponse response){
		List<Xt_Data_Dictionary> xtDataDictionaryList = CommonUtils.getXtDataDictionaryCache("xt_userinfo_workYear");
		return outComboDataStr(xtDataDictionaryList);
	}
	
	/**
	 * 获取是否已婚数据字典
	 * @param xt_userinfo_nation
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getXtUserinfoIsmarriedList",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtUserinfoIsmarriedList(HttpServletRequest request,HttpServletResponse response){
		List<Xt_Data_Dictionary> xtDataDictionaryList = CommonUtils.getXtDataDictionaryCache("xt_userinfo_ismarried");
		return outComboDataStr(xtDataDictionaryList);
	}
	
	/**
	 * 获取用户状态数据字典
	 * @param xt_userinfo_nation
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/getXtUserinfoStateList",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtUserinfoStateList(HttpServletRequest request,HttpServletResponse response){
		List<Xt_Data_Dictionary> xtDataDictionaryList = CommonUtils.getXtDataDictionaryCache("xt_userinfo_state");
		return outComboDataStr(xtDataDictionaryList);
	}
	
	/**
	 * 判断用户名即登陆账号是否重复
	 * @param xt_userinfo_name
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value="/validateUser",method={RequestMethod.POST,RequestMethod.GET})
	public String validateUser(String xt_userinfo_name,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> condition = new HashMap<String, Object>();
		if(null != xt_userinfo_name && !"".equals(xt_userinfo_name)){
			condition.put("xt_userinfo_name", xt_userinfo_name);
			int i = xt_UserinfoService.validateUser(condition);
			if(i > 0){
				return outAudStr(true,"1");
			}else{
				return outAudStr(true,"0");
			}
		}else{
			return outAudStr(false,"用户名参数未获取!验证失败!");
		}
	}
	
	/**
	* 恢复数据
	* @param xt_userinfo_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/recoverXtUserinfo",method={RequestMethod.POST,RequestMethod.GET})
	public String recoverXtUserinfo(String xt_userinfo_id,HttpServletRequest request){
		int i = 0;
		if(null != xt_userinfo_id && !"".equals(xt_userinfo_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_userinfo_id",xt_userinfo_id.split(","));
			i=xt_UserinfoService.recoverXtUserinfo(condition);
		}
		if(i>0){
			aBLogs("用户控制层", "恢复用户", "恢复用户成功");
			return outAudStr(true);
		}else{
			aBLogs("用户控制层", "恢复用户", "恢复用户失败");
			return outAudStr(false);
		}
	}
	
	/**
	 * 根据用户编号查找角色权限集合
	 * @param xt_userinfo_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getXtRoleinfoListByUserinfoId",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtRoleinfoListByUserinfoId(String xt_userinfo_id,HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("xt_userinfo_id", xt_userinfo_id);
		commonHPager(condition,request);
		List<Xt_U_R> Xt_U_RList = xt_U_RService.getXtRoleinfoListByUserinfoId(condition);
		PageInfo<Xt_U_R> page = new PageInfo<Xt_U_R>(Xt_U_RList);
		return outPageStr(page,request);
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getChattingUserinfoList",method={RequestMethod.POST,RequestMethod.GET})
	public String getChattingUserinfoList(HttpServletRequest request){
		Map<String, Object> condition = new HashMap<String, Object>();
		List<BaseTreeGridEntity> list = new ArrayList<BaseTreeGridEntity>();
		List<Xt_Userinfo> XtUserinfoList = xt_UserinfoService.getXtUserinfoListByCondition(condition);
		for(int i = 0; i < XtUserinfoList.size(); i++){
			Xt_Userinfo Xt_Userinfo = XtUserinfoList.get(i);
			BaseTreeGridEntity BaseTreeGridEntity = new BaseTreeGridEntity();
			BaseTreeGridEntity.setId(Xt_Userinfo.getXt_userinfo_id());
			BaseTreeGridEntity.setPid("0");
			BaseTreeGridEntity.setText(Xt_Userinfo.getXt_userinfo_realName());
			BaseTreeGridEntity.setContent("");
			BaseTreeGridEntity.setLeaf(true);
			BaseTreeGridEntity.setIcon("../deng/images/icons/user.png");
			list.add(BaseTreeGridEntity);
		}
		return outStr(BaseTreeGridEntity.buildTree(list,false));
	}

	/**
	 * 重置密码
	 * @param xt_userinfo_id
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/resetXtUserinfoPwd",method={RequestMethod.POST,RequestMethod.GET})
	public String resetXtUserinfoPwd(String xt_userinfo_id,String xt_userinfo_name,String xt_userinfo_realName,HttpServletRequest request){
		int i = 0;
		MD5 md5 = new MD5();
		if(null != xt_userinfo_id && !"".equals(xt_userinfo_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_userinfo_id",xt_userinfo_id);
			condition.put("xt_userinfo_passWord", md5.getMD5ofStr(getXtConstantCache("XtUserinfoDefaultPwd").getXt_constantValue()));
			i=xt_UserinfoService.updatePwd(condition);
		}
		if(i>0){
			aBLogs("用户控制层", "重置用户密码", "重置用户密码，用户名：【"+xt_userinfo_name+"】用户姓名：【"+xt_userinfo_realName+"】成功");
			return outAudStr(true);
		}else{
			aBLogs("用户控制层", "重置用户密码", "重置用户密码，用户名：【"+xt_userinfo_name+"】用户姓名：【"+xt_userinfo_realName+"】失败");
			return outAudStr(false);
		}
	}
	
	/**
	 * 根据各种情况查找集合不分页（流程设计器中处理人 发起人 发起人组等使用）
	 * @param xt_userinfo_id
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getXtUserinfoList",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtUserinfoList(String xt_userinfo_id,HttpServletRequest request){
		List<Xt_Userinfo> list = new ArrayList<Xt_Userinfo>();
		if(null != xt_userinfo_id && !"".equals(xt_userinfo_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_userinfo_id", xt_userinfo_id.split(","));
			list = xt_UserinfoService.getXtUserinfoList(condition);
		}
		return  outItemsStr(list);
	}
}
