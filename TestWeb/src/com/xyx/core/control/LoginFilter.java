package com.xyx.core.control;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xyx.core.bean.CommonData;
import com.xyx.core.bean.CoreAuth;
import com.xyx.core.bean.CoreLog;
import com.xyx.core.bean.CorePerson;
import com.xyx.core.service.CommonService;

public class LoginFilter implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		 String path=req.getServletPath(); 
		 if(path.contains("core/initSystem.do")){
			 chain.doFilter(request, response);
		 }else if(path.contains(".html") || path.contains(".do")){
			if( req.getSession().getAttribute("isLogin")==null && (!path.contains("login.html")) && (!path.contains("login.do"))){
				String contextPath=req.getContextPath();
				if(path.contains(".html")){
					((HttpServletResponse)response).sendRedirect(contextPath+"/login.html?msg=nologin");//��¼�µ�¼����Ҫ��ת����ҳ�棬���磺/logined/account.jsp  
				}else{
					response.setContentType("application/json; charset=UTF-8");
					try {
						JSONObject jObject=new JSONObject();
						jObject.put("status", "nologin");
						response.getWriter().print(jObject.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}else{
				checkAuth(chain, request, response);
			}
		 }else{
			 checkAuth(chain, request, response);
		 }
	}
	
	public void checkAuth(FilterChain chain, ServletRequest request,
			ServletResponse response) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getServletPath();
		if (req.getSession().getAttribute("isLogin") != null
				&& path.endsWith(".do")) {
			Object userObject = req.getSession().getAttribute("user");
			CorePerson corePerson = (CorePerson) userObject;
			List<CoreAuth> auths = CommonData.authMap.get(corePerson
					.getUsername());
			System.out.println("request path" + path);
			saveLog(req, path, "", corePerson);
			boolean isAuth=true;
			if(auths==null){
				auths=new ArrayList<CoreAuth>();
			}
			for (CoreAuth coreAuth : auths) {
				if(coreAuth.getFunctionUrl().contains(path)){
					isAuth=true;
					break;
				}
			}
			if(!isAuth){
				HttpServletResponse resp=(HttpServletResponse)response;
				resp.setContentType("application/json; charset=UTF-8");
				try {
					JSONObject jObject=new JSONObject();
					jObject.put("status", "noauth");
					resp.getWriter().print(jObject.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				chain.doFilter(request, response);
			}
		}else{
			chain.doFilter(request, response);
		}
	}
	
	public void saveLog(HttpServletRequest request,String path,String modelname,CorePerson corePerson){
		if(path.contains("core") && path.toLowerCase().contains("log")){
			return ;
		}
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ServletContext servletContext = request.getSession().getServletContext();      
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		CommonService commonService=(CommonService)ctx.getBean("CommonService");
		CoreLog log=new CoreLog();
		log.setAccessurl(path);
		log.setCreatetime(dateFormat.format(new Date()));
		log.setModelname(modelname);
		log.setUserid(corePerson.getId());
		log.setUsername(corePerson.getRealname());
		commonService.save(log);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
