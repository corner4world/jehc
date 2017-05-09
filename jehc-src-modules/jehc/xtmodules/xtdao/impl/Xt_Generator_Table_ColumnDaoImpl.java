package jehc.xtmodules.xtdao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_Generator_Table_ColumnDao;
import jehc.xtmodules.xtmodel.Xt_Generator_Table_Column;
/**
 * 代码生成-表字段信息
 * @author邓纯杰
 *
 */
@Repository("xt_Generator_Table_ColumnDao")
public class Xt_Generator_Table_ColumnDaoImpl extends BaseDaoImpl implements
		Xt_Generator_Table_ColumnDao {
	/**
	 * 获取所有表字段
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Generator_Table_Column> getXtGeneratorTableColumnList(Map<String, Object> condition){
		return (List<Xt_Generator_Table_Column>)this.getList("getXtGeneratorTableColumnList",condition);
	}
}
