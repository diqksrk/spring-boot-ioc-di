package com.example.clonespringboot.web.server.scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class BeanScanner extends ClassesScanner {

    private final List<Class<?>> classes;

    public BeanScanner() {
        this.classes = this.getAllClasses();
    }

    public BeanScanner(List<Class<?>> classes) {
        this.classes = classes;
    }

    public BeanScanner(Class<?>... classes) {
        this(Arrays.asList(classes));
    }

    public List<Class<?>> extractBean() {
        return this.classes.stream()
                .filter(this::isBeanAnnotation)
                .collect(toList());
    }

    private boolean isBeanAnnotation(Class<?> classes) {
        return classes.isAnnotationPresent(Controller.class) ||
                classes.isAnnotationPresent(Service.class) ||
                classes.isAnnotationPresent(Component.class) ||
                classes.isAnnotationPresent(Bean.class);
    }
}
