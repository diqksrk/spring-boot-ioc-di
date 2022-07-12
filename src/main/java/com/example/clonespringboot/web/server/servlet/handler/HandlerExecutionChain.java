package com.example.clonespringboot.web.server.servlet.handler;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

public class HandlerExecutionChain {
    private static final Log logger = LogFactory.getLog(HandlerExecutionChain.class);

    private final Object handler;

    private final List<HandlerInterceptor> interceptorList = new ArrayList<>();

    public HandlerExecutionChain(Object handler, List<HandlerInterceptor> interceptorList) {
        if (handler instanceof HandlerExecutionChain) {
            HandlerExecutionChain originalChain = (HandlerExecutionChain) handler;
            this.handler = originalChain.getHandler();
            this.interceptorList.addAll(originalChain.interceptorList);
        }
        else {
            this.handler = handler;
        }
        this.interceptorList.addAll(interceptorList);
    }

    public Object getHandler() {
        return this.handler;
    }
}
