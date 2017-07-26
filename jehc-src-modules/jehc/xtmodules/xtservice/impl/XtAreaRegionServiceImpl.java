package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtAreaRegionDao;
import jehc.xtmodules.xtmodel.XtAreaRegion;
import jehc.xtmodules.xtservice.XtAreaRegionService;

/**
* 行政区划表 
* 2017-05-04 14:54:34  邓纯杰
*/
@Service("xtAreaRegionService")
public class XtAreaRegionServiceImpl extends BaseService implements XtAreaRegionService{
	@Autowired
	private XtAreaRegionDao xtAreaRegionDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtAreaRegion> getXtAreaRegionListByCondition(Map<String,Object> condition){
		try{
			return xtAreaRegionDao.getXtAreaRegionListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param ID 
	* @return
	*/
	public XtAreaRegion getXtAreaRegionById(String ID){
		try{
			XtAreaRegion xt_Area_Region = xtAreaRegionDao.getXtAreaRegionById(ID);
			return xt_Area_Region;
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_area_region 
	* @return
	*/
	public int addXtAreaRegion(XtAreaRegion xt_Area_Region){
		int i = 0;
		try {
			i = xtAreaRegionDao.addXtAreaRegion(xt_Area_Region);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_area_region 
	* @return
	*/
	public int updateXtAreaRegion(XtAreaRegion xt_Area_Region){
		int i = 0;
		try {
			i = xtAreaRegionDao.updateXtAreaRegion(xt_Area_Region);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改（根据动态条件）
	* @param xt_area_region 
	* @return
	*/
	public int updateXtAreaRegionBySelective(XtAreaRegion xt_Area_Region){
		int i = 0;
		try {
			i = xtAreaRegionDao.updateXtAreaRegionBySelective(xt_Area_Region);
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
	public int delXtAreaRegion(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtAreaRegionDao.delXtAreaRegion(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量添加
	* @param xt_area_regionList 
	* @return
	*/
	public int addBatchXtAreaRegion(List<XtAreaRegion> xt_Area_RegionList){
		int i = 0;
		try {
			i = xtAreaRegionDao.addBatchXtAreaRegion(xt_Area_RegionList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改
	* @param xt_area_regionList 
	* @return
	*/
	public int updateBatchXtAreaRegion(List<XtAreaRegion> xt_Area_RegionList){
		int i = 0;
		try {
			i = xtAreaRegionDao.updateBatchXtAreaRegion(xt_Area_RegionList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 批量修改（根据动态条件）
	* @param xt_area_regionList 
	* @return
	*/
	public int updateBatchXtAreaRegionBySelective(List<XtAreaRegion> xt_Area_RegionList){
		int i = 0;
		try {
			i = xtAreaRegionDao.updateBatchXtAreaRegionBySelective(xt_Area_RegionList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
