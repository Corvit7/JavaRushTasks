package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HHStrategy implements Strategy{
    private static final String URL_FORMAT = "https://hh.ru/search/vacancy?text=java+%s&page=%d";
//    private static final String URL_FORMAT = "https://javarush.ru/testdata/big28data.html";

    @Override
    public List<Vacancy> getVacancies(String searchString)  {
        Document doc;
        List<Vacancy> vacancies = new ArrayList<>();
        int i =0;
        try {
            while ((doc = getDocument(searchString,i++)) != null)
//            doc = Jsoup.connect("https://javarush.ru/testdata/big28data.html")
//                    .userAgent("Chrome/91.0.4472.77")
//                    .get();
            {
                Elements data_qa =  doc.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
                for (Element el: data_qa
                     ) {
                    Vacancy vacancy = new Vacancy();
                    vacancy.setCity(el.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").text());
                    vacancy.setCompanyName(el.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text());
                    vacancy.setTitle(el.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").text());
                    vacancy.setSiteName("hh.ru");
                    vacancy.setUrl(el.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").attr("href"));
                    vacancy.setSalary(el.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").text());
                    vacancies.add(vacancy);
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException
    {
//        if(page > 0)
//            return null;
        Document document = Jsoup.connect(String.format(URL_FORMAT, searchString, page))
//                .userAgent("Chrome/91.0.4472.77")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")
                .referrer("https://hh.ru/")
                .get();
        return document;
    }
}
