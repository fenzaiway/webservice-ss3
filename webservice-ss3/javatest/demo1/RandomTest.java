package demo1;

import java.util.Random;

public class RandomTest {
	
	public static void main(String[] args) {
		for(int i=0; i<5; i++){
			int index = new Random().nextInt(40);
			System.out.println(index);
		}
	}
}
