/**
 * File	Name   : WebContextPathTag.java
 * Package     : org.com.lutongnet.jfl.tag
 * Description : TODO
 * Author      : zhangfj
 * Date        : 2012-9-20
 * Version     : V1.0		
 */
package com.lutongnet.test.spring.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class WebContextPathTag extends SimpleTagSupport {

	private boolean	fullPath;

	public boolean isFullPath ( ) {
		return fullPath;
	}

	public void setFullPath ( boolean fullPath ) {
		this.fullPath = fullPath;
	}

	@Override
	public void doTag ( ) throws JspException , IOException {
		super.doTag ( );
		PageContext ctx = ( PageContext ) getJspContext ( );
		JspWriter out = getJspContext ( ).getOut ( );
		HttpServletRequest request = ( HttpServletRequest ) ctx.getRequest ( );
		String path = request.getContextPath ( ) + "/";
		if ( fullPath ){
			path = request.getScheme ( ) + "://" + request.getServerName ( ) + ":" + request.getServerPort ( ) + path;
		}
		System.out.println("path==" + path);
		out.write ( path );
	}
}
