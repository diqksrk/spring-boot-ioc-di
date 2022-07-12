package com.example.clonespringboot.web.server.support;

import com.example.clonespringboot.web.server.context.ApplicationContext;
import com.example.clonespringboot.web.server.exception.BeansException;

public interface ApplicationContextAware {
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
