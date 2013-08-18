package com.lutongnet.test.spring.controller;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lutongnet.test.spring.entity.StudentEntity;
import com.lutongnet.test.spring.service.StudentMapper;

/**
 * 
 * ����SpringMVC ��������ϸ������
 * 
 * @author lianggongfen
 */
@Controller
public class TestController
{
    @Autowired
    private StudentMapper studentMapper;
   
    @RequestMapping(value="/test/index.do")
    public String test(Model model)
    {
         System.out.println("aaaaa");
//        model.addAttribute("username", "fenzaiway");
        System.out.println(studentMapper.getStudent("123456"));
        StudentEntity se = new StudentEntity();
        se.setClassId("222222");
        se.setStudentBirthday(new Date());
        se.setStudentID("12345465");
        se.setStudentName("����");
        se.setStudentSex("��");
        studentMapper.insertStudent(se);
        System.out.println(studentMapper.getStudent("12345465"));
        
        model.addAttribute("username", "fenzaiway");
        return "test";
    }
}
