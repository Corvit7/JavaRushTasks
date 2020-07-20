package com.javarush.task.task30.task3008.client;


//С него и начнем:
//        1) Создай новый класс BotClient в пакете client.
//        Он должен быть унаследован от Client.
//        2) В классе BotClient создай внутренний класс BotSocketThread унаследованный от SocketThread.
//        Класс BotSocketThread должен быть публичным.
//        3) Переопредели методы:
//        а) getSocketThread(). Он должен создавать и возвращать объект класса BotSocketThread.
//        б) shouldSendTextFromConsole(). Он должен всегда возвращать false.
//        Мы не хотим, чтобы бот отправлял текст введенный в консоль.
//        в) getUserName(), метод должен генерировать новое имя бота, например: date_bot_X, где X - любое число от 0 до 99.
//        Для генерации X используй метод Math.random().
//        4) Добавь метод main(). Он должен создавать новый объект BotClient и вызывать у него метод run().

public class BotClient extends Client {

    public class BotSocketThread extends SocketThread {
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }


    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return String.format("date_bot_%d", (int) (Math.random() * 100));
    }

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }
}
