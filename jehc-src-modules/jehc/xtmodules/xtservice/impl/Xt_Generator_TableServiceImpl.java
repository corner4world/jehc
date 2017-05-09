package jehc.xtmodules.xtservice.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_Generator_TableDao;
import jehc.xtmodules.xtmodel.Xt_Generator_Table;
import jehc.xtmodules.xtservice.Xt_Generator_TableService;
/**
 * 代码生成-表信息
 * @author 邓纯杰
 *
 */
@Service("xt_Generator_TableService")
public class Xt_Generator_TableServiceImpl extends BaseService implements Xt_Generator_TableService {
	@Autowired
	private Xt_Generator_TableDao xt_Generator_TableDao;
	/**
	 * 获取所有表
	 * @return
	 */
	public List<Xt_Generator_Table> getXtGeneratorTableList(Map<String, Object> condition){
		try {
			return xt_Generator_TableDao.getXtGeneratorTableList(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
}
