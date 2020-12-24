package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {

    private String partOfName;
    private String partOfContent;
    private int minSize;
    private int maxSize;

    private List<Path> foundFiles = new LinkedList<>();

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

//    @Override
//    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//        byte[] content = Files.readAllBytes(file); // размер файла: content.length
//        String name = file.getFileName().toString();
//
//        if(partOfName == null)
//            partOfName = "";
//        if(partOfContent == null)
//            partOfContent = "";
//        if(content.length > minSize && content.length < maxSize && name.contains(partOfName)) {
//            try{
//                List<String> contents = Files.readAllLines(file);
//                //Read from the stream
//                for(String line:contents){//for each line of content in contents
//                    if(line.contains(partOfContent))
//                    {foundFiles.add(file);
//                    break;}
//                }
//
//            }catch(IOException ex){
//                ex.printStackTrace();//handle exception here
//            }
//        }
//
//        return super.visitFile(file, attrs);
//    }

        @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length
        String name = file.getFileName().toString();

            boolean isFiltered = true;

            if (attrs.isDirectory())
                return FileVisitResult.CONTINUE;

            if (partOfName != null)
                isFiltered = file.getFileName().toString().contains(partOfName);
            if (partOfContent != null) {
                if (!new String(Files.readAllBytes(file)).contains(partOfContent))
                    isFiltered = false;
            }
            if (minSize != 0 && content.length < minSize)
                isFiltered = false;
            if (maxSize != 0 && content.length > maxSize)
                isFiltered = false;

            if (isFiltered)
                foundFiles.add(file);

        return super.visitFile(file, attrs);
    }
}
