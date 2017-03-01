/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.web;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 *
 * @author Administrator
 */
@WebFilter(filterName = "CharsetFilter", urlPatterns = {"*.jsp", "*.view", "*.do"}, initParams = {
    @WebInitParam(name = "charset", value = "UTF-8")})
public class CharsetFilter implements Filter {
    private static final String default_charset="UTF-8";
    private FilterConfig filterConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //目的:初始化才能抓到的東西存到屬性中，讓其它也能用到
        this.filterConfig=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       String charset=this.filterConfig.getInitParameter("charset");
       if(charset==null){
          charset=default_charset;
       }
       request.setCharacterEncoding(charset);//設完
       request.getParameterNames();//鎖住
       response.setCharacterEncoding(charset);
       response.getWriter();
       //以上程式為前置處理
       chain.doFilter(request, response);//!!!類似分隔線
       //以下程式為後續處理
    }

    @Override
    public void destroy() {
        
    }
    
}
