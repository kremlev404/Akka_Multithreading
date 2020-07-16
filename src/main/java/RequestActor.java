import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.Receive;

public class RequestActor extends AbstractBehavior<RequestActor.Command> {
    //class fields
    float time;

    //main interface type
    interface Command{}

    //Constructor
    public RequestActor(ActorContext<Command> context) {
        super(context);
    }

    @Override
    public Receive<Command> createReceive() {
        return null;
    }

    public static Behavior<RequestActor.Command> create(){
        return Behaviors.setup(context-> new RequestActor(context));
    }
    //Class to receive type of message ( Generate big loads if 3, middle = 2, small loads of hanlde if 1
    class message{
        public int type_of_message(int type) {
            if (type == 1) {
                return 1;
            }
            if (type == 2) {
                return 2;
            }
            if (type == 3) {
                return 3;
            }
            else
                return  0;
        }

    }

}
