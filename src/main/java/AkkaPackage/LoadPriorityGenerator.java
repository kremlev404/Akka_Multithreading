package AkkaPackage;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import lombok.SneakyThrows;

import java.util.List;

public class LoadPriorityGenerator extends AbstractBehavior<LoadPriorityGenerator.Command> {

    //
    interface Command {
    }

    //
    public enum LoadPriorityGeneratorCommand implements Command {
        INIT,
    }

    //
    public static class LoadPriority implements Command {
        //
        public final List<ActorRef<PiCalculator.Command>> replyTo;

        //
        public LoadPriority(List<ActorRef<PiCalculator.Command>> replyTo) {
            super();
            this.replyTo = replyTo;
        }

    }

    //
    private LoadPriorityGenerator(ActorContext<Command> context) {
        super(context);
    }

    //
    public static Behavior<Command> create(int i) {
        return Behaviors.setup(LoadPriorityGenerator::new);
    }

    @Override
    public Receive<Command> createReceive() {
        return newReceiveBuilder()
                .onMessageEquals(LoadPriorityGeneratorCommand.INIT, this::onSay)
                .onMessage(LoadPriority.class, this::onStartGen)
                .build();
    }

    //
    private Behavior<Command> onSay() {
        getContext().getLog().info("LoadPriorityGenerator Actor");
        return this;
    }

    //
    @SneakyThrows
    private Behavior<Command> onStartGen(LoadPriority command) {

        //
        while (true) {

            //
            Thread.sleep(2000);

            List<ActorRef<PiCalculator.Command>> replyTo = command.replyTo;
            for (int i1 = 0; i1 < replyTo.size(); i1++) {
                //getContext().getLog().info(" Gen new priority: {}", i1 + 1);
                ActorRef<PiCalculator.Command> commandActorRef = replyTo.get(i1);
                commandActorRef.tell(new PiCalculator.PiCalc(i1 + 1));

            }
        }
    }
}