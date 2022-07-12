package com.example.clonespringboot.web.server.servlet.handler.requestMapping;

import com.sun.istack.internal.Nullable;
import com.woowahan.framework.web.servlet.pattern.PathPatternsRequestCondition;

public class RequestMappingInfo {
    private static final PathPatternsRequestCondition EMPTY_PATH_PATTERNS = new PathPatternsRequestCondition();

    @Nullable
    private final PathPatternsRequestCondition pathPatternsCondition;

    public RequestMappingInfo(@Nullable PathPatternsRequestCondition pathPatternsCondition) {
        this.pathPatternsCondition = pathPatternsCondition;
    }
}
