package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_Service_CenterDao;
import jehc.xtmodules.xtmodel.Xt_Service_Center;
import jehc.xtmodules.xtmodel.Xt_Service_Center_Parameter;
import jehc.xtmodules.xtservice.Xt_Service_CenterService;
import jehc.xtmodules.xtservice.Xt_Service_Center_ParameterService;

import java.util.HashMap;
import java.util.ArrayList;

/**
* 服务中心 
* 2017-03-27 12:32:04  邓纯杰
*/
@Service("xt_Service_CenterService")
public class Xt_Service_CenterServiceImpl extends BaseService implements Xt_Service_CenterService{
	@Autowired
	private Xt_Service_CenterDao xt_Service_CenterDao;
	@Autowired
	private Xt_Service_Center_ParameterService xt_Service_Center_ParameterService;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Service_Center> getXtServiceCenterListByCondition(Map<String,Object> condition){
		try{
			return xt_Service_CenterDao.getXtServiceCenterListByCondition(condition);
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
	public Xt_Service_Center getXtServiceCenterById(String xt_service_center_id){
		try{
			Xt_Service_Center xt_Service_Center = xt_Service_CenterDao.getXtServiceCenterById(xt_service_center_id);
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("xt_service_center_id", xt_service_center_id);
			List<Xt_Service_Center_Parameter> xt_Service_Center_Parameter = xt_Service_Center_ParameterService.getXtServiceCenterParameterListByCondition(condition);
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
	public int addXtServiceCenter(Xt_Service_Center xt_Service_Center){
		int i = 0;
		try {
			i = xt_Service_CenterDao.addXtServiceCenter(xt_Service_Center);
			List<Xt_Service_Center_Parameter> xt_Service_Center_ParameterTempList = xt_Service_Center.getXt_Service_Center_Parameter();
			List<Xt_Service_Center_Parameter> xt_Service_Center_ParameterList = new ArrayList<Xt_Service_Center_Parameter>();
			for(int j = 0; j < xt_Service_Center_ParameterTempList.size(); j++){
				if(xt_Service_Center.getXt_Service_Center_Parameter_removed_flag().indexOf(","+j+",") == -1){
					xt_Service_Center_ParameterTempList.get(j).setXt_service_center_id(xt_Service_Center.getXt_service_center_id());
					xt_Service_Center_ParameterTempList.get(j).setXt_service_center_parameter_id(toUUID());
					xt_Service_Center_ParameterList.add(xt_Service_Center_ParameterTempList.get(j));
				}
			}
			if(!xt_Service_Center_ParameterList.isEmpty()&&xt_Service_Center_ParameterList.size()>0){
				xt_Service_Center_ParameterService.addBatchXtServiceCenterParameter(xt_Service_Center_ParameterList);
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
	public int updateXtServiceCenter(Xt_Service_Center xt_Service_Center){
		int i = 0;
		try {
			i = xt_Service_CenterDao.updateXtServiceCenter(xt_Service_Center);
			List<Xt_Service_Center_Parameter> xt_Service_Center_ParameterList = xt_Service_Center.getXt_Service_Center_Parameter();
			List<Xt_Service_Center_Parameter> xt_Service_Center_ParameterAddList = new ArrayList<Xt_Service_Center_Parameter>();
			List<Xt_Service_Center_Parameter> xt_Service_Center_ParameterUpdateList = new ArrayList<Xt_Service_Center_Parameter>();
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
				xt_Service_Center_ParameterService.addBatchXtServiceCenterParameter(xt_Service_Center_ParameterAddList);
			}
			if(!xt_Service_Center_ParameterUpdateList.isEmpty()&&xt_Service_Center_ParameterUpdateList.size()>0){
				xt_Service_Center_ParameterService.updateBatchXtServiceCenterParameter(xt_Service_Center_ParameterUpdateList);
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
			i = xt_Service_CenterDao.delXtServiceCenter(condition);
			String[] xt_service_center_idList= (String[])condition.get("xt_service_center_id");
			for(String xt_service_center_id:xt_service_center_idList){
				xt_Service_Center_ParameterService.delXtServiceCenterParameterByForeignKey(xt_service_center_id);
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
	public int addBatchXtServiceCenter(List<Xt_Service_Center> xt_Service_CenterList){
		int i = 0;
		try {
			i = xt_Service_CenterDao.addBatchXtServiceCenter(xt_Service_CenterList);
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
	public int updateBatchXtServiceCenter(List<Xt_Service_Center> xt_Service_CenterList){
		int i = 0;
		try {
			i = xt_Service_CenterDao.updateBatchXtServiceCenter(xt_Service_CenterList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
