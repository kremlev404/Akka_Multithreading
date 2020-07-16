import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Receive;
import java.util.concurrent.TimeUnit;

public class Handler {

    //HANLDER CLASS FIELDS
    //private static RequestActor.Message type1 = new RequestActor.Message(1);
    public static double pi = 0;
    public static long time_tooked = 0;
    /*
    //createReceive() method for handling the incoming messages from other actors:
    @Override
    public Receive<Command> createReceive() {
        //return receiveBuilder().build();
        return null;
    }

    //MAIN Interface
    interface Command{}

    //CONSTRUCTOR
    public Handler() {
        super(context);
    }
     */

    //PI CALCULATING
    //Method 1 - Pi Leibnic
    public static void GeneratePiLeibnicMethod(int load_priority) throws InterruptedException {
        long start = 0;
        switch (load_priority){
            case(1):
                start = System.nanoTime();
                TimeUnit.MILLISECONDS.sleep(100);
                for (int i = 1; i < 1000000000; i += 4) {
                    pi += 8.0 / (i * (i + 2L));
                }
                time_tooked = System.nanoTime() - start;
                break;
            case(2):
                start = System.nanoTime();
                TimeUnit.MILLISECONDS.sleep(400);
                for (int i = 1; i < 1000000000; i += 4) {
                    pi += 8.0 / (i * (i + 2L));
                }
                time_tooked = System.nanoTime() - start;
            case(3):
                start = System.nanoTime();
                TimeUnit.MILLISECONDS.sleep(700);
                for (int i = 1; i < 1000000000; i += 4) {
                    pi += 8.0 / (i * (i + 2L));
                }
                time_tooked = System.nanoTime() - start;
                break;
            default:
                break;
        }
    }
    public static void refresh_generator(){
        pi = 0;
        time_tooked =0;
    }

    //Method 2  - Monte-Carlo
}
