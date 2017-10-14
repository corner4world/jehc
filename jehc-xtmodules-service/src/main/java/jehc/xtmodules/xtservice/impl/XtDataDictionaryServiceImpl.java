package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.XtDataDictionaryDao;
import jehc.xtmodules.xtmodel.XtDataDictionary;
import jehc.xtmodules.xtservice.XtDataDictionaryService;

/**
* 数据字典 
* 2015-05-24 08:09:31  邓纯杰
*/
@Service("xtDataDictionaryService")
public class XtDataDictionaryServiceImpl extends BaseService implements XtDataDictionaryService{
	@Autowired
	private XtDataDictionaryDao xtDataDictionaryDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtDataDictionary> getXtDataDictionaryListByCondition(Map<String,Object> condition){
		try {
			return xtDataDictionaryDao.getXtDataDictionaryListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	 * 加载ListAll不分页
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtDataDictionary> getXtDataDictionaryListAllByCondition(Map<String,Object> condition){
		try {
			return xtDataDictionaryDao.getXtDataDictionaryListAllByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		} 
	}
	/**
	* 查询对象
	* @param xt_data_dictionary_id 
	* @return
	*/
	public XtDataDictionary getXtDataDictionaryById(String xt_data_dictionary_id){
		try {
			return xtDataDictionaryDao.getXtDataDictionaryById(xt_data_dictionary_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_data_dictionary 
	* @return
	*/
	public int addXtDataDictionary(XtDataDictionary xt_Data_Dictionary){
		int i = 0;
		try {
			i = xtDataDictionaryDao.addXtDataDictionary(xt_Data_Dictionary);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_data_dictionary 
	* @return
	*/
	public int updateXtDataDictionary(XtDataDictionary xt_Data_Dictionary){
		int i = 0;
		try {
			i = xtDataDictionaryDao.updateXtDataDictionary(xt_Data_Dictionary);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtDataDictionary(Map<String,Object> condition){
		int i = 0;
		try {
			i = xtDataDictionaryDao.delXtDataDictionary(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
