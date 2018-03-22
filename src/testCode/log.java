package testCode;

public class log {

	public static class system {
		public static void update(String data) {
			System.out.println(data);
		}
	}
	public static class startUp {
		public static void update(String data) {
			System.out.println(data);
		}
	}
	public static class test{
		public static class test2{
			public static class test3{
				public static class test4{
					public static class test5{
						public static void method(String data) {
							System.out.println(data);
						}
					}
				}
			}
		}
	}
	
	
	public static void manuUpdate(String data) {
		System.out.println(data);
	}

}
