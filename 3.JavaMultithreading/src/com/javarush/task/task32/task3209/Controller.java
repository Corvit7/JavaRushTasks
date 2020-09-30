package com.javarush.task.task32.task3209;

import javax.swing.text.html.HTMLDocument;
import java.io.File;

public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    public void exit()
    {
        System.exit(0);
    }


//    2.2. Теперь напишем в классе Controller метод main().
//    Он должен:
//            2.2.1. Создавать объект представления.
//2.2.2. Создавать контроллер, используя представление.
//2.2.3. Устанавливать у представления контроллер.
//            2.2.4. Инициализировать представление.
//            2.2.5. Инициализировать контроллер. Контроллер должен инициализироваться после представления.
//2.3. Добавь в контроллер метод exit(), он должен вызывать статический метод exit у класса System.
//            2.3.1. Метод exit в классе Controller не должен быть статическим.
//2.4. Добавь в представление метод exit(), он должен вызывать exit() у контроллера.
//

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
        view.showAbout();

    }

    public void init(){}

    public HTMLDocument getDocument() {
        return document;
    }
}
