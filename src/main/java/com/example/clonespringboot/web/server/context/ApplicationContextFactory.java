package com.example.clonespringboot.web.server.context;

@FunctionalInterface
public interface ApplicationContextFactory {

    ApplicationContextFactory DEFAULT = () -> {
        return new AnnotationConfigApplicationContext();
    };

    AnnotationConfigApplicationContext create();
}
