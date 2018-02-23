package jehc.xtmodules.xtservice.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtdao.XtNumberDao;
import jehc.xtmodules.xtmodel.XtNumber;
import jehc.xtmodules.xtservice.XtNumberService;

/**
 * 单号生成
 * @author 邓纯杰
 *
 */
@Service("xtNumberService")
public class XtNumberServiceImpl extends BaseService implements XtNumberService{
	@Autowired
	private XtNumberDao xtNumberDao;
	/**
	 * 分页查询
	 * @param condition
	 * @return
	 */
	public List<XtNumber> getXtNumberListByCondition(Map<String, Object> condition){
		return xtNumberDao.getXtNumberListByCondition(condition);
	}
}
