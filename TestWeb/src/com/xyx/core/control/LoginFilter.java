package com.xyx.core.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xyx.core.bean.CommonData;
import com.xyx.core.bean.CoreAuth;
import com.xyx.core.bean.CorePerson;

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
				((HttpServletResponse)response).sendRedirect(contextPath+"/login.html?msg=nologin");//��¼�µ�¼����Ҫ��ת����ҳ�棬���磺/logined/account.jsp  
				
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
			for (CoreAuth coreAuth : auths) {
				System.out.println("auth" + coreAuth.getFunctionUrl());
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
