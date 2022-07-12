package com.example.clonespringboot.web.server.context;

import com.example.clonespringboot.web.server.exception.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext {
    AbstractBeanFactory getBeanFactory() throws IllegalStateException;

    void refresh() throws BeansException, IllegalStateException;
}
