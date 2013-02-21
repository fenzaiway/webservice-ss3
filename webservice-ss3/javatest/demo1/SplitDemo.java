package demo1;

public class SplitDemo {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String tags = "测试，关键字";
		String[] tag = tags.split("，");
		for (String string : tag) {
			System.out.println(string);
		}
	}
}
