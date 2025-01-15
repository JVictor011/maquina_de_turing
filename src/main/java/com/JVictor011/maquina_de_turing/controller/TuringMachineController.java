package com.JVictor011.maquina_de_turing.controller;

import com.JVictor011.maquina_de_turing.dto.TuringMachineRequest;
import com.JVictor011.maquina_de_turing.dto.TuringMachineResponse;
import com.JVictor011.maquina_de_turing.service.TuringMachineService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class TuringMachineController {
    private final SimpMessagingTemplate messagingTemplate;
    private final TuringMachineService service;

    public TuringMachineController(SimpMessagingTemplate messagingTemplate, TuringMachineService service) {
        this.messagingTemplate = messagingTemplate;
        this.service = service;
    }

    @PostMapping("/run")
    public ResponseEntity<TuringMachineResponse> runMachine(@RequestBody TuringMachineRequest request) {
        TuringMachineResponse response = service.runMachine(request);
        return ResponseEntity.ok(response);
    }
}