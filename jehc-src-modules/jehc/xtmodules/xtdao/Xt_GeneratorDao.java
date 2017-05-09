package jehc.xtmodules.xtdao;

import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Generator;

/**
 * 代码生成器
 * @author邓纯杰
 *
 */
public interface Xt_GeneratorDao {
	/**
	 * 查询所有生成信息并分页
	 * @param condition
	 * @return
	 */
	public List<Xt_Generator> getXtGeneratorListByCondition(Map<String, Object> condition);
	
	/**
	 * 生成代码
	 * @param xt_Generator
	 */
	public int addXtGenerator(Xt_Generator xt_Generator);
	
	/**
	 * 删除
	 * @param condition
	 */
	public int delXtGenerator(Map<String,Object> condition);
}
