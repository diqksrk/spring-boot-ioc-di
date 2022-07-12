package com.example.clonespringboot.web.server.servlet.handler.requestMapping;

import com.sun.istack.internal.Nullable;
import com.woowahan.framework.web.annotation.Component;
import com.woowahan.framework.web.annotation.RequestMapping;
import com.woowahan.framework.web.servlet.pattern.PathPattern;
import com.woowahan.framework.web.servlet.pattern.PathPatternsRequestCondition;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class RequestMappingHandlerMapping extends RequestMappingInfoHandlerMapping {

    @Override
    protected Object getMappingForMethod(Method method, Class handlerType) {
        RequestMappingInfo info = createRequestMappingInfo(method);
        return info;
    }

    @Nullable
    private RequestMappingInfo createRequestMappingInfo(AnnotatedElement element) {
        RequestMapping requestMapping = element.getAnnotation(RequestMapping.class);
        PathPattern pathPattern = new PathPattern(requestMapping.value());
        List<PathPattern> pathPatternList = new ArrayList<>();
        pathPatternList.add(pathPattern);
        PathPatternsRequestCondition pathPatternsRequestCondition = new PathPatternsRequestCondition(new HashSet<>(pathPatternList));

        return new RequestMappingInfo(pathPatternsRequestCondition);
    }
}
