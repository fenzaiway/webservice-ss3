package com.way.blog.util;
//���û����ܰ�
import java.security.*;

public class MD5{
	
	//MD5算法
	public final static String code(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	/////直接返回变形后的密码，用户只需传递参数即可
	public final static String getPasswordMD5Code(String password, String username){
		return code(changePassword(password, username));
	}
	
	/**
	 * 变换密码
	 * @param password
	 * @param username
	 * @return
	 */
	public final static String  changePassword(String password,String username){
		String str = password+"{" + username+"}";
		return str;
	}
	
	/**
	 * 测试方法
	 * @param args
	 */
	public static void main(String[] args) {
		//System.out.println(code("way890727{fenzaiway@qq.com}"));
		System.out.println(getPasswordMD5Code("way890727", "fenzaiway@qq.com"));
	}
}
