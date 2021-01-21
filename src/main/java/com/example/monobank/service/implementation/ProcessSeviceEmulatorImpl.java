package com.example.monobank.service.implementation;

import com.example.monobank.dto.BidUpdateRequestDto;
import com.example.monobank.entities.Status.StatusName;
import com.example.monobank.service.BidService;
import com.example.monobank.service.ProcessSeviceEmulator;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessSeviceEmulatorImpl implements ProcessSeviceEmulator {
    private final List<String> statusNames = new ArrayList<>();
    private final BidService bidService;

    @Autowired
    public ProcessSeviceEmulatorImpl(BidService bidService) {
        this.bidService = bidService;
    }

    @Override
    public String getRandomStatusName() {
        statusNames.add("DONE");
        statusNames.add("ERROR");
        statusNames.add("IN_PROGRESS");
        return statusNames.get((int) (Math.random() * statusNames.size()));
    }

    @Override
    public void startProcessing() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                BidUpdateRequestDto bidUpdateRequestDto = new BidUpdateRequestDto();
                bidUpdateRequestDto.setId(bidService.findBidToProcess(StatusName.NEW).getId());
                bidUpdateRequestDto.setStatusName(getRandomStatusName());
                bidService.updateBidStatus(bidUpdateRequestDto);
            }
        }, 0, 5000);
    }
}
