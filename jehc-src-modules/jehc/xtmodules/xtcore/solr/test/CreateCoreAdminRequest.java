package jehc.xtmodules.xtcore.solr.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.apache.solr.common.util.NamedList;
import org.apache.solr.util.FileUtils;

/**
 * 创建多个Solr Core
 * 
 * @author 邓纯杰
 * 
 */
public class CreateCoreAdminRequest {
	public static final String SOLR_URL = "http://127.0.0.1:8080/solr/";
	public static final String DEFAULT_CORE_NAME = "defaultmodules";
	public static final String NEW_CORE_NAME = "productmodules";

	public static void main(String[] args) {
		try {
			createCore(NEW_CORE_NAME);
			reloadCore(DEFAULT_CORE_NAME);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param coreName
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public static void reloadCore(String coreName) throws SolrServerException,
			IOException {
		// 连接solr服务器
		HttpSolrServer server = new HttpSolrServer(SOLR_URL);
		CoreAdminRequest.reloadCore(coreName, server);
		System.out.println("重新加载" + coreName + "成功");
	}

	/**
	 * 创建多个Core
	 * 
	 * @param coreName
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public static void createCore(String coreName) throws SolrServerException,
			IOException {
		// 连接solr服务器
		HttpSolrServer server = new HttpSolrServer(SOLR_URL);

		// 获得solr.xml配置好的cores作为默认，获得默认core的路径
		NamedList<Object> list = CoreAdminRequest.getStatus(DEFAULT_CORE_NAME,
				server).getCoreStatus().get(DEFAULT_CORE_NAME);
		String path = (String) list.get("instanceDir");

		// 获得solrhome,也就是solr放置索引的主目录
		String solrHome = path.substring(0, path.indexOf(DEFAULT_CORE_NAME));

		// 建立新core所在文件夹
		File corePath = new File(solrHome + File.separator + NEW_CORE_NAME);
		if (!corePath.exists()) {
			corePath.mkdirs();
		}
		// 建立新core下的conf文件夹
		/**
		 * File confPath = new File(corePath.getAbsolutePath() + File.separator +
		 * "conf/");
		 */
		File confPath = new File(corePath.getAbsolutePath() + File.separator);
		if (!confPath.exists()) {
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
		// 整个文件拷贝
		new CreateCoreAdminRequest().copyFolder(new File(path), new File(corePath.getAbsolutePath()
				+ File.separator));
		//创建新core,同时会把新core的信息添加到solr.xml里
		CoreAdminRequest.createCore(coreName, coreName, server);
	}

	/** 
	 * 复制一个目录及其子目录、文件到另外一个目录 
	 * @param src 
	 * @param dest 
	 * @throws IOException 
	 */  
	public void copyFolder(File src, File dest) throws IOException {  
	    if (src.isDirectory()) {  
	        if (!dest.exists()) {  
	            dest.mkdir();  
	        }  
	        String files[] = src.list();  
	        for (String file : files) {  
	            File srcFile = new File(src, file);  
	            File destFile = new File(dest, file);  
	            // 递归复制  
	            copyFolder(srcFile, destFile);  
	        }  
	    } else {  
	    	FileUtils.copyFile(src, dest);
	    }  
	}  
}
