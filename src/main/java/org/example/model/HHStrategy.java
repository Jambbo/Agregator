package org.example.model;

import org.example.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy{
    private static final String URL_FORMAT  = "https://hh.ru/search/vacancy?text=Java+%s&area=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        try{
            Document document = Jsoup.connect(String.format(URL_FORMAT,"MyTown",0)).get();
        }catch(IOException e){

        }
        return new ArrayList<>();
    }

}
