package demo1;

import java.util.Random;

public class RandomTest {
	
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
	
	public static void main(String[] args) {
		/*for(int i=0; i<5; i++){
			int index = new Random().nextInt(40);
			System.out.println(index);
		}*/
		int a[] = ranNumber(4, 3);
		for (int i : a) {
			System.out.println(i);
		}
	}
}
