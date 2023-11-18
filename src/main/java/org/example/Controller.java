package org.example;

import org.example.model.Provider;

import java.util.Arrays;

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
}
