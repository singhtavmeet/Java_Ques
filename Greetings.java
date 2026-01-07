@FunctionalInterface
interface Greet{
	public void greet(String name, String msg);
}


public class Greetings {
	
	public static void main(String[] args) {
		
		Greet g = (name , msg) -> {
			System.out.println("Hi " + name + " " + msg);
		};
		
		g.greet("Tavmeet Singh", "Good evening");
	}

}
