package AkkaPackage;

import akka.actor.typed.ActorSystem;

public class App {
	public static void main(String[] args) {

		//
		ActorSystem<Init.Command> mySystem = 
				ActorSystem.create(Init.create(), "MySystem");

		//
		mySystem.tell(Init.InitCommand.INIT);
		mySystem.tell(new Init.ChangeMessage("message was sended before command START_GEN"));
		mySystem.tell(Init.InitCommand.INIT);
		mySystem.tell(Init.InitCommand.START_GEN);
		mySystem.tell(new Init.ChangeMessage("message was sended after command START_GEN"));
		mySystem.tell(Init.InitCommand.INIT);

		//
		try {
			Thread.sleep(11000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//
		mySystem.terminate();
	}
}
