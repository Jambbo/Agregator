package org.example;

import org.example.model.Model;
import org.example.model.Provider;
import org.example.vo.Vacancy;

import java.io.IOException;
import java.util.*;

public class Controller {
   private Model model;
    public Controller(Model model){
        this.model=model;
    }
    public void onCitySelect(String cityName)  {
        model.selectCity(cityName);
    }
}
