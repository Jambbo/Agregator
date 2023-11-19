package org.example;

import org.example.model.HHStrategy;
import org.example.model.HabrCareerStrategy;
import org.example.model.Model;
import org.example.model.Provider;
import org.example.view.HtmlView;
import org.example.view.View;

public class Aggregator {
    public static void main(String[] args) {
        HtmlView view = new HtmlView();
        Model model = new Model(view, new Provider(new HHStrategy()), new Provider(new HabrCareerStrategy()));
        Controller controller = new Controller(model);
        view.setController(controller);
        view.userCitySelectEmulationMethod();
    }
}
