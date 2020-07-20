package AkkaPackage;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class Init extends AbstractBehavior<Init.Command> {

    //
    private String message = "Initiation started";
    //Load Priority Actor
    private final ActorRef<LoadPriorityGenerator.Command> loadGen;
    //Pi calculating Actor
    private final ActorRef<PiCalculator.Command> piCalc;

    //interface
    interface Command{}

    //
    public enum InitCommand implements Command{
        INIT,
        START_GEN,
        START_CALC
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
        loadGen = context.spawn(LoadPriorityGenerator.create(), "LoadPriorityGenerator");
        piCalc = context.spawn(PiCalculator.create(), "PiCalculator");
    }

    // Fabric method
    public static Behavior<Command> create(){
        return Behaviors.setup(context -> new Init(context));
    }
    
    //
    @Override
    public Receive<Command> createReceive(){
        return newReceiveBuilder()
                .onMessageEquals(InitCommand.INIT,this::onSay)
                .onMessageEquals(InitCommand.START_GEN,this::onStartGen)
                .onMessageEquals(InitCommand.START_CALC,this::onStartCalc)
                .onMessage(ChangeMessage.class, this::onChangeMessage)
                .build();
    }

    //
    private Behavior<Command> onChangeMessage(ChangeMessage command){
        message = command.newMessage;
        return this;
    }

    // 
    private Behavior<Command> onSay(){
        getContext().getLog().info(message);
        return this;
    }

    //
    private Behavior<Command> onStartGen(){
        loadGen.tell(new LoadPriorityGenerator.LoadPriority(piCalc));
        return this;
    }

    //
    private Behavior<Command> onStartCalc(){
        piCalc.tell(PiCalculator.PiCalculatorCommand.INIT);
        piCalc.tell(PiCalculator.PiCalculatorCommand.START_CALC);
        return this;
    }

}