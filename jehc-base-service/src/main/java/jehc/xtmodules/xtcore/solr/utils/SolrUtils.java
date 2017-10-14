package jehc.xtmodules.xtcore.solr.utils;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;

/**
 * Solr支持类
 * @author 邓纯杰
 *
 */
public class SolrUtils {
	public static SolrServer server(String url){
		SolrServer solrServer = new HttpSolrServer(url);
		return solrServer;
	}
}
