package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtAreaRegionDao;
import jehc.xtmodules.xtmodel.XtAreaRegion;

/**
* 行政区划表 
* 2017-05-04 14:54:34  邓纯杰
*/
@Repository("xtAreaRegionDao")
public class XtAreaRegionDaoImpl  extends BaseDaoImpl implements XtAreaRegionDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtAreaRegion> getXtAreaRegionListByCondition(Map<String,Object> condition){
		return (List<XtAreaRegion>)this.getList("getXtAreaRegionListByCondition",condition);
	}
	/**
	* 查询对象
	* @param ID 
	* @return
	*/
	public XtAreaRegion getXtAreaRegionById(String ID){
		return (XtAreaRegion)this.get("getXtAreaRegionById", ID);
	}
	/**
	* 添加
	* @param xt_area_region 
	* @return
	*/
	public int addXtAreaRegion(XtAreaRegion xt_Area_Region){
		return this.add("addXtAreaRegion", xt_Area_Region);
	}
	/**
	* 修改
	* @param xt_area_region 
	* @return
	*/
	public int updateXtAreaRegion(XtAreaRegion xt_Area_Region){
		return this.update("updateXtAreaRegion", xt_Area_Region);
	}
	/**
	* 修改（根据动态条件）
	* @param xt_area_region 
	* @return
	*/
	public int updateXtAreaRegionBySelective(XtAreaRegion xt_Area_Region){
		return this.update("updateXtAreaRegionBySelective", xt_Area_Region);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtAreaRegion(Map<String,Object> condition){
		return this.del("delXtAreaRegion", condition);
	}
	/**
	* 批量添加
	* @param xt_area_regionList 
	* @return
	*/
	public int addBatchXtAreaRegion(List<XtAreaRegion> xt_Area_RegionList){
		return this.add("addBatchXtAreaRegion", xt_Area_RegionList);
	}
	/**
	* 批量修改
	* @param xt_area_regionList 
	* @return
	*/
	public int updateBatchXtAreaRegion(List<XtAreaRegion> xt_Area_RegionList){
		return this.update("updateBatchXtAreaRegion", xt_Area_RegionList);
	}
	/**
	* 批量修改（根据动态条件）
	* @param xt_area_regionList 
	* @return
	*/
	public int updateBatchXtAreaRegionBySelective(List<XtAreaRegion> xt_Area_RegionList){
		return this.update("updateBatchXtAreaRegionBySelective", xt_Area_RegionList);
	}
}
