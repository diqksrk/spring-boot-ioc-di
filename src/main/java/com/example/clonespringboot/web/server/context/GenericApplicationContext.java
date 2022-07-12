package com.example.clonespringboot.web.server.context;

import java.util.List;

public class GenericApplicationContext extends AbstractApplicationContext {
    private final AbstractBeanFactory beanFactory;

    public GenericApplicationContext() {
        this.beanFactory = new AbstractBeanFactory();
    }

    @Override
    public AbstractBeanFactory getBeanFactory() throws IllegalStateException {
        return this.beanFactory;
    }

    public void setBeanFactoryBeanClass(List<Class<?>> classes) {
        this.beanFactory.setBeanFactoryBeanClass(classes);
    }
}
