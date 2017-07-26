package jehc.xtmodules.xtservice;

import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtGeneratorTableColumn;

/**
 * 代码生成-表字段信息
 * @author邓纯杰
 *
 */
public interface XtGeneratorTableColumnService {
	/**
	 * 获取所有表字段
	 * @return
	 */
	public List<XtGeneratorTableColumn> getXtGeneratorTableColumnList(Map<String, Object> condition);
}
