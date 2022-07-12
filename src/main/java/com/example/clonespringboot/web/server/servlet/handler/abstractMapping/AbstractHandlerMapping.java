package com.example.clonespringboot.web.server.servlet.handler.abstractMapping;

import com.sun.istack.internal.Nullable;
import com.woowahan.framework.web.server.support.ApplicationObjectSupport;
import com.woowahan.framework.web.servlet.handler.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractHandlerMapping extends ApplicationObjectSupport implements HandlerMapping {

    @Nullable
    private Object defaultHandler;

    public void setDefaultHandler(@Nullable Object defaultHandler) {
        this.defaultHandler = defaultHandler;
    }

    @Nullable
    public Object getDefaultHandler() {
        return this.defaultHandler;
    }

    @Override
    public Object getHandler(HttpServletRequest request) throws Exception {
        return null;
    }
}
