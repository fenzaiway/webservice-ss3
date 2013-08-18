/**
 * File	Name   : ActionResult.java
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
public class ActionResult {

	public static final String	SUCCESS	= "success";
	public static final String	ERROR		= "error";

	private String							type;
	private String							message;

	public ActionResult ( String type , String message ) {
		super ( );
		this.type = type;
		this.message = message;
	}

	public String getType ( ) {
		return type;
	}

	public void setType ( String type ) {
		this.type = type;
	}

	public String getMessage ( ) {
		return message;
	}

	public void setMessage ( String message ) {
		this.message = message;
	}

}
