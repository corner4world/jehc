package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_Area_RegionDao;
import jehc.xtmodules.xtmodel.Xt_Area_Region;
import jehc.xtmodules.xtservice.Xt_Area_RegionService;

/**
* 行政区划表 
* 2017-05-04 14:54:34  邓纯杰
*/
@Service("xt_Area_RegionService")
public class Xt_Area_RegionServiceImpl extends BaseService implements Xt_Area_RegionService{
	@Autowired
	private Xt_Area_RegionDao xt_Area_RegionDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Area_Region> getXtAreaRegionListByCondition(Map<String,Object> condition){
		try{
			return xt_Area_RegionDao.getXtAreaRegionListByCondition(condition);
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
	public Xt_Area_Region getXtAreaRegionById(String ID){
		try{
			Xt_Area_Region xt_Area_Region = xt_Area_RegionDao.getXtAreaRegionById(ID);
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
	public int addXtAreaRegion(Xt_Area_Region xt_Area_Region){
		int i = 0;
		try {
			i = xt_Area_RegionDao.addXtAreaRegion(xt_Area_Region);
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
	public int updateXtAreaRegion(Xt_Area_Region xt_Area_Region){
		int i = 0;
		try {
			i = xt_Area_RegionDao.updateXtAreaRegion(xt_Area_Region);
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
	public int updateXtAreaRegionBySelective(Xt_Area_Region xt_Area_Region){
		int i = 0;
		try {
			i = xt_Area_RegionDao.updateXtAreaRegionBySelective(xt_Area_Region);
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
			i = xt_Area_RegionDao.delXtAreaRegion(condition);
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
	public int addBatchXtAreaRegion(List<Xt_Area_Region> xt_Area_RegionList){
		int i = 0;
		try {
			i = xt_Area_RegionDao.addBatchXtAreaRegion(xt_Area_RegionList);
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
	public int updateBatchXtAreaRegion(List<Xt_Area_Region> xt_Area_RegionList){
		int i = 0;
		try {
			i = xt_Area_RegionDao.updateBatchXtAreaRegion(xt_Area_RegionList);
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
	public int updateBatchXtAreaRegionBySelective(List<Xt_Area_Region> xt_Area_RegionList){
		int i = 0;
		try {
			i = xt_Area_RegionDao.updateBatchXtAreaRegionBySelective(xt_Area_RegionList);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
