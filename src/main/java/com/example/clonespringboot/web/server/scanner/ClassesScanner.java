package com.example.clonespringboot.web.server.scanner;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ClassesScanner {
    private final String[] basePackage = {"com.mission.your", "com.woowahan.framework.web" };

    public List<Class<?>> getAllClasses() {
        Reflections reflections = new Reflections(basePackage, new SubTypesScanner(false));

        List<Class<?>> classes = reflections.getSubTypesOf(Object.class)
                .stream()
                .distinct()
                .collect(toList());

        return classes;
    }
}
