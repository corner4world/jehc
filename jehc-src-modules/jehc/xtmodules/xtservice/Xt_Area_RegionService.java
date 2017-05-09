package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;
import jehc.xtmodules.xtmodel.Xt_Area_Region;

/**
* 行政区划表 
* 2017-05-04 14:54:34  邓纯杰
*/
public interface Xt_Area_RegionService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Area_Region> getXtAreaRegionListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param ID 
	* @return
	*/
	public Xt_Area_Region getXtAreaRegionById(String ID);
	/**
	* 添加
	* @param xt_area_region 
	* @return
	*/
	public int addXtAreaRegion(Xt_Area_Region xt_Area_Region);
	/**
	* 修改
	* @param xt_area_region 
	* @return
	*/
	public int updateXtAreaRegion(Xt_Area_Region xt_Area_Region);
	/**
	* 修改（根据动态条件）
	* @param xt_area_region 
	* @return
	*/
	public int updateXtAreaRegionBySelective(Xt_Area_Region xt_Area_Region);
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
	public int addBatchXtAreaRegion(List<Xt_Area_Region> xt_Area_RegionList);
	/**
	* 批量修改
	* @param xt_area_regionList 
	* @return
	*/
	public int updateBatchXtAreaRegion(List<Xt_Area_Region> xt_Area_RegionList);
	/**
	* 批量修改（根据动态条件）
	* @param xt_area_regionList 
	* @return
	*/
	public int updateBatchXtAreaRegionBySelective(List<Xt_Area_Region> xt_Area_RegionList);
}
