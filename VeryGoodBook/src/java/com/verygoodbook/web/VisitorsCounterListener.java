/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 *
 * @author Administrator
 */
//@WebListener
public class VisitorsCounterListener implements ServletContextListener, HttpSessionListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
       //TODO:
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       //TODO:
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext application=se.getSession().getServletContext();
        Integer counter= (Integer)application.getAttribute("app.vistiors.counter");
               if(counter==null){
                   counter=16800;
               }
               application.setAttribute("app.vistiors.counter",++counter);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        
    }
}
