package com.xyx.common.lucene;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
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
		query.setStart((currentPage-1)*pageSize+1);
		query.setRows(pageSize);

		QueryResponse response = server.query(query);
		System.out.println("Find:" + response.getResults().getNumFound());
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
		delDocs();
	}

}
