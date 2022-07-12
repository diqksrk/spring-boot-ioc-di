package com.example.clonespringboot.web.server.context;

public interface BeanFactory {
    <T> T getBean(Class<T> type);

    <T> T createInstance(Class<T> type);
}
