package com.JVictor011.maquina_de_turing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class State {
    private String name;
    private boolean isFinal;

    public State(boolean isFinal, String name) {
        this.isFinal = isFinal;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                ", isFinal=" + isFinal +
                '}';
    }
}
