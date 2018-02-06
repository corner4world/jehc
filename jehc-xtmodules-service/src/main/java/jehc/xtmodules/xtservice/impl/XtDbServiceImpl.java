package jehc.xtmodules.xtservice.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtDbDao;
import jehc.xtmodules.xtmodel.XtDb;
import jehc.xtmodules.xtservice.XtDbService;
/**
 * 数据库备份
 * @author 邓纯杰
 *
 */
@Service("xtDbService")
public class XtDbServiceImpl extends BaseService implements XtDbService {
	@Autowired
	private XtDbDao xtDbDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtDb> getXtDbListByCondition(Map<String,Object> condition){
		return xtDbDao.getXtDbListByCondition(condition);
	}
	
	/**
	 * 新增一条数据
	 * @param xtDb
	 */
	public int addXtDb(XtDb xtDb){
		int i = 0;
		try {
			xtDbDao.addXtDb(xtDb);
			i = 1;
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
