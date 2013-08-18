/**
 * File	Name   : ActionLog.java
 * Package     : org.com.lutongnet.jfl.annotation
 * Description : TODO
 * Author      : zhangfj
 * Date        : 2012-9-19
 * Version     : V1.0		
 */
package com.lutongnet.test.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.Mapping;

/**
 * @author 凌剑�?
 */
@Retention ( RetentionPolicy.RUNTIME )
@Documented
@Target ( ElementType.METHOD )
@Mapping
public @ interface Log {
	String value ( ) default "";
}
