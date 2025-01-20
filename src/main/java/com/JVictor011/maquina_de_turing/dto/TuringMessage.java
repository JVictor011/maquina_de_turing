package com.JVictor011.maquina_de_turing.dto;

public class TuringMessage {
    private TuringStepDetails stepDetails;

    public TuringMessage() {}

    public TuringMessage(TuringStepDetails stepDetails) {
        this.stepDetails = stepDetails;
    }

    public TuringStepDetails getStepDetails() {
        return stepDetails;
    }

    public void setStepDetails(TuringStepDetails stepDetails) {
        this.stepDetails = stepDetails;
    }

    @Override
    public String toString() {
        return "TuringMessage{" +
                "stepDetails=" + stepDetails +
                '}';
    }
}
