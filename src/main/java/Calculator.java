public class Calculator {
    //Print output of Handler reusalts

    public static void start(int load) throws InterruptedException {

        Handler.Leibnic leibnic_hand = new Handler.Leibnic();
        Handler.Monte monte_hand = new Handler.Monte();

        leibnic_hand.calculate(load);
        monte_hand.calculate(load);

    }
}
