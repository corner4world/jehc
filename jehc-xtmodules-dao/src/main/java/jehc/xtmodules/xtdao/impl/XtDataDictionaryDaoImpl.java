package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtDataDictionaryDao;
import jehc.xtmodules.xtmodel.XtDataDictionary;

/**
* 数据字典 
* 2015-05-24 08:09:31  邓纯杰
*/
@Repository("xtDataDictionaryDao")
public class XtDataDictionaryDaoImpl  extends BaseDaoImpl implements XtDataDictionaryDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtDataDictionary> getXtDataDictionaryListByCondition(Map<String,Object> condition){
		return (List<XtDataDictionary>)this.getList("getXtDataDictionaryListByCondition",condition);
	}
	/**
	 * 加载ListAll不分页
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<XtDataDictionary> getXtDataDictionaryListAllByCondition(Map<String,Object> condition){
		return (List<XtDataDictionary>)this.getList("getXtDataDictionaryListAllByCondition", condition);
	}
	/**
	* 查询对象
	* @param xt_data_dictionary_id 
	* @return
	*/
	public XtDataDictionary getXtDataDictionaryById(String xt_data_dictionary_id){
		return (XtDataDictionary)this.get("getXtDataDictionaryById", xt_data_dictionary_id);
	}
	/**
	* 添加
	* @param xt_data_dictionary 
	* @return
	*/
	public int addXtDataDictionary(XtDataDictionary xt_Data_Dictionary){
		return this.add("addXtDataDictionary", xt_Data_Dictionary);
	}
	/**
	* 修改
	* @param xt_data_dictionary 
	* @return
	*/
	public int updateXtDataDictionary(XtDataDictionary xt_Data_Dictionary){
		return this.update("updateXtDataDictionary", xt_Data_Dictionary);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtDataDictionary(Map<String,Object> condition){
		return this.del("delXtDataDictionary", condition);
	}
}
