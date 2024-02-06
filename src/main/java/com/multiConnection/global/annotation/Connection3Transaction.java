package com.multiConnection.global.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Transactional("connection3TransactionManager")
public @interface Connection3Transaction {

    @AliasFor(annotation = Transactional.class)
    boolean readOnly() default false;
}
