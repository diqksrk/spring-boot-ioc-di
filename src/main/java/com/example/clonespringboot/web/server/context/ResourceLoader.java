package com.example.clonespringboot.web.server.context;

import com.example.clonespringboot.web.server.scanner.BeanScanner;
import com.example.clonespringboot.web.server.scanner.UriScanner;
import org.springframework.lang.Nullable;

public class ResourceLoader {
    @Nullable
    private final BeanScanner beanScanner;
    @Nullable
    private final UriScanner uriScanner;

    public ResourceLoader() {
        this.beanScanner = new BeanScanner();
        this.uriScanner = new UriScanner();
    }

    @Nullable
    public BeanScanner getBeanScanner() {
        return this.beanScanner;
    }
}
