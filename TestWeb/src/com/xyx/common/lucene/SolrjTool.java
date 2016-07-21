package com.xyx.common.lucene;

import java.util.Iterator;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import com.xyx.common.Page;
import com.xyx.document.bean.Document;

public class SolrjTool {

	private static final String SOLRHOST_STRING = "http://localhost:8983/solr/xyx";

	public static void AddDoc(Document document) {
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", "document" + document.getId());
		doc.addField("content", document.getDoccontent());
		doc.addField("createtime", document.getCreatetime());
		doc.addField("title", document.getDoctitle());
		doc.addField("type", document.getDoctype());
		doc.addField("categoryid", document.getCategoryid());
		doc.addField("isopen", document.getIsopen());
		doc.addField("personid", document.getPersonid());
		try {
			HttpSolrServer server = new HttpSolrServer(SOLRHOST_STRING);
			server.add(doc);
			server.commit();
			System.out.println(document.getDoccontent().length());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void delDocsById(String id) {
		long start = System.currentTimeMillis();
		try {
			HttpSolrServer server = new HttpSolrServer(SOLRHOST_STRING);
			server.deleteById(id);
			server.commit();
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("time elapsed(ms):"
				+ (System.currentTimeMillis() - start));
	}
	
	public static void delDocs() {
		long start = System.currentTimeMillis();
		try {
			HttpSolrServer server = new HttpSolrServer(SOLRHOST_STRING);

			server.deleteByQuery("*:*");
			server.commit();
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("time elapsed(ms):"
				+ (System.currentTimeMillis() - start));
	}

	public static Page query(int currentPage,int pageSize,String personid,String categoryid,String queryString) throws SolrServerException {
		HttpSolrServer server = new HttpSolrServer(SOLRHOST_STRING);
		server.setMaxRetries(1); // defaults to 0. > 1 not recommended.
		server.setConnectionTimeout(5000); // 5 seconds to establish TCP

		SolrQuery query = new SolrQuery();
		query.setQuery("(content:"+queryString+")");
		query.setStart((currentPage-1)*pageSize);
		query.setRows(pageSize);
		
		query.setHighlight(true);// 开启高亮组件
        query.addHighlightField("title");// 高亮字段
        query.addHighlightField("content");// 高亮字段
        query.setHighlightSimplePre("<font color='red'>");//标记，高亮关键字前缀
        query.setHighlightSimplePost("</font>");//后缀
        query.setHighlight(true).setHighlightSnippets(1); //获取高亮分片数，一般搜索词可能分布在文章中的不同位置，其所在一定长度的语句即为一个片段，默认为1，但根据业务需要有时候需要多取出几个分片。 - 此处设置决定下文中titleList, contentList中元素的个数
        query.setHighlightFragsize(20);//每个分片的最大长度，默认为100。适当设置此值，如果太小，高亮的标题可能会显不全；设置太大，摘要可能会太长。
		
		QueryResponse response = server.query(query);
		Page page = new Page();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalCount((int)response.getResults().getNumFound());
		page.resetPageNo();
		page.setList(response.getResults());
		return page;

//		for (SolrDocument doc : response.getResults()) {
//			System.out.println("----------" + iRow + "------------");
//			System.out.println("id: " + doc.getFieldValue("id").toString());
//			System.out.println("name: " + doc.getFieldValue("name").toString());
//			delDocsById(doc.getFieldValue("id").toString());
//			iRow++;
//		}
	}

	public static void main(String[] args) {
		Page page=null;
		try {
			page = query(1, 10, "60", "1", "谈论 马拉松");
			System.out.println(page.getList().size());
			for (SolrDocument doc : (SolrDocumentList)page.getList()) {
				System.out.println("id: " + doc.getFieldValue("id").toString());
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		System.out.println(page.getTotalCount());
	}

}
