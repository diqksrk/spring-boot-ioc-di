package com.example.clonespringboot.web.server.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@FunctionalInterface
public interface ServletContextInitializer {
    /**
     * Configure the given {@link ServletContext} with any servlets, filters, listeners
     * context-params and attributes necessary for initialization.
     * @param servletContext the {@code ServletContext} to initialize
     * @throws ServletException if any call against the given {@code ServletContext}
     * throws a {@code ServletException}
     */
    void onStartup(ServletContext servletContext) throws ServletException;
}
