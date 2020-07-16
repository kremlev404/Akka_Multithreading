import akka.actor.typed.ActorSystem;
import akka.actor.typed.javadsl.AbstractBehavior;

import java.io.IOException;

public class App  {

    public static void main(String[] args) {
        //Должно запускаться так, но пока без акторов
        /*
        ActorSystem<RequestActor.Command> mySystem =
                ActorSystem.create(RequestActor.create(),"MySystem");
        */

        Printer.print();

        //HelloWorld Setup comments
        /*
        ActorSystem<HellowWorld.Command> mySystem =
                ActorSystem.create(HellowWorld.create(),"MySystem");
        mySystem.tell(HellowWorld.SayHello.INSTANCE);
        //mySystem.tell(HellowWorld.SayHello.INSTANCE);
        mySystem.tell(new HellowWorld.ChangeMessage("Hi actor world"));
        mySystem.tell(HellowWorld.SayHello.INSTANCE);
        //mySystem.tell(HellowWorld.SayHello.INSTANCE);
        */


    }

}