package com.example.monobank.service.implementation;

import com.example.monobank.entities.Bid;
import com.example.monobank.entities.Status;
import com.example.monobank.service.BidService;
import com.example.monobank.service.ProcessSeviceEmulator;
import com.example.monobank.service.StatusService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessSeviceEmulatorImpl implements ProcessSeviceEmulator {
    private final List<Status.BidStatus> bidStatuses = new ArrayList<>();
    private final StatusService statusService;
    private final BidService bidService;

    @Autowired
    public ProcessSeviceEmulatorImpl(StatusService statusService, BidService bidService) {
        this.statusService = statusService;
        this.bidService = bidService;
    }

    @Override
    public Status getRandomStatus() {
        bidStatuses.add(Status.BidStatus.DONE);
        bidStatuses.add(Status.BidStatus.ERROR);
        bidStatuses.add(Status.BidStatus.IN_PROGRESS);
        Status.BidStatus randomBidStatus =
                bidStatuses.get((int) (Math.random() * bidStatuses.size()));
        return statusService.findByBidStatus(randomBidStatus);
    }

    @Override
    public void startProcessing() {
        Bid bid = bidService.findBidToProcess(Status.BidStatus.NEW);
        Status status = getRandomStatus();
        bidService.updateBidStatus(bid.getId(), status);
    }
}
