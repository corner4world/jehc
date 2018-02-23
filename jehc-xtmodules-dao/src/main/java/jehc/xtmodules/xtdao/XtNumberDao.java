package jehc.xtmodules.xtdao;

import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtNumber;

/**
 * 单号生成
 * @author 邓纯杰
 *
 */
public interface XtNumberDao {
	/**
	 * 分页查询
	 * @param condition
	 * @return
	 */
	public List<XtNumber> getXtNumberListByCondition(Map<String, Object> condition);
	
	/**
	 * 读取唯一一条记录根据类型
	 * @param modulesType
	 * @return
	 */
	public XtNumber getXtNumberSingleByType(String modulesType);
	
	/**
	 * 添加
	 * @param xtNumber
	 * @return
	 */
	public int addXtNumber(XtNumber xtNumber);
	
	/**
	 * 修改
	 * @param xtNumber
	 * @return
	 */
	public int updateXtNumber(XtNumber xtNumber);
}
