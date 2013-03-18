package demo1;

public class SplitDemo {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String tags = "测试，关键字";
		String tags = "";
		String[] tag = tags.split(",");
		System.out.println(tag.length);
		for (String string : tag) {
			System.out.println(string+"1");
			System.out.println("".equals(string));
		}
	}
}
