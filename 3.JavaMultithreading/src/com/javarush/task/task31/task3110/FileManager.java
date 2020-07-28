package com.javarush.task.task31.task3110;

        import java.io.IOException;
        import java.nio.file.DirectoryStream;
        import java.nio.file.Files;
        import java.nio.file.Path;
//        import java.nio.file.Paths;
//        import java.util.Iterator;
        import java.util.LinkedList;
        import java.util.List;

public class FileManager {

    private Path rootPath;

    public List<Path> getFileList() {
        return fileList;
    }

    private List<Path> fileList;

    public FileManager(Path rootPath) throws IOException{
        this.rootPath=rootPath;
        fileList = new LinkedList<>();
        collectFileList(rootPath);
    }

    private void collectFileList(Path path) throws IOException {
        if (Files.isRegularFile(path))
            fileList.add(rootPath.relativize(path));
        else if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
                for (Path p : stream) {
                    collectFileList(p);
                }
            }
        }
    }

//    для тестирования collectFileList
//    public static void main(String[] args) throws IOException {
//        Path rootPath = Paths.get("E:\\");
//        Path path = Paths.get("E:\\фото\\");
//        FileManager fileManager = new FileManager(rootPath);
//        fileManager.collectFileList(path);
//
//        for (Iterator<Path> it = fileManager.getFileList().iterator(); it.hasNext();) {
//            System.out.println(it.next());
//        }
//    }
}