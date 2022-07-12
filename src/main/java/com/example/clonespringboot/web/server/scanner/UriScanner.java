package com.example.clonespringboot.web.server.scanner;

import com.example.clonespringboot.web.server.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class UriScanner {
    public UriScanner() {
    }

    ;

    public List<String> extractMappingUris(List<Class<?>> beanClasses) {
        List<String> uris = new ArrayList<>();
        List<Class<?>> controllers = this.extractController(beanClasses);

        for (Class<?> controller : controllers) {
            List<String> controllerUris = extractControllerUri(controller);
            List<String> methodUris = extractMethodUris(controller);

            uris.addAll(extractUris(controllerUris, methodUris));
        }

        return uris;
    }

    private List<String> extractMethodUris(Class<?> controller) {
        List<Method> methods = new MethodScanner(controller).extractAnnotatedMethod();

        return methods.stream()
                .flatMap(method -> Stream.of(method.getAnnotation(RequestMapping.class).value()))
                .collect(toList());
    }

    private List<String> extractUris(List<String> controllerUris, List<String> methodUris) {
        List<String> uris = new ArrayList<>();
        for (String controllerUri : controllerUris) {
            for (String methodUri : methodUris) {
                uris.add(createUri(controllerUri, methodUri));
            }
        }
        return uris;
    }

    private String createUri(String controllerUri, String methodUri) {
        return "/" + Stream.of(controllerUri.split("/"), methodUri.split("/"))
                .flatMap(Arrays::stream)
                .filter(uri -> !uri.isEmpty())
                .collect(joining("/"));
    }

    private List<String> extractControllerUri(Class<?> controller) {
        if (controller.isAnnotationPresent(RequestMapping.class)) {
            return Arrays.asList(controller.getAnnotation(RequestMapping.class).value());
        }

        return Arrays.asList("");
    }

    public List<Class<?>> extractController(List<Class<?>> beanClasses) {
        return beanClasses.stream()
                .filter(bean -> bean.isAnnotationPresent(Controller.class))
                .collect(toList());
    }
}
