package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.view.View;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private View view;
    private Provider[] providers;

    public Model(View view, Provider... providers) throws IllegalArgumentException{
        if (view == null || providers == null || providers.length == 0) throw new IllegalArgumentException();
        this.view = view;
        this.providers = providers;
    }

    public void selectCity(String city) {
//        for (Provider provider: providers
//             ) {
//            this.view.update(provider.getJavaVacancies(city));
//        }
        List<Vacancy> vacancyList = new ArrayList<>();
        for (Provider provider : providers)
            vacancyList.addAll(provider.getJavaVacancies(city));
        view.update(vacancyList);
    }
}
