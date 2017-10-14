package jehc.xtmodules.xtdao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtGeneratorTableDao;
import jehc.xtmodules.xtmodel.XtGeneratorTable;
/**
 * 代码生成-表信息
 * @author 邓纯杰
 *
 */
@Repository("xtGeneratorTableDao")
public class XtGeneratorTableDaoImpl extends BaseDaoImpl implements XtGeneratorTableDao {
	/**
	 * 获取所有表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtGeneratorTable> getXtGeneratorTableList(Map<String, Object> condition){
		return (List<XtGeneratorTable>)this.getList("getXtGeneratorTableList", condition);
	}
}
