package com.lwh.techproducts.template.compat.annotation;

import com.lwh.jackknife.multiproxy.annotation.Proxy;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

@Proxy
@Target(TYPE)
@Retention(SOURCE)
public @interface BBB {
}