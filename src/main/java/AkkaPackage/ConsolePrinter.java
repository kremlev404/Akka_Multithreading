package AkkaPackage;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import lombok.AllArgsConstructor;
import lombok.Builder;

public class ConsolePrinter extends AbstractBehavior<ConsolePrinter.Command> {

    public ConsolePrinter(ActorContext<Command> context) {
        super(context);
    }
    public static Behavior<Command> create(){
        return Behaviors.setup(ConsolePrinter::new);
    }
    @Override
    public Receive<Command> createReceive() {
        return newReceiveBuilder()
                .onMessage(Print.class,this::onPrint)
                .build();
    }

    private Behavior<Command> onPrint(Print a) {
        getContext().getLog().info(a.message);
        return this;
    }

    interface Command{}

    @AllArgsConstructor
    public static class Print implements Command{
        public final String message;
    }

}
