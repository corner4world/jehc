package jehc.xtmodules.xtservice;

import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Generator_Table_Column;

/**
 * 代码生成-表字段信息
 * @author邓纯杰
 *
 */
public interface Xt_Generator_Table_ColumnService {
	/**
	 * 获取所有表字段
	 * @return
	 */
	public List<Xt_Generator_Table_Column> getXtGeneratorTableColumnList(Map<String, Object> condition);
}
