package AkkaPackage;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

import java.util.ArrayList;
import java.util.List;

public class Init extends AbstractBehavior<Init.Command> {

    //
    private String message = "Initiation started";
    //Load Priority Actor
    private final ActorRef<LoadPriorityGenerator.Command> loadGen;
    //Pi calculating Actor's
    private final List<ActorRef<PiCalculator.Command>> piCalc;

    //interface
    interface Command {
    }

    //
    public enum InitCommand implements Command {
        INIT,
        START_GEN,
    }

    //Command implementing
    public static class ChangeMessage implements Command {
        public final String newMessage;

        public ChangeMessage(String newMessage) {
            super();
            this.newMessage = newMessage;
        }
    }

    // Constructor
    private Init(ActorContext<Command> context) {
        super(context);
        ActorRef<ConsolePrinter.Command> printer = context.spawn(ConsolePrinter.create(), "Printer");
        loadGen = context.spawn(LoadPriorityGenerator.create(6), "LoadPriorityGenerator");
        //spawn 3 actors to calculate each pi with different loadpriority
        piCalc = new ArrayList<>() {{
            add(context.spawn(PiCalculator.create(printer), "PiCalculator"));
            add(context.spawn(PiCalculator.create(printer), "PiCalculator2"));
            add(context.spawn(PiCalculator.create(printer), "PiCalculator3"));
        }};
    }

    // Fabric method
    public static Behavior<Command> create() {
        return Behaviors.setup(context -> new Init(context));
    }

    //
    @Override
    public Receive<Command> createReceive() {
        return newReceiveBuilder()
                .onMessageEquals(InitCommand.INIT, this::onSay)
                .onMessageEquals(InitCommand.START_GEN, this::onStartGen)
                .onMessage(ChangeMessage.class, this::onChangeMessage)
                //.onMessageEquals(InitCommand.START_CALC,this::onStartCalc)
                .build();
    }

    //
    private Behavior<Command> onChangeMessage(ChangeMessage command) {
        message = command.newMessage;
        return this;
    }

    // 
    private Behavior<Command> onSay() {
        getContext().getLog().info(message);
        return this;
    }

    //
    private Behavior<Command> onStartGen() {
        loadGen.tell(new LoadPriorityGenerator.LoadPriority(piCalc));
        return this;
    }
}