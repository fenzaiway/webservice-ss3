package com.way.blog.util;

import java.util.regex.Pattern;

public class RegexPatternUtil {
	  
	/**
     * 验证Email
     * @param email email地址，格式：zhangsan@sina.com，zhangsan@xxx.com.cn，xxx代表邮件服务�?
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkEmail(String email) { 
        String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?"; 
        return Pattern.matches(regex, email); 
    } 
     
    /**
     * 验证身份证号�?
     * @param idCard 居民身份证号�?15位或18位，�?后一位可能是数字或字�?
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkIdCard(String idCard) { 
        String regex = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}"; 
        return Pattern.matches(regex,idCard); 
    } 
     
    /**
     * 验证手机号码（支持国际格式，+86135xxxx...（中国内地）�?+00852137xxxx...（中国香港）�?
     * @param mobile 移动、联通�?�电信运营商的号码段
     *<p>移动的号段：134(0-8)�?135�?136�?137�?138�?139�?147（预计用于TD上网卡）
     *�?150�?151�?152�?157（TD专用）�??158�?159�?187（未启用）�??188（TD专用�?</p>
     *<p>联�?�的号段�?130�?131�?132�?155�?156（世界风专用）�??185（未启用）�??186�?3g�?</p>
     *<p>电信的号段：133�?153�?180（未启用）�??189</p>
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkMobile(String mobile) { 
        String regex = "(\\+\\d+)?1[3458]\\d{9}$"; 
        return Pattern.matches(regex,mobile); 
    } 
     
    /**
     * 验证固定电话号码
     * @param phone 电话号码，格式：国家（地区）电话代码 + 区号（城市代码） + 电话号码，如�?+8602085588447
     * <p><b>国家（地区） 代码 �?</b>标识电话号码的国家（地区）的标准国家（地区）代码。它包含�? 0 �? 9 的一位或多位数字�?
     *  数字之后是空格分隔的国家（地区）代码�?</p>
     * <p><b>区号（城市代码）�?</b>这可能包含一个或多个�? 0 �? 9 的数字，地区或城市代码放在圆括号—�??
     * 对不使用地区或城市代码的国家（地区），则省略该组件�??</p>
     * <p><b>电话号码�?</b>这包含从 0 �? 9 的一个或多个数字 </p>
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkPhone(String phone) { 
        String regex = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$"; 
        return Pattern.matches(regex, phone); 
    } 
     
    /**
     * 验证整数（正整数和负整数�?
     * @param digit �?位或多位0-9之间的整�?
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkDigit(String digit) { 
        String regex = "\\-?[1-9]\\d+"; 
        return Pattern.matches(regex,digit); 
    } 
     
    /**
     * 验证整数和浮点数（正负整数和正负浮点数）
     * @param decimals �?位或多位0-9之间的浮点数，如�?1.23�?233.30
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkDecimals(String decimals) { 
        String regex = "\\-?[1-9]\\d+(\\.\\d+)?"; 
        return Pattern.matches(regex,decimals); 
    }  
     
    /**
     * 验证空白字符
     * @param blankSpace 空白字符，包括：空格、\t、\n、\r、\f、\x0B
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkBlankSpace(String blankSpace) { 
        String regex = "\\s+"; 
        return Pattern.matches(regex,blankSpace); 
    } 
     
    /**
     * 验证中文
     * @param chinese 中文字符
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkChinese(String chinese) { 
        String regex = "^[\u4E00-\u9FA5]+$"; 
        return Pattern.matches(regex,chinese); 
    } 
     
    /**
     * 验证日期（年月日�?
     * @param birthday 日期，格式：1992-09-03，或1992.09.03
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkBirthday(String birthday) { 
        String regex = "[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}"; 
        return Pattern.matches(regex,birthday); 
    } 
     
    /**
     * 验证URL地址
     * @param url 格式：http://blog.csdn.net:80/xyang81/article/details/7705960? �? http://www.csdn.net:80
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkURL(String url) { 
        String regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?"; 
        return Pattern.matches(regex, url); 
    } 
     
    /**
     * 匹配中国邮政编码
     * @param postcode 邮政编码
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkPostcode(String postcode) { 
        String regex = "[1-9]\\d{5}"; 
        return Pattern.matches(regex, postcode); 
    } 
     
    /**
     * 匹配IP地址(�?单匹配，格式，如�?192.168.1.1�?127.0.0.1，没有匹配IP段的大小)
     * @param ipAddress IPv4标准地址
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkIpAddress(String ipAddress) { 
        String regex = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))"; 
        return Pattern.matches(regex, ipAddress); 
    }
    
    /** 
    * �?查ID是否 合法，开头必须是大小写字母，其他位可以有大小写字符�?�数字�?�下划线 
    * @param value 
    * @return 
    */ 
    public static boolean checkID(String value){ 
    return value.matches("[a-zA-Z][a-zA-Z0-9_]{4,15}$"); 
    } 
    /** 
    * �?查QQ是否 合法，必须是数字，且首位不能�?0，最�?15�? 
    * @param value 
    * @return 
    */ 

    public static boolean checkQQ(String value){ 
    return value.matches("[1-9][0-9]{4,13}"); 
    }
    
    /** 
    * �?查字符串�? 否含有HTML标签 
    * @param value 
    * @return 
    */ 

    public static boolean checkHtmlTag(String value){ 
    return value.matches("<(\\S*?)[^>]*>.*?</\\1>|<.*? />"); 
    } 
    
    /** 
    * �?查输入是�? 超出规定长度 
    * Java教程:http://www.javaweb.cc
    * @param length 
    * @param value 
    * @return 
    */ 
    public static boolean checkLength(String value, int length) { 
    return ((value == null || "".equals(value.trim())) ? 0 : value.length()) <= length; 
    } 
    
    public static String toHTMLString(String in) {
        StringBuffer out = new StringBuffer();
        for (int i = 0; in != null && i < in.length(); i++) {
            char c = in.charAt(i);
            if (c == '\'')
                out.append("&#039;");
            else if (c == '\"')
                out.append("&#034;");
            else if (c == '<')
                out.append("&lt;");
            else if (c == '>')
                out.append("&gt;");
            else if (c == '&')
                out.append("&amp;");
            else if (c == ' ')
                out.append("&nbsp;");
            else if (c == '\n')
                out.append("<br/>");
            else
                out.append(c);
        }
        return out.toString();
    }
    
    public static String Html2Text(String inputString) {    
        String htmlStr = inputString; // 含html标签的字符串    
        String textStr = "";    
        java.util.regex.Pattern p_script;    
        java.util.regex.Matcher m_script;    
        java.util.regex.Pattern p_style;    
        java.util.regex.Matcher m_style;    
        java.util.regex.Pattern p_html;    
        java.util.regex.Matcher m_html;    
  
        java.util.regex.Pattern p_html1;    
        java.util.regex.Matcher m_html1;    
  
       try {    
            String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[//s//S]*?<///script>    
            String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[//s//S]*?<///style>    
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式    
            String regEx_html1 = "<[^>]+";    
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);    
            m_script = p_script.matcher(htmlStr);    
            htmlStr = m_script.replaceAll(""); // 过滤script标签    
  
            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);    
            m_style = p_style.matcher(htmlStr);    
            htmlStr = m_style.replaceAll(""); // 过滤style标签    
  
            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);    
            m_html = p_html.matcher(htmlStr);    
            htmlStr = m_html.replaceAll(""); // 过滤html标签    
  
            p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);    
            m_html1 = p_html1.matcher(htmlStr);    
            htmlStr = m_html1.replaceAll(""); // 过滤html标签    
  
            textStr = htmlStr;    
  
        } catch (Exception e) {    
            System.err.println("Html2Text: " + e.getMessage());    
        }    
  
       return textStr;// 返回文本字符串    
    } 
}
