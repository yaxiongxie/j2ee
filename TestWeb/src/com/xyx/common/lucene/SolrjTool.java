package com.xyx.common.lucene;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
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
		doc.addField("tid", document.getId());
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

	public static Page query(int currentPage,int pageSize,int personid,int categoryid,String queryString) throws SolrServerException {
		long begin=System.currentTimeMillis();
		HttpSolrServer server = new HttpSolrServer(SOLRHOST_STRING);
		server.setMaxRetries(1); // defaults to 0. > 1 not recommended.
		server.setConnectionTimeout(5000); // 5 seconds to establish TCP

		SolrQuery query = new SolrQuery();
		if(StringUtils.isEmpty(queryString)){
			query.setQuery("*:*");
		}else{
			query.setQuery("(content:"+queryString+" OR title:"+queryString+" OR type:"+queryString+")");
		}
		query.addSort("tid", ORDER.desc);
		query.setStart((currentPage-1)*pageSize);
		query.setRows(pageSize);
		query.setFields("id","title","type");
		
		query.setHighlight(true);// 开启高亮组件
        query.addHighlightField("title");// 高亮字段
        query.addHighlightField("content");// 高亮字段
        query.addHighlightField("type");// 高亮字段
        query.setHighlightSimplePre("<font color='red'>");//标记，高亮关键字前缀
        query.setHighlightSimplePost("</font>");//后缀
        query.setHighlight(true).setHighlightSnippets(2); //获取高亮分片数，一般搜索词可能分布在文章中的不同位置，其所在一定长度的语句即为一个片段，默认为1，但根据业务需要有时候需要多取出几个分片。 - 此处设置决定下文中titleList, contentList中元素的个数
        query.setHighlightFragsize(40);//每个分片的最大长度，默认为100。适当设置此值，如果太小，高亮的标题可能会显不全；设置太大，摘要可能会太长。
		
		QueryResponse response = server.query(query);
		Page page = new Page();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		page.setTotalCount((int)response.getResults().getNumFound());
		page.resetPageNo();

		Map<String,Map<String,List<String>>> highlightMap=response.getHighlighting();
		List<Document> documents=new ArrayList<Document>();
		for (SolrDocument doc : response.getResults()) {
			Document document=new Document();
			String idStr=doc.getFieldValue("id").toString();
			
			List<String> titleList=highlightMap.get(idStr).get("title");
            List<String> contentList=highlightMap.get(idStr).get("content");
            List<String> typelist=highlightMap.get(idStr).get("type");
            document.setId(Integer.parseInt(idStr.substring(8)));
            if(titleList!=null && titleList.size()>0){
                document.setDoctitle(titleList.get(0));
            }else{
            	document.setDoctitle(doc.getFieldValue("title").toString());
            }
            if(contentList!=null && contentList.size()>0){
            	document.setDoccontent(contentList.get(0));
            }else{
            	document.setDoccontent("");
            }
            if(typelist!=null && typelist.size()>0){
            	document.setDoctype(typelist.get(0));
            }else{
            	document.setDoctype(doc.getFieldValue("type").toString());
            }
            documents.add(document);
		}
		page.setList(documents);
		long end=System.currentTimeMillis();
		System.out.println((end-begin));
		return page;
	}

	public static void main(String[] args) {
//		Page page=null;
//		try {
//			page = query(1, 10, 60, 1, "陈");
//			System.out.println(page.getList().size());
//			for (Document doc : (List<Document>)page.getList()) {
//				System.out.println(doc.getDoctitle()+"==="+doc.getDoctype()+"==="+doc.getDoccontent());
//			}
//		} catch (SolrServerException e) {
//			e.printStackTrace();
//		}
//		System.out.println(page.getTotalCount());
		delDocs();
	}

}
