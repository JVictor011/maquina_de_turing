package com.JVictor011.maquina_de_turing.service;

import com.JVictor011.maquina_de_turing.controller.TuringMachineController;
import com.JVictor011.maquina_de_turing.dto.*;
import com.JVictor011.maquina_de_turing.model.Tape;
import com.JVictor011.maquina_de_turing.model.Transition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TuringMachineService {

    private static final Logger logger = LoggerFactory.getLogger(TuringMachineService.class);

    private final WebSocketService webSocketService;

    @Autowired
    public TuringMachineService(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    @RabbitListener(queues = "turing-machine-queue", containerFactory = "rabbitListenerContainerFactory")
    public void receiveMessage(TuringMessage message) {
        try {
            if (message != null && message.getStepDetails() != null) {
                webSocketService.sendMessageToFrontend("/topic/turing", message.getStepDetails());
            } else {
                webSocketService.sendMessageToFrontend("/topic/turing", new TuringStepDetails(0, ""));
            }
        } catch (Exception e) {
            logger.error("Erro ao processar a mensagem", e);
        }
    }


    public TuringMachineResponse runMachine(TuringMachineRequest request) {
        request.setTransitions(initializeTransitions(request.getBodyTransitions()));

        System.out.println("Transições: "+ request.getTransitions());

        Tape tape = new Tape();
        String currentState = request.getInitialState();
        Map<String, Transition> transitionTable = buildTransitionTable(request.getTransitions());

        initializeTape(tape, request.getInput());

        List<TuringExecutionStep> executionSteps = new ArrayList<>();

        while (true) {
            char readSymbol = tape.read() == '_' ? ' ' : tape.read();

            String key = currentState + "_" + readSymbol;

            if (!transitionTable.containsKey(key)) {
                break;
            }

            Transition transition = transitionTable.get(key);

            TuringStepDetails stepDetails = new TuringStepDetails(tape.getHeadPosition(), String.valueOf(transition.getWriteSymbol()));

            webSocketService.sendMessageToFrontend("/topic/turing", stepDetails);

            tape.write(transition.getWriteSymbol());
            tape.moveHead(transition.getDirectionAsInt());
            currentState = transition.getNextState();

            executionSteps.add(new TuringExecutionStep(
                    currentState,
                    readSymbol,
                    tape.toString(),
                    transition.toString()
            ));

            System.out.println("Estado atual: " + currentState);
            System.out.println("Símbolo lido: " + readSymbol);
            System.out.println("Conteúdo da fita: " + tape.toString());
            System.out.println("Próxima transição: " + transition);
        }

        return new TuringMachineResponse(executionSteps, tape.toString(), currentState, isFinalState(currentState));
    }

    private List<Transition> initializeTransitions(String bodyTransitions) {
        if (bodyTransitions == null || bodyTransitions.isEmpty()) {
            throw new IllegalArgumentException("A string de transições não pode ser vazia");
        }

        return Arrays.stream(bodyTransitions.split("\n"))
                .map(line -> line.split(","))
                .filter(parts -> parts.length == 5)
                .map(parts -> {
                    try {
                        String currentState = parts[0].trim();
                        char readSymbol = parts[1].trim().equals("_") ? ' ' : parts[1].trim().charAt(0); // Tratar "_" como espaço
                        String nextState = parts[2].trim();
                        char writeSymbol = parts[3].trim().equals("_") ? ' ' : parts[3].trim().charAt(0); // Tratar "_" como espaço
                        String direction = parts[4].trim();

                        return new Transition(
                                currentState,
                                readSymbol,
                                nextState,
                                writeSymbol,
                                direction
                        );
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Erro ao processar a linha: " + Arrays.toString(parts), e);
                    }
                })
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