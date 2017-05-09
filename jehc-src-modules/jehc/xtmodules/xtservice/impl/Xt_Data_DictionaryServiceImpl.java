package jehc.xtmodules.xtservice.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtdao.Xt_Data_DictionaryDao;
import jehc.xtmodules.xtmodel.Xt_Data_Dictionary;
import jehc.xtmodules.xtservice.Xt_Data_DictionaryService;

/**
* 数据字典 
* 2015-05-24 08:09:31  邓纯杰
*/
@Service("xt_Data_DictionaryService")
public class Xt_Data_DictionaryServiceImpl extends BaseService implements Xt_Data_DictionaryService{
	@Autowired
	private Xt_Data_DictionaryDao xt_Data_DictionaryDao;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Data_Dictionary> getXtDataDictionaryListByCondition(Map<String,Object> condition){
		try {
			return xt_Data_DictionaryDao.getXtDataDictionaryListByCondition(condition);
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
	public List<Xt_Data_Dictionary> getXtDataDictionaryListAllByCondition(Map<String,Object> condition){
		try {
			return xt_Data_DictionaryDao.getXtDataDictionaryListAllByCondition(condition);
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
	public Xt_Data_Dictionary getXtDataDictionaryById(String xt_data_dictionary_id){
		try {
			return xt_Data_DictionaryDao.getXtDataDictionaryById(xt_data_dictionary_id);
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
	public int addXtDataDictionary(Xt_Data_Dictionary xt_Data_Dictionary){
		int i = 0;
		try {
			i = xt_Data_DictionaryDao.addXtDataDictionary(xt_Data_Dictionary);
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
	public int updateXtDataDictionary(Xt_Data_Dictionary xt_Data_Dictionary){
		int i = 0;
		try {
			i = xt_Data_DictionaryDao.updateXtDataDictionary(xt_Data_Dictionary);
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
			i = xt_Data_DictionaryDao.delXtDataDictionary(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
