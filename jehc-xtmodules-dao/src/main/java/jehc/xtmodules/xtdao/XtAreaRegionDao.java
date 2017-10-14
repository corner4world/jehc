package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtmodel.XtAreaRegion;

/**
* 行政区划表 
* 2017-05-04 14:54:34  邓纯杰
*/
public interface XtAreaRegionDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtAreaRegion> getXtAreaRegionListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param ID 
	* @return
	*/
	public XtAreaRegion getXtAreaRegionById(String ID);
	/**
	* 添加
	* @param xt_area_region 
	* @return
	*/
	public int addXtAreaRegion(XtAreaRegion xt_Area_Region);
	/**
	* 修改
	* @param xt_area_region 
	* @return
	*/
	public int updateXtAreaRegion(XtAreaRegion xt_Area_Region);
	/**
	* 修改（根据动态条件）
	* @param xt_area_region 
	* @return
	*/
	public int updateXtAreaRegionBySelective(XtAreaRegion xt_Area_Region);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtAreaRegion(Map<String,Object> condition);
	/**
	* 批量添加
	* @param xt_area_regionList 
	* @return
	*/
	public int addBatchXtAreaRegion(List<XtAreaRegion> xt_Area_RegionList);
	/**
	* 批量修改
	* @param xt_area_regionList 
	* @return
	*/
	public int updateBatchXtAreaRegion(List<XtAreaRegion> xt_Area_RegionList);
	/**
	* 批量修改（根据动态条件）
	* @param xt_area_regionList 
	* @return
	*/
	public int updateBatchXtAreaRegionBySelective(List<XtAreaRegion> xt_Area_RegionList);
}
