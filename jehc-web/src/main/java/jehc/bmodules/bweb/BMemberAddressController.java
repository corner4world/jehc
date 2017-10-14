package jehc.bmodules.bweb;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import jehc.bmodules.bmodel.BMemberAddress;
import jehc.bmodules.bservice.BMemberAddressService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.base.BaseSearch;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtcore.util.excel.poi.ExportExcel;

/**
* 基础会员常用地址 
* 2016-02-22 16:44:23  邓纯杰
*/
@Controller
@RequestMapping("/bMemberAddressController")
public class BMemberAddressController extends BaseAction{
	@Autowired
	private BMemberAddressService bMemberAddressService;
	/**
	* 载入初始化页面
	* @param b_member_address 
	* @param request 
	* @return
	*/
	@RequestMapping(value="/loadBMemberAddress",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadBMemberAddress(String b_member_id ,BMemberAddress b_Member_Address,HttpServletRequest request){
		request.setAttribute("b_member_id", b_member_id);
		return new ModelAndView("pc/b-view/b-member-address/b-member-address-list");
	}
	/**
	* 加载初始化列表数据并分页
	* @param b_member_address 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBMemberAddressListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getBMemberAddressListByCondition(String b_member_id,BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		condition.put("b_member_id", b_member_id);
		commonHPager(condition,request);
		List<BMemberAddress> b_Member_AddressList = bMemberAddressService.getBMemberAddressListByCondition(condition);
		PageInfo<BMemberAddress> page = new PageInfo<BMemberAddress>(b_Member_AddressList);
		return outPageStr(page,request);
	}
	/**
	* 获取对象
	* @param b_member_address_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getBMemberAddressById",method={RequestMethod.POST,RequestMethod.GET})
	public String getBMemberAddressById(String b_member_address_id,HttpServletRequest request){
		BMemberAddress b_Member_Address = bMemberAddressService.getBMemberAddressById(b_member_address_id);
		return outDataStr(b_Member_Address);
	}
	/**
	* 添加
	* @param b_member_address 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addBMemberAddress",method={RequestMethod.POST,RequestMethod.GET})
	public String addBMemberAddress(BMemberAddress b_Member_Address,HttpServletRequest request){
		int i = 0;
		if(null != b_Member_Address && !"".equals(b_Member_Address)){
			b_Member_Address.setB_member_address_ctime(CommonUtils.getSimpleDateFormat());
			b_Member_Address.setB_member_address_id(UUID.toUUID());
			i=bMemberAddressService.addBMemberAddress(b_Member_Address);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 修改
	* @param b_member_address 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateBMemberAddress",method={RequestMethod.POST,RequestMethod.GET})
	public String updateBMemberAddress(BMemberAddress b_Member_Address,HttpServletRequest request){
		int i = 0;
		if(null != b_Member_Address && !"".equals(b_Member_Address)){
			b_Member_Address.setB_member_address_mtime(CommonUtils.getSimpleDateFormat());
			i=bMemberAddressService.updateBMemberAddress(b_Member_Address);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 删除
	* @param b_member_address_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/delBMemberAddress",method={RequestMethod.POST,RequestMethod.GET})
	public String delBMemberAddress(String b_member_address_id,HttpServletRequest request){
		int i = 0;
		if(null != b_member_address_id && !"".equals(b_member_address_id)){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("b_member_address_id",b_member_address_id.split(","));
			i=bMemberAddressService.delBMemberAddress(condition);
		}
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	/**
	* 复制一行并生成记录
	* @param b_member_address_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/copyBMemberAddress",method={RequestMethod.POST,RequestMethod.GET})
	public String copyBMemberAddress(String b_member_address_id,HttpServletRequest request){
		int i = 0;
		BMemberAddress b_Member_Address = bMemberAddressService.getBMemberAddressById(b_member_address_id);
		if(null != b_Member_Address && !"".equals(b_Member_Address)){
			b_Member_Address.setB_member_address_id(UUID.toUUID());
			i=bMemberAddressService.addBMemberAddress(b_Member_Address);
		}
		if(i>0){
			return outAudStr(true);
		}else{
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
	@RequestMapping(value="/exportBMemberAddress",method={RequestMethod.POST,RequestMethod.GET})
	public void exportBMemberAddress(String excleData,String excleHeader,String excleText,HttpServletRequest request,HttpServletResponse response){
		ExportExcel exportExcel = new ExportExcel();
		exportExcel.exportExcel(excleData, excleHeader,excleText,response);
	}
}
