package org.example.model;

import org.example.view.View;
import org.example.vo.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private View view;
    private Provider[] providers;

    public Model(View view, Provider...providers) {
        if(view==null || providers==null || providers.length==0){
            throw new IllegalArgumentException();
        }
        this.view = view;
        this.providers = providers;
    }
    public void selectCity(String city)  {
        List<Vacancy> list = new ArrayList<>();
       for(Provider p:providers){
          list.addAll(p.getJavaVacancies(city));
       }
        view.update(list);

    }
}
