package com.javarush.task.task30.task3008.client;


//Чат (19)
//        Сейчас будем реализовывать класс BotSocketThread, вернее переопределять некоторые его методы, весь основной функционал он уже унаследовал от SocketThread.
//
//        1. Переопредели метод clientMainLoop():
//        а) С помощью метода sendTextMessage() отправь сообщение с текстом "Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды."
//        б) Вызови реализацию clientMainLoop() родительского класса.
//
//        2. Переопредели метод processIncomingMessage(String message).
//        Он должен следующим образом обрабатывать входящие сообщения:
//        а) Вывести в консоль текст полученного сообщения message.
//        б) Получить из message имя отправителя и текст сообщения. Они разделены ": ".
//        в) Отправить ответ в зависимости от текста принятого сообщения.
//        Если текст сообщения:
//        "дата" - отправить сообщение содержащее текущую дату в формате "d.MM.YYYY";
//        "день" - в формате "d";
//        "месяц" - "MMMM";
//        "год" - "YYYY";
//        "время" - "H:mm:ss";
//        "час" - "H";
//        "минуты" - "m";
//        "секунды" - "s".
//        Указанный выше формат используй для создания объекта SimpleDateFormat. Для получения текущей даты необходимо использовать класс Calendar и метод getTime().
//        Ответ должен содержать имя клиента, который прислал запрос и ожидает ответ, например, если Боб отправил запрос "время", мы должны отправить ответ "Информация для Боб: 12:30:47".
//
//        Наш бот готов. Запусти сервер, запусти бота, обычного клиента и убедись, что все работает правильно.
//        Помни, что message бывают разных типов и не всегда содержат ":"
//
//
//        Требования:
//        1. Метод clientMainLoop()класса BotSocketThread должен вызывать метод sendTextMessage() у внешнего объекта BotClient c приветственным сообщением указанном в условии задачи.
//        2. Метод clientMainLoop() класса BotSocketThread должен вызывать clientMainLoop() у объекта родительского класса (super).
//        3. Метод processIncomingMessage() должен выводить на экран полученное сообщение message.
//        4. Метод processIncomingMessage() должен отправлять ответ с помощью метода sendTextMessage() (форматирование согласно условию задачи).
//        5. Метод processIncomingMessage() не должен вызывать метод sendTextMessage() для некорректных запросов

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BotClient extends Client {

    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
         protected void processIncomingMessage(String message) {
            String userName="";
            String query="";
            String queryRes="";
            Calendar calendar = Calendar.getInstance();
            Date time;
            String template = "Информация для %s: %s";
            boolean matched = false;

            super.processIncomingMessage(message);

            Pattern pattern = Pattern.compile("(.*):\\s([а-яА-Я]*)");
            Matcher matcher = pattern.matcher(message);
            if(matcher.matches())
            {
                userName = matcher.group(1);
                query = matcher.group(2);
                time = calendar.getTime();
                switch (query)
                {
                    case "год":
                        queryRes = new SimpleDateFormat("yyyy").format(time);
                        matched = true;
                        break;

                    case "дата":
                        queryRes = new SimpleDateFormat("d.MM.YYYY").format(time);
                        matched = true;
                        break;

                    case "день":
                        queryRes = new SimpleDateFormat("d").format(time);
                        matched = true;
                        break;

                    case "месяц":
                        queryRes = new SimpleDateFormat("MMMM").format(time);
                        matched = true;
                        break;

                    case "время":
                        queryRes = new SimpleDateFormat("H:mm:ss").format(time);
                        matched = true;
                        break;

                    case "час":
                        queryRes = new SimpleDateFormat("H").format(time);
                        matched = true;
                        break;

                    case "минуты":
                        queryRes = new SimpleDateFormat("m").format(time);
                        matched = true;
                        break;

                    case "секунды":
                        queryRes = new SimpleDateFormat("s").format(time);
                        matched = true;
                        break;
                }
            }

            if(matched)
            {
                sendTextMessage(String.format(template, userName, queryRes));
                matched = false;
            }
        }
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

        //тест
//        String userName;
//        String query;
//        String queryRes = "";
//        Calendar calendar = Calendar.getInstance();
//        Date time = calendar.getTime();
//        String template = "Информация для %s: %s";
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
//        String inp = "Боб: секунды";
//        Pattern pattern = Pattern.compile("(.*):\\s([а-яА-Я]*)");
//        Matcher matcher = pattern.matcher(inp);
//        if (matcher.matches()) {
//            matcher.groupCount();
//            userName = matcher.group(1);
//            query = matcher.group(2);
//
//            switch (query)
//            {
//                case "год":
//                    queryRes = new SimpleDateFormat("yyyy").format(time);
//                    break;
//
//                case "дата":
//                    queryRes = new SimpleDateFormat("d.MM.YYYY").format(time);
//                    break;
//
//                case "день":
//                    queryRes = new SimpleDateFormat("d").format(time);
//                    break;
//
//                case "месяц":
//                    queryRes = new SimpleDateFormat("MMMM").format(time);
//                    break;
//
//                case "время":
//                    queryRes = new SimpleDateFormat("H:mm:ss").format(time);
//                    break;
//
//                case "час":
//                    queryRes = new SimpleDateFormat("H").format(time);
//                    break;
//
//                case "минуты":
//                    queryRes = new SimpleDateFormat("m").format(time);
//                    break;
//
//                case "секунды":
//                    queryRes = new SimpleDateFormat("s").format(time);
//                    break;
//
//            }
//            System.out.println(String.format(template, userName, queryRes));

//            System.out.println(time);
//            System.out.println(new SimpleDateFormat("yyyy").format(time));
//            System.out.println(userName);
//            System.out.println(query);
//        }

        //пример
//        Pattern phonePattern = Pattern.compile(".*(\\d{3})-(\\d{3})-(\\d{4}).*");
//        Matcher phoneMatcher = phonePattern.matcher("abcd800-555-1234wxyz");
//        Matcher phoneMatcher = phonePattern.matcher("800-555-1234");
//        phoneMatcher.matches();
//        phoneMatcher.groupCount();
//        System.out.println(phoneMatcher.group(0));
//        System.out.println(phoneMatcher.group(1));
//        System.out.println(phoneMatcher.group(2));
//        System.out.println(phoneMatcher.group(1));

    }


}
