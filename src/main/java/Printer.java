public class Printer {
    //Print output of Handler reusalts

    public static void print() {
        Handler.GeneratePiLeibnicMethod();
        System.out.println("pi = " + Handler.pi + " took " + Handler.time_tooked / 1000000 / 1e3 + " secs.");
    }


}
