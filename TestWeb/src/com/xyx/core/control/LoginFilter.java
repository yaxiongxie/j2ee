package com.xyx.core.control;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		 String path=req.getServletPath(); 
		 System.out.println(path);
		 if(path.contains(".html") || path.contains(".do")){
			if( req.getSession().getAttribute("isLogin")==null && (!path.contains("login.html")) && (!path.contains("login.do"))){
				String contextPath=req.getContextPath();
				((HttpServletResponse)response).sendRedirect(contextPath+"/login.html?msg=nologin");//��¼�µ�¼����Ҫ��ת����ҳ�棬���磺/logined/account.jsp  
				
			}else{
				chain.doFilter(request, response);
			}
		 }else{
			 chain.doFilter(request, response);
		 }
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
