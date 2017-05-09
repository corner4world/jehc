package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_Data_DictionaryDao;
import jehc.xtmodules.xtmodel.Xt_Data_Dictionary;

/**
* 数据字典 
* 2015-05-24 08:09:31  邓纯杰
*/
@Repository("xt_Data_DictionaryDao")
public class Xt_Data_DictionaryDaoImpl  extends BaseDaoImpl implements Xt_Data_DictionaryDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Data_Dictionary> getXtDataDictionaryListByCondition(Map<String,Object> condition){
		return (List<Xt_Data_Dictionary>)this.getList("getXtDataDictionaryListByCondition",condition);
	}
	/**
	 * 加载ListAll不分页
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xt_Data_Dictionary> getXtDataDictionaryListAllByCondition(Map<String,Object> condition){
		return (List<Xt_Data_Dictionary>)this.getList("getXtDataDictionaryListAllByCondition", condition);
	}
	/**
	* 查询对象
	* @param xt_data_dictionary_id 
	* @return
	*/
	public Xt_Data_Dictionary getXtDataDictionaryById(String xt_data_dictionary_id){
		return (Xt_Data_Dictionary)this.get("getXtDataDictionaryById", xt_data_dictionary_id);
	}
	/**
	* 添加
	* @param xt_data_dictionary 
	* @return
	*/
	public int addXtDataDictionary(Xt_Data_Dictionary xt_Data_Dictionary){
		return this.add("addXtDataDictionary", xt_Data_Dictionary);
	}
	/**
	* 修改
	* @param xt_data_dictionary 
	* @return
	*/
	public int updateXtDataDictionary(Xt_Data_Dictionary xt_Data_Dictionary){
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
