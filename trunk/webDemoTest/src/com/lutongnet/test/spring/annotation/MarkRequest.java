/**
 * File	Name   : MarkRequestInfo.java
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

import com.lutongnet.test.spring.constant.AppConstant;

/**
 * @author 凌剑�?
 */
@Target ( ElementType.METHOD )
@Retention ( RetentionPolicy.RUNTIME )
@Documented
@Mapping
public @ interface MarkRequest {

	String value ( ) default "returnURI";

	String scope ( ) default AppConstant.SESSION_SCOPE;

	String [ ] excludes ( ) default { };
}
