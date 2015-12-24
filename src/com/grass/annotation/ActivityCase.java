package com.grass.annotation;

/**
 * Created by grass on 15/12/20.
 */
public @interface ActivityCase {
    String name() default "name";

    String des() default "this is for testjavaPoet";
}
