/**
 * File	Name   : CustomizeExceptionResolver.java
 * Package     : org.com.lutongnet.jfl.interceptor
 * Description : TODO
 * Author      : zhangfj
 * Date        : 2012-9-19
 * Version     : V1.0		
 */
package com.lutongnet.test.spring.resolver;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.lutongnet.test.spring.annotation.Log;
import com.lutongnet.test.spring.entity.ActionLog;
import com.lutongnet.test.spring.entity.ActionLogDetail;
import com.lutongnet.test.spring.message.ActionResult;
import com.lutongnet.test.spring.service.LogService;

/**
 * @author 凌剑东
 */
public class CustomizeExceptionResolver extends SimpleMappingExceptionResolver {

    private Logger          log = LoggerFactory.getLogger ( CustomizeExceptionResolver.class );

    @Resource ( name = "logService" )
    private LogService  logService;

    @SuppressWarnings ( "unchecked" )
    @Override
    protected ModelAndView doResolveException ( HttpServletRequest request , HttpServletResponse response , Object handler , Exception ex ) {
        String content = "";
        if ( handler instanceof HandlerMethod ){
            HandlerMethod h = ( HandlerMethod ) handler;
            Log logAnnotation = h.getMethodAnnotation ( Log.class );
            if ( logAnnotation != null ){
                content = logAnnotation.value ( );
                StringWriter sw = new StringWriter ( );
                ex.printStackTrace ( new PrintWriter ( sw ) );
//                UserInfo user = ( UserInfo ) request.getSession ( ).getAttribute ( AppConstant.USER_INFO );
//                String actor = user == null ? "system" : user.getUserid ( );
                String uri = request.getRequestURI ( );
                String paramInfo = "";
                Map<String, String [ ]> params = request.getParameterMap ( );
                for ( Map.Entry<String, String [ ]> entry : params.entrySet ( ) ){
                    String key = entry.getKey ( ) + ":";
                    String [ ] values = entry.getValue ( );
                    for ( int i = 0 ; i < values.length ; ++i ){
                        key += values [ i ] + ",";
                    }
                    key = key.substring ( 0 , key.length ( ) - 1 );
                    key += "<br/>";
                    paramInfo += key;
                }

                ActionLog actionLog = new ActionLog ( );
                actionLog.setContent ( content );
                actionLog.setActor ( "" );
                actionLog.setType ( ActionResult.ERROR );
                actionLog.setParam ( paramInfo );
                actionLog.setUri ( uri );
                actionLog.setAddDate ( DateTime.now ( ) );
                ActionLogDetail detail = new ActionLogDetail ( );
                detail.setContent ( sw.toString ( ) );
                actionLog.setDetail ( detail );
                logService.add ( actionLog );
            }
        }
        log.error ( content + " 操作异常：" , ex );
        if ( "XMLHttpRequest".equals ( request.getHeader ( "X-Requested-With" ) ) ){
            try{
                response.setStatus ( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
                response.getWriter ( ).write ( "系统异常" );
                response.getWriter ( ).flush ( );
                response.getWriter ( ).close ( );
            }catch ( IOException e ){
                log.error ( "" , e );
            }
            return null;
        }
        return super.doResolveException ( request , response , handler , ex );
    }

}