package com.JVictor011.maquina_de_turing.model;

import java.util.HashMap;
import java.util.Map;

public class Tape {
    private Map<Integer, Character> tape = new HashMap<>();
    private int headPosition = 0;

    public Tape() {}

    public Tape(Map<Integer, Character> tape, int headPosition) {
        this.tape = tape;
        this.headPosition = headPosition;
    }

    public Map<Integer, Character> getTape() {
        return tape;
    }

    public void setTape(Map<Integer, Character> tape) {
        this.tape = tape;
    }

    public int getHeadPosition() {
        return headPosition;
    }

    public void setHeadPosition(int headPosition) {
        this.headPosition = headPosition;
    }

    public void write(char symbol) {
        tape.put(headPosition, symbol);
    }

    public char read() {
        return tape.getOrDefault(headPosition, '_');
    }

    public void moveHead(int direction) {
        headPosition += direction;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = -5; i <= headPosition + 5; i++) {
            sb.append(tape.getOrDefault(i, '_'));
        }
        return sb.toString();
    }
}
