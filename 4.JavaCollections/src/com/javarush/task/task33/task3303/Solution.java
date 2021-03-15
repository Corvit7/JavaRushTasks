package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.deploy.model.Resource;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Десериализация JSON объекта
*/

public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassLoader classLoader = Solution.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        String data = FileUtils.readFileToString(file, "UTF-8");
//        Path path =  Paths.get(fileName);
//        String data = Files.readAllLines(path).get(0);
        return (T) objectMapper.readValue(data, clazz);
    }

    public static void main(String[] args) {
        try {
            com.javarush.task.task33.task3302.Solution.Cat cat = convertFromJsonToNormal("inp.txt", com.javarush.task.task33.task3302.Solution.Cat.class);
            System.out.println(cat.name);
            System.out.println(cat.weight);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}


//public class Solution {
//    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
//
//        ObjectMapper mapper = new ObjectMapper();
////        return mapper.readValue(Paths.get(fileName).toFile(),clazz);
//        return  mapper.readValue(new File(fileName),clazz);
//    }
//
//    public static void main(String[] args) throws IOException {
//
//
//        Cat cat = convertFromJsonToNormal("C:\\repos\\JavaRushTasks\\inp.txt",Cat.class);
//        System.out.println(cat.over);
//
//
//    }
//
//
//    @JsonAutoDetect
//    public static class Cat{
//        public String wildAnimal;
//        public int over;
//
//        public Cat() {
//        }
//    }
//}
