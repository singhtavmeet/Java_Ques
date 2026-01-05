import java.time.LocalDateTime;

class Singleton{

	private static Singleton singObj;
	private final LocalDateTime createdAt;
	private Singleton() {
		this.createdAt = LocalDateTime.now();
	}
	
	public static synchronized Singleton getInstance() {
		if(singObj == null) {
			return new Singleton();
		}
		return singObj;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

}

public class StPattern {

	public static void main(String[] args) {
		Singleton obj = Singleton.getInstance();
		System.out.println(obj.getCreatedAt());
	}

}
