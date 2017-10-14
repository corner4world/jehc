package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtDataDictionary;

/**
* 数据字典 
* 2015-05-24 08:09:31  邓纯杰
*/
public interface XtDataDictionaryService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtDataDictionary> getXtDataDictionaryListByCondition(Map<String,Object> condition);
	/**
	 * 加载ListAll不分页
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtDataDictionary> getXtDataDictionaryListAllByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_data_dictionary_id 
	* @return
	*/
	public XtDataDictionary getXtDataDictionaryById(String xt_data_dictionary_id);
	/**
	* 添加
	* @param xt_data_dictionary 
	* @return
	*/
	public int addXtDataDictionary(XtDataDictionary xt_Data_Dictionary);
	/**
	* 修改
	* @param xt_data_dictionary 
	* @return
	*/
	public int updateXtDataDictionary(XtDataDictionary xt_Data_Dictionary);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtDataDictionary(Map<String,Object> condition);
}
