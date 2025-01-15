package com.JVictor011.maquina_de_turing.dto;

import com.JVictor011.maquina_de_turing.model.Transition;

import java.util.List;

public class TuringMachineRequest {
    private List<Transition> transitions;
    private String initialState;
    private String finalState;
    private String input;
    private String bodyTransitions;

    public TuringMachineRequest() {}

    public TuringMachineRequest(List<Transition> transitions, String initialState, String finalState, String input, String bodyTransitions) {
        this.transitions = transitions;
        this.initialState = initialState;
        this.finalState = finalState;
        this.input = input;
        this.bodyTransitions = bodyTransitions;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

    public String getInitialState() {
        return initialState;
    }

    public void setInitialState(String initialState) {
        this.initialState = initialState;
    }

    public String getFinalState() {
        return finalState;
    }

    public void setFinalState(String finalState) {
        this.finalState = finalState;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getBodyTransitions() {
        return bodyTransitions;
    }

    public void setBodyTransitions(String bodyTransitions) {
        this.bodyTransitions = bodyTransitions;
    }

    @Override
    public String toString() {
        return "TuringMachineRequest{" +
                "transitions=" + transitions +
                ", initialState='" + initialState + '\'' +
                ", finalState='" + finalState + '\'' +
                ", input='" + input + '\'' +
                ", bodyTransitions='" + bodyTransitions + '\'' +
                '}';
    }
}
