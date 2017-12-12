package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtServiceCenterDao;
import jehc.xtmodules.xtmodel.XtServiceCenter;
import jehc.xtmodules.xtmodel.XtServiceCenterParameter;
import jehc.xtmodules.xtservice.XtServiceCenterService;
import jehc.xtmodules.xtservice.XtServiceCenterParameterService;

import java.util.HashMap;
import java.util.ArrayList;

/**
* 服务中心 
* 2017-03-27 12:32:04  邓纯杰
*/
@Service("xtServiceCenterService")
public class XtServiceCenterServiceImpl extends BaseService implements XtServiceCenterService{
	@Autowired
	private XtServiceCenterDao xtServiceCenterDao;
	@Autowired
	private XtServiceCenterParameterService xtServiceCenterParameterService;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtServiceCenter> getXtServiceCenterListByCondition(Map<String,Object> condition){
		try{
			return xtServiceCenterDao.getXtServiceCenterListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_service_center_id 
	* @return
	*/
	public XtServiceCenter getXtServiceCenterById(String xt_service_center_id){
		try{
			XtServiceCenter xt_Service_Center = xtServiceCenterDao.getXtServiceCenterById(xt_service_center_id);
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_service_center_id", xt_service_center_id);
			List<XtServiceCenterParameter> xt_Service_Center_Parameter = xtServiceCenterParameterService.getXtServiceCenterParameterListByCondition(condition);
			xt_Service_Center.setXt_Service_Center_Parameter(xt_Service_Center_Parameter);
			return xt_Service_Center;
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_service_center 
	* @return
	*/
	public int addXtServiceCenter(XtServiceCenter xt_Service_Center){
		int i = 0;
		try {
			i = xtServiceCenterDao.addXtServiceCenter(xt_Service_Center);
			List<XtServiceCenterParameter> xt_Service_Center_ParameterTempList = xt_Service_Center.getXt_Service_Center_Parameter();
			List<XtServiceCenterParameter> xt_Service_Center_ParameterList = new ArrayList<XtServiceCenterParameter>();
			for(int j = 0; j < xt_Service_Center_ParameterTempList.size(); j++){
				if(xt_Service_Center.getXt_Service_Center_Parameter_removed_flag().indexOf(","+j+",") == -1){
					xt_Service_Center_ParameterTempList.get(j).setXt_service_center_id(xt_Service_Center.getXt_service_center_id());
					xt_Service_Center_ParameterTempList.get(j).setXt_service_center_parameter_id(toUUID());
					xt_Service_Center_ParameterList.add(xt_Service_Center_ParameterTempList.get(j));
				}
			}
			if(!xt_Service_Center_ParameterList.isEmpty()&&xt_Service_Center_ParameterList.size()>0){
				for(XtServiceCenterParameter xtServiceCenterParameter:xt_Service_Center_ParameterList){
					xtServiceCenterParameterService.addXtServiceCenterParameter(xtServiceCenterParameter);
				}
			}
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_service_center 
	* @return
	*/
	public int updateXtServiceCenter(XtServiceCenter xt_Service_Center){
		int i = 0;
		try {
			i = xtServiceCenterDao.updateXtServiceCenter(xt_Service_Center);
			List<XtServiceCenterParameter> xt_Service_Center_ParameterList = xt_Service_Center.getXt_Service_Center_Parameter();
			List<XtServiceCenterParameter> xt_Service_Center_ParameterAddList = new ArrayList<XtServiceCenterParameter>();
			List<XtServiceCenterParameter> xt_Service_Center_ParameterUpdateList = new ArrayList<XtServiceCenterParameter>();
			for(int j = 0; j < xt_Service_Center_ParameterList.size(); j++){
				if(xt_Service_Center.getXt_Service_Center_Parameter_removed_flag().indexOf(","+j+",") == -1){
					xt_Service_Center_ParameterList.get(j).setXt_service_center_id(xt_Service_Center.getXt_service_center_id());
					if(StringUtil.isEmpty(xt_Service_Center_ParameterList.get(j).getXt_service_center_parameter_id())){
						xt_Service_Center_ParameterList.get(j).setXt_service_center_parameter_id(toUUID());
						xt_Service_Center_ParameterAddList.add(xt_Service_Center_ParameterList.get(j));
					}else{
						xt_Service_Center_ParameterUpdateList.add(xt_Service_Center_ParameterList.get(j));
					}
				}
			}
			if(!xt_Service_Center_ParameterAddList.isEmpty()&&xt_Service_Center_ParameterAddList.size()>0){
				xtServiceCenterParameterService.addBatchXtServiceCenterParameter(xt_Service_Center_ParameterAddList);
			}
			if(!xt_Service_Center_ParameterUpdateList.isEmpty()&&xt_Service_Center_ParameterUpdateList.size()>0){
				xtServiceCenterParameterService.updateBatchXtServiceCenterParameter(xt_Service_Center_ParameterUpdateList);
			}
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtServiceCenter(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtServiceCenterDao.delXtServiceCenter(condition);
			String[] xt_service_center_idList= (String[])condition.get("xt_service_center_id");
			for(String xt_service_center_id:xt_service_center_idList){
				xtServiceCenterParameterService.delXtServiceCenterParameterByForeignKey(xt_service_center_id);
			}
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param xt_service_centerList 
	* @return
	*/
	public int addBatchXtServiceCenter(List<XtServiceCenter> xt_Service_CenterList){
		int i = 0;
		try {
			i = xtServiceCenterDao.addBatchXtServiceCenter(xt_Service_CenterList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param xt_service_centerList 
	* @return
	*/
	public int updateBatchXtServiceCenter(List<XtServiceCenter> xt_Service_CenterList){
		int i = 0;
		try {
			i = xtServiceCenterDao.updateBatchXtServiceCenter(xt_Service_CenterList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
