package AkkaPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class LoadPriorityGenerator extends AbstractBehavior<LoadPriorityGenerator.Command> {

    //
    private ActorRef<PiCalculator.Command> replyTo;
    public static int load_priority = 1;
    private List<Integer> numArray = new ArrayList<Integer>();
    private long dt = 1000;
    private int startNum = 0;
    private int countEnd = 10;

    //
    interface Command{}

    //
    public enum LoadPriorityGeneratorCommand implements Command{
        INIT,
    }

    //
    public static class LoadPriority implements Command {
        //
        public final ActorRef<PiCalculator.Command> replyTo;
        //
        public LoadPriority(ActorRef<PiCalculator.Command> replyTo) {
            super();
            this.replyTo = replyTo;
        }
        
    }

    //
    private LoadPriorityGenerator(ActorContext<Command> context) {
        super(context);
    }

    //
    public static Behavior<Command> create(){
        return Behaviors.setup(context -> new LoadPriorityGenerator(context));
    }

    @Override
    public Receive<Command> createReceive(){
        return newReceiveBuilder()
                .onMessageEquals(LoadPriorityGeneratorCommand.INIT,this::onSay)
                .onMessage(LoadPriority.class, this::onStartGen)
                .build();
    }

    //
    private Behavior<Command> onSay(){
        getContext().getLog().info("LoadPriorityGenerator Actor");
        return this;
    }

    //
    private Behavior<Command> onStartGen(LoadPriority command){

        //
        replyTo = command.replyTo;
        int i=0;
        int a = 1;
        int b  = 3;
        int diff = b - a;

        //
        while (true) {

            //
            try {
                Thread.sleep(dt);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //
            Random random = new Random();
            int load_priority = random.nextInt(diff + 1);
            load_priority += a;
            numArray.add(load_priority);
            getContext().getLog().info(" Gen new priority: {}", numArray.get(i));
            i++;
            replyTo.tell(new PiCalculator.PiCalc(numArray));
            replyTo.tell(PiCalculator.PiCalculatorCommand.START_CALC);
        }
        //return this;
    }

}