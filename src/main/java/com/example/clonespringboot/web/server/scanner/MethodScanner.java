package com.example.clonespringboot.web.server.scanner;

import com.example.clonespringboot.web.server.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class MethodScanner {
    private final List<Class<?>> controllers;

    public MethodScanner(List<Class<?>> controllers) {
        this.controllers = controllers;
    }

    public MethodScanner(Class<?>... controllers) {
        this(Arrays.asList(controllers));
    }

    public List<Method> extractAnnotatedMethod() {
        return controllers.stream()
                .flatMap(controller -> Arrays.stream(controller.getMethods()))
                .filter(method ->
                        method.isAnnotationPresent(RequestMapping.class))
                .collect(toList());
    }

}
