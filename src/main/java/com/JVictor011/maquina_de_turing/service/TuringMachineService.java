package com.JVictor011.maquina_de_turing.service;

import com.JVictor011.maquina_de_turing.controller.TuringMachineController;
import com.JVictor011.maquina_de_turing.dto.TuringMachineResponse;
import com.JVictor011.maquina_de_turing.model.Tape;
import com.JVictor011.maquina_de_turing.model.Transition;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.JVictor011.maquina_de_turing.dto.TuringMachineRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TuringMachineService {

    private final MessagingService messagingService;

    public TuringMachineService(MessagingService messagingService) {
        this.messagingService = messagingService;
    }

    @RabbitListener(queues = "turing-machine-queue")
    public void receiveMessage(String message) {
        messagingService.sendStepToFrontend(message);
    }

    public TuringMachineResponse runMachine(TuringMachineRequest request) {
        request.setTransitions(initializeTransitions(request.getBodyTransitions()));

        Tape tape = new Tape();
        String currentState = request.getInitialState();
        Map<String, Transition> transitionTable = buildTransitionTable(request.getTransitions());

        initializeTape(tape, request.getInput());

        while (true) {
            char readSymbol = tape.read();
            String key = currentState + "_" + readSymbol;

            if (!transitionTable.containsKey(key)) {
                break;
            }

            Transition transition = transitionTable.get(key);
            tape.write(transition.getWriteSymbol());
            tape.moveHead(transition.getDirectionAsInt());
            currentState = transition.getNextState();
        }

        return new TuringMachineResponse(tape.toString(), currentState, isFinalState(currentState));
    }

    private List<Transition> initializeTransitions(String bodyTransitions) {
        return Arrays.stream(bodyTransitions.split("\n"))
                .map(line -> line.split(","))
                .filter(parts -> parts.length == 5)
                .map(parts -> new Transition(
                        parts[0].trim(),
                        parts[1].trim().charAt(0),
                        parts[2].trim(),
                        parts[3].trim().charAt(0),
                        parts[4].trim()
                ))
                .collect(Collectors.toList());
    }

    private void initializeTape(Tape tape, String input) {
        for (char c : input.toCharArray()) {
            tape.write(c);
            tape.moveHead(1);
        }
        tape.moveHead(-input.length());
    }

    private Map<String, Transition> buildTransitionTable(List<Transition> transitions) {
        Map<String, Transition> table = new HashMap<>();
        for (Transition t : transitions) {
            String key = t.getCurrentState() + "_" + t.getReadSymbol();
            table.put(key, t);
        }
        return table;
    }

    private boolean isFinalState(String state) {
        return state.equals("qf");
    }
}