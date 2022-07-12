package com.example.clonespringboot.web.server.servlet;

import com.sun.istack.internal.Nullable;
import com.woowahan.framework.web.servlet.handler.HandlerExecutionChain;
import com.woowahan.framework.web.servlet.handler.HandlerMapping;
import com.woowahan.framework.web.servlet.handler.requestMapping.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * todo : Implementation {@link javax.servlet.Servlet#service}
 */
public class DispatcherServlet extends HttpServlet {
    @Nullable
    private List<HandlerMapping> handlerMappings;

    RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        try {
            doDispatch(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpServletRequest processedRequest = request;
        HandlerExecutionChain mappedHandler = null;

        mappedHandler = getHandler(processedRequest);
        if (mappedHandler == null) {
            return;
        }
    }


    @Nullable
    protected HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
        if (this.handlerMappings != null) {
            for (HandlerMapping mapping : this.handlerMappings) {
                HandlerExecutionChain handler = mapping.getHandler(request);
                if (handler != null) {
                    return handler;
                }
            }
        }
        return null;
    }
}
