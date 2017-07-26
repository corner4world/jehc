package jehc.xtmodules.xtservice;

import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtGenerator;

/**
 * 代码生成器
 * @author邓纯杰
 *
 */
public interface XtGeneratorService {
	/**
	 * 查询所有生成信息并分页
	 * @param condition
	 * @return
	 */
	public List<XtGenerator> getXtGeneratorListByCondition(Map<String, Object> condition);
	
	/**
	 * 生成代码
	 * @param xt_Generator
	 */
	public int addXtGenerator(XtGenerator xt_Generator);
	
	/**
	 * 删除
	 * @param condition
	 */
	public int delXtGenerator(Map<String,Object> condition);
}
