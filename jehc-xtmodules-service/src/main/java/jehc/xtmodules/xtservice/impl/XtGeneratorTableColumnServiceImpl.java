package jehc.xtmodules.xtservice.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtGeneratorTableColumnDao;
import jehc.xtmodules.xtmodel.XtGeneratorTableColumn;
import jehc.xtmodules.xtservice.XtGeneratorTableColumnService;
/**
 * 代码生成-表字段信息
 * @author邓纯杰
 *
 */
@Service("xtGeneratorTableColumnService")
public class XtGeneratorTableColumnServiceImpl extends BaseService implements XtGeneratorTableColumnService {
	@Autowired
	private XtGeneratorTableColumnDao xtGeneratorTableColumnDao;
	/**
	 * 获取所有表字段
	 * @return
	 */
	public List<XtGeneratorTableColumn> getXtGeneratorTableColumnList(Map<String, Object> condition){
		try {
			return xtGeneratorTableColumnDao.getXtGeneratorTableColumnList(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
