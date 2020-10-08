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

    public String getPlainText()
    {
        StringWriter stringWriter = new StringWriter();
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
//        ElementIterator iterator = new ElementIterator(this.document);
//        Element element;
//        while((element = iterator.next()) != null)
//        {
//            AttributeSet attributes = element.getAttributes();
//            Object name = attributes.getAttribute(StyleConstants.NameAttribute);
//            if((name instanceof HTML.Tag)
//                    && ((name == HTML.Tag.H1) || (name == HTML.Tag.H2) || (name == HTML.Tag.H3))) {
////                StringBuffer text = new StringBuffer();
//                int count = element.getElementCount();
//                for (int i = 0; i < count; i++) {
//                    Element child = element.getElement(i);
//                    AttributeSet childAttributes = child.getAttributes();
//                    if(childAttributes.getAttribute(StyleConstants.NameAttribute) == HTML.Tag.CONTENT) {
//                        int startOffset = child.getStartOffset();
//                        int endOffset = child.getEndOffset();
//                        int length = endOffset - startOffset;
//                        try{
//                            htmlEditorKit.write(stringWriter);
////                            text.append(document.getText(startOffset,length));
//                        } catch (BadLocationException e)
//                        {
//                            ExceptionHandler.log(e);
//                        }
//                    }
//                }
//            }
//        }
        try {
            //this.document.getRootElements();
            htmlEditorKit.write(stringWriter, this.document, 0, document.getLength());
            //htmlEditorKit.write(stringWriter, this.document, 0, text.length());
        } catch (IOException | BadLocationException e)
        {
            ExceptionHandler.log(e);
        }
        return stringWriter.toString();
    }
}
