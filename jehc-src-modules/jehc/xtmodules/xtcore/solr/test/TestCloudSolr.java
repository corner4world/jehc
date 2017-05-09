package jehc.xtmodules.xtcore.solr.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.cloud.ClusterState;
import org.apache.solr.common.cloud.ZkStateReader;
/**
 * 测试云连接
 * @author 邓纯杰
 *
 */
public class TestCloudSolr {
	private static CloudSolrServer cloudSolrServer;

	private static synchronized CloudSolrServer getCloudSolrServer(
			final String zkHost) {
		if (cloudSolrServer == null) {
			cloudSolrServer = new CloudSolrServer(zkHost);
		}
		return cloudSolrServer;
	}
	private void addIndex(SolrServer solrServer) {
		try {
			SolrInputDocument doc1 = new SolrInputDocument();
			doc1.addField("id", "1");
			doc1.addField("name", "张民");

			SolrInputDocument doc2 = new SolrInputDocument();
			doc2.addField("id", "2");
			doc2.addField("name", "刘俊");

			SolrInputDocument doc3 = new SolrInputDocument();
			doc3.addField("id", "3");
			doc3.addField("name", "刘俊2");

			Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
			docs.add(doc1);
			docs.add(doc2);
			docs.add(doc3);

			solrServer.add(docs);
			solrServer.commit();

		} catch (SolrServerException e) {
			System.out.println("Add docs Exception !!!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Unknowned Exception!!!!!");
			e.printStackTrace();
		}

	}

	public void search(SolrServer solrServer, String String) {
		SolrQuery query = new SolrQuery();
		query.setQuery(String);

		try {
			QueryResponse response = solrServer.query(query);
			SolrDocumentList docs = response.getResults();

			System.out.println("文档个数：" + docs.getNumFound());
			System.out.println("查询时间：" + response.getQTime());

			for (SolrDocument doc : docs) {
				String name = (String) doc.getFieldValue("name");
				String id = (String) doc.getFieldValue("id");
				System.out.println("id: " + id);
				System.out.println("name: " + name);
				System.out.println();
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Unknowned Exception!!!!");
			e.printStackTrace();
		}
	}

	public void deleteAllIndex(SolrServer solrServer) {
		try {
			solrServer.deleteByQuery("*:*");// delete everything!
			solrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Unknowned Exception !!!!");
			e.printStackTrace();
		}
	}
	
	/**
	 * 邓纯杰T1
	 */
	public void t1(){
		final String zkHost = "localhost:8080";
		final String defaultCollection = "collectionOne";
		final int zkClientTimeout = 20000;
		final int zkConnectTimeout = 1000;
		CloudSolrServer cloudSolrServer = getCloudSolrServer(zkHost);
		System.out.println("The Cloud SolrServer Instance has benn created!");
		cloudSolrServer.setDefaultCollection(defaultCollection);
		cloudSolrServer.setZkClientTimeout(zkClientTimeout);
		cloudSolrServer.setZkConnectTimeout(zkConnectTimeout);
		cloudSolrServer.connect();
		System.out.println("The cloud Server has been connected !!!!");
		ZkStateReader zkStateReader = cloudSolrServer.getZkStateReader();
		ClusterState cloudState = zkStateReader.getClusterState();
		System.out.println(cloudState);
		// 测试实例！
		TestCloudSolr test = new TestCloudSolr();
		System.out.println("测试添加index！！！");
		// 添加index
		test.addIndex(cloudSolrServer);
		System.out.println("测试查询query！！！！");
		test.search(cloudSolrServer, "id:*");
		System.out.println("测试删除！！！！");
		test.deleteAllIndex(cloudSolrServer);
		System.out.println("删除所有文档后的查询结果：");
		test.search(cloudSolrServer, "*:*");
		cloudSolrServer.shutdown();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final String zkHost = "127.0.0.1:8080/solr";
		final String defaultCollection = "modules";
		final int zkClientTimeout = 200000;
		final int zkConnectTimeout = 10000;
		CloudSolrServer cloudSolrServer = getCloudSolrServer(zkHost);
		System.out.println("The Cloud SolrServer Instance has benn created!");
		cloudSolrServer.setDefaultCollection(defaultCollection);
		cloudSolrServer.setZkClientTimeout(zkClientTimeout);
		cloudSolrServer.setZkConnectTimeout(zkConnectTimeout);
		cloudSolrServer.connect();
		System.out.println("The cloud Server has been connected !!!!");
		ZkStateReader zkStateReader = cloudSolrServer.getZkStateReader();
		ClusterState cloudState = zkStateReader.getClusterState();
		System.out.println(cloudState);
		// 测试实例！
		TestCloudSolr test = new TestCloudSolr();
		test.search(cloudSolrServer, "id:*");
		System.out.println("测试删除！！！！");
		test.deleteAllIndex(cloudSolrServer);
		System.out.println("删除所有文档后的查询结果：");
		test.search(cloudSolrServer, "*:*");
		cloudSolrServer.shutdown();
	}
}
