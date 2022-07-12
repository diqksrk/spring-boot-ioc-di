package com.example.clonespringboot.web.server.servlet.handler;

import com.sun.istack.internal.Nullable;

import javax.servlet.http.HttpServletRequest;

public interface HandlerMapping {
    @Nullable
    HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception;
}
