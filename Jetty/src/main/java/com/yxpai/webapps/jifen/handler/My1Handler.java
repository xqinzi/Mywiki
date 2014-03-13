package com.yxpai.webapps.jifen.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.ScopedHandler;

/**
 * 只处理以target开头的请求
 */
public class My1Handler extends ScopedHandler {
	
	private String target;
	
	@Override
	public void doScope(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 如果target为空，则跳过此handel，由后续的handel处理
		if(StringUtils.isEmpty(this.target))
			nextHandle(target,baseRequest,request,response);
		
		if(target.indexOf(this.target)==-1)
			// 如果不以target开头，则跳过此handel，由后续的handel处理
			nextHandle(target,baseRequest,request,response);
		else{
			doHandle(target,baseRequest,request,response);
		}
	}

	@Override
	public void doHandle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");  
        response.setStatus(HttpServletResponse.SC_OK);  
        baseRequest.setHandled(true);  
        response.getWriter().println("<h1>Hello World</h1>");  
        response.getWriter().println("<li>Request url: " + target + "</li>");  
        response.getWriter().println("<li>Server port: " + request.getServerPort() + "</li>");
	}

	public void setTarget(String target) {
		this.target = target;
	}
}