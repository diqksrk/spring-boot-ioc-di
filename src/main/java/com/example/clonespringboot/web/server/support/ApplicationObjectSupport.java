package com.example.clonespringboot.web.server.support;

import com.example.clonespringboot.web.server.context.ApplicationContext;
import com.example.clonespringboot.web.server.exception.BeansException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.lang.Nullable;

public class ApplicationObjectSupport implements ApplicationContextAware {
    protected final Log logger = LogFactory.getLog(getClass());

    @Nullable
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public final ApplicationContext getApplicationContext() throws IllegalStateException {
        if (this.applicationContext == null) {
            throw new IllegalStateException(
                    "ApplicationObjectSupport instance [" + this + "] does not run in an ApplicationContext");
        }
        return this.applicationContext;
    }
}
