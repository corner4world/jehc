package jehc.xtmodules.xtcore.solr.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.GroupResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.GroupParams;
import org.apache.solr.common.util.NamedList;
import org.apache.solr.util.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import jehc.solrmodules.solrmodel.SolrCore;
import jehc.solrmodules.solrmodel.SolrDataConfig;
import jehc.solrmodules.solrmodel.SolrDocument;
import jehc.solrmodules.solrmodel.SolrEntity;
import jehc.solrmodules.solrmodel.SolrFiledCopy;
import jehc.solrmodules.solrmodel.SolrIndex;
import jehc.solrmodules.solrmodel.SolrIndexAttribute;
import jehc.solrmodules.solrmodel.SolrIndexSql;
import jehc.solrmodules.solrmodel.SolrIndexSqlFiled;
import jehc.solrmodules.solrmodel.SolrSchemaTemplet;
import jehc.solrmodules.solrmodel.SolrSort;
import jehc.solrmodules.solrservice.SolrDataConfigService;
import jehc.solrmodules.solrservice.SolrEntityService;
import jehc.solrmodules.solrservice.SolrIndexSqlService;
import jehc.solrmodules.solrservice.SolrIndexSqlFiledService;
import jehc.solrmodules.solrservice.SolrSchemaTempletService;
import jehc.xtmodules.xtcore.allutils.AllUtils;
import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.base.BaseAction;
import jehc.xtmodules.xtcore.solr.utils.model.SolrAutocomplete;
import jehc.xtmodules.xtcore.solr.utils.pages.PageSolr;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtcore.util.springutil.SpringUtil;
import jehc.xtmodules.xtmodel.XtDataDictionary;


/**
 * 支撑类
 * @author 邓纯杰
 *
 */
public class SolrUtil extends BaseAction{
	/**
	 * 复制默认
	 */
	public static final String DEFAULT_CORE_NAME = "defaultmodules";
	/**
	 * 一，(1)动态创建Core
	 * @param baseURL
	 * @param coreName
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public static void createCore(String baseURL,SolrCore solr_Core,SolrDocument solr_Document,List<SolrIndex> solr_IndexList) throws SolrServerException,IOException {
		//连接solr服务器
		HttpSolrServer server = new HttpSolrServer(baseURL);
		//获得solr.xml配置好的cores作为默认，获得默认core的路径
		NamedList<Object> list = CoreAdminRequest.getStatus(DEFAULT_CORE_NAME,server).getCoreStatus().get(DEFAULT_CORE_NAME);
		String path = (String) list.get("instanceDir");
		//获得solrhome,也就是solr放置索引的主目录
		String solrHome = path.substring(0, path.indexOf(DEFAULT_CORE_NAME));
		//建立新core所在文件夹
		File corePath = new File(solrHome + File.separator + solr_Core.getSolr_core_name());
		if (!corePath.exists()) {
			corePath.mkdirs();
		}
		//建立新core下的conf文件夹
		/**
		 * File confPath = new File(corePath.getAbsolutePath() + File.separator +
		 * "conf/");
		 */
		File confPath = new File(corePath.getAbsolutePath() + File.separator);
		if(!confPath.exists()){
			confPath.mkdirs();
		}
		/**
		 * //将默认core下conf里的solrconfig.xml和schema.xml拷贝到新core的conf下。这步是必须的
		 * //因为新建的core solr会去其conf文件夹下找这两个文件，如果没有就会报错，新core则不会创建成功
		 * FileUtils.copyFile(new File(path + "conf/solrconfig.xml"), new File(
		 * confPath.getAbsolutePath() + File.separator + "solrconfig.xml"));
		 * FileUtils.copyFile(new File(path + "conf/schema.xml"), new File(
		 * confPath.getAbsolutePath() + File.separator + "schema.xml"));
		 */
		//整个文件拷贝
		new SolrUtil().copyFolder(new File(path), new File(corePath.getAbsolutePath() + File.separator));
		//修改core文件参数值
//		File corePropertiesFile = new File((corePath.getAbsolutePath() + File.separator + "core.properties"));
		String corePropertiesPath = (corePath.getAbsolutePath() + File.separator + "core.properties");
		modify(corePropertiesPath,solr_Core.getSolr_core_name());
		//修改solrconfig参数值
//		File solrconfigFile = new File((corePath.getAbsolutePath() + File.separator + "conf/solrconfig.xml"));
		String solrconfigPath = (corePath.getAbsolutePath() + File.separator + "conf/solrconfig.xml");
		update(solrconfigPath,solr_Core.getSolr_core_name());
		//修改schema参数值
		String schemaPath = confPath.getAbsolutePath() + File.separator + "conf/schema.xml";
		updateSchema(schemaPath, solr_Core,solr_IndexList,null);
//		File schemaFile = new File(confPath.getAbsolutePath() + File.separator + "conf/schema.xml");
		//创建新core,同时会把新core的信息添加到solr.xml里
		//CoreAdminRequest.createCore(solr_Core.getSolr_core_name(), solr_Core.getSolr_core_name(), server);
		//CoreAdminRequest req = new CoreAdminRequest(); 
        //根据已经配置好的目录注册一个solrcore实例  
        //req.createCore(coreName, confPath.getAbsolutePath() + File.separator, server);  
        //server.commit();  
//		String urlJSON = baseURL+"/admin/cores?action=CREATE&name="+solr_Core.getSolr_core_name()+"&instanceDir="+path+"&config="+corePath.getAbsolutePath() + File.separator + "conf/solrconfig.xml"+"&schema="+confPath.getAbsolutePath() + File.separator + "schema.xml&dataDir="+corePath.getAbsolutePath() + File.separator+"data";
		String urlURL = baseURL+"/admin/cores?action=CREATE&name="+solr_Core.getSolr_core_name()+"&instanceDir="+corePath;
		if(sendURL(urlURL)){
			urlURL = baseURL+"/admin/cores?action=LOAD&name="+solr_Core.getSolr_core_name();
			sendURL(urlURL);
		}
	}
	
	/**
	 * 一.(2)动态修改Core 切记修改的时候无需在进行复制文件夹  否则大索引库数据太多 复制相当慢 若core名称变了 则采用重新创建core方法,调用createCore方法即可
	 * @param baseURL
	 * @param coreName
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public static void updateCore(String baseURL,SolrCore solr_Core,SolrDocument solr_Document,List<SolrIndex> solr_IndexList,List<SolrFiledCopy> solr_Filed_CopyList) throws SolrServerException,IOException {
		//连接solr服务器
		HttpSolrServer server = new HttpSolrServer(baseURL);
		//获得solr.xml配置好的cores作为默认，获得默认core的路径
		NamedList<Object> list = CoreAdminRequest.getStatus(DEFAULT_CORE_NAME,server).getCoreStatus().get(DEFAULT_CORE_NAME);
		String path = (String) list.get("instanceDir");
		//获得solrhome,也就是solr放置索引的主目录
		String solrHome = path.substring(0, path.indexOf(DEFAULT_CORE_NAME));
		//查找已存在core所在文件夹
		File corePath = new File(solrHome + File.separator + solr_Core.getSolr_core_name());
		//建立新core下的conf文件夹
		File confPath = new File(corePath.getAbsolutePath() + File.separator);
		//修改data-config文件参数值
		String dataConfigPath = confPath.getAbsolutePath() + File.separator + "conf/data-config.xml";
		updateDataConfig(dataConfigPath, solr_Core,solr_Document);
		//修改schema参数值
		String schemaPath = confPath.getAbsolutePath() + File.separator + "conf/schema.xml";
		updateSchema(schemaPath, solr_Core,solr_IndexList,solr_Filed_CopyList);
	}
	
	/**
	 * 修改data-config文件参数值
	 * @param dataConfigPath
	 * @param solr_Core
	 */
	private static void updateDataConfig(String dataConfigPath,SolrCore solr_Core,SolrDocument solr_Document) {
		try {
			SolrEntityService solr_EntityService = (SolrEntityService)SpringUtil.getBean("solrEntityService");
			SolrDataConfigService solr_Data_ConfigService = (SolrDataConfigService)SpringUtil.getBean("solrDataConfigService");
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("solr_document_id", solr_Document.getSolr_document_id());
			List<SolrEntity> solrEntityList = solr_EntityService.getSolrEntityListByCondition(condition);
			/**数据源配置文件**/
			SolrDataConfig solr_Data_Config = solr_Data_ConfigService.getSolrDataConfigById(solr_Core.getSolr_data_config_id());
			/**递归实体循环**/
			String solrEntity = solrEntity(solrEntityList,"0");
			OutputStream out = null;  
	        XMLWriter xmlwriter = null; 
			File file = new File(dataConfigPath);
			SAXReader sax = new SAXReader();
			Document document = sax.read(file);
			//根节点 
			Element root = document.getRootElement();
			//删除原有所有内容
			root.detach();
			document = DocumentHelper.parseText(AllUtils.formatXML("<dataConfig>"+solr_Data_Config.getSolr_data_config_datasource()+"<document name=\""+solr_Document.getSolr_document_name()+"\">"+solrEntity+"</document>"+"</dataConfig>"));
            //输出格式  
            OutputFormat outformat = new OutputFormat();  
            //指定XML编码 
            outformat.setEncoding("UTF-8");  
            out = new FileOutputStream(dataConfigPath);  
            xmlwriter = new XMLWriter(out, outformat);  
            xmlwriter.write(document); 
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 递归实体循环
	 * @param solrEntityList
	 * @return
	 */
	public static String solrEntity(List<SolrEntity> solrEntityList,String solr_entity_pid){
		SolrIndexSqlService solr_Index_SqlService = (SolrIndexSqlService)SpringUtil.getBean("solrIndexSqlService");
		SolrIndexSqlFiledService solr_Index_Sql_FiledService = (SolrIndexSqlFiledService)SpringUtil.getBean("solrIndexSqlFiledService");
		List<XtDataDictionary> xtDataDictionaryList = CommonUtils.getXtDataDictionaryCache("solr_index_sql_type");
		StringBuffer sbf = new StringBuffer();
		for(int i = 0; i < solrEntityList.size(); i++){
			SolrEntity solrEntity = solrEntityList.get(i);
			String pid = solrEntity.getSolr_entity_pid();
			String id = solrEntity.getSolr_entity_id();
			if(solr_entity_pid.equals(pid)){
				//可以采用循环sql做法原因 数据很小
				Map<String, Object> condition = new HashMap<String, Object>();
				condition.put("solr_entity_id", solrEntity.getSolr_entity_id());
				List<SolrIndexSql> solr_Index_SqlList = solr_Index_SqlService.getSolrIndexSqlListByCondition(condition);
				List<SolrIndexSqlFiled> solr_Index_Sql_FiledList = solr_Index_Sql_FiledService.getSolrIndexSqlFiledListByCondition(condition);
				//1-1实体开区间
				sbf.append("<entity name=\""+solrEntity.getSolr_entity_name()+"\" pk=\"id\" ");
				//2循环SQL语句
				for(int j = 0; j < solr_Index_SqlList.size(); j++){
					SolrIndexSql solr_Index_Sql = solr_Index_SqlList.get(j);
					String type = "";
					for(int l = 0; l < xtDataDictionaryList.size(); l++){
						XtDataDictionary xt_Data_Dictionary = xtDataDictionaryList.get(l);
						if(xt_Data_Dictionary.getXt_data_dictionary_id().equals(solr_Index_Sql.getSolr_index_sql_type())){
							type = xt_Data_Dictionary.getXt_data_dictionary_value();
							break;
						}
					}
					sbf.append(type+" = \""+solr_Index_Sql.getSolr_index_sql_query()+"\" ");
				}
				sbf.append(">");
				//3循环数据库映射索引字段
				for(int j = 0; j < solr_Index_Sql_FiledList.size(); j++){
					SolrIndexSqlFiled solr_Index_Sql_Filed = solr_Index_Sql_FiledList.get(j);
					sbf.append("<field column=\""+solr_Index_Sql_Filed.getSolr_index_sql_filed_name()+"\" name=\""+solr_Index_Sql_Filed.getSolr_index_filed_name()+"\"/>");
				}
				sbf.append(solrEntity(solrEntityList,id));
				//1-2实体闭区间
				sbf.append("</entity>");
			}
		}
		return sbf.toString();
	}

	/**
	 * 二，重命名Core
	 * @param baseURL
	 * @param coreName
	 */
	public static void reNameCore(String baseURL,String oldCoreName,String newCoreName){
		String json = loadJSON(baseURL+"/admin/cores?action=RENAME&wt=json&core="+oldCoreName+"&other="+newCoreName);
		reloadCore(baseURL, newCoreName);
		System.out.println(json);
	}
	
	/**
	 * 三，重新刷新Core
	 * @param baseURL
	 * @param coreName
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public static boolean reloadCore(String baseURL,String coreName){
		boolean flag = true;
		try {
			HttpSolrServer server = new HttpSolrServer(baseURL);
			System.out.println(coreName+"---"+baseURL);
			CoreAdminRequest.reloadCore(coreName, server);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 四，只卸载Core 根据各种情况
	 * @param baseURL
	 * @param coreName
	 * @param deleteIndex主要使用于删除卸载的core的索引 
	 * @param deleteDataDir删除数据及子文件  
	 * @param deleteInstanceDir删除实例目录，删除所有与这个core相关的文件比如索引文件、配置文件等，注意这里也有一个Bug在你需要指定绝对路径才能做到 
	 */
	public static void unloadCore(String baseURL,String coreName,boolean deleteIndex,boolean deleteDataDir,boolean deleteInstanceDir){
		StringBuffer sbf = new StringBuffer();
		sbf.append(baseURL+"/admin/cores?action=UNLOAD&wt=json&core="+coreName);
		if(deleteIndex){
			sbf.append("&deleteIndex=true");
		}
		if(deleteDataDir){
			sbf.append("&deleteDataDir=true");
		}
		if(deleteInstanceDir){
			sbf.append("&deleteInstanceDir=true");
		}
		loadJSON(sbf.toString());
	}
	
	/**
	 * 五，合并两个core中索引
	 * @param baseURL
	 * @param name core1
	 * @param name_ core2
	 * @param newname 合并之后的core
	 */
	public static void mergeindexes(String baseURL,String name,String name_,String newname){
//		http://localhost:8983/solr/admin/cores?action=mergeindexes&core=core0&indexDir=/opt/solr/core1/data/index&indexDir=/opt/solr/core2/data/index 
		loadJSON(baseURL+"/admin/cores?action=mergeindexes&wt=json&core="+newname+"&srcCore="+name+"&srcCore="+name_);
	}
	
	/**
	 	六，从 Solr4.3 后将索引分离两个或多个索引，它接受下面的参数   
    	"core" - 该core下的索引将要被拆分  
    	"path" - 分拆到多个索引的位置参数 
    	"targetCore" - 目标core，必须存在指定合并的位置 
    	注意path和tartgetCore必须指定其中一个，两个都指定不是必须的 
    	http://localhost:8983/solr/admin/cores?action=SPLIT&core=core0&targetCore=core1&targetCore=core2  
    	http://localhost:8983/solr/admin/cores?action=SPLIT&core=core0&path=/path/to/index/1&path=/path/to/index/2   
    	这个命令通常作为solrCloud分离shard集合的api,同时也可以在非SolrCloud-solr下使用，当使用了非cloud core这样原索引将分离成等量的多个索引文档 
	 */
	public static void split(String baseURL,String coreName,String targetCore,String tragetCore_){
		loadJSON(baseURL+"/admin/cores?action=SPLIT&wt=json&core="+coreName+"&srcCore="+targetCore+"&targetCore="+tragetCore_);
	}
	
	/**
	 * 七，增量导入数据
	 * @param baseURL
	 * @param coreName
	 * @return
	 */
	public static boolean dataimport(String baseURL,String coreName){
		boolean flag = true;
		try {
			String result = loadJSON(baseURL+"/"+coreName+"/dataimport?command=delta-import&wt=json");
			SolrUtil.reLoad(baseURL, coreName);
			System.out.println(result);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 全量导入数据
	 * @param baseURL
	 * @param coreName
	 * @return
	 */
	public static boolean fullimport(String baseURL,String coreName){
		boolean flag = true;
		try {
			String result = loadJSON(baseURL+"/"+coreName+"/dataimport?command=full-import&wt=json");
			System.out.println(result);
			SolrUtil.reLoad(baseURL, coreName);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	
	/**
	 * 修改properties
	 * @param corePropertiesPath
	 */
	public static void modify(String corePropertiesPath,String coreName){
		Properties prop = new Properties();//属性集合对象 
		FileInputStream fis;
		try {
			fis = new FileInputStream(corePropertiesPath);
			//属性文件输入流 
			try {
				prop.load(fis);
				//将属性文件流装载到Properties对象中 
				fis.close();// 关闭流 
				//获取属性值，sitename已在文件中定义 
				//获取属性值，country未在文件中定义，将在此程序中返回一个默认值，但并不修改属性文件 
				//System.out.println("获取属性值：country=" + prop.getProperty("country", "中国")); 
				//修改sitename的属性值 
				prop.setProperty("name", coreName); 
				//文件输出流 
				FileOutputStream fos = new FileOutputStream(corePropertiesPath); 
				//将Properties集合保存到流中 
				try {
					prop.store(fos, "Copyright (c) hs dengcj");
					fos.close();//关闭流 
				} catch (IOException e) {
					e.printStackTrace();
				} 
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/** 
	 * 复制一个目录及其子目录、文件到另外一个目录 
	 * @param src 
	 * @param dest 
	 * @throws IOException 
	 */  
	public void copyFolder(File src, File dest) throws IOException {  
	    if(src.isDirectory()){  
	        if(!dest.exists()){  
	            dest.mkdir();  
	        }  
	        String files[] = src.list();  
	        for(String file : files){  
	            File srcFile = new File(src, file);  
	            File destFile = new File(dest, file);  
	            //递归复制  
	            copyFolder(srcFile, destFile);  
	        }  
	    }else{  
	    	FileUtils.copyFile(src, dest);
	    }  
	}  
	
	@SuppressWarnings("unchecked")
	public static void update(String path,String coreName){
		String solr_home_path=path.substring(0, path.indexOf(coreName));;
		try {
	        OutputStream out = null;  
	        XMLWriter xmlwriter = null; 
			File file = new File(path);
			SAXReader sax = new SAXReader();
			Document document = sax.read(file);
			//根节点 
			Element root = document.getRootElement();
			//取得某节点下名为"config"的所有字节点  
            List nodes = root.elements("dataDir");  
            //xml元素  
            Element element = null;  
            for (Object obj : nodes) {  
                element = (Element) obj; 
                /**
                element.element("dataDir").setText("${solr.data.dir:E://hssource/honeycomb/solr/home/modules/"+NEW_CORE_NAME+"/data}");
                **/
                element.setText("${solr.data.dir:"+solr_home_path+coreName+"/data}");  
            }  
            //输出格式  
            OutputFormat outformat = new OutputFormat();  
            //指定XML编码  
            outformat.setEncoding("UTF-8");  
            //outformat.setNewlines(true);  
            //outformat.setIndent(true);  
            //outformat.setTrimText(true);  
            out = new FileOutputStream(path);  
            xmlwriter = new XMLWriter(out, outformat);  
            xmlwriter.write(document); 
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 重新写schema.xml内容
	 * @param path
	 * @param coreName
	 */
	@SuppressWarnings("unchecked")
	public static void updateSchema(String path,SolrCore solr_Core,List<SolrIndex> solrIndexList,List<SolrFiledCopy> solr_Filed_CopyList){
		try {
			SolrSchemaTempletService solr_Schema_TempletService = (SolrSchemaTempletService)SpringUtil.getBean("solrSchemaTempletService");
			SolrSchemaTemplet solr_Schema_Templet = solr_Schema_TempletService.getSolrSchemaTempletById(solr_Core.getSolr_schema_templet_id());
			List<XtDataDictionary> xtDataDictionaryList = CommonUtils.getXtDataDictionaryCache("solr_index_type");
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("solr_core_id", solr_Core.getSolr_core_id());
			StringBuffer sbf = new StringBuffer();
			//拷贝索引字段配置
			for(int i = 0; i < solr_Filed_CopyList.size(); i++){
				SolrFiledCopy solr_filed_copy = solr_Filed_CopyList.get(i);
				sbf.append("<copyField source=\""+solr_filed_copy.getSolr_filed_copy_source_name()+"\" dest=\""+solr_filed_copy.getSolr_filed_copy_dest_name()+"\"/>");
			}
			//索引配置
			for(int i = 0; i < solrIndexList.size(); i++){
				String type = "";
				SolrIndex solr_Index = solrIndexList.get(i);
				for(int j = 0; j < xtDataDictionaryList.size(); j++){
					XtDataDictionary xtDataDictionary = xtDataDictionaryList.get(j);
					if(xtDataDictionary.getXt_data_dictionary_id().equals(solr_Index.getSolr_index_type())){
						type = xtDataDictionary.getXt_data_dictionary_value();
						break;
					}
				}
				sbf.append("<field name=\""+solr_Index.getSolr_index_name()+"\" type=\""+type+"\" indexed=\""+solr_Index.getSolr_index_indexed()+"\" stored=\""+solr_Index.getSolr_index_stored()+"\" multiValued=\""+solr_Index.getSolr_index_multiValued()+"\"/>");
			}
			OutputStream out = null;  
	        XMLWriter xmlwriter = null; 
			File file = new File(path);
			SAXReader sax = new SAXReader();
			Document document = sax.read(file);
			//根节点 
			Element root = document.getRootElement();
			//删除原有所有内容
			root.detach();
			document = DocumentHelper.parseText(AllUtils.formatXML("<schema name=\""+solr_Core.getSolr_core_name()+"\" version=\"1.5\">"+sbf.toString()+solr_Schema_Templet.getSolr_schema_templet_content()+"</schema>"));
            //输出格式  
            OutputFormat outformat = new OutputFormat();  
            //指定XML编码 
            outformat.setEncoding("UTF-8");  
            out = new FileOutputStream(path);  
            xmlwriter = new XMLWriter(out, outformat);  
            xmlwriter.write(document); 
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 读取URL连接返回JSON
	 * @param url
	 * @return
	 */
	public static String loadJSON (String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL urlServer = new URL(url);
            URLConnection uc = urlServer.openConnection();
            HttpURLConnection httpUrlConnection  =  (HttpURLConnection) uc;
            String code = new Integer(httpUrlConnection.getResponseCode()).toString();
            if(code.equals("200")){
            	 BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
                 String inputLine = null;
                 while((inputLine = in.readLine()) != null){
                     json.append(inputLine);
                 }
                 in.close();
            }else{
            	throw new RuntimeException("无法连接当前URL.....");
            }
        } catch (MalformedURLException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return json.toString();
    }
	
	/**
	 * 读取URL连接返回JSON
	 * @param url
	 * @return
	 */
	public static boolean sendURL (String url) {
		boolean flag=false;
        try {
            URL urlServer = new URL(url);
            HttpURLConnection httpUrlConnection  =  (HttpURLConnection)urlServer.openConnection();
            new Integer(httpUrlConnection.getResponseCode()).toString();
            flag = true;
        } catch (MalformedURLException e) {
        	e.printStackTrace();
        	flag = false;
        } catch (IOException e) {
        	e.printStackTrace();
        	flag = false;
        }
        return flag;
    }
	
	/**
	 * 重启core(可用于schema.xml变动) 发送HTTP指令
	 * @param baseURL
	 * @param coreName
	 * @return
	 */
	public static boolean reLoad(String baseURL,String coreName){
		String url = baseURL+"/admin/cores?action=RELOAD&core="+coreName;
		return sendURL(url);
	}
	
	/**
     * 分页查询，包含查询，分页，高亮及获取高亮处摘要等内容；不同于数据库的查询分页，
     * solr的查询返回值中有文档总数，所以无需再次查询总条数。
     * @param page 自定义的翻页对象，包含查询信息及当前页数据列表。
     */
    public static void solrQueryPage(String baseURL,PageSolr pageSolr) {
        SolrQuery query = new SolrQuery();
//        String keywords = pageSolr.getConditions().get("keywords").toString();
//        query.setQuery(keywords);
        /////////////操作排序功能
//        query.addSort("create_time", SolrQuery.ORDER.desc);
        query.setStart((int)pageSolr.getStart());
        query.setRows(pageSolr.getSize());
        query.setQuery("xt_knowledge_title:extjs");
        //设置高亮
//        query.setHighlight(true);//开启高亮组件
//        query.addHighlightField("title");//高亮字段
//        query.addHighlightField("content");//高亮字段
//        query.setHighlightSimplePre("<font color='red'>");//标记，高亮关键字前缀
//        query.setHighlightSimplePost("</font>");//后缀
//        query.setHighlight(true).setHighlightSnippets(1);//获取高亮分片数，一般搜索词可能分布在文章中的不同位置，其所在一定长度的语句即为一个片段，默认为1，但根据业务需要有时候需要多取出几个分片。 - 此处设置决定下文中titleList, contentList中元素的个数
//        query.setHighlightFragsize(150);//每个分片的最大长度，默认为100。适当设置此值，如果太小，高亮的标题可能会显不全；设置太大，摘要可能会太长。
        List<Object> list = new ArrayList<Object>();
        try {
        	//连接solr服务器
    		HttpSolrServer server = new HttpSolrServer(baseURL);
            QueryResponse rsp = server.query(query);
            SolrDocumentList docs = rsp.getResults();
            JSONArray jsonArray = JSONArray.fromObject(docs);  
    		String jsonStr = jsonArray.toString();
            System.out.println(jsonStr);
//            //获取所有高亮的字段
//            Map<String,Map<String,List<String>>> highlightMap=rsp.getHighlighting();
//            Iterator<SolrDocument> iter = docs.iterator();
//            while (iter.hasNext()) {
//                SolrDocument doc = iter.next();
//                String idStr = doc.getFieldValue("id").toString();
//                int id = Integer.parseInt(idStr);
//                String title = doc.getFieldValue("title").toString();
//                String description = doc.getFieldValue("description").toString();
//                String writer = doc.getFieldValue("writer").toString();
//                int catalogId = Integer.parseInt(doc.getFieldValue("catalog_id").toString());
//                Date createTime = (Date)doc.getFieldValue("create_time");
//                list.add(object);
//            }
//            pageSolr.setDatas(list);
//            pageSolr.setCount(docs.getNumFound());
        } catch (Exception e) {
        	throw new ExceptionUtil(e.getMessage(),e.getCause());
        }
    }
    
    


    /**
     * Solr简单根据各种条件查询
     * @param solrCore实例对象
     * @param pageSolr
     */
    public static String solrSimpleQueryPage(SolrCore solrCore,PageSolr pageSolr){
    	List<SolrIndexAttribute> solr_index_attribute_list = solrCore.getSolr_index_attribute_list();
        List<SolrSort> solr_sort_list = solrCore.getSolr_sort_list();
    	SolrQuery query = new SolrQuery();
        //初始化查询对象  
        query = new SolrQuery(); 
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < solr_index_attribute_list.size(); i++) {  
        	SolrIndexAttribute Solr_Index_Attribute = solr_index_attribute_list.get(i);
        	String solr_index_name = solr_index_attribute_list.get(i).getSolr_index_name();
        	Object solr_index_value = pageSolr.getConditions().get(solr_index_name);
        	if(null != solr_index_value && !"".equals(solr_index_value)){
        		if("AND".equals(Solr_Index_Attribute.getSolr_index_attribute_type())){
        			sb.append("AND "+solr_index_name+":"+solr_index_value.toString()+"");
        		}else if("OR".equals(Solr_Index_Attribute.getSolr_index_attribute_type())){
        			sb.append("OR "+solr_index_name+":"+solr_index_value.toString()+"");
        		}else if("NOT".equals(Solr_Index_Attribute.getSolr_index_attribute_type())){
        			sb.append("NOT "+solr_index_name+":"+solr_index_value.toString()+"");
        		}else if("TO".equals(Solr_Index_Attribute.getSolr_index_attribute_type())){
        			//MAP中参数为数组即范围值即格式为map.put("index","1,2"); 范围值
        			sb.append(solr_index_name+":["+solr_index_value.toString().split(",")[0]+" TO "+solr_index_value.toString().split(",")[1]+"]");
        		}
        	}
        }
        query.setQuery(sb.toString()); 
        //设置起始位置与返回结果数  
        query.setStart((int)pageSolr.getStart());  
        query.setRows(pageSolr.getSize());  
        //设置排序  
        for(int i = 0; i < solr_sort_list.size(); i++){  
        	SolrSort solr_Sort = solr_sort_list.get(i);
            if(("asc").equals(solr_Sort.getSolr_sort_code())){  
                query.addSort(solr_Sort.getSolr_index_name(), SolrQuery.ORDER.asc);  
            }else if(("desc").equals(solr_Sort.getSolr_sort_code())){  
                query.addSort(solr_Sort.getSolr_index_name(), SolrQuery.ORDER.desc);  
            }  
        }  
        //设置高亮
        for (int i = 0; i < solr_index_attribute_list.size(); i++) {  
        	String solr_index_name = solr_index_attribute_list.get(i).getSolr_index_name();
        	String sor_index_highlight = solr_index_attribute_list.get(i).getSor_index_highlight();
        	if(null != sor_index_highlight && !"".equals(sor_index_highlight)){
        		if("0".equals(sor_index_highlight)) {  
                    query.setHighlight(true);//开启高亮组件  
                    query.addHighlightField(solr_index_name);//高亮字段  
                    query.setHighlightSimplePre("<font color=\"red\">");//标记  
                    query.setHighlightSimplePost("</font>");  
                    query.setHighlightSnippets(1);//结果分片数，默认为1  
                    query.setHighlightFragsize(1000);//每个分片的最大长度，默认为100  
                }
        	}
        }  
        try {
        	//连接solr服务器
    		HttpSolrServer server = new HttpSolrServer(solrCore.getSolr_url_url());
            QueryResponse rsp = server.query(query);
            SolrDocumentList docs = rsp.getResults();
            JSONArray jsonArray = JSONArray.fromObject(docs);  
    		String jsonStr = jsonArray.toString();
    		String jsonString = "{sucess:true,start:"+docs.getStart()+",limit:"+pageSolr.getSize()+",total:"+docs.getNumFound()+",items:"+jsonStr+"}";
    		System.out.println(jsonString);
    		return jsonString;
        } catch (Exception e) {
        	throw new ExceptionUtil(e.getMessage(),e.getCause());
        }
    }
    
    /**
     * Solr简单根据关键词全文检索查询
     * @param solrCore实例对象
     * @param pageSolr
     */
    public static String solrSimpleQueryFullTextPage(SolrCore solrCore,PageSolr pageSolr){
    	List<SolrIndexAttribute> solr_index_attribute_list = solrCore.getSolr_index_attribute_list();
        List<SolrSort> solr_sort_list = solrCore.getSolr_sort_list();
    	SolrQuery query = new SolrQuery();
        //初始化查询对象  
    	String keywords = "";
    	Object solr_index_value = pageSolr.getConditions().get("keywords");
    	if(null != solr_index_value && !"".equals(solr_index_value)){
    		keywords = solr_index_value.toString();
    	}
        query = new SolrQuery(); 
        StringBuilder sb= new StringBuilder();
        for (int i = 0; i < solr_index_attribute_list.size(); i++) {  
        	String solr_index_name = solr_index_attribute_list.get(i).getSolr_index_name();
        	sb.append(solr_index_name+":"+keywords+" ");
        }  
        query.setQuery(sb.toString());
        //设置起始位置与返回结果数  
        query.setStart((int)pageSolr.getStart());  
        query.setRows(pageSolr.getSize());  
        //设置排序  
        for(int i = 0; i < solr_sort_list.size(); i++){  
        	SolrSort solr_Sort = solr_sort_list.get(i);
            if(("asc").equals(solr_Sort.getSolr_sort_code())){  
                query.addSort(solr_Sort.getSolr_index_name(), SolrQuery.ORDER.asc);  
            }else if(("desc").equals(solr_Sort.getSolr_sort_code())){  
                query.addSort(solr_Sort.getSolr_index_name(), SolrQuery.ORDER.desc);  
            }  
        }  
        //设置高亮
        for (int i = 0; i < solr_index_attribute_list.size(); i++) {  
        	String solr_index_name = solr_index_attribute_list.get(i).getSolr_index_name();
        	String sor_index_highlight = solr_index_attribute_list.get(i).getSor_index_highlight();
        	if(null != sor_index_highlight && !"".equals(sor_index_highlight)){
        		if("0".equals(sor_index_highlight)) {  
                    query.setHighlight(true);//开启高亮组件  
                    query.addHighlightField(solr_index_name);//高亮字段  
                    query.setHighlightSimplePre("<font color=\"red\">");//标记  
                    query.setHighlightSimplePost("</font>");  
                    query.setHighlightSnippets(1);//结果分片数，默认为1  
                    query.setHighlightFragsize(1000);//每个分片的最大长度，默认为100  
                }
        	}
        }  
        try {
        	//连接solr服务器
    		HttpSolrServer server = new HttpSolrServer(solrCore.getSolr_url_url());
            QueryResponse rsp = server.query(query);
            SolrDocumentList docs = rsp.getResults();
            JSONArray jsonArray = JSONArray.fromObject(docs);  
    		String jsonStr = jsonArray.toString();
    		String jsonString = "{sucess:true,start:"+docs.getStart()+",limit:"+pageSolr.getSize()+",total:"+docs.getNumFound()+",items:"+jsonStr+"}";
    		System.out.println(jsonString);
    		return jsonString;
        } catch (Exception e) {
        	throw new ExceptionUtil(e.getMessage(),e.getCause());
        }
    }
    
    
    /**
     * Solr简单过滤查询（带过滤）
     * @param solrCore实例对象
     * @param pageSolr分页信息
     * 过滤器查询，可以提高性能 filter 类似多个条件组合，如and
     */
    public static String solrSimpleQueryFilterPage(SolrCore solrCore,PageSolr pageSolr) {
    	List<SolrIndexAttribute> solr_index_attribute_list = solrCore.getSolr_index_attribute_list();
        List<SolrSort> solr_sort_list = solrCore.getSolr_sort_list();
        //初始化查询对象 
        SolrQuery query = new SolrQuery();
        ///////////////////////关键代码 执行过滤操作////////////////////
        for(int i = 0; i < solr_index_attribute_list.size(); i++) {  
        	String solr_index_name = solr_index_attribute_list.get(i).getSolr_index_name();
        	String sor_index_filter = solr_index_attribute_list.get(i).getSor_index_filter();
        	Object solr_index_value = pageSolr.getConditions().get(solr_index_name);
        	if(null != solr_index_value && !"".equals(solr_index_value) && !"".equals(sor_index_filter) && "0".equals(sor_index_filter)){
        		query.addFilterQuery(solr_index_name + ":" + solr_index_value.toString());  
        	}
        }  
        //设置起始位置与返回结果数  
        query.setStart((int)pageSolr.getStart());  
        query.setRows(pageSolr.getSize());  
        //设置排序  
        for(int i = 0; i < solr_sort_list.size(); i++){  
        	SolrSort solr_Sort = solr_sort_list.get(i);
            if(("asc").equals(solr_Sort.getSolr_sort_code())){  
                query.addSort(solr_Sort.getSolr_index_name(), SolrQuery.ORDER.asc);  
            }else if(("desc").equals(solr_Sort.getSolr_sort_code())){  
                query.addSort(solr_Sort.getSolr_index_name(), SolrQuery.ORDER.desc);  
            }  
        }  
        //设置高亮
        for (int i = 0; i < solr_index_attribute_list.size(); i++) {  
        	String solr_index_name = solr_index_attribute_list.get(i).getSolr_index_name();
        	String sor_index_highlight = solr_index_attribute_list.get(i).getSor_index_highlight();
        	if(null != sor_index_highlight && !"".equals(sor_index_highlight)){
        		if("0".equals(sor_index_highlight)) {  
                    query.setHighlight(true);//开启高亮组件  
                    query.addHighlightField(solr_index_name);//高亮字段  
                    query.setHighlightSimplePre("<font color=\"red\">");//标记  
                    query.setHighlightSimplePost("</font>");  
                    query.setHighlightSnippets(1);//结果分片数，默认为1  
                    query.setHighlightFragsize(1000);//每个分片的最大长度，默认为100  
                }
        	}
        }  
        try {
        	//连接solr服务器
    		HttpSolrServer server = new HttpSolrServer(solrCore.getSolr_url_url());
            QueryResponse rsp = server.query(query);
            SolrDocumentList docs = rsp.getResults();
            JSONArray jsonArray = JSONArray.fromObject(docs);  
    		String jsonStr = jsonArray.toString();
    		String jsonString = "{sucess:true,start:"+docs.getStart()+",limit:"+pageSolr.getSize()+",total:"+docs.getNumFound()+",items:"+jsonStr+"}";
    		System.out.println(jsonString);
    		return jsonString;
        } catch (Exception e) {
        	throw new ExceptionUtil(e.getMessage(),e.getCause());
        }
    }
    
    /**
     * 过滤关键词
     * @param s
     * @return
     */
    public static String escapeQueryChars(String s) {  
        StringBuilder sb = new StringBuilder();  
        for (int i = 0; i < s.length(); i++) {  
          char c = s.charAt(i);  
          // These characters are part of the query syntax and must be escaped  
          if (c == '\\' || c == '+' || c == '-' || c == '!'  || c == '(' || c == ')' || c == ':'  
            || c == '^' || c == '[' || c == ']' || c == '\"' || c == '{' || c == '}' || c == '~'  
            || c == '*' || c == '?' || c == '|' || c == '&'  || c == ';' || c == '/'  
            || Character.isWhitespace(c)) {  
            sb.append('\\');  
          } 
          sb.append(c);  
        }  
        String str = sb.toString();
        if(!StringUtil.isEmpty(str)){
        	str = str.replaceAll("AND", "and").replaceAll("OR", "or").replaceAll("TO", "to").replaceAll("NOT", "not");
        }
        return str;  
      }  
    
    /**
     * 按Facet自动补全
     * Facet的一个应用：自动补全  
     * prefix为前缀，min为最大返回结果数  
     * field需要查询并返回不全的字段，prefix需要查询并返回的字段不全值 
     */
    public static List<SolrAutocomplete> autoCompleteByFacet(SolrCore solrCore,String field, String prefix, Integer min,Integer facetLimit) {  
    	SolrServer server = SolrUtils.server(solrCore.getSolr_url_url()+"/"+solrCore.getSolr_core_name());
    	List<Count> countList = null;  
    	List<SolrAutocomplete> solrAutocompleteList = new ArrayList<SolrAutocomplete>();
        SolrQuery query;  
        QueryResponse rsp = new QueryResponse();  
        // Facet为solr中的层次分类查询  
        try {  
            query = new SolrQuery(field + ":" + prefix);  
            query.setFacet(true);  
            query.setFacetPrefix(prefix); 
            /**
            query.addFacetField(new String[]{"field1","field2","field3"});//设置需要facet的字段 
            **/ 
            query.setFacetMissing(false);//不统计null的值  
            query.addFacetField(field);  
            if(null != min){
            	query.setFacetMinCount(min);//设置返回的数据中每个分组的数据最小值，比如设置为1，则统计数量最小为1，不然不显示  
            }
            if(null != facetLimit){
            	query.setFacetLimit(facetLimit);//限制facet返回的数量  
            }
            rsp = server.query(query);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
        if (null != rsp) {  
            FacetField ff = rsp.getFacetField(field);  
            countList = ff.getValues();  
            if (null == countList) {  
                return null;  
            }  
            for(int i = 0; i < countList.size(); i++){
            	SolrAutocomplete  solrAutocomplete = new SolrAutocomplete();
            	solrAutocomplete.setResultTitle(countList.get(i).getName());
            	solrAutocomplete.setResultCount(countList.get(i).getCount());
            	solrAutocompleteList.add(solrAutocomplete);
            }
        } else {  
            return null;  
        }  
        return solrAutocompleteList;  
    } 
    
    /**
     * 按Group自动补全
     * Group的一个应用：自动补全  
     */
    public static List<SolrAutocomplete> autoCompleteByGroup(SolrCore solrCore,String field, String prefix,Integer rows) {  
    	SolrServer server = SolrUtils.server(solrCore.getSolr_url_url()+"/"+solrCore.getSolr_core_name());
    	List<SolrAutocomplete> solrAutocompleteList = new ArrayList<SolrAutocomplete>();
        SolrQuery query;  
        QueryResponse rsp = new QueryResponse();  
        try {  
            query = new SolrQuery(field + ":" + prefix);  
            //设置通过facet查询为true，表示查询时使用facet机制
            query.setParam(GroupParams.GROUP,true);   
            query.setParam(GroupParams.GROUP_FIELD,field);
            //设置每个quality对应的
            query.setParam(GroupParams.GROUP_LIMIT,"1");
            //设置返回doc文档数据，因只需要数量，故设置为0
            if(null != rows){
            	query.setRows(rows);
            }else{
            	query.setRows(10);
            }
            rsp = server.query(query);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
        if (null != rsp) {  
        	GroupResponse groupResponse =rsp.getGroupResponse();   
        	if(groupResponse !=null) {   
                 List<GroupCommand> groupList =groupResponse.getValues();    
                 for(GroupCommand groupCommand : groupList){   
                     List<Group> groups =groupCommand.getValues();   
                     for(Group group : groups) {
                        SolrAutocomplete  solrAutocomplete = new SolrAutocomplete();
	                   	solrAutocomplete.setResultTitle(group.getGroupValue());
	                   	solrAutocomplete.setResultCount(group.getResult().getNumFound());
	                   	solrAutocompleteList.add(solrAutocomplete);
                     }   
                 }   
             } 
        } else {  
            return null;  
        }  
        return solrAutocompleteList;  
    } 
}
