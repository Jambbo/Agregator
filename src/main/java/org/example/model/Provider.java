package org.example.model;

import org.example.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class Provider {
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }
    List<Vacancy> getJavaVacancies(String searchString){
        return null;
    }
}
