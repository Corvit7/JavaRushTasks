package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.undo.UndoManager;
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

//    HTML Editor (15)
//    Добавь в контроллер метод resetDocument(), который будет сбрасывать текущий документ. Он должен:
//            15.1. Удалять у текущего документа document слушателя правок которые можно отменить/вернуть (найди подходящий для этого метод, унаследованный от AbstractDocument).
//    Слушателя нужно запросить у представления (метод getUndoListener()).
//    Не забудь проверить, что текущий документ существует (не null).
//            15.2. Создавать новый документ по умолчанию и присваивать его полю document.
//
//    Подсказка: воспользуйся подходящим методом класса HTMLEditorKit.
//
//15.3. Добавлять новому документу слушателя правок.
//15.4. Вызывать у представления метод update().
//
//
//    Требования:
//            1. Класс Controller должен содержать публичный метод resetDocument(), который будет сбрасывать текущий документ.
//            2. Метод resetDocument() должен удалять у текущего документа document слушателя правок через метод removeUndoableEditListener().
//            3. Метод resetDocument() должен создавать новый документ по умолчанию через метод createDefaultDocument().
//            4. Метод resetDocument() должен добавлять новому документу слушателя правок через метод addUndoableEditListener().
//            5. Метод resetDocument() должен вызывать у представления метод update().
    

    public void resetDocument() {
        UndoListener listener = view.getUndoListener();

        if (document != null) {
            document.removeUndoableEditListener(listener);
        }

        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        document.addUndoableEditListener(listener);
        view.update();
    }
}
