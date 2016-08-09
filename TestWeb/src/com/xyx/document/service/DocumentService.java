package com.xyx.document.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.xyx.common.BaseService;
import com.xyx.common.Page;
import com.xyx.common.lucene.SolrjTool;
import com.xyx.common.tree.TreeUtil;
import com.xyx.core.bean.CorePerson;
import com.xyx.document.bean.Document;
import com.xyx.document.bean.DocumentCategory;

@Component("DocumentService")
public class DocumentService extends BaseService {

	public void saveCategory(JSONObject jsonObject) throws Exception {
		DocumentCategory documentCategory=new DocumentCategory();
		int id=jsonObject.getInt("id");
		String name=jsonObject.getString("name");
		int pid=jsonObject.getInt("pid");
		int personid=jsonObject.getInt("personid");
		documentCategory.setCode("");
		documentCategory.setName(name);
		documentCategory.setOrdernum(0);
		documentCategory.setParentId(new Integer(pid));
		documentCategory.setPersonid(personid);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		documentCategory.setCreatetime(sdf.format(new Date()));
		if(id!=0){
			documentCategory.setId(new Integer(id));
		}
		saveOrUpdate(documentCategory);
	}

	public DocumentCategory loadCategory(JSONObject jsonObject) throws Exception {
		int id = jsonObject.getInt("id");
		DocumentCategory documentCategory = load(DocumentCategory.class, id);
		return documentCategory;
	}

	public void deleteCategory(JSONObject jsonObject) throws Exception {
		int id = jsonObject.getInt("id");
		deleteById(DocumentCategory.class, id);
	}
	
	@SuppressWarnings("rawtypes")
	public String loadCategoryAll(int personid) throws Exception{
		List list=getListByHQL("from DocumentCategory where personid=?",personid);
		String result=new TreeUtil().getJson(list, -1).toString();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public String loadDocumentPage(JSONObject jsonObject) throws Exception{
		int pageNo=jsonObject.getInt("currentPage");
		int pageSize=jsonObject.getInt("pageSize");
		int categoryid=jsonObject.getInt("categoryid");
		String queryString=jsonObject.getString("queryString");
		int personid=jsonObject.getInt("personid");
		Page page=SolrjTool.query(pageNo, pageSize, personid, categoryid, queryString);
		String hqlString="from Document ";
		List<Document> documents=page.getList();
		String ids=getIds(documents);
		String whereString="where id in("+ids+")";
		List<Document> list=getListByHQL(hqlString+whereString, new Object[]{});
		list=sortList(list, ids);
		for(Document document:list){
			for(Document doc:documents){
				if(doc.getId()==document.getId()){
					document.setDoctitle(doc.getDoctitle());
					document.setDoctype(doc.getDoctype());
					if(!StringUtils.isEmpty(doc.getDoccontent())){
						document.setDoccontent(doc.getDoccontent());
					}
				}
			}
		}
		page.setList(list);
		System.out.println("page:"+page.getCurrentPage()+":"+page.getPageCount()+":"+page.getPageSize()+":"+page.getTotalCount());
		return net.sf.json.JSONObject.fromObject(page).toString();
	}
	
	public List<Document> sortList(List<Document> list,String ids){
		List<Document> returnList=new ArrayList<Document>();
		String[] sArr=ids.split(",");
		for(String id:sArr){
			for(Document document:list){
				if((document.getId()+"").equals(id)){
					returnList.add(document);
					break;
				}
			}
		}
		return returnList;
	}
	
	public String getIds(List<Document> list){
		String resultString="";
		for(Document document:list){
			resultString=resultString+document.getId()+",";
		}
		if(!StringUtils.isEmpty(resultString)){
			resultString=resultString.substring(0,resultString.length()-1);
		}
		if(resultString.equals("")){
			resultString="0";
		}
		return resultString;
	}
	
	public void deleteDocumentByIds(JSONObject jsonObject) throws Exception {
		String ids=jsonObject.getString("ids");
		for(String id:ids.split(",")){
			if(id.equals("")){
				continue;
			}
			deleteById(Document.class, Integer.parseInt(id));
		}
	}
}
