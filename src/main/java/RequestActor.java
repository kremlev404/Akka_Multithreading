public class RequestActor {
    /*
        //class fields
        float time;

        public RequestActor() {
            super();
        }

        //main interface type
        interface Command{}

        //Constructor
        public RequestActor(ActorContext<Command> context) {
            super(context);
        }

        @Override
        public Receive<Command> createReceive() {
            return null;
        }

        public static Behavior<RequestActor.Command> create(){
            return Behaviors.setup(context-> new RequestActor(context));
        }

         */
    public static int load_priority = 1;
    //Class to receive type of message ( Generate big loads if 3, middle = 2, small loads of hanlde if 1
    public static void SendRequset() throws InterruptedException {

        int a = 1;
        int b  = 3;
        while(true){

            load_priority = (int) ((Math.random() * ((b - a) + 1)) + a);

            Calculator.start(load_priority);
        }
    }
    /*
    public static class Message{
        public static double type;

        public Message(double type){
            this.type = type;
        }
        public static double type_of_message() {
            if (Message.type == 1) {
                return 1.;
            }
            if (Message.type == 2) {
                return 2.;
            }
            if (Message.type == 3) {
                return 3.;
            }
            else
                return  0.;
        }

    }

     */

}
