package com.example.clonespringboot.web.server.processor;

import com.example.clonespringboot.web.server.context.ConfigurableApplicationContext;
import com.example.clonespringboot.web.server.exception.BeansException;
import com.example.clonespringboot.web.server.support.ApplicationContextAware;

public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    private final ConfigurableApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ConfigurableApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean) throws BeansException {
        if (!(bean instanceof ApplicationContextAware)) {
            return bean;
        }

        invokeAwareInterfaces(bean);
        return bean;
    }

    private void invokeAwareInterfaces(Object bean) {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(this.applicationContext);
        }
    }
}
