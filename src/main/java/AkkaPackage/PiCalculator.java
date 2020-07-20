package AkkaPackage;

import java.util.ArrayList;
import java.util.List;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class PiCalculator extends AbstractBehavior<PiCalculator.Command> {

    //
    private long dt = 1000;
    private long countEnd = 10;
    private List<Integer> numArray= new ArrayList<Integer>();
    public static Double eps= 1E-8;
    public static Double monte_pi= 0.0;
    public static Double n = 1.0;
    public static Double member = 1.0;
    public static double start_monte = 0D;
    public static Double time_tooked_monte= 0D;

    //
    interface Command{}

    //...........   Реализация интерфейса command ..............
    public enum PiCalculatorCommand implements Command{
        INIT,
        START_CALC
    }

    //
    public static class PiCalc implements Command {
        public final List<Integer> numArray;

        public PiCalc(List<Integer> numArray) {
            super();
            this.numArray = numArray;
        }
    }

    //Constructor
    private PiCalculator(ActorContext<Command> context) {
        super(context);
    }

    // Fabcric method
    public static Behavior<Command> create(){
        return Behaviors.setup(context -> new PiCalculator(context));
    }

    //
    @Override
    public Receive<Command> createReceive(){
        return newReceiveBuilder()
                .onMessageEquals(PiCalculatorCommand.INIT,this::onSay)
                .onMessageEquals(PiCalculatorCommand.START_CALC,this::onStartCalc)
                .onMessage(PiCalc.class, this::onCalcStat)
                .build();
    }

    //
    private Behavior<Command> onCalcStat(PiCalc command){
        getContext().getLog().info("{}", command.numArray);
        numArray = command.numArray;
        return this;
    }

    //
    private Behavior<Command> onSay(){
        getContext().getLog().info("I passed the message");
        return this;
    }

    //
    private Behavior<Command> onStartCalc(){
        //
        while (countEnd-->0) {
            try {
                Thread.sleep(dt);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //
            int i =0;

            //Pi calculating by Monte
            switch (numArray.get(i)) {

                //
                case (1):

                    System.out.println("Monte Carlo Method, Load Priority: " + numArray.get(i));
                    eps= 1E-4;
                    System.out.print("Calculating Pi with a given EPS ( " + eps);

                    calculate_monte();

                    System.out.println(" ): " + monte_pi + " took " + time_tooked_monte + "sec.");
                    System.out.println();

                    refresh();

                    break;

                //
                case (2):

                    System.out.println("Monte Carlo Method, Load Priority: " + numArray.get(i));
                    eps= 1E-6;
                    System.out.print("Calculating Pi with a given EPS ( " + eps);

                    calculate_monte();

                    System.out.println(" ): " + monte_pi + " took " + time_tooked_monte + "sec.");
                    System.out.println();

                    refresh();

                    break;

                //
                case (3):

                    System.out.println("Monte Carlo Method, Load Priority: " + numArray.get(i));
                    eps= 1E-8;
                    System.out.print("Calculating Pi with a given EPS ( " + eps);

                    calculate_monte();

                    System.out.println(" ): " + monte_pi + " took " + time_tooked_monte + "sec.");
                    System.out.println();

                    refresh();

                    break;

                default:
                    break;

            }
            i++;

        }
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
