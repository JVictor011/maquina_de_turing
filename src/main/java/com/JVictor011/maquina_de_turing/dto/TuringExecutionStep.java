package com.JVictor011.maquina_de_turing.dto;

public class TuringExecutionStep {
    private String currentState;
    private char readSymbol;
    private String tapeContent;
    private String nextTransition;

    public TuringExecutionStep(String currentState, char readSymbol, String tapeContent, String nextTransition) {
        this.currentState = currentState;
        this.readSymbol = readSymbol;
        this.tapeContent = tapeContent;
        this.nextTransition = nextTransition;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public char getReadSymbol() {
        return readSymbol;
    }

    public void setReadSymbol(char readSymbol) {
        this.readSymbol = readSymbol;
    }

    public String getTapeContent() {
        return tapeContent;
    }

    public void setTapeContent(String tapeContent) {
        this.tapeContent = tapeContent;
    }

    public String getNextTransition() {
        return nextTransition;
    }

    public void setNextTransition(String nextTransition) {
        this.nextTransition = nextTransition;
    }

    @Override
    public String toString() {
        return "TuringExecutionStep{" +
                "currentState='" + currentState + '\'' +
                ", readSymbol=" + readSymbol +
                ", tapeContent='" + tapeContent + '\'' +
                ", nextTransition='" + nextTransition + '\'' +
                '}';
    }
}
