package AkkaPackage;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class PiCalculator extends AbstractBehavior<PiCalculator.Command> {

    //
    private long dt = 1000;
    private long countEnd = 10;
    //private List<Integer> numArray= new ArrayList<Integer>();
    public static Double eps = 1E-8;
    public static Double monte_pi = 0.0;
    public static Double n = 1.0;
    public static Double member = 1.0;
    public static double start_monte = 0D;
    public static Double time_tooked_monte = 0D;
    private ActorRef<ConsolePrinter.Command> printer;

    //
    interface Command {
    }

    //...........   Реализация интерфейса command ..............
    public enum PiCalculatorCommand implements Command {
        //INIT,
        START_CALC
    }

    //
    public static class PiCalc implements Command {
        public final int numArray;

        public PiCalc(int numArray) {
            super();
            this.numArray = numArray;
        }
    }

    //Constructor
    private PiCalculator(ActorContext<Command> context, ActorRef<ConsolePrinter.Command> printer) {
        super(context);
        this.printer = printer;
    }

    // Fabric method
    public static Behavior<Command> create(ActorRef<ConsolePrinter.Command> printer) {
        return Behaviors.setup(context -> new PiCalculator(context, printer));
    }

    //
    @Override
    public Receive<Command> createReceive() {
        return newReceiveBuilder()
                //.onMessageEquals(PiCalculatorCommand.INIT,this::onSay)
                //.onMessageEquals(PiCalculatorCommand.START_CALC, this::onCalcStat)
                .onMessage(PiCalc.class, this::onCalcStat)
                .build();
    }

    //
    private Behavior<Command> onCalcStat(PiCalc command) {
        getContext().getLog().info("{}", command.numArray);
        int numArray = command.numArray;
        StringBuilder stringBuilder = new StringBuilder();
        //
        //Pi calculating by Monte
        switch (numArray) {
            //
            case (1):
                eps = 1E-4;
                break;

            //
            case (2):
                eps = 1E-6;
                break;

            //
            case (3):
                eps = 1E-8;
                break;

            default:
                break;

        }
        stringBuilder.append("Monte Carlo Method, Load Priority: ").append(numArray);
        stringBuilder.append("Calculating Pi with a given EPS ( ").append(eps);

        calculate_monte();

        stringBuilder.append(" ): ").append(monte_pi).append(" took ").append(time_tooked_monte).append("sec.");
        stringBuilder.append("\n");

        refresh();

        printer.tell(new ConsolePrinter.Print(stringBuilder.toString()));
        return this;
    }

    //
    private Behavior<Command> onSay() {
        getContext().getLog().info("I passed the message");
        return this;
    }

    //Monte help method
    private static void calculate_monte(){
        start_monte = 0D;
        start_monte = System.nanoTime();
        while (eps < Math.abs(member)) {
            monte_pi += member;
            member = ((Math.pow(-1, n)) * (1 / ((2 * n) + 1)));
            n++;
        }
        monte_pi = monte_pi * 4;
        time_tooked_monte = (double) ((System.nanoTime() - start_monte) / 1000000 / 1e3);
    }

    //Monte - refresh method
    public static void refresh(){
        eps= 1E-8;
        monte_pi= 0.0;
        n = 1.0;
        member = 1.0;
        start_monte = 0D;
        time_tooked_monte= 0D;
    }
}

