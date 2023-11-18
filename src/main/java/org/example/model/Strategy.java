package org.example.model;

import org.example.vo.Vacancy;

import java.util.*;

public interface Strategy {
    List<Vacancy> getVacancies(String searchString);
}
