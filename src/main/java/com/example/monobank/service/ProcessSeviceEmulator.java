package com.example.monobank.service;

import com.example.monobank.entities.Status;

public interface ProcessSeviceEmulator {
    Status getRandomStatus();

    void startProcessing() throws InterruptedException;
}
