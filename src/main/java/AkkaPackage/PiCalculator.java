package AkkaPackage;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class PiCalculator extends AbstractBehavior<PiCalculator.Command> {

    //
    //private List<Integer> numArray= new ArrayList<Integer>();
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

        double eps = 0D;
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

        calculate_monte(eps);
        //refresh();
        return this;
    }

    //Monte help method
    private void calculate_monte(double eps){
        Double monte_pi = 0.0;
        Double n = 1.0;
        Double member = 1.0;
        Double start_monte = 0D;
        Double time_tooked_monte = 0D;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Monte Carlo Method, Calculating Pi with a given EPS ( ").append(eps);
        
        start_monte = ((double) System.nanoTime());
        while (eps < Math.abs(member)) {
            monte_pi += member;
            member = ((Math.pow(-1, n)) * (1 / ((2 * n) + 1)));
            n++;
        }
        monte_pi = monte_pi * 4;
        time_tooked_monte = (double) ((System.nanoTime() - start_monte) / 1000000 / 1e3);
        stringBuilder.append(" ): ").append(monte_pi).append(" took ").append(time_tooked_monte).append("sec.");
        stringBuilder.append("\n");

        printer.tell(new ConsolePrinter.Print(stringBuilder.toString()));
    }

}

