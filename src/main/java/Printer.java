public class Printer {
    //Print output of Handler reusalts

    public static void print(int load) throws InterruptedException {
        //Handler.GeneratePiLeibnicMethod();
        Handler handl = new Handler();
        handl.GeneratePiLeibnicMethod(load);
        System.out.println("pi = " + handl.pi + " took " + handl.time_tooked / 1000000 / 1e3 + " secs.");
        handl.refresh_generator();
    }
}
