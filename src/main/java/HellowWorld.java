import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Receive;
import akka.actor.typed.javadsl.Behaviors;
/*
	Создаем класс HelloWorld, объекты которого обладают способностью
	а) пересылать текстовое сообщение
	б) принимать текстовое сообщение
	в) изменять текстовое сообщение

	Класс наследуется от AbstractBehavior<T>, который типизирован HelloWorld.Command
    Каждый субъект определяет тип T для сообщений, которые он может получать.
    Сообщения являются неизменяемыми, поддерживают сопоставление с образцом.

    При определении актеров и их сообщений придерживаемся рекомендаций:
    1. Поскольку сообщения являются общедоступным API-интерфейсом Actor,
       рекомендуется качественно именовать сообщения (чтобы смысл их был ясен),
       даже если они просто "переносят тип данных".
       Это облегчит использование, понимание и отладку.
    2. Сообщения должны быть неизменными, так как они разделены между различными потоками.
    3. Хорошей практикой является размещение связанных с актером сообщений в виде статических классов
       в классе AbstractBehaavior. Это облегчает понимание того, какие сообщения ожидает
       и обрабатывает субъект.
    4. Хорошей практикой является получение исходного поведения актера с помощью статического метода фабрики.
*/

public class HellowWorld extends AbstractBehavior<HellowWorld.Command> {
    private String message = "Hi";

    interface Command{}
    //...........   Реализация интерфейса command ..............
    public enum SayHello implements Command{
        INSTANCE
    }

    //...........   Реализация интерфейса command ..............
    public static class ChangeMessage implements Command{
        public final String newMessage;
        public ChangeMessage(String newMessage){
            this.newMessage = newMessage;
        }
    }
    // Конструктор
    private HellowWorld(ActorContext<Command> context){
        super((akka.actor.typed.javadsl.ActorContext<Command>) context);
    }

    // static фабричный метод,
    public static Behavior<Command> create(){
        return Behaviors.setup(context-> new HellowWorld( context));
    }

    @Override
    public Receive<Command> createReceive() {
        return  newReceiveBuilder()
                .onMessageEquals(SayHello.INSTANCE, this::onSayHello)
                .onMessage(ChangeMessage.class,this::OnChangeMessage)
                .build();
    }

    // Обработчики событий изменения сообщения
    private Behavior<Command> OnChangeMessage(ChangeMessage command){
        message = command.newMessage;
        return this;
    }

    // Обработчики событий передачи сообщения
    private Behavior<Command> onSayHello(){
        System.out.println(message);
        return this;
    }
}