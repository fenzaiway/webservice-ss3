/**
 * File	Name   : SuccessActionResult.java
 * Package     : org.com.lutongnet.jfl.message
 * Description : TODO
 * Author      : zhangfj
 * Date        : 2012-9-13
 * Version     : V1.0		
 */
package com.lutongnet.test.spring.message;

/**
 * @author 凌剑�?
 */
public class SuccessActionResult extends ActionResult {

	public SuccessActionResult ( String message ) {
		super ( ActionResult.SUCCESS , message );
	}

	public SuccessActionResult ( ) {
		super ( ActionResult.SUCCESS , "action.result.success" );
	}
}
