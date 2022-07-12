package com.example.clonespringboot.web.server.config;

import com.example.clonespringboot.web.server.servlet.handler.requestMapping.RequestMappingHandlerMapping;
import org.springframework.context.annotation.Bean;

public class WebMvcAutoConfiguration {
    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping requestMappingHandlerMapping = createRequestMappingHandlerMapping();

        return requestMappingHandlerMapping;
    }

    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        return new RequestMappingHandlerMapping();
    }
}
