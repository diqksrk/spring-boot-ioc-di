package com.example.clonespringboot.web.server.scanner;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public final class MethodIntrospector {
    public static <T> Map<Method, T> selectMethods(Class<?> targetType) {
        final Map<Method, T> methodMap = new LinkedHashMap<>();
        Set<Class<?>> handlerTypes = new LinkedHashSet<>();
        Class<?> specificHandlerType = null;

        Method[] declaredMethods = targetType.getDeclaredMethods();



//        for (Class<?> currentHandlerType : handlerTypes) {
//            final Class<?> targetClass = (specificHandlerType != null ? specificHandlerType : currentHandlerType);
//
//
//        }

        System.out.println("asd");

        return methodMap;
    }

}
