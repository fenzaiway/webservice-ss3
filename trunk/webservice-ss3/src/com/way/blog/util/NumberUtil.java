package com.way.blog.util;

import java.util.Random;

/**
 * 数字工具类
 * @author fenzaiway
 *
 */
public class NumberUtil {

	/**
     * 生成在value范围内的num个不同的随机数
     * @param num 生成的个数
     * @param value 范围，从0开始
     * @return
     */
	public static int[] ranNumber(int num,int value){
		Random random = new Random();
        int n=0;
        int b[]=new int[num];
        while(n<num){
	        b[n]=random.nextInt(value);
	        
	        if(n>=1){
		        int i=0;
		        while(i<n){
		            if(b[i]==b[n]) break;//如果相同，外层循环再执行一次，此时n的值不变
		            i++;
		        }
		        
		        if(i==n) n++;
	        }
	        else
	        n++;
        }
        return b ;
    }
}
