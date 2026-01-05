final class Person{
	private final String userName ="Nothing";
	private final int age = 16;
	
	private String getUserName() {
		return userName;
	}
	private int getAge() {
		return age;
	}
	public void getDetails() {
		System.out.println(getUserName());
		System.out.println(getAge());
	}
	
}

public class CImmuEx {

	public static void main(String[] args) {
		Person obj = new Person();
		obj.getDetails();

	}

}
