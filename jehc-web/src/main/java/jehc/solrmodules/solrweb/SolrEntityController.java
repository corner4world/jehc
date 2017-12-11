package jehc.solrmodules.solrweb;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jehc.solrmodules.solrmodel.SolrEntity;
import jehc.solrmodules.solrmodel.SolrIndexSql;
import jehc.solrmodules.solrmodel.SolrIndexSqlFiled;
import jehc.solrmodules.solrservice.SolrEntityService;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtmodel.XtDataDictionary;

/**
 * SOLR实体
 * @author邓纯杰
 *
 */
@Controller
@RequestMapping("/solrEntityController")
public class SolrEntityController extends BaseAction{
	@Autowired
	private SolrEntityService solrEntityService;
	/**
	* 获取对象
	* @param solr_entity_id 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/getSolrEntityById",method={RequestMethod.POST,RequestMethod.GET})
	public String getSolrEntityById(String solr_entity_id,HttpServletRequest request){
		if(null != solr_entity_id && !"".equals(solr_entity_id)){
			List<XtDataDictionary> xtDataDictionaryList = CommonUtils.getXtDataDictionaryCache("solrTransformer");
			String solr_entity_transformer_text="";
			SolrEntity solr_Entity = solrEntityService.getSolrEntityById(solr_entity_id);
			for(int i = 0; i < xtDataDictionaryList.size(); i++){
				XtDataDictionary xtDataDictionary = xtDataDictionaryList.get(i);
				if(null != solr_Entity.getSolr_entity_transformer() && !"".equals(solr_Entity.getSolr_entity_transformer()) && solr_Entity.getSolr_entity_transformer().indexOf(xtDataDictionary.getXt_data_dictionary_id())>=0){
					if(null != solr_entity_transformer_text && !"".equals(solr_entity_transformer_text)){
						solr_entity_transformer_text = solr_entity_transformer_text+",【"+xtDataDictionary.getXt_data_dictionary_name()+"】";
					}else{
						solr_entity_transformer_text = "【"+xtDataDictionary.getXt_data_dictionary_name()+"】";
					}
				}
			}
			solr_Entity.setSolr_entity_transformer_text(solr_entity_transformer_text);
			return outDataStr(solr_Entity);
		}else{
			throw new RuntimeException("未能获取到solr_entity_id");
		}
	}
	/**
	* 添加
	* @param solr_Entity 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/addSolrEntity",method={RequestMethod.POST,RequestMethod.GET})
	public String addSolrEntity(SolrEntity solr_Entity,String solrIndexSqlJSON,String solrIndexSqlFiledJSON,HttpServletRequest request){
		int i = 0;
		solr_Entity.setXt_userinfo_id(CommonUtils.getXtUid());
		solr_Entity.setSolr_entity_ctime(getDate());
		List<SolrIndexSql> solr_Index_SqlList = commonSolrIndexSqlList(solrIndexSqlJSON);
		List<SolrIndexSqlFiled> solr_Index_Sql_FiledList = commonSolrIndexSqlFiledJSON(solrIndexSqlFiledJSON);
		for(int j = 0; j < solr_Index_SqlList.size(); j++){
			solr_Index_SqlList.get(j).setSolr_index_ctime(getDate());
			solr_Index_SqlList.get(j).setSolr_index_sql_id(UUID.toUUID());
			solr_Index_SqlList.get(j).setXt_userinfo_id(CommonUtils.getXtUid());
		}
		for(int j = 0; j < solr_Index_Sql_FiledList.size(); j++){
			solr_Index_Sql_FiledList.get(j).setSolr_index_sql_filed_id(UUID.toUUID());
			solr_Index_Sql_FiledList.get(j).setSolr_index_sql_filed_ctime(getDate());
			solr_Index_Sql_FiledList.get(j).setXt_userinfo_id(CommonUtils.getXtUid());
		}
		i=solrEntityService.addSolrEntity(solr_Entity,solr_Index_SqlList,solr_Index_Sql_FiledList);
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	
	/**
	* 修改
	* @param solr_Entity 
	* @param request 
	*/
	@ResponseBody
	@RequestMapping(value="/updateSolrEntity",method={RequestMethod.POST,RequestMethod.GET})
	public String updateSolrEntity(SolrEntity solr_Entity,String solrIndexSqlJSON,String solrIndexSqlFiledJSON,HttpServletRequest request){
		int i = 0;
		solr_Entity.setXt_userinfo_id(CommonUtils.getXtUid());
		solr_Entity.setSolr_entity_mtime(getDate());
		List<SolrIndexSql> solr_Index_SqlList = commonSolrIndexSqlList(solrIndexSqlJSON);
		List<SolrIndexSqlFiled> solr_Index_Sql_FiledList = commonSolrIndexSqlFiledJSON(solrIndexSqlFiledJSON);
		for(int j = 0; j < solr_Index_SqlList.size(); j++){
			if(null == solr_Index_SqlList.get(j).getSolr_index_sql_id() || "".equals(solr_Index_SqlList.get(j).getSolr_index_sql_id())){
				solr_Index_SqlList.get(j).setSolr_index_ctime(getDate());
				
			}else{
				solr_Index_SqlList.get(j).setSolr_index_mtime(getDate());
			}
			solr_Index_SqlList.get(j).setXt_userinfo_id(CommonUtils.getXtUid());
		}
		for(int j = 0; j < solr_Index_Sql_FiledList.size(); j++){
			if(null == solr_Index_Sql_FiledList.get(j).getSolr_index_sql_filed_id() || "".equals(solr_Index_Sql_FiledList.get(j).getSolr_index_sql_filed_id())){
				solr_Index_Sql_FiledList.get(j).setSolr_index_sql_filed_ctime(getDate());
			}else{
				solr_Index_Sql_FiledList.get(j).setSolr_index_sql_filed_mtime(getDate());
			}
			solr_Index_Sql_FiledList.get(j).setXt_userinfo_id(CommonUtils.getXtUid());
		}
		i=solrEntityService.updateSolrEntity(solr_Entity,solr_Index_SqlList,solr_Index_Sql_FiledList);
		if(i>0){
			return outAudStr(true);
		}else{
			return outAudStr(false);
		}
	}
	
	/**
	 * 返回查询SQL集合
	 * @param solrIndexSqlJSON
	 * @return
	 */
	private List<SolrIndexSql> commonSolrIndexSqlList(String solrIndexSqlJSON){
		try {
			solrIndexSqlJSON = URLDecoder.decode(solrIndexSqlJSON, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<SolrIndexSql> solr_Index_SqlList = new ArrayList<SolrIndexSql>();
		JSONArray arr = JSONArray.fromObject(solrIndexSqlJSON);
		Object[] o = arr.toArray();
		for(Object obj:o){ 
		    if (obj instanceof JSONObject) {
		    	JSONObject json = (JSONObject)obj; 
		    	SolrIndexSql solr_Index_Sql = new SolrIndexSql();
		    	Object solr_index_sql_id = json.get("solr_index_sql_id");
		    	Object solr_index_sql_query = json.get("solr_index_sql_query");
		    	Object solr_index_sql_type = json.get("solr_index_sql_type");
		    	Object solr_entity_id = json.get("solr_entity_id");
		    	if(null != solr_index_sql_id){
		    		solr_Index_Sql.setSolr_index_sql_id((String)solr_index_sql_id);
				}
		    	if(null != solr_index_sql_query){
		    		solr_Index_Sql.setSolr_index_sql_query((String)solr_index_sql_query);
		    	}
		    	if(null != solr_index_sql_type){
		    		solr_Index_Sql.setSolr_index_sql_type((String)solr_index_sql_type);
		    	}
		    	if(null != solr_entity_id){
		    		solr_Index_Sql.setSolr_entity_id((String)solr_entity_id);
		    	}
		    	solr_Index_SqlList.add(solr_Index_Sql);
		    }
		}
		return solr_Index_SqlList;
	}
	
	/**
	 * 返回SQL结果字段
	 * @param solrIndexSqlFiledJSON
	 * @return
	 */
	private List<SolrIndexSqlFiled> commonSolrIndexSqlFiledJSON(String solrIndexSqlFiledJSON){
		try {
			solrIndexSqlFiledJSON = URLDecoder.decode(solrIndexSqlFiledJSON, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<SolrIndexSqlFiled> solr_Index_Sql_FiledList = new ArrayList<SolrIndexSqlFiled>();
		JSONArray arr = JSONArray.fromObject(solrIndexSqlFiledJSON);
		Object[] o = arr.toArray();
		for(Object obj:o){ 
		    if (obj instanceof JSONObject) {
		    	JSONObject json = (JSONObject)obj; 
		    	SolrIndexSqlFiled solr_Index_Sql_Filed = new SolrIndexSqlFiled();
		    	Object solr_index_sql_filed_id = json.get("solr_index_sql_filed_id");
		    	Object solr_index_sql_filed_name = json.get("solr_index_sql_filed_name");
		    	Object solr_index_sql_filed_zh = json.get("solr_index_sql_filed_zh");
		    	Object solr_index_filed_name = json.get("solr_index_filed_name");
		    	Object solr_entity_id = json.get("solr_entity_id");
		    	if(null != solr_index_sql_filed_id){
		    		solr_Index_Sql_Filed.setSolr_index_sql_filed_id((String)solr_index_sql_filed_id);
				}
		    	if(null != solr_index_sql_filed_name){
		    		solr_Index_Sql_Filed.setSolr_index_sql_filed_name((String)solr_index_sql_filed_name);
		    	}
		    	if(null != solr_index_sql_filed_zh){
		    		solr_Index_Sql_Filed.setSolr_index_sql_filed_zh((String)solr_index_sql_filed_zh);
		    	}
		    	if(null != solr_index_filed_name){
		    		solr_Index_Sql_Filed.setSolr_index_filed_name((String)solr_index_filed_name);
		    	}
		    	if(null != solr_entity_id){
		    		solr_Index_Sql_Filed.setSolr_entity_id((String)solr_entity_id);
		    	}
		    	solr_Index_Sql_FiledList.add(solr_Index_Sql_Filed);
		    }
		}
		return solr_Index_Sql_FiledList;
	}
}
