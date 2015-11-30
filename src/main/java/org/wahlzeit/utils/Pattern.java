package org.wahlzeit.utils;

public @interface Pattern {
    String name();

    String[] participants() default "";
}
