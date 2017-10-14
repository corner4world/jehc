package jehc.xtmodules.xtdao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtGeneratorTableColumnDao;
import jehc.xtmodules.xtmodel.XtGeneratorTableColumn;
/**
 * 代码生成-表字段信息
 * @author邓纯杰
 *
 */
@Repository("xtGeneratorTableColumnDao")
public class XtGeneratorTableColumnDaoImpl extends BaseDaoImpl implements
		XtGeneratorTableColumnDao {
	/**
	 * 获取所有表字段
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtGeneratorTableColumn> getXtGeneratorTableColumnList(Map<String, Object> condition){
		return (List<XtGeneratorTableColumn>)this.getList("getXtGeneratorTableColumnList",condition);
	}
}
