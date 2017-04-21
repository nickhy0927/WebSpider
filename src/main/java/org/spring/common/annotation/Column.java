package org.spring.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 列注解 Created by Huangyuan on 16/4/25.
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

	int length() default 0;

	String name() default "fieldName";

	String comment();

	boolean notNull() default false;

	String idType() default "native";

}
