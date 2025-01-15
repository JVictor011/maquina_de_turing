package com.JVictor011.maquina_de_turing.model;

public class Transition {
    private String currentState;
    private char readSymbol;
    private String nextState;
    private char writeSymbol;
    private String direction;

    public Transition() {}

    public Transition(String currentState, char readSymbol, String nextState, char writeSymbol, String direction) {
        this.currentState = currentState;
        this.readSymbol = readSymbol;
        this.nextState = nextState;
        this.writeSymbol = writeSymbol;
        this.direction = direction;
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

    public String getNextState() {
        return nextState;
    }

    public void setNextState(String nextState) {
        this.nextState = nextState;
    }

    public char getWriteSymbol() {
        return writeSymbol;
    }

    public void setWriteSymbol(char writeSymbol) {
        this.writeSymbol = writeSymbol;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getDirectionAsInt() {
        return ">".equals(direction) ? 1 : "<".equals(direction) ? -1 : 0;
    }

    @Override
    public String toString() {
        return "Transition{" +
                "currentState='" + currentState + '\'' +
                ", readSymbol=" + readSymbol +
                ", nextState='" + nextState + '\'' +
                ", writeSymbol=" + writeSymbol +
                ", direction='" + direction + '\'' +
                '}';
    }
}
