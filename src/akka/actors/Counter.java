package akka.actors;

import akka.actor.AbstractLoggingActor;
import akka.actor.Props;

public class Counter extends AbstractLoggingActor {
	
	private int counter;
	
	static class Message{};

	@Override
	public Receive createReceive() {
		
		return receiveBuilder().match(Message.class, this::onMessage).build();
	}
	
	
	public static Props props() {
		return Props.create(Counter.class);
	}
	
	
	private void onMessage(Message message) {
		
		counter++;
		log().info("Increased counter : " + counter);
		
	}

}
