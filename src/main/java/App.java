import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.javadsl.ActorContext;

import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.UUID;
import java.util.function.IntSupplier;


public class App  {

    public static void main(String[] args) throws InterruptedException {

        RequestActor.SendRequset();

        //ActorSystem.create(App.create(), "Main");

        /*
        Scanner sc = new Scanner(System.in);
        while(true) {
            String str = sc.next();
            // Exit if q pressed
            if("q".equals(str)) {
                system.tell(poisonPill());
                return;
            }
            worker.tell(1);
        }
        */

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