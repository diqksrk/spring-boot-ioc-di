package com.example.clonespringboot.web.server.servlet.handler.abstractMapping;

import com.sun.istack.internal.Nullable;
import com.woowahan.framework.web.annotation.RequestMapping;
import com.woowahan.framework.web.server.context.InitializingBean;
import com.woowahan.framework.web.servlet.handler.HandlerMethod;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractHandlerMethodMapping<T> extends AbstractHandlerMapping implements InitializingBean {

    private final MappingRegistry mappingRegistry = new MappingRegistry();

    class MappingRegistry {
        private final Map<T, MappingRegistration<T>> registry = new HashMap<>();

        public MappingRegistry() {
        }

        public Map<T, MappingRegistration<T>> getRegistrations() {
            return this.registry;
        }

        public void register(T mapping, Object handler, Method method) {
            HandlerMethod handlerMethod = createHandlerMethod(handler, method);

            this.registry.put(mapping,
                    new MappingRegistration<>(mapping, handlerMethod));
        }
    }

    static class MappingRegistration<T> {
        private final T mapping;

        private final HandlerMethod handlerMethod;

        public MappingRegistration(T mapping, HandlerMethod handlerMethod) {
            this.mapping = mapping;
            this.handlerMethod = handlerMethod;
        }
    }

    @Override
    public void afterPropertiesSet() {
        initHandlerMethods();
    }

    protected void initHandlerMethods() {
        if (logger.isDebugEnabled()) {
            logger.debug("Looking for request mappings in application context: " + getApplicationContext());
        }

        List<Class<?>> beanDefinitionMap = getApplicationContext().getBeanFactory().getBeanDefinitionMap();

        for (Class<?> bean : beanDefinitionMap) {
            if (bean.isAnnotationPresent(RequestMapping.class)) {
                detectHandlerMethods(bean);
            }
        }
    }

    protected void detectHandlerMethods(final Object handler) {
        Class<?> handlerType = (Class) handler;
        final Map<Method, T> methodMap = new LinkedHashMap<>();

        Method[] declaredMethods = handlerType.getDeclaredMethods();

        for (Method method : declaredMethods) {
            methodMap.put(method, this.getMappingForMethod(method, handlerType));
        }

        methodMap.forEach((method, mapping) -> {
            registerHandlerMethod(handler, method, mapping);
        });
    }

    @Nullable
    protected abstract T getMappingForMethod(Method method, Class<?> handlerType);

    protected void registerHandlerMethod(Object handler, Method method, T mapping) {
        this.mappingRegistry.register(mapping, handler, method);
    }

    protected HandlerMethod createHandlerMethod(Object handler, Method method) {
        return new HandlerMethod(handler, method);
    }
}
