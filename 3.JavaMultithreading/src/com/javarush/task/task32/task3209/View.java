package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class View extends JFrame implements ActionListener {
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();

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

    @Override
    public void actionPerformed(ActionEvent e) {

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


//    HTML Editor (9)
//    9.1. Реализуй метод initMenuBar(). Он должен:
//    9.1.1. Создавать новый объект типа JMenuBar. Это и будет наша панель меню.
//    9.1.2. С помощью MenuHelper инициализировать меню в следующем порядке: Файл, Редактировать, Стиль, Выравнивание, Цвет, Шрифт и Помощь.
//    9.1.3. Добавлять в верхнюю часть панели контента текущего фрейма нашу панель меню, аналогично тому, как это мы делали с панелью вкладок.
//    9.2. Добавь конструктор класса View. Он должен устанавливать внешний вид и поведение (look and feel) нашего приложения такими же, как это определено в системе.
//    Конструктор не должен кидать исключений, только логировать их с помощью ExceptionHandler.
//
//    Подсказа: для реализации задания используй класс UIManager.
//
//    Запусти приложение, теперь ты должен видеть панель с меню вверху окна. Некоторые из пунктов меню (например: Вырезать, Копировать, Вставить, Стиль (частично), Выравнивание, Цвет, Шрифт) должны уже работать. Убедись, что все работает и только затем продолжи разработку.
//
//
//    Требования:
//            1. В методе initMenuBar() должно создаваться новое меню (объект типа JMenuBar).
//            2. В методе initMenuBar() c помощью MenuHelper должно быть проинициализировано меню в следующем порядке: Файл, Редактировать, Стиль, Выравнивание, Цвет, Шрифт и Помощь.
//            3. В методе initMenuBar() должно добавляться новосозданное меню в верхнюю часть панели контента текущего фрейма, используя метод getContentPane().
//            4. В конструкторе класса View

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

//    Реализуй метод инициализации панелей редактора initEditor(). Он должен:
//            6.1. Устанавливать значение "text/html" в качестве типа контента для компонента htmlTextPane.
//    Найди и используй подходящий метод.
//6.2. Создавать новый локальный компонент JScrollPane на базе htmlTextPane.
//            6.3. Добавлять вкладку в панель tabbedPane с именем "HTML" и компонентом из предыдущего пункта.
//6.4. Создавать новый локальный компонент JScrollPane на базе plainTextPane.
//            6.5. Добавлять еще одну вкладку в tabbedPane с именем "Текст" и компонентом из предыдущего пункта.
//6.6. Устанавливать предпочтительный размер панели tabbedPane.
//6.7. Создавать объект класса TabbedPaneChangeListener и устанавливать его в качестве слушателя изменений в tabbedPane.
//6.8. Добавлять по центру панели контента текущего фрейма нашу панель с вкладками.
//    Получить панель контента текущего фрейма можно с помощью метода getContentPane(), его реализация унаследовалась от JFrame.
//    Подумай, метод с какими параметрами необходимо вызвать, чтобы панель с вкладками отображалась по центру панели контента текущего фрейма.
//    После запуска приложения можно будет увидеть текущие результаты: две независимые закладки (HTML и Текст), в каждой из которых можно набирать свой текст.
//
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

    public void selectedTabChanged(){}

    public boolean canUndo () {
        return false;
    }

    public boolean canRedo(){
        return false;
    }
}
