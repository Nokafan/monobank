package com.example.monobank.controllers;

import com.example.monobank.service.ProcessSeviceEmulator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessController {
    private final ProcessSeviceEmulator processSeviceEmulator;

    public ProcessController(ProcessSeviceEmulator processSeviceEmulator) {
        this.processSeviceEmulator = processSeviceEmulator;
    }

    @GetMapping("/start")
    public String start() throws InterruptedException {
        processSeviceEmulator.startProcessing();
        return "Start";
    }
}
