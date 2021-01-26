package com.example.monobank.service.implementation;

import com.example.monobank.dto.BidUpdateRequestDto;
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
    public static final String STATUS_NAME_NEW = "NEW";
    private final List<String> statusNames = new ArrayList<>();
    private final BidService bidService;

    @Autowired
    public ProcessSeviceEmulatorImpl(BidService bidService) {
        this.bidService = bidService;
    }

    @Override
    public void startProcessing() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                BidUpdateRequestDto bidUpdateDto = new BidUpdateRequestDto();
                bidUpdateDto.setId(bidService.findBidToProcess(STATUS_NAME_NEW).getId());
                bidUpdateDto.setStatusName(getRandomStatusName());
                bidService.updateBidStatus(bidUpdateDto);
            }
        }, 0, 5000);
    }

    public String getRandomStatusName() {
        statusNames.add("DONE");
        statusNames.add("ERROR");
        statusNames.add("IN_PROGRESS");
        return statusNames.get((int) (Math.random() * statusNames.size()));
    }
}
