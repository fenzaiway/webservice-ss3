/**
 * File	Name   : MenuController.java
 * Package     : org.com.lutongnet.jfl.controller
 * Description : TODO
 * Author      : zhangfj
 * Date        : 2012-9-24
 * Version     : V1.0		
 */
package com.lutongnet.test.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lutongnet.test.spring.annotation.Log;
import com.lutongnet.test.spring.annotation.MarkRequest;
import com.lutongnet.test.spring.entity.Menu;
import com.lutongnet.test.spring.message.SuccessActionResult;
import com.lutongnet.test.spring.req.MenuReq;
import com.lutongnet.test.spring.service.MenuService;
import com.lutongnet.test.spring.util.ChangeCharset;
import com.lutongnet.test.spring.util.HttpUtils;
import com.lutongnet.test.spring.util.PaginationBean;

/**
 * @author 鍑屽墤涓?
 */
@Controller
public class MenuController {

    @Resource ( name = "menuService" )
    private MenuService     menuService;


    /**
     * 查看所有菜单树
     * 
     * @return
     */
    @RequestMapping ( value = "/menu/list" )
    @MarkRequest
    public String list ( @ModelAttribute ( "menu" ) MenuReq menu , Model model ) {
        PaginationBean pb = menuService.list ( menu );
        model.addAttribute ( "pb" , pb );
        return "menu/list";
    }

    /**
     * 初始化新增菜单树
     * 
     * @return
     */
    @RequestMapping ( value = "/menu/addTree" , method = RequestMethod.GET )
    public String addForm ( @ModelAttribute ( "tree" ) Menu menu ) {
        return "menu/add";
    }

    /**
     * 添加菜单树
     * 
     * @param menu
     * @param result
     * @param locale
     * @return
     */
    @RequestMapping ( value = "/menu/addTree" , method = RequestMethod.POST )
    @Log ( "添加菜单树" )
    public ResponseEntity<String> addTree ( @ModelAttribute ( "tree" ) Menu menu , BindingResult result , Locale locale ) {
        HttpHeaders headers = new HttpHeaders ( );
        headers.set ( "Content-Type" , "text/plain;charset=utf-8" );
        if ( menuService.existsByName ( menu.getName ( ) , menu.getId ( ) , Menu.class ) ){
            return new ResponseEntity<String> ( "" , headers , HttpStatus.BAD_REQUEST );
        }else{
            menuService.add ( menu );
            return new ResponseEntity<String> ( String.valueOf ( menu.getId ( ) ) , HttpStatus.OK );
        }
    }

    /**
     * 添加菜单
     * 
     * @param menu
     * @param locale
     * @return
     */
    @RequestMapping ( value = "/menu/addMenu" , method = RequestMethod.POST )
    @Log ( "添加菜单" )
    public ResponseEntity<String> addMenu ( Menu menu , Locale locale ) {
        HttpHeaders headers = new HttpHeaders ( );
        headers.set ( "Content-Type" , "text/plain;charset=utf-8" );
        if ( menuService.existsInParent ( menu ) ){
            return new ResponseEntity<String> ( "" , headers , HttpStatus.BAD_REQUEST );
        }else{
            menuService.add ( menu );
            return new ResponseEntity<String> ( String.valueOf ( menu.getId ( ) ) , HttpStatus.OK );
        }
    }

    /**
     * 删除菜单
     * 
     * @param id
     * @return
     */
    @RequestMapping ( value = "/menu/remove" , method = RequestMethod.POST )
    public ResponseEntity<String> remove ( Integer id ) {
        menuService.remove ( id );
        return new ResponseEntity<String> ( HttpStatus.OK );
    }

    /**
     * 移动菜单
     * 
     * @param id
     * @param pid
     * @param prevId
     * @param nextId
     * @return
     */
    @RequestMapping ( value = "/menu/move" , method = RequestMethod.POST )
    public ResponseEntity<String> move ( Integer id , Integer pid , Integer prevId , Integer nextId ) {
        menuService.move ( id , pid , prevId , nextId );
        return new ResponseEntity<String> ( HttpStatus.OK );
    }

    /**
     * 获取菜单信息
     * 
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping ( value = "/menu/get" , produces = "application/json" )
    public Map<String, Object> get ( Integer id ) {
        Menu menu = menuService.get ( id );
        Map<String, Object> rsp = new HashMap<String, Object> ( );
        rsp.put ( "id" , menu.getId ( ) );
        rsp.put ( "pid" , menu.getParent ( ).getId ( ) );
        rsp.put ( "name" , menu.getName ( ) );
        rsp.put ( "uri" , menu.getUri ( ) );
        rsp.put ( "style" , menu.getStyle ( ) );
        return rsp;
    }

    /**
     * 更新菜单
     * 
     * @param menu
     * @return
     */
    @ResponseBody
    @RequestMapping ( value = "/menu/updateMenu" , produces = "application/json" )
    public ResponseEntity<String> update ( Menu menu , Locale locale ) throws Exception {
        HttpHeaders headers = new HttpHeaders ( );
        headers.set ( "Content-Type" , "text/plain;charset=utf-8" );
        if ( menuService.existsInParentWhenUpdate ( menu ) ){
            return new ResponseEntity<String> ( "" , headers , HttpStatus.BAD_REQUEST );
        }

        menuService.update ( menu );
        ObjectMapper mapper = new ObjectMapper ( );

        Map<String, Object> rsp = new HashMap<String, Object> ( );
        rsp.put ( "id" , menu.getId ( ) );
        rsp.put ( "pid" , menu.getParent ( ).getId ( ) );
        rsp.put ( "name" , menu.getName ( ) );
        rsp.put ( "uri" , menu.getUri ( ) );
        rsp.put ( "style" , menu.getStyle ( ) );
        String json = mapper.writeValueAsString ( rsp );
        headers.set ( "Content-Type" , "application/json;charset=utf-8" );
        return new ResponseEntity<String> ( json , headers , HttpStatus.OK );
    }

    /**
     * 将菜单树载入到 application 中
     * 
     * @param id
     * @param session
     */
    @RequestMapping ( value = "/menu/load/{id}" )
    public ResponseEntity<String> load ( @PathVariable Integer id , HttpSession session ) {
        Menu tree = menuService.getTree ( id );
        session.getServletContext ( ).setAttribute ( tree.getName ( ) , tree );
        return new ResponseEntity<String> ( HttpStatus.OK );
    }

    public String removeTree ( Integer id , HttpServletRequest request , HttpSession session , RedirectAttributes ra ) {
        Menu tree = menuService.getTree ( id );
        menuService.remove ( tree.getId ( ) );
        session.getServletContext ( ).removeAttribute ( tree.getName ( ) );
        ra.addFlashAttribute ( "actionResult" , new SuccessActionResult ( ) );
        return "redirect:" + HttpUtils.getMarkRequestInfo ( request , "returnURI" );
    }

    @RequestMapping ( value = "/menu/update" , method = RequestMethod.GET )
    public String updateTree ( Integer id , Model model ) {
        Menu tree = menuService.getTree ( id );
        model.addAttribute ( "tree" , tree );
        model.addAttribute ( "treeNodes" , getTreeJSON ( tree ) );
        treeJSON = "";
        return "/zTree/mydemo/test002";
    }
    
//    @ResponseBody
//    @RequestMapping ( value = "/menu/update" , method = RequestMethod.GET )
//    public ResponseEntity<String> updateTree ( Integer id , Model model,HttpServletResponse response ) {
//        Menu tree = menuService.getTree ( id );
//       // model.addAttribute ( "tree" , tree );
//       // model.addAttribute ( "treeNodes" , getTreeJSON ( tree ) );
////        response.setContentType("application/json;charset=utf-8");          
////        response.setHeader("Cache-Control","no-cache");      
////        try
////        {
////           // treeJSON =  ChangeCharset.toUTF_8(getTreeJSON ( tree ));
////        }
////        catch (UnsupportedEncodingException e)
////        {
////            e.printStackTrace();
////        }
////        PrintWriter pw = null;
////        try {
////            pw = response.getWriter();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        System.out.println("treeJSON=" + treeJSON);
////        pw.print(treeJSON);          
////        pw.flush();          
////        pw.close(); 
//       HttpHeaders headers = new HttpHeaders ( );
//       headers.set ( "Content-Type" , "application/json;charset=utf-8" );
//       return new ResponseEntity<String> ( getTreeJSON ( tree ) , headers , HttpStatus.OK );
//    }

    private String  treeJSON    = "";

    public String getTreeJSON ( Menu menu ) {
        Menu parent = menu.getParent ( );
        int pid = 0;
        if ( parent != null ){
            pid = parent.getId ( );
        }
        String uri = menu.getUri ( );
        uri = uri == null ? "" : uri;
        boolean open = "open".equals ( menu.getStyle ( ) ) ? true : false;
        treeJSON += "{id:" + menu.getId ( ) + ", pId:" + pid + ", name:'" + menu.getName ( ) + "', url:'" + uri + "', open:" + open + "},";
        if ( menu.getChildren ( ).size ( ) > 0 ){
            for ( Menu child : menu.getChildren ( ) ){
                getTreeJSON ( child );
            }
        }
        return "[" + treeJSON.substring ( 0 , treeJSON.length ( ) - 1 ) + "]";
    }

    /**
     * 更新菜单树描述
     * 
     * @param id
     * @param description
     * @return
     */
    @RequestMapping ( value = "/menu/updateDescription" , method = RequestMethod.POST )
    public ResponseEntity<String> updateDescription ( Integer id , String description ) {
        menuService.updateDescription ( id , description );
        return new ResponseEntity<String> ( HttpStatus.OK );
    }
}
