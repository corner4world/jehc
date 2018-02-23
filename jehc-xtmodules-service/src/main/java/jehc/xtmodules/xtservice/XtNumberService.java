package jehc.xtmodules.xtservice;

import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtNumber;

/**
 * 单号生成
 * @author 邓纯杰
 *
 */
public interface XtNumberService {
	/**
	 * 分页查询
	 * @param condition
	 * @return
	 */
	public List<XtNumber> getXtNumberListByCondition(Map<String, Object> condition);
}
