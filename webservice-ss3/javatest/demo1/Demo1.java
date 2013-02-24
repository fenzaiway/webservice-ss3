package demo1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo1 {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
//		list.add(1);
//		list.add(2);
//		list.add(3);
		int []ids = new int[3];
		ids[0]=1;
		///ids[1]=2;
		//ids[2]=3;
		///list.toArray(ids);
		//list = new ArrayList<Integer>(ids);
		System.out.println(getIntId(ids));
	}
	
	public static String getIntId(int[] ids){
		String id = "";
		for(int i = 0; i<ids.length; i++){
			id+=ids[i];
			id+=",";
		}
		id = id.substring(0, id.length()-1);
		return id;
	}
}
