package jehc.xtmodules.xtservice;

import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Generator_Table;

/**
 * 代码生成-表信息
 * @author 邓纯杰
 *
 */
public interface Xt_Generator_TableService {
	/**
	 * 获取所有表
	 * @return
	 */
	public List<Xt_Generator_Table> getXtGeneratorTableList(Map<String, Object> condition); 
}
