package jehc.xtmodules.xtdao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtNumberDao;
import jehc.xtmodules.xtmodel.XtNumber;
/**
 * 单号生成
 * @author 邓纯杰
 *
 */
@Repository("xtNumberDao")
public class XtNumberDaoImpl extends BaseDaoImpl implements XtNumberDao {
	/**
	 * 分页查询
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtNumber> getXtNumberListByCondition(Map<String, Object> condition){
		return (List<XtNumber>)this.getList("getXtNumberListByCondition", condition);
	}
	
	/**
	 * 读取唯一一条记录根据类型
	 * @param modulesType
	 * @return
	 */
	public XtNumber getXtNumberSingleByType(String modulesType){
		return (XtNumber)this.get("getXtNumberSingleByType", modulesType);
	}
	
	/**
	 * 添加
	 * @param xtNumber
	 * @return
	 */
	public int addXtNumber(XtNumber xtNumber){
		return this.add("addXtNumber", xtNumber);
	}
	
	/**
	 * 修改
	 * @param xtNumber
	 * @return
	 */
	public int updateXtNumber(XtNumber xtNumber){
		return this.update("updateXtNumber", xtNumber);
	}
}
