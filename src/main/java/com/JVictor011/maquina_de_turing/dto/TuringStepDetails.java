package com.JVictor011.maquina_de_turing.dto;

public class TuringStepDetails {
    private int headPosition;  // Posição da cabeça da fita
    private String writeSymbol;  // Símbolo a ser gravado

    public TuringStepDetails(int headPosition, String writeSymbol) {
        this.headPosition = headPosition;
        this.writeSymbol = writeSymbol;
    }

    public int getHeadPosition() {
        return headPosition;
    }

    public void setHeadPosition(int headPosition) {
        this.headPosition = headPosition;
    }

    public String getWriteSymbol() {
        return writeSymbol;
    }

    public void setWriteSymbol(String writeSymbol) {
        this.writeSymbol = writeSymbol;
    }
}
