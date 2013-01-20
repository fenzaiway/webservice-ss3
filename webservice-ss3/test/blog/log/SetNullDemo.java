package blog.log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author fenzaiway
 * 设置list中对象的某些字段为给定的值
 * @param <T> 泛型类
 */

public class SetNullDemo<T> {
	
	private static SetNullDemo setNullDemo;
	
	/**
	 * 设置构造函数为私有，设计单例
	 */
	private SetNullDemo(){
		//setNullDemo = new SetNullDemo<T>();
	}
	
	/**
	 * 单例设计
	 * @return
	 */
	public static SetNullDemo instance(){
		setNullDemo = new SetNullDemo();
		return setNullDemo;
	}
	
	/**
	 * @param list 要设置的list
	 * @param value 设置的值
	 * @param values 要设置null的字段
	 * @return 经过设置变量后的list
	 */
	public  List<T> setNull(List<T> list, Object value, String...values){
		
		List<T> returnList = new ArrayList<T>();
		for(T t : list){
			for(String fieldName : values){
				SetNullDemo.invokeSet(t, fieldName, value);
			}
			returnList.add(t);
		}
		
		return returnList;
		
	}
	
	
	/**   
	 * java反射bean的get方法   
	 * @param objectClass   
	 * @param fieldName   
	 * @return   
	 */     

	@SuppressWarnings("unchecked")     

	public static Method getGetMethod(Class objectClass, String fieldName) {     
	    StringBuffer sb = new StringBuffer();     
	    sb.append("get");     
	    sb.append(fieldName.substring(0, 1).toUpperCase());     
	    sb.append(fieldName.substring(1));     
	    try {     
	        return objectClass.getMethod(sb.toString());     
	    } catch (Exception e) {     
	    }     
	    return null;     

	}     

	/**   
	 * java反射bean的set方法   
	 * @param objectClass   
	 * @param fieldName   
	 * @return   
	 */     

	@SuppressWarnings("unchecked")     

	public static Method getSetMethod(Class objectClass, String fieldName) {     
	    try {     
	        Class[] parameterTypes = new Class[1];     
	        Field field = objectClass.getDeclaredField(fieldName);     
	        parameterTypes[0] = field.getType();     
	        StringBuffer sb = new StringBuffer();     
	        sb.append("set");     
	        sb.append(fieldName.substring(0, 1).toUpperCase());     
	        sb.append(fieldName.substring(1));     
	        Method method = objectClass.getMethod(sb.toString(), parameterTypes);     
	        return method;     

	    } catch (Exception e) {     
	        e.printStackTrace();     
	    }     
	    return null;     

	}     
	     

	/**   
	 * 执行set方法   
	 *    
	 * @param o执行对象   
	 * @param fieldName属性   
	 * @param value值   
	 */     

	public static void invokeSet(Object o, String fieldName, Object value) {     

	    Method method = getSetMethod(o.getClass(), fieldName);     
	    try {     
	        method.invoke(o, new Object[] { value });     
	    } catch (Exception e) {     
	        e.printStackTrace();     
	    }     

	}     
	     

	/**   
	 * 执行get方法   
	 * @param o执行对象   
	 * @param fieldName属性   
	 */     

	public static Object invokeGet(Object o, String fieldName) {     

	    Method method = getGetMethod(o.getClass(), fieldName);     
	    try {     
	        return method.invoke(o, new Object[0]);     
	    } catch (Exception e) {     
	        e.printStackTrace();     
	    }     
	    return null;     

	}

}
