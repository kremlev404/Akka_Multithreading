import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Receive;
import akka.actor.typed.javadsl.Behaviors;

public class HellowWorld extends AbstractBehavior<HellowWorld.Command> {
    interface Command{}
    public enum SayHello implements Command{
        INSTANCE
    }
    public static class ChangeMessage implements Command{
        public final String newMessage;
        public ChangeMessage(String newMessage){
            this.newMessage = newMessage;
        }
    }
    public static Behavior<Command> create(){
        return Behaviors.setup(context-> new HellowWorld( context));
    }
    private String message = "Hi";

    private HellowWorld(ActorContext<Command> context){
        super((akka.actor.typed.javadsl.ActorContext<Command>) context);
    }
    @Override
    public Receive<Command> createReceive() {
        return  newReceiveBuilder()
                .onMessageEquals(SayHello.INSTANCE, this::onSayHello)
                .onMessage(ChangeMessage.class,this::OnChangeMessage)
                .build();
    }
    private Behavior<Command> OnChangeMessage(ChangeMessage command){
        message = command.newMessage;
        return this;
    }
    private Behavior<Command> onSayHello(){
        System.out.println(message);
        return this;
    }
}