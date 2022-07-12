package com.example.clonespringboot.web.server.servlet.pattern;

import java.util.Set;
import java.util.TreeSet;

public final class PathPatternsRequestCondition {
    private static final Set<PathPattern> EMPTY_PATH_PATTERN =
            new TreeSet<>();
    private final Set<PathPattern> patterns;

    public PathPatternsRequestCondition() {
        this(EMPTY_PATH_PATTERN);
    }

    public PathPatternsRequestCondition(Set<PathPattern> patterns) {
        this.patterns = patterns;
    }
}
