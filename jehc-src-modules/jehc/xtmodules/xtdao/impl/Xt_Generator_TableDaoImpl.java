package jehc.xtmodules.xtdao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_Generator_TableDao;
import jehc.xtmodules.xtmodel.Xt_Generator_Table;
/**
 * 代码生成-表信息
 * @author 邓纯杰
 *
 */
@Repository("xt_Generator_TableDao")
public class Xt_Generator_TableDaoImpl extends BaseDaoImpl implements Xt_Generator_TableDao {
	/**
	 * 获取所有表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Generator_Table> getXtGeneratorTableList(Map<String, Object> condition){
		return (List<Xt_Generator_Table>)this.getList("getXtGeneratorTableList", condition);
	}
}
