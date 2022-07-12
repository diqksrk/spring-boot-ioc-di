package com.example.clonespringboot.web.server.context;

import com.example.clonespringboot.web.server.exception.BeansException;
import com.example.clonespringboot.web.server.processor.ApplicationContextAwareProcessor;
import com.example.clonespringboot.web.server.processor.BeanPostProcessor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class AbstractBeanFactory implements BeanFactory {
    private final List<Class<?>> beanDefinitionMap;
    private final Map<Class<?>, Object> beanMap;
    private final List<BeanPostProcessor> beanPostProcessors;

    public AbstractBeanFactory() {
        this.beanMap = new HashMap<>();
        this.beanDefinitionMap = new ArrayList<>();
        beanPostProcessors = new ArrayList<>();
    }

    public void setBeanFactoryBeanClass(List<Class<?>> classes) {
        classes.stream().forEach(beanClass -> {
            this.beanDefinitionMap.add(beanClass);
        });
    }

    public void setbeanPostProcessors(ConfigurableApplicationContext configurableApplicationContext) {
        this.beanPostProcessors.add(new ApplicationContextAwareProcessor(configurableApplicationContext));
    }

    public List<Class<?>> getBeanDefinitionMap() {
        return this.beanDefinitionMap;
    }

    @Override
    public <T> T getBean(Class<T> type) {
        return Optional.ofNullable(beanMap.get(type)).map(o -> (T) o).orElseGet(() -> {
            final T instance = createInstance(type);
            Arrays.stream(type.getDeclaredFields())
                    .forEach(field -> {
                        final Object fieldInstance = getBean(field.getType());
                        field.setAccessible(true);
                        try {
                            field.set(instance, fieldInstance);
                        } catch (IllegalAccessException e) {
                            throw new BeansException("허용되지 않습니다. ", e);
                        }
                    });
            beanMap.put(type, instance);
            return instance;
        });
    }

    @Override
    public <T> T createInstance(Class<T> type) {
        try {
            final Constructor<?>[] candidates = type.getDeclaredConstructors();
            for (Constructor candidate : candidates) {

                Object instance = candidate.newInstance(
                        Arrays.stream(candidate.getParameterTypes())
                                .map(beanMap::get)
                                .toArray()
                );

                applyBeanPostProcessorsBeforeInitialization(instance);
                initializeBean(instance);
                return (T) instance;
            }
            throw new IllegalStateException("Can not found constructor of " + type.getName());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    protected Object initializeBean(Object bean) {
        try {
            invokeInitMethods(bean);
        } catch (Throwable ex) {
            /*
            throw new BeanCreationException(
                    (mbd != null ? mbd.getResourceDescription() : null),
                    beanName, "Invocation of init method failed", ex);
             */
            ex.printStackTrace();
        }
        return bean;
    }

    protected void invokeInitMethods(Object bean) throws Throwable {
        boolean isInitializingBean = (bean instanceof InitializingBean);
        if (isInitializingBean) {
            ((InitializingBean) bean).afterPropertiesSet();
        }
    }


}
