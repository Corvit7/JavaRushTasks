package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.text.*;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.undo.UndoManager;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

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
    public void saveDocumentAs() {}

}
