package demo1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapDemo {
	public static void main(String[] args) {
		List<Map<String,Integer>> mapList = new ArrayList<Map<String,Integer>>();
		
		Map<String,Integer> map1 = new HashMap<String, Integer>();
		map1.put("b", 1);
		Map<String,Integer> map2 = new HashMap<String, Integer>();
		map1.put("a", 2);
		Map<String,Integer> map3 = new HashMap<String, Integer>();
		map1.put("d", 3);
		mapList.add(map1);
		mapList.add(map2);
		mapList.add(map3);
		Arrays.sort(mapList.toArray());
		System.out.println(mapList.toArray());
	}
}
