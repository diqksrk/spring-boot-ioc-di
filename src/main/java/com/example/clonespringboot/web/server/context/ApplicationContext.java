package com.example.clonespringboot.web.server.context;

import org.springframework.lang.Nullable;

public interface ApplicationContext extends BeanFactory {
    @Nullable
    String getId();

    public abstract AbstractBeanFactory getBeanFactory() throws IllegalStateException;
}
