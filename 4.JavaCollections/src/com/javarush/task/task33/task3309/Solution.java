package com.javarush.task.task33.task3309;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/* 
Комментарий внутри xml
*/

public class Solution {

    public static String toXmlWithComment(Object obj, String tagName, String comment) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(obj, writer);
        String text = writer.toString();
        List<String> list = new ArrayList<>();

        //if(text.contains(tagName)) {
            String[] allText = text.split("\n");

            for (int i = 0; i < allText.length; i++) {
                String allTextTrimmed = allText[i].trim();
                if(allTextTrimmed.startsWith("<" + tagName + ">")){
                    list.add("<!--" + comment + "-->");
                    list.add(allText[i]);
                } else list.add(allText[i]);
            }
        //}

        String xml = "";
        for (int i = 0; i < list.size(); i++) {
            if(i != list.size() - 1){
                xml += list.get(i) + "\n";
            } else xml += list.get(i);
        }

        return xml;
    }

    public static void main(String[] args) throws JAXBException {
        System.out.println(Solution.toXmlWithComment(new First(), "second", "it's a comment"));
    }


    @XmlRootElement(name = "first")
    public static class First {
        @XmlElement(name = "second")
        public String item1 = "some string";
        @XmlElement(name = "second")
        public String item2 = "need CDATA because of <second>";
        @XmlElement(name = "second")
        public String item3 = "";
        @XmlElement(name = "third")
        public String item4 = "second";
        @XmlElement(name = "second")
        public String item5 = "![CDATA[need CDATA because of \"";
    }
}

