package com.JVictor011.maquina_de_turing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class TuringMachineResponse {
    private String tapeContent;
    private String currentState;
    private boolean isAccepted;
    private List<TuringExecutionStep> turingExecutionSteps;

    public TuringMachineResponse(){}

    public TuringMachineResponse(String tapeContent, String currentState, boolean isAccepted){
        this.tapeContent = tapeContent;
        this.currentState = currentState;
        this.isAccepted = isAccepted;
    }

    public TuringMachineResponse(List<TuringExecutionStep> turingExecutionSteps, String tapeContent, String currentState, boolean isAccepted) {
        this.turingExecutionSteps = turingExecutionSteps;
        this.tapeContent = tapeContent;
        this.currentState = currentState;
        this.isAccepted = isAccepted;
    }

    public List<TuringExecutionStep> getTuringExecutionSteps() {
        return turingExecutionSteps;
    }

    public void setTuringExecutionSteps(List<TuringExecutionStep> turingExecutionSteps) {
        this.turingExecutionSteps = turingExecutionSteps;
    }

    public String getTapeContent() {
        return tapeContent;
    }

    public void setTapeContent(String tapeContent) {
        this.tapeContent = tapeContent;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    @Override
    public String toString() {
        return "TuringMachineResponse{" +
                "tapeContent='" + tapeContent + '\'' +
                ", currentState='" + currentState + '\'' +
                ", isAccepted=" + isAccepted +
                '}';
    }
}
