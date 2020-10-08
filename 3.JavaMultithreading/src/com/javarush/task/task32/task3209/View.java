package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);

    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }


//    HTML Editor (19)
//    Реализуем метод actionPerformed(ActionEvent actionEvent) у представления, этот метод наследуется от интерфейса ActionListener и будет вызваться при выборе пунктов меню, у которых наше представление указано в виде слушателя событий.
//            19.1. Получи из события команду с помощью метода getActionCommand(). Это будет обычная строка.
//    По этой строке ты можешь понять какой пункт меню создал данное событие.
//            19.2. Если это команда "Новый", вызови у контроллера метод createNewDocument().
//    В этом пункте и далее, если необходимого метода в контроллере еще нет - создай заглушки.
//            19.3. Если это команда "Открыть", вызови метод openDocument().
//            19.4. Если "Сохранить", то вызови saveDocument().
//            19.5. Если "Сохранить как..." - saveDocumentAs().
//            19.6. Если "Выход" - exit().
//            19.7. Если "О программе", то вызови метод showAbout() у представления.
//    Проверь, что заработали пункты меню Выход и О программе.
//
//
//    Требования:
//            1. Метод actionPerformed(ActionEvent actionEvent) должен получать из события команду с помощью метода getActionCommand().
//            2. Если в метод actionPerformed(ActionEvent actionEvent) передано событие с командой "Новый", метод должен вызывать у контроллера createNewDocument().
//            3. Если в метод actionPerformed(ActionEvent actionEvent) передано событие с командой "Открыть", метод должен вызывать у контроллера openDocument().
//            4. Если в метод actionPerformed(ActionEvent actionEvent) передано событие с командой "Сохранить", метод должен вызывать у контроллера saveDocument().
//            5. Если в метод actionPerformed(ActionEvent actionEvent) передано событие с командой "Сохранить как...", метод должен вызывать у контроллера saveDocumentAs().
//            6. Если в метод actionPerformed(ActionEvent actionEvent) передано событие с командой "Выход", метод должен вызывать у контроллера exit().
//            7. Если в метод actionPerformed(ActionEvent actionEvent) передано событие с командой "О программе", метод должен вызывать у представления showAbout().
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command){
            case "Новый": this.controller.createNewDocument();
            break;

            case "Открыть": this.controller.openDocument();
            break;

            case "Сохранить": this.controller.saveDocument();
            break;

            case "Сохранить как...": this.controller.saveDocumentAs();
            break;

            case "Выход": this.controller.exit();
            break;

            case "О программе": this.showAbout();
            break;
        }
    }

    public void init(){
        initGui();
        FrameListener frameListener = new FrameListener(this);
        this.addWindowListener(frameListener);
        this.setVisible(true);
    }

    public void exit()
    {
        controller.exit();
    }


    public void initMenuBar(){
        JMenuBar jMenuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, jMenuBar);
        MenuHelper.initEditMenu(this, jMenuBar);
        MenuHelper.initStyleMenu(this, jMenuBar);
        MenuHelper.initAlignMenu(this, jMenuBar);
        MenuHelper.initColorMenu(this, jMenuBar);
        MenuHelper.initFontMenu(this, jMenuBar);
        MenuHelper.initHelpMenu(this, jMenuBar);

        this.getContentPane().add(jMenuBar, BorderLayout.NORTH);
    }

    public void initEditor(){
        htmlTextPane.setContentType("text/html");
        JScrollPane jScrollPaneHTML = new JScrollPane(htmlTextPane);
        tabbedPane.addTab("HTML", jScrollPaneHTML);

        JScrollPane jScrollPanePlain = new JScrollPane(plainTextPane);
        tabbedPane.addTab("Текст", jScrollPanePlain);

        tabbedPane.setPreferredSize(new Dimension(1000, 500));

        TabbedPaneChangeListener tabbedPaneChangeListener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(tabbedPaneChangeListener);

        this.getContentPane().add(tabbedPane, BorderLayout.CENTER);

    }

    public void initGui(){
        initMenuBar();
        initEditor();
        pack();
    }

    //    HTML Editor (18)
//    Реализуй метод selectedTabChanged() представления. Этот метод вызывается, когда произошла смена выбранной вкладки. Итак:
//            18.1. Метод должен проверить, какая вкладка сейчас оказалась выбранной.
//18.2. Если выбрана вкладка с индексом 0 (html вкладка), значит нам нужно получить текст из plainTextPane и установить его в контроллер с помощью метода setPlainText.
//            18.3. Если выбрана вкладка с индексом 1 (вкладка с html текстом), то необходимо получить текст у контроллера с помощью метода getPlainText() и установить его в панель plainTextPane.
//            18.4. Сбросить правки (вызвать метод resetUndo представления).
//
//
//    Требования:
//            1. Метод selectedTabChanged() должен проверить, какая вкладка сейчас оказалась выбранной.
//            2. Если индекс вкладки равен 0 - метод selectedTabChanged() должен получить текст из plainTextPane и установить его в контроллер с помощью метода setPlainText().
//            3. Если индекс вкладки равен 1 - метод selectedTabChanged() должен получить текст у контроллера с помощью метода getPlainText() и установить его в панель plainTextPane.
//            4. Метод selectedTabChanged() должен сбросить правки.
    public void selectedTabChanged(){

        if(tabbedPane.getSelectedIndex() == 0)
            controller.setPlainText(plainTextPane.getText());
        else
            plainTextPane.setText(controller.getPlainText());
        this.resetUndo();
    }

    public boolean canUndo () {
        if (undoManager.canUndo())
            return true;
        else
            return false;
    }

    public boolean canRedo(){
        if (undoManager.canRedo())
            return true;
        else
            return false;
    }

    public void undo()
    {
        try{
            undoManager.undo();
        } catch (Exception e)
        {
            ExceptionHandler.log(e);
        }
    }

    public void redo()
    {
        try{
            undoManager.redo();
        } catch (Exception e)
        {
            ExceptionHandler.log(e);
        }
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    public void resetUndo()
    {
        undoManager.discardAllEdits();
    }

    public boolean isHtmlTabSelected()
    {
        if(tabbedPane.getSelectedIndex() == 0)
            return true;
        else
            return false;
    }

    public void selectHtmlTab(){
        this.tabbedPane.setSelectedIndex(0);
        this.resetUndo();
    }

    public void update()
    {
        htmlTextPane.setDocument(controller.getDocument());
    }

    public void showAbout()
    {
        JOptionPane.showMessageDialog(this.tabbedPane, "Some Info", "About", JOptionPane.INFORMATION_MESSAGE);

    }
}
