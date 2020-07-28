package com.javarush.task.task31.task3110;



import com.javarush.task.task31.task3110.exception.WrongZipFileException;
import java.io.IOException;

public class Archiver {

    public static Operation askOperation() throws IOException {
        Operation operation = null;

        ConsoleHelper.writeMessage("Выберите операцию:");
        System.out.println(Operation.CREATE.ordinal() + " - упаковать файлы в архив" );
        System.out.println(Operation.ADD.ordinal() + " - добавить файл в архив" );
        System.out.println(Operation.REMOVE.ordinal() + " - удалить файл в архив" );
        System.out.println(Operation.EXTRACT.ordinal() + " - распаковать архив" );
        System.out.println(Operation.CONTENT.ordinal() + " - просмотреть содержимое архива" );
        System.out.println(Operation.EXIT.ordinal() + " - выход" );
        switch (ConsoleHelper.readInt())
        {
            case 0: operation = Operation.CREATE;
            break;

            case 1: operation = Operation.ADD;
            break;

            case 2: operation = Operation.REMOVE;
            break;

            case 3: operation = Operation.EXTRACT;
            break;

            case 4: operation = Operation.CONTENT;
            break;

            case 5: operation = Operation.EXIT;
            break;

            default: throw new IOException();
        }

        return operation;
    }


//    2. Перепиши метод main():
//    2.1. Объяви локальную переменную типа Operation
//    2.2. В цикле запрашивай новое значение для переменной п.2.1. с помощью метода askOperation() и вызывай выполнение операции с помощью CommandExecutor.execute()
//    2.3. Обеспечь выход из цикла, если пользователь выбрал операцию Operation.EXIT
//    2.4. Оберни вызов askOperation() и execute(operation) в блок try-catch.
//    Если произойдет исключение WrongZipFileException выведи сообщение "Вы не выбрали файл архива или выбрали неверный файл." с помощью ConsoleHelper, при любых других исключениях выводи "Произошла ошибка. Проверьте введенные данные.".
//    2.5. Проследи, чтобы программа продолжила свою работу (перешла на новый шаг цикла), после обработки исключений.
//    3. Запусти программу и проверь, что команда "выход" работает.
//


    public static void main(String[] args)  {

        boolean isExit=false;
        Operation operation = null;

        while (!isExit){
            try {operation=askOperation();
                if(operation.ordinal()==Operation.EXIT.ordinal()){isExit=true;}
                CommandExecutor.execute(operation);

            }catch (WrongZipFileException e){
                ConsoleHelper.writeMessage("Вы не выбрали файл архива или выбрали неверный файл.");
            }
            catch (Exception e){
                ConsoleHelper.writeMessage("Произошла ошибка. Проверьте введенные данные.");
            }

        }

//        Operation operation = null;
//        boolean exitFlag = false;
//        do
//        {
//            try
//            {
//                operation = Archiver.askOperation();
//                CommandExecutor.execute(operation);
//            } catch (WrongZipFileException e)
//            {
//                ConsoleHelper.writeMessage("Вы не выбрали файл архива или выбрали неверный файл.");
//            }
//            catch (Exception e)
//            {
//                ConsoleHelper.writeMessage("Произошла ошибка. Проверьте введенные данные.");
//            }
//        } while (!operation.equals(Operation.EXIT));

//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Введите путь к архиву:");
//        try{
//            String stringPath;
//            Path path;
//            stringPath = bufferedReader.readLine();
//            path= Paths.get(stringPath);
////            path = Paths.get("E:\\фото\\test_arch");
//            ZipFileManager zipFileManager= new ZipFileManager(path);
//            System.out.println("Введите путь к архивируемому файлу:");
//            stringPath = bufferedReader.readLine();
//            path = Paths.get(stringPath);
////            path = Paths.get("E:\\фото\\test_file.txt");
//            zipFileManager.createZip(path);
//        } catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }

//        ExitCommand exitCommand = new ExitCommand();
//        try
//        {
//            exitCommand.execute();
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//        }
    }
}
