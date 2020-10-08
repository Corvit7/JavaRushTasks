package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.undo.UndoManager;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

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
        view.showAbout();

    }

    public void init(){}

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
}
