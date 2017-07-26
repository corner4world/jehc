package jehc.xtmodules.xtservice;

import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtGeneratorTable;

/**
 * 代码生成-表信息
 * @author 邓纯杰
 *
 */
public interface XtGeneratorTableService {
	/**
	 * 获取所有表
	 * @return
	 */
	public List<XtGeneratorTable> getXtGeneratorTableList(Map<String, Object> condition); 
}
