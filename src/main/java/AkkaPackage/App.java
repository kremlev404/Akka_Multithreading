package AkkaPackage;

import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.Terminated;
import akka.actor.typed.javadsl.Behaviors;

public class App {
	public static Behavior<Void> create() {

		return Behaviors.setup(context -> {
			ActorRef<Init.Command> init = context.spawn(Init.create(), "INIT");
			init.tell(Init.InitCommand.INIT);
			init.tell(Init.InitCommand.START_GEN);
			init.tell(new Init.ChangeMessage("START_GENERATE"));
			init.tell(Init.InitCommand.INIT);

			return Behaviors.receive(Void.class)
					.onSignal(Terminated.class, sig -> Behaviors.stopped())
					.build();
		});
	}

	public static void main(String[] args) {
		ActorSystem.create(App.create(), "App");
	}
}
