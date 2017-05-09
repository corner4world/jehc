package jehc.xtmodules.xtdao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_GeneratorDao;
import jehc.xtmodules.xtmodel.Xt_Generator;
/**
 * 代码生成器
 * @author邓纯杰
 *
 */
@Repository("xt_GeneratorDao")
public class Xt_GeneratorDaoImpl extends BaseDaoImpl implements Xt_GeneratorDao {
	/**
	 * 查询所有生成信息并分页
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Generator> getXtGeneratorListByCondition(Map<String, Object> condition){
		return (List<Xt_Generator>)this.getList("getXtGeneratorListByCondition", condition);
	}
	
	/**
	 * 生成代码
	 * @param xt_Generator
	 */
	public int addXtGenerator(Xt_Generator xt_Generator){
		return this.add("addXtGenerator", xt_Generator);
	}
	
	/**
	 * 删除
	 * @param condition
	 */
	public int delXtGenerator(Map<String,Object> condition){
		return this.del("delXtGenerator", condition);
	}
}
