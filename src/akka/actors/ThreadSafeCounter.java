package akka.actors;


public class ThreadSafeCounter {
	
	public static class Message{};	
	static int counter = 0;
	
	
	public static synchronized void onMessage(Message message) {
		
		counter++;
		System.out.println("Increased counter : " + counter);
		
	}
	

}
