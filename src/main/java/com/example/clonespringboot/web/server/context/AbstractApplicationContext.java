package com.example.clonespringboot.web.server.context;

import com.example.clonespringboot.web.server.exception.BeansException;

import java.util.List;

public abstract class AbstractApplicationContext extends ResourceLoader implements ConfigurableApplicationContext {
    private String id = this.toString();

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public <T> T getBean(Class<T> type) {
        return getBeanFactory().getBean(type);
    }

    @Override
    public <T> T createInstance(Class<T> type) {
        return getBeanFactory().createInstance(type);
    }

    @Override
    public void refresh() throws BeansException, IllegalStateException {
        invokeBeanFactoryPostProcessors(this.getBeanFactory());
        registerClasses(getBeanScanner().extractBean());
        finishBeanFactoryInitialization(this.getBeanFactory());
    }

    protected void invokeBeanFactoryPostProcessors(AbstractBeanFactory beanFactory) {
        beanFactory.setbeanPostProcessors(this);
    }

    protected void registerClasses(List<Class<?>> classes) {
        this.getBeanFactory().setBeanFactoryBeanClass(classes);
    }

    protected void finishBeanFactoryInitialization(AbstractBeanFactory beanFactory) {
        this.getBeanFactory().getBeanDefinitionMap().stream()
                .forEach(this::getBean);
    }
}
