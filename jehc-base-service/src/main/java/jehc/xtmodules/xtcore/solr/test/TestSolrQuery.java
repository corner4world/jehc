package jehc.xtmodules.xtcore.solr.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.GroupResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.SolrInputField;
import org.apache.solr.common.params.GroupParams;
import org.apache.solr.common.params.ModifiableSolrParams;

import jehc.xtmodules.xtcore.solr.utils.SolrUtils;

/**
 * 测试Query
 * @author 邓纯杰
 *
 */
public class TestSolrQuery {
	/**1.测试一
	@SuppressWarnings("unchecked")
	public static void main(String [] arg){
		String id = "123456789";
		String xt_userinfo_realName = "*邓*";
//		SolrServer server = SolrUtils.server("http://127.0.0.1:8080/solr/#/modules");
		SolrServer server = SolrUtils.server("http://127.0.0.1:8080/solr/modules");
		SolrQuery solrQuery = new SolrQuery();
		//设置查询字段并设置值
		solrQuery.setQuery("xt_userinfo_realName:"+xt_userinfo_realName);
		solrQuery.setRows(100);
		try {
			QueryResponse queryResponse = server.query(solrQuery);
			SolrDocumentList documentList = queryResponse.getResults();
			//1获取字段
			List<FacetField> facets = queryResponse.getFacetFields();
			if(null != facets && !facets.isEmpty()){
				for (FacetField facet : facets) {
			      System.out.println("Facet:" + facet);
			    }
			}
		    //2获取值
			SolrDocument doc;
			int index = 0;
			for(Iterator iterator = documentList.iterator();iterator.hasNext();){
				index++;
				doc = (SolrDocument)iterator.next();
				id = doc.get("id").toString();
				xt_userinfo_realName = doc.get("xt_userinfo_realName").toString();
				String xt_userinfo_address = doc.get("xt_userinfo_address").toString();
				System.out.println("----------id:"+id+"----------xt_userinfo_realName:"+xt_userinfo_realName+"----------xt_userinfo_address:"+xt_userinfo_address);
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
	}
	**/
	
	/**
	 * 一，所有方式查询
	 */
	@SuppressWarnings("unchecked")
	public static void main(String [] arg){
		String id = "123456789";
		String xt_userinfo_realName = "*邓*";
//		SolrServer server = SolrUtils.server("http://127.0.0.1:8080/solr/#/modules");
		SolrServer server = SolrUtils.server("http://127.0.0.1:8080/solr/modules");
		SolrQuery solrQuery = new SolrQuery();
		//设置查询字段并设置值
		solrQuery.setQuery("xt_userinfo_realName:"+xt_userinfo_realName);
		solrQuery.setRows(100);
		solrQuery.addFacetField(new String[] {"xt_userinfo_realName", "xt_userinfo_address"});//设置需要facet的字段  
		solrQuery.setFacetLimit(10);//限制facet返回的数量  
		solrQuery.setFacetMissing(false);//不统计null的值 
		solrQuery.setFacetMinCount(1);//设置返回的数据中每个分组的数据最小值，比如设置为1，则统计数量最小为1，不然不显示
		/** 查询条件
		//设置分页  start=0就是从0开始，，rows=5当前返回5条记录，第二页就是变化start这个值为5就可以了。
		solrQuery.setStart((page.getCurrentPage()-1)*page.getPerPageSize());
		solrQuery.setRows(page.getPerPageSize());
        //排序 如果按照blogId 排序，，那么将blogId desc(or asc) 改成 id desc(or asc)
		solrQuery.addSortField("blogId", ORDER.asc);
       
        //设置高亮
		solrQuery.setHighlight(true); // 开启高亮组件
		solrQuery.addHighlightField("content");// 高亮字段
        solrQuery.addHighlightField("title");// 高亮字段
        solrQuery.setHighlightSimplePre("<font color='red'>");//标记，高亮关键字前缀
        solrQuery.setHighlightSimplePost("</font>");//后缀
        solrQuery.setHighlightSnippets(2);//结果分片数，默认为1
        solrQuery.setHighlightFragsize(1000);//每个分片的最大长度，默认为100
		//分片信息
        solrQuery.setFacet(true)
           .setFacetMinCount(1)
           .setFacetLimit(5)//段
           .addFacetField("content");//分片字段
           **/
		try {
			QueryResponse queryResponse = server.query(solrQuery);
			SolrDocumentList documentList = queryResponse.getResults();
			//1获取字段
			List<FacetField> facets = queryResponse.getFacetFields();
			if(null != facets && !facets.isEmpty()){
				for (FacetField facet : facets) {
			      System.out.println("Facet:" + facet);
			    }
			}
		    //2获取值
			SolrDocument doc;
			int index = 0;
			for(Iterator iterator = documentList.iterator();iterator.hasNext();){
				index++;
				doc = (SolrDocument)iterator.next();
				id = doc.get("id").toString();
				xt_userinfo_realName = doc.get("xt_userinfo_realName").toString();
				String xt_userinfo_address = doc.get("xt_userinfo_address").toString();
				System.out.println("----------id:"+id+"----------xt_userinfo_realName:"+xt_userinfo_realName+"----------xt_userinfo_address:"+xt_userinfo_address);
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
	}
	
	/* 
	 * 二，facet查询
     * 介绍了一下facet之后，来说说怎么实现facet。facet的实现其实很简单，主要在搜索参数上带上就OK。 
     *  
     * facet=on/true #代表开启facet facet.field=cate #代表要统计的面（分组），比如上面的分类，品牌，可以多次出现 
     * facet.limit =20 #每个分组最多返回条数 facet.mincount = 1 #这个表示分组下某一条目的最小数据量 
     * facet.missing = on/true #统计null的值 facet.method = #默认为fc, fc表示Field Cache 
     * 比如 
     * ：http://localhost/product/select/?q=铁观音&facet=on&facet.field=category&facet 
     * .field=brand&facet.mincount=1在搜索结果中返回xml的facet结果 
     *  
     *  
     * view sourceprint? 01 <lst name="facet_counts"> 02 <lst 
     * name="facet_queries"/> 03 <lst name="facet_fields"> 04 <lst 
     * name="category"> 05 <int name="2742">64</int> 06 <int name="793">48</int> 
     * 07 <int name="2741">12</int> 08 <int name="801">6</int> 09 <int 
     * name="1087">1</int> 10 </lst> 11 <lst name="brand"> 12 <int 
     * name="229">74</int> 13 <int name="227">16</int> 14 <int 
     * name="270">13</int> 15 <int name="317">10</int> 16 <int name="0">4</int> 
     * 17 <int name="165">4</int> 18 <int name="203">3</int> 19 <int 
     * name="147">2</int> 20 <int name="166">2</int> 21 <int name="217">1</int> 
     * 22 <int name="342">1</int> 23 <int name="343">1</int> 24 </lst> 25 </lst> 
     * <lst name="category"> 分组名 <int name="2742">64</int> 
     * 分组内条目，name表示条目，64是统计结果数。 
     *  
     *  
     * Date Facet 日期类型的字段在文档中很常见 , 如商品上市时间 , 货物出仓时间 , 书籍上架时间等等 . 某些情况下需要针对这些字段进行 
     * Facet. 不过时间字段的取值有无限性 , 用户往往关心的不是某个时间点而是某个时间段内的查询统计结果 . Solr 
     * 为日期字段提供了更为方便的查询统计方式 . 当然 , 字段的类型必须是 DateField( 或其子类型 ). 需要注意的是 , 使用 Date 
     * Facet 时 , 字段名 , 起始时间 , 结束时间 , 时间间隔这 4 个参数都必须提供 . 与 Field Facet 类似 ,Date 
     * Facet 也可以对多个字段进行 Facet. 并且针对每个字段都可以单独设置参数 . 2.1 facet.date 该参数表示需要进行 Date 
     * Facet 的字段名 , 与 facet.field 一样 , 该参数可以被设置多次 , 表示对多个字段进行 Date Facet. 2.2 
     * facet.date.start 起始时间 , 时间的一般格式为 ” 1995-12-31T23:59:59Z”, 另外可以使用 
     * ”NOW”,”YEAR”,”MONTH” 等等 , 具体格式可以参考 org.apache.solr.schema. DateField 的 
     * java doc. 2.3 facet.date.end 结束时间 . 2.4 facet.date.gap 时间间隔 . 如果 start 为 
     * 2009-1-1,end 为 2010-1-1.gap 设置为 ”+1MONTH” 表示间隔 1 个月 , 那么将会把这段时间划分为 12 
     * 个间隔段 . 注意 ”+” 因为是特殊字符所以应该用 ”%2B” 代替 . 2.5 facet.date.hardend 取值可以为 
     * true|false, 默认为 false. 它表示 gap 迭代到 end 处采用何种处理 . 举例说明 start 为 
     * 2009-1-1,end 为 2009-12-25,gap 为 ”+1MONTH”,hardend 为 false 的话最后一个时间段为 
     * 2009-12-1 至 2010-1-1;hardend 为 true 的话最后一个时间段为 2009-12-1 至 2009-12-25. 
     * 2.6 facet.date.other 取值范围为 before|after|between|none|all, 默认为 none. 
     * before 会对 start 之前的值做统计 . after 会对 end 之后的值做统计 . between 会对 start 至 end 
     * 之间所有值做统计 . 如果 hardend 为 true 的话 , 那么该值就是各个时间段统计值的和 . none 表示该项禁用 . all 表示 
     * before,after,all 都会统计 . 举例 : &facet=on &facet.date=date 
     * &facet.date.start=2009-1-1T0:0:0Z &facet.date.end=2010-1-1T0:0:0Z 
     * &facet.date.gap=%2B1MONTH &facet.date.other=all 
     */  
	public void FacetFieldQuery(){
		String xt_userinfo_realName = "*邓*";
//		SolrServer server = SolrUtils.server("http://127.0.0.1:8080/solr/#/modules");
		SolrServer server = SolrUtils.server("http://127.0.0.1:8080/solr/modules");
		SolrQuery solrQuery = new SolrQuery();
		//设置查询字段并设置值
		solrQuery.setQuery("xt_userinfo_realName:"+xt_userinfo_realName);
		solrQuery.setRows(100);
		//设置需要facet的字段  
		solrQuery.addFacetField(new String[] {"xt_userinfo_realName", "xt_userinfo_address"});
		solrQuery.setFacetLimit(10);//限制facet返回的数量  
		solrQuery.setFacetMissing(false);//不统计null的值 
		solrQuery.setFacetMinCount(1);//设置返回的数据中每个分组的数据最小值，比如设置为1，则统计数量最小为1，不然不显示
		/** 查询条件
		//设置分页  start=0就是从0开始，，rows=5当前返回5条记录，第二页就是变化start这个值为5就可以了。
		solrQuery.setStart((page.getCurrentPage()-1)*page.getPerPageSize());
		solrQuery.setRows(page.getPerPageSize());
        //排序 如果按照blogId 排序，，那么将blogId desc(or asc) 改成 id desc(or asc)
		solrQuery.addSortField("blogId", ORDER.asc);
       
        //设置高亮
		solrQuery.setHighlight(true); // 开启高亮组件
		solrQuery.addHighlightField("content");// 高亮字段
        solrQuery.addHighlightField("title");// 高亮字段
        solrQuery.setHighlightSimplePre("<font color='red'>");//标记，高亮关键字前缀
        solrQuery.setHighlightSimplePost("</font>");//后缀
        solrQuery.setHighlightSnippets(2);//结果分片数，默认为1
        solrQuery.setHighlightFragsize(1000);//每个分片的最大长度，默认为100
		//分片信息
        solrQuery.setFacet(true)
           .setFacetMinCount(1)
           .setFacetLimit(5)//段
           .addFacetField("content");//分片字段
           **/
		try {
			QueryResponse queryResponse = server.query(solrQuery);
			//1获取字段
			List<FacetField> facets = queryResponse.getFacetFields();
			if(null != facets && !facets.isEmpty()){
				for (FacetField facet : facets) {
			      System.out.println("Facet:" + facet);
			    }
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
	}
	
	 /** 
     * 三，按group进行查找
     * @Title SearchGroup 
     * @Description 按group进行查找 
     * @param QUERY_CONTENT 查询内容 
     * @param QUERY_ROWS 查找的数量,默认是10 
     * @param GROUP true or false 是否按group查询 
     * @param GROUP_FIELD 查询field 
     * @param GROUP_LIMIT The number of results (documents) to return for each group. Defaults to 1 
     * @Return void 
     * @Throws  
     * @Date 2014-5-7 
     * 输出结果的时候，由于定义的数据索引没有做很好是调整，显示的结果并不理想，不过此方法可以作为参考 
     */  
    public void SearchGroup(String QUERY_CONTENT,int QUERY_ROWS, Boolean GROUP, String GROUP_FIELD,String GROUP_LIMIT) {  
         SolrServer server = SolrUtils.server("http://127.0.0.1:8080/solr/modules");
         SolrQuery param = new SolrQuery();    
         param.setQuery("xt_userinfo_realName:"+QUERY_CONTENT);    
         param.setRows(QUERY_ROWS);    
         param.setParam(GroupParams.GROUP, GROUP);    
         param.setParam(GroupParams.GROUP_FIELD, GROUP_FIELD);            
         param.setParam(GroupParams.GROUP_LIMIT, GROUP_LIMIT);    
         QueryResponse response = null;    
         try {    
             response = server.query(param);    
         } catch (SolrServerException e) {   
        	 e.printStackTrace();
         }    
         Map<String, Integer> info = new HashMap<String, Integer>();    
         GroupResponse groupResponse = response.getGroupResponse();    
         if(groupResponse != null) {    
             List<GroupCommand> groupList = groupResponse.getValues();    
             for(GroupCommand groupCommand : groupList) {    
                 List<Group> groups = groupCommand.getValues();    
                 for(Group group : groups) {    
                     info.put(group.getGroupValue(), (int)group.getResult().getNumFound());   
                     System.out.println(group.getGroupValue()+"---"+group.getResult().getNumFound());  
                 }    
             }    
         }    
    }  
    
    /**
     * 四，自动补全（Facet）
     * Facet的一个应用：自动补全  
     * prefix为前缀，min为最大返回结果数  
     * field需要查询并返回不全的字段，prefix需要查询并返回的字段不全值 
     */
    public String[] autoComplete(String field, String prefix, int min) {  
        /*------------第一处标记------------------------*/  
    	SolrServer server = SolrUtils.server("http://127.0.0.1:8080/solr/modules");
        String words[] = null;  
        StringBuffer sb = new StringBuffer("");  
        SolrQuery query = new SolrQuery(field + ":" + prefix);  
        QueryResponse rsp = new QueryResponse();  
        // Facet为solr中的层次分类查询  
        /*------------第二处标记：程序从第一处标记执行到这里需要300ms所以将上面的代码进行实例化最好------------------------*/  
        try {  
            query.setFacet(true);  
            // query.setQuery("*:*");  
            query = new SolrQuery(field + ":" + prefix);  
            query.setFacetPrefix(prefix);  
            query.addFacetField(field);  
            rsp = server.query(query);  
            /*------------第三处标记：程序从第二处标记执行到这里需要200ms但此处很难再进行优化，由于查询的复杂性------------------------*/  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
        if (null != rsp) {  
            FacetField ff = rsp.getFacetField(field);  
            List<Count> countList = ff.getValues();  
            if (null == countList) {  
                return null;  
            }  
            for (int i = 0; i < countList.size(); i++) {  
                String tmp[] = countList.get(i).toString().split(" ");  
                // 排除单个字  
                if (tmp[0].length() < 2) {  
                    continue;  
                }  
                sb.append(tmp[0] + " ");  
                min--;  
                if (min == 0) {  
                    break;  
                }  
            }  
            words = sb.toString().split(" ");  
        } else {  
            return null;  
        }  
        return words;  
    }  
    
    /**
     * 五，查询
     * SolrJ提供的查询功能比较强大，可以进行结果中查询、范围查询、排序等。
     * 补充一下范围查询的格式：[star t TO end]，start与end是相应数据格式的值的字符串形式，“TO” 一定要保持大写！ 
     * field 查询的字段名称数组 key 查询的字段名称对应的值 start 查询的起始位置 count 一次查询出来的数量 sortfield 
     * 需要排序的字段数组 flag 需要排序的字段的排序方式如果为true 升序 如果为false 降序 hightlight 是否需要高亮显示 
     * @param field
     * @param key
     * @param start
     * @param count
     * @param sortfield
     * @param flag
     * @param hightlight
     * @return
     */
    @SuppressWarnings("deprecation")
	public QueryResponse Search(String[] field, String[] key, int start,  
            int count, String[] sortfield, Boolean[] flag, Boolean hightlight) {  
    	SolrServer server = SolrUtils.server("http://127.0.0.1:8080/solr/modules");
        // 检测输入是否合法  
        if (null == field || null == key || field.length != key.length) {  
            return null;  
        }  
        if (null == sortfield || null == flag  
                || sortfield.length != flag.length) {  
            return null;  
        }  
        SolrQuery query = null;  
        try {  
            //初始化查询对象  
            query = new SolrQuery(field[0] + ":" + key[0]);  
            for (int i = 0; i < field.length; i++) {  
                query.addFilterQuery(field[i] + ":" + key[i]);  
            }  
            //设置起始位置与返回结果数  
            query.setStart(start);  
            query.setRows(count);  
            //设置排序  
            for (int i = 0; i < sortfield.length; i++) {  
                if (flag[i]) {  
                    query.addSortField(sortfield[i], SolrQuery.ORDER.asc);  
                } else {  
                    query.addSortField(sortfield[i], SolrQuery.ORDER.desc);  
                }  
            }  
            //设置高亮  
            if (null != hightlight) {  
                query.setHighlight(true); //开启高亮组件  
                query.addHighlightField("xt_userinfo_realName");//高亮字段  
                query.setHighlightSimplePre("<font color=\"red\">");//标记  
                query.setHighlightSimplePost("</font>");  
                query.setHighlightSnippets(1);//结果分片数，默认为1  
                query.setHighlightFragsize(1000);//每个分片的最大长度，默认为100  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        QueryResponse rsp = null;  
        try {  
            rsp = server.query(query);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
        //返回查询结果  
        return rsp;  
    }  
    /////////////////////////////////////////////////以下为待测试///////////////////////////////////////
    /**
     * 六，查询所有索引信息
     */
    public void queryAll() {
    	SolrServer server = SolrUtils.server("http://127.0.0.1:8080/solr/modules");
        ModifiableSolrParams params = new ModifiableSolrParams();
        //查询关键词，*:*代表所有属性、所有值，即所有index
        params.set("q", "*:*");
        //分页，start=0就是从0开始，rows=5当前返回5条记录，第二页就是变化start这个值为5就可以了。
        params.set("start", 0);
        params.set("rows", Integer.MAX_VALUE);
        //排序，如果按照id 排序，，那么将xt_userinfo_realName desc 改成 id desc(or asc)
        params.set("sort", "xt_userinfo_realName desc");
        //返回信息 * 为全部 这里是全部加上xt_userinfo_realName，如果不加下面就不能使用score
        params.set("fl", "*,xt_userinfo_realName");
        try {
            QueryResponse response = server.query(params);
            
            SolrDocumentList list = response.getResults();
            for (int i = 0; i < list.size(); i++) {
                fail(list.get(i));
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }
    public final void fail(Object o) {
        System.out.println(o);
    }
    
    /**
     * 添加Bean完成doc添加操作
     * <b>function:</b> 添加JavaEntity Bean
     * @createDate 2011-10-21 上午09:55:37
     */
    public void addBean() {
    	SolrServer server = SolrUtils.server("http://127.0.0.1:8080/solr/modules");
        //Index需要添加相关的Annotation注解，便于告诉solr哪些属性参与到index中
        Index index = new Index();
        index.setId("4");
        index.setName("add bean index");
        index.setManu("index bean manu");
        index.setCat(new String[] { "a1", "b2" });
        
        try {
            //添加Index Bean到索引库
            UpdateResponse response = server.addBean(index);
            fail(server.commit());//commit后才保存到索引库
            fail(response);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        queryAll();
    }
    
    /**
     * 其他方法
     */
    public void otherMethod() {
    	SolrServer server = SolrUtils.server("http://127.0.0.1:8080/solr/modules");
        fail(server.getBinder());
        try {
            fail(server.optimize());//合并索引文件，可以优化索引、提供性能，但需要一定的时间
            fail(server.ping());//ping服务器是否连接成功
            Index index = new Index();
            index.setId("299");
            index.setName("add bean index199");
            index.setManu("index bean manu199");
            index.setCat(new String[] { "a199", "b199" });
            UpdateResponse response = server.addBean(index);
            fail("response: " + response);
            queryAll();
            //回滚掉之前的操作，rollback addBean operation
            fail("rollback: " + server.rollback());
            //提交操作，提交后无法回滚之前操作；发现addBean没有成功添加索引
            fail("commit: " + server.commit());
            queryAll();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * <b>function:</b> 删除索引操作
     * @author hoojo
     * @createDate 2011-10-21 上午10:04:28
     */
    public void remove() {
        try {
        	SolrServer server = SolrUtils.server("http://127.0.0.1:8080/solr/modules");
            //删除id为1的索引
            server.deleteById("1");
            server.commit();
            
            //根据id集合，删除多个索引
            List<String> ids = new ArrayList<String>();
            ids.add("2");
            ids.add("3");
            server.deleteById(ids);
            server.commit(true, true);
            
            //删除查询到的索引信息
            server.deleteByQuery("id:4 id:6");
            server.commit(true, true);
            queryAll();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 文档查询
     */
    public void queryCase() {
    	SolrServer server = SolrUtils.server("http://127.0.0.1:8080/solr/modules");
        //AND 并且
        SolrQuery params = new SolrQuery("name:apple AND manu:inc");
        
        //OR 或者
        params.setQuery("name:apple OR manu:apache");
        //空格 等同于 OR
        params.setQuery("name:server manu:dell");
        
        //params.setQuery("name:solr - manu:inc");
        //params.setQuery("name:server + manu:dell");
        
        //查询name包含solr apple
        params.setQuery("name:solr,apple");
        //manu不包含inc
        params.setQuery("name:solr,apple NOT manu:inc");
        
        //50 <= price <= 200
        params.setQuery("price:[50 TO 200]");
        params.setQuery("popularity:[5 TO 6]");
        //params.setQuery("price:[50 TO 200] - popularity:[5 TO 6]");
        //params.setQuery("price:[50 TO 200] + popularity:[5 TO 6]");
        
        //50 <= price <= 200 AND 5 <= popularity <= 6
        params.setQuery("price:[50 TO 200] AND popularity:[5 TO 6]");
        params.setQuery("price:[50 TO 200] OR popularity:[5 TO 6]");
        
        //过滤器查询，可以提高性能 filter 类似多个条件组合，如and
        //params.addFilterQuery("id:VA902B");
        //params.addFilterQuery("price:[50 TO 200]");
        //params.addFilterQuery("popularity:[* TO 5]");
        //params.addFilterQuery("weight:*");
        //0 < popularity < 6  没有等于
        //params.addFilterQuery("popularity:{0 TO 6}");
        
        //排序
        params.addSortField("id", SolrQuery.ORDER.asc);
        
        //分页：start开始页，rows每页显示记录条数
        //params.add("start", "0");
        //params.add("rows", "200");
        //params.setStart(0);
        //params.setRows(200);
        
        //设置高亮
        params.setHighlight(true); // 开启高亮组件
        params.addHighlightField("name");// 高亮字段
        params.setHighlightSimplePre("<font color='red'>");//标记，高亮关键字前缀
        params.setHighlightSimplePost("</font>");//后缀
        params.setHighlightSnippets(1);//结果分片数，默认为1
        params.setHighlightFragsize(1000);//每个分片的最大长度，默认为100
     
        //分片信息
        params.setFacet(true)
            .setFacetMinCount(1)
            .setFacetLimit(5)//段
            .addFacetField("name")//分片字段
            .addFacetField("inStock"); 
        
        //params.setQueryType("");
        
        try {
            QueryResponse response = server.query(params);
            
            /*List<Index> indexs = response.getBeans(Index.class);
            for (int i = 0; i < indexs.size(); i++) {
                fail(indexs.get(i));
            }*/
            
            //输出查询结果集
            SolrDocumentList list = response.getResults();
            fail("query result nums: " + list.getNumFound());
            for (int i = 0; i < list.size(); i++) {
                fail(list.get(i));
            }
            
            //输出分片信息
            List<FacetField> facets = response.getFacetFields();
            for (FacetField facet : facets) {
                fail(facet);
                List<Count> facetCounts = facet.getValues();
                for (FacetField.Count count : facetCounts) {
                    System.out.println(count.getName() + ": " + count.getCount());
                }
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } 
    }
    
    /**
     * 创建Solr文档
     */
    public void createDoc() {
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", System.currentTimeMillis());
        doc.addField("name", "SolrInputDocument");
        doc.addField("age", 22, 2.0f);
        doc.addField("like", new String[] { "music", "book", "sport" });
        doc.put("address", new SolrInputField("guangzhou"));
        doc.setField("sex", "man");
        doc.setField("remark", "china people", 2.0f);
        fail(doc);
    }
    
    /**
     * Document文档和JavaBean相互转换
     * <b>function:</b> 利用DocumentObjectBinder对象将SolrInputDocument 和 User对象相互转换
     */
    public void docAndBean4Binder() {
        SolrDocument doc = new SolrDocument();
        doc.addField("id", 456);
        doc.addField("name", "SolrInputDocument");
        
        doc.addField("likes", new String[] { "music", "book", "sport" });
        
        doc.put("address", "guangzhou");
        
        doc.setField("sex", "man");
        doc.setField("remark", "china people");
        
        DocumentObjectBinder binder = new DocumentObjectBinder();
        
        User user = new User();
        user.setId(222);
        user.setName("JavaBean");
        user.setLike(new String[] { "music", "book", "sport" });
        user.setAddress("guangdong");
        
        fail(doc);
        // User ->> SolrInputDocument
        fail(binder.toSolrInputDocument(user));
        // SolrDocument ->> User
        fail(binder.getBean(User.class, doc));
        
        SolrDocumentList list = new SolrDocumentList();
        list.add(doc);
        list.add(doc);
        //SolrDocumentList ->> List
        fail(binder.getBeans(User.class, list));
    }
    
    /**
     * <b>function:</b> SolrInputDocument的相关方法
     * @author hoojo
     * @createDate 2011-10-21 下午03:44:30
     */
    public void docMethod() {
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", System.currentTimeMillis());
        doc.addField("name", "SolrInputDocument");
        doc.addField("age", 23, 1.0f);
        doc.addField("age", 22, 2.0f);
        doc.addField("age", 24, 0f);
        fail(doc.entrySet());
        fail(doc.get("age"));
        //排名有用，类似百度竞价排名
        doc.setDocumentBoost(2.0f);
        fail(doc.getDocumentBoost());
        fail(doc.getField("name"));
        fail(doc.getFieldNames());//keys
        fail(doc.getFieldValues("age"));
        fail(doc.getFieldValues("id"));
        fail(doc.values());
    }
}
