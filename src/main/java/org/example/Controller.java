package org.example;

import org.example.model.Provider;
import org.example.vo.Vacancy;

import java.util.*;

public class Controller {
    Provider[] providers;
    public Controller(Provider...providers) {
        if(providers.length==0) throw new IllegalArgumentException();
        this.providers=providers;
    }

    @Override
    public String toString() {
        return "Controller{" +
                "providers=" + Arrays.toString(providers) +
                '}';
    }
    public void scan(){
        List<Vacancy> vacancies = new ArrayList<>();
        for(Provider provider: providers){
            vacancies.addAll(provider.getJavaVacancies("MyTown"));
        }
        System.out.println(vacancies.size());
    }
}
