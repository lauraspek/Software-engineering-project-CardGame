package com.sweng.gwt.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.google.gwt.core.client.GWT;

public class AppServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    	DatabaseInitializer initializer = new DatabaseInitializer();
        initializer.initializeDatabaseIfEmpty();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
