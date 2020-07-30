package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.exception.PathIsNotFoundException;
import com.javarush.task.task31.task3110.exception.WrongZipFileException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipFileManager {
    // Полный путь zip файла
    private final Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

    public void createZip(Path source) throws Exception {
        // Проверяем, существует ли директория, где будет создаваться архив
        // При необходимости создаем ее
        Path zipDirectory = zipFile.getParent();
        if (Files.notExists(zipDirectory))
            Files.createDirectories(zipDirectory);

        // Создаем zip поток
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile))) {

            if (Files.isDirectory(source)) {
                // Если архивируем директорию, то нужно получить список файлов в ней
                FileManager fileManager = new FileManager(source);
                List<Path> fileNames = fileManager.getFileList();

                // Добавляем каждый файл в архив
                for (Path fileName : fileNames)
                    addNewZipEntry(zipOutputStream, source, fileName);

            } else if (Files.isRegularFile(source)) {

                // Если архивируем отдельный файл, то нужно получить его директорию и имя
                addNewZipEntry(zipOutputStream, source.getParent(), source.getFileName());
            } else {

                // Если переданный source не директория и не файл, бросаем исключение
                throw new PathIsNotFoundException();
            }
        }
    }

    public List<FileProperties> getFilesList() throws Exception {
        // Проверяем существует ли zip файл
        if (!Files.isRegularFile(zipFile)) {
            throw new WrongZipFileException();
        }

        List<FileProperties> files = new ArrayList<>();

        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipFile))) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();

            while (zipEntry != null) {
                // Поля "размер" и "сжатый размер" не известны, пока элемент не будет прочитан
                // Давайте вычитаем его в какой-то выходной поток
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                copyData(zipInputStream, baos);

                FileProperties file = new FileProperties(zipEntry.getName(), zipEntry.getSize(), zipEntry.getCompressedSize(), zipEntry.getMethod());
                files.add(file);
                zipEntry = zipInputStream.getNextEntry();
            }
        }

        return files;
    }

    private void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path fileName) throws Exception {
        Path fullPath = filePath.resolve(fileName);
        try (InputStream inputStream = Files.newInputStream(fullPath)) {
            ZipEntry entry = new ZipEntry(fileName.toString());

            zipOutputStream.putNextEntry(entry);

            copyData(inputStream, zipOutputStream);

            zipOutputStream.closeEntry();
        }
    }

    private void copyData(InputStream in, OutputStream out) throws Exception {
        byte[] buffer = new byte[8 * 1024];
        int len;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
//        in.close();
//        out.close();
    }

    public void extractFile (ZipInputStream zipInputStream, Path filePath, Path fileName) throws Exception
    {
        Path fullPath = filePath.resolve(fileName);
        try (OutputStream outputStream = Files.newOutputStream(fullPath)) {
            copyData(zipInputStream, outputStream );
        }
//        zipInputStream.closeEntry();
    }

    private class Hierarchy {
        private List<Path> hierarchyList = new ArrayList<>();

        public List<Path> getHierarchyList() {
            return hierarchyList;
        }

        // 1. рекурсивно для каждого outputFolder.getParent проверяем существует ли директория.
        // 2. Если не существует, то добавляем в список dirsToCreate не существующую директорию.
        // 3. Рекурсивный вызов происходит при условии, что очередной уровень иерархии директорий не существует
        // соответственно, когда дойдем до существующего уровня, рекурсивный вызов не выполнится и поток начнет итеративно выходить из
        // стека вызовов dirsToCreate.
        // Если же корневая директория не существует, то будет брошено исключение NullPointerException.
        public void getDirectoryHierarchy(Path outputFolder) throws NullPointerException {
            if (!Files.exists(outputFolder))
            {
                hierarchyList.add(outputFolder);
                Path path = outputFolder.getParent();
                if(!Files.exists(path))
                    getDirectoryHierarchy(path);
            }
        }

        public void getFileHierarchy(Path file)
        {
            hierarchyList.add(file);
            Path path = file.getParent();
            if(path != null)
//                if(!Files.exists(path))
                getFileHierarchy(path);
        }
    }

    public void extractAll(Path outputFolder) throws Exception{
//        Метод extractAll(Path outputFolder) должен бросать исключение WrongZipFileException, если файл архива не существует.
//        if(!Files.exists(zipFile))
//            throw new WrongZipFileException();

        if(!Files.exists(zipFile)){
            throw new WrongZipFileException();
        }else{
            String namefile = zipFile.getFileName().toString();
            if(!namefile.substring(namefile.length()-3,namefile.length()).equals("zip")){
                throw new WrongZipFileException();
            }
        }

//        Path archive = outputFolder.resolve(zipFile.getFileName().toString().);
        Path archive = outputFolder;

        //создаем требуемую иерархию директорий, куда будем распаковывать архив.
        Hierarchy wrapper = new Hierarchy();
        if(!Files.exists(archive)) {
            wrapper.getDirectoryHierarchy(archive);
            for (int i = wrapper.getHierarchyList().size(); i-- > 0;) {
                Files.createDirectory(wrapper.getHierarchyList().get(i));
            }
        }

        //теперь распаковываем архив.
        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(this.zipFile))) {
            ZipEntry ze;
            while((ze = zipInputStream.getNextEntry()) != null) {
                wrapper = new Hierarchy();
                wrapper.getFileHierarchy(Paths.get(ze.getName()));
                for (int i = wrapper.getHierarchyList().size(); i-- > 0; ) {
                    Path curLevel = archive.resolve(wrapper.getHierarchyList().get(i));
                    if (i != 0) {
                        if (!Files.exists(curLevel))
                            Files.createDirectory(curLevel);
                    } else {
                        if (!Files.exists(curLevel))
                            Files.createFile(curLevel);
                        extractFile(zipInputStream, curLevel.getParent(), curLevel);
                    }
                }
            }
        }
    }

}
