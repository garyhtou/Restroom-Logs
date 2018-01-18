package testCode;

public class subclass_constructor {

	public static void main(String[] args) {
		sub test = new sub();
		System.out.println(test.get_something());

	}
	
	private static String name = null;
	public class sub {
		
		public sub() {
			name = "hello!";
		}
		public String get_something() {
			return name;
		}
	}

}
