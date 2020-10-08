package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.undo.UndoManager;
import java.io.*;

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

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
        //HTMLFileFilter htmlFileFilter = new HTMLFileFilter();
        //System.out.println(htmlFileFilter.accept(new File("E:\\Games\\test\\Online regex tester and debugger_ PHP, PCRE, Python, Golang and JavaScript.htM")));
    }

    public void init(){
        this.createNewDocument();
    }

    public HTMLDocument getDocument() {
        return document;
    }

    public void resetDocument() {
        UndoListener listener = view.getUndoListener();

        if (document != null) {
            document.removeUndoableEditListener(listener);
        }

        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        document.addUndoableEditListener(listener);
        view.update();
    }

    public void setPlainText(String text) {
        this.resetDocument();
        StringReader stringReader = new StringReader(text);
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        try {
            htmlEditorKit.read(stringReader, this.document, 0);
        } catch (IOException | BadLocationException e)
        {
            ExceptionHandler.log(e);
        }
    }

    public String getPlainText()
    {
        StringWriter stringWriter = new StringWriter();
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        try {
            htmlEditorKit.write(stringWriter, this.document, 0, document.getLength());
        } catch (IOException | BadLocationException e)
        {
            ExceptionHandler.log(e);
        }
        return stringWriter.toString();
    }

//    HTML Editor (20)
//20.1. Реализуй метод создания нового документа createNewDocument() в контроллере. Он должен:
//            20.1.1. Выбирать html вкладку у представления.
//20.1.2. Сбрасывать текущий документ.
//20.1.3. Устанавливать новый заголовок окна, например: "HTML редактор". Воспользуйся методом setTitle(), который унаследован в нашем представлении.
//            20.1.4. Сбрасывать правки в Undo менеджере. Используй метод resetUndo представления.
//            20.1.5. Обнулить переменную currentFile.
//20.2. Реализуй метод инициализации init() контроллера.
//    Он должен просто вызывать метод создания нового документа.
//    Проверь работу пункта меню Новый.
//
//
//            Требования:
//            1. Метод createNewDocument() в контроллере должен выбирать html вкладку у представления.
//            2. Метод createNewDocument() в контроллере должен сбрасывать текущий документ.
//            3. Метод createNewDocument() в контроллере должен устанавливать новый заголовок окна.
//            4. Метод createNewDocument() в контроллере должен сбрасывать правки в Undo менеджере.
//            5. Метод createNewDocument() в контроллере должен обнулить currentFile.
//            6. Метод init() в контроллере должен вызывать метод создания нового документа.
    public void createNewDocument()
    {
        this.view.selectHtmlTab();
        this.resetDocument();
        this.view.setTitle("HTML редактор");
        this.view.resetUndo();
        this.currentFile = null;
    }
    public void openDocument() {}
    public void saveDocument() {}

//    HTML Editor (22)
//    Реализуем в контроллере метод для сохранения файла под новым именем saveDocumentAs().
//    Реализация должна:
//            22.1. Переключать представление на html вкладку.
//22.2. Создавать новый объект для выбора файла JFileChooser.
//22.3. Устанавливать ему в качестве фильтра объект HTMLFileFilter.
//22.4. Показывать диалоговое окно "Save File" для выбора файла.
//22.5. Если пользователь подтвердит выбор файла:
//            22.5.1. Сохранять выбранный файл в поле currentFile.
//            22.5.2. Устанавливать имя файла в качестве заголовка окна представления.
//            22.5.3. Создавать FileWriter на базе currentFile.
//22.5.4. Переписывать данные из документа document в объекта FileWriter-а аналогично тому, как мы это делали в методе getPlainText().
//            22.6. Метод не должен кидать исключения.
//    Проверь работу пункта меню Сохранить как...
//
//
//    Требования:
//            1. Метод saveDocumentAs() в контроллере должен переключать представление на html вкладку.
//            2. Метод saveDocumentAs() в контроллере должен создавать новый объект для выбора файла JFileChooser.
//            3. Метод saveDocumentAs() в контроллере должен устанавливать объекту класса JFileChooser в качестве фильтра объект HTMLFileFilter.
//            4. Метод saveDocumentAs() в контроллере должен, используя метод showSaveDialog() у JFileChooser показывать диалоговое окно "Save File" для выбора файла.
//5. Метод saveDocumentAs() в контроллере должен сохранять выбранный файл в поле currentFile, если пользователь подтвердит выбор файла.
//            6. Метод saveDocumentAs() в контроллере должен устанавливать имя файла в качестве заголовка окна представления, если пользователь подтвердит выбор файла.
//            7. Метод saveDocumentAs() в контроллере должен создавать FileWriter на базе currentFile, если пользователь подтвердит выбор файла.
//            8. Метод saveDocumentAs() в контроллере должен используя HTMLEditorKit переписывать данные из документа document в объект FileWriter-а, если пользователь подтвердит выбор файла.
//9. Метод saveDocumentAs() в контроллере не должен кидать исключения, а логировать через ExceptionHandler.

public void saveDocumentAs() {
    view.selectHtmlTab();
    JFileChooser jFileChooser = new JFileChooser();
    jFileChooser.setFileFilter(new HTMLFileFilter());
    jFileChooser.setDialogTitle("Save File");
    int index = jFileChooser.showSaveDialog(view);
    if (index == JFileChooser.APPROVE_OPTION) {
        currentFile = jFileChooser.getSelectedFile();
        view.setTitle(currentFile.getName());
        try {
            FileWriter fileWriter = new FileWriter(currentFile);
            new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
            fileWriter.close();
//            JOptionPane.showMessageDialog(view,
//                    "Файл '" + jFileChooser.getSelectedFile() +
//                            " сохранен");

        } catch (IOException | BadLocationException e) {
            ExceptionHandler.log(e);
        }
    }
}

}
