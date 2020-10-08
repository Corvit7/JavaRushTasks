package com.javarush.task.task32.task3209;



import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
//        String literal = ".*(html|htm)";
//        Pattern pattern = Pattern.compile(literal, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(f.getName());
//        if(matcher.find() || f.isDirectory())
//            return true;
//        else
//            return false;
        return f.isDirectory() || f.getName().toLowerCase().endsWith(".htm") || f.getName().toLowerCase().endsWith(".html");
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }

}
