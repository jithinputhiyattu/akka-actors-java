package akka.actors;

import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class Main {

	public static void main(String[] args) {

		ActorSystem actorSystem = ActorSystem.create("system-1");
		ActorRef counterRef = actorSystem.actorOf(Counter.props(), "counter-1");

		long st = System.currentTimeMillis();

		List<Thread> listT = new ArrayList<>();

		for (int t = 0; t < 10; t++) {

			Thread tr = new Thread(() -> {

				for (int i = 0; i < 50; i++) {
					counterRef.tell(new Counter.Message(), ActorRef.noSender());
				}

			});
			listT.add(tr);
			tr.start();

		}

		for (int t = 0; t < 10; t++) {

			Thread tr = new Thread(() -> {

				for (int i = 0; i < 50; i++) {
					ThreadSafeCounter.onMessage(null);
				}

			});

			listT.add(tr);
			tr.start();
		}

		listT.forEach(t -> {

			try {
				t.join();
			} catch (InterruptedException e) {

			}

		});

		long end = System.currentTimeMillis();

		System.out.println(end - st);

	}

}
