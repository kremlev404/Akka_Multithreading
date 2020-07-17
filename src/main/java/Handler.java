import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Receive;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Handler {

    //HANLDER CLASS FIELDS
    //private static RequestActor.Message type1 = new RequestActor.Message(1);

    /*
    //createReceive() method for handling the incoming messages from other actors:
    @Override
    public Receive<Command> createReceive() {
        //return receiveBuilder().build();
        return null;
    }

    //MAIN Interface
    interface Command{}

    //CONSTRUCTOR
    public Handler() {
        super(context);
    }
     */

    //Pi CALCULATING
    //Method 1 - Leibnic
    static class Leibnic {
        //Class fields
        public static double leibnic_pi = 0;
        public static double time_tooked = 0;
        public static double start = 0;
        //Additional class method
        public static void calculate_leibnic(int chill) throws InterruptedException {
            start = System.nanoTime();
            TimeUnit.MILLISECONDS.sleep(chill);
            for (int i = 1; i < 1000000000; i += 4) {
                leibnic_pi += 8.0 / (i * (i + 2L));
            }
            time_tooked = (System.nanoTime() - start)/ 1000000 / 1e3 ;
        }
        //Main class method
        public static void calculate(int load_priority) throws InterruptedException {
            switch (load_priority) {
                case (1):
                    System.out.println("Leibnic Method, Load Priority: " + load_priority);

                    calculate_leibnic(75);

                    System.out.println("Pi = " + leibnic_pi + " took " + time_tooked + " secs.");
                    System.out.println();

                    Leibnic.refresh();
                    break;
                case (2):
                    System.out.println("Leibnic Method, Load Priority: " + load_priority);

                    calculate_leibnic(150);

                    System.out.println("Pi = " + leibnic_pi + " took " + time_tooked + " secs.");
                    System.out.println();

                    Leibnic.refresh();
                    break;
                case (3):
                    System.out.println("Leibnic Method, Load Priority: " + load_priority);

                    calculate_leibnic(300);

                    System.out.println("Pi = " + leibnic_pi + " took " + time_tooked + " secs.");
                    System.out.println();

                    Leibnic.refresh();
                    break;
                default:
                    break;
            }
        }
        public static void refresh() {
            leibnic_pi = 0;
            time_tooked = 0;
            start = 0;
        }
    }

    //Method 2  - Monte-Carlo
    static class Monte {
        //Class fields
        public static Double eps= 1E-8;
        public static Double monte_pi= 0.0;
        public static Double n = 1.0;
        public static Double member = 1.0;
        public static double start_monte = 0D;
        public static Double time_tooked_monte= 0D;
        //Additional class method
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
        //Main class method
        public static void calculate(int load_priority) throws InterruptedException {
            switch (load_priority) {
                case (1):
                    System.out.println("Monte Carlo Method, Load Priority: " + load_priority);
                    eps= 1E-4;
                    System.out.print("Calculating Pi with a given EPS ( " + eps);

                    calculate_monte();

                    System.out.println(" ): " + monte_pi + " took " + time_tooked_monte + "sec.");
                    System.out.println();
                    Monte.refresh();
                    break;
                case (2):
                    System.out.println("Monte Carlo Method, Load Priority: " + load_priority);
                    eps= 1E-6;
                    System.out.print("Calculating Pi with a given EPS ( " + eps);

                    calculate_monte();

                    System.out.println(" ): " + monte_pi + " took " + time_tooked_monte + "sec.");
                    System.out.println();
                    Monte.refresh();
                    break;

                case (3):
                    System.out.println("Monte Carlo Method, Load Priority: " + load_priority);
                    eps= 1E-8;
                    System.out.print("Calculating Pi with a given EPS ( " + eps);

                    calculate_monte();

                    System.out.println(" ): " + monte_pi + " took " + time_tooked_monte + "sec.");
                    System.out.println();
                    Monte.refresh();
                    break;
                default:
                    break;

            }
        }

        public static void refresh(){
            eps= 1E-8;
            monte_pi= 0.0;
            n = 1.0;
            member = 1.0;
            start_monte = 0D;
            time_tooked_monte= 0D;
        }
    }
}
