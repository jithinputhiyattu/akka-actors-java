package akka.actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class Main {

	public static void main(String[] args) {

		ActorSystem actorSystem = ActorSystem.create("system-1");
		ActorRef counterRef = actorSystem.actorOf(Counter.props(), "counter-1");

		for (int t = 0; t < 10; t++) {

			new Thread(() -> {

				for (int i = 0; i < 500; i++) {
					counterRef.tell(new Counter.Message(), ActorRef.noSender());
				}

			}).start();

		}
	}

}
