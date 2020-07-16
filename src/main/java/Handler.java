public class Handler {
    private static RequestActor.Message type1 = new RequestActor.Message(1);
    public static double pi = 0;
    public static long time_tooked = 0;
    //Hanld Request
    //Вычисление числа пи
    //Способ 1 - ряд лейбница
    public static void GeneratePiLeibnicMethod() {
        if (type1.type_of_message() == 1) {
            int k = 1;
            long start = System.nanoTime();
            for (int i = 1; i < 199999999; i += 4) {
                pi += 8.0 / (i * (i + 2L));
            }
            time_tooked = System.nanoTime() - start;

        }
    }
    //Способ 2  - Метод Монте - Карло
}
