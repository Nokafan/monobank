package com.example.monobank.service;

import com.example.monobank.dto.BidRequestDto;
import com.example.monobank.entities.Bid;
import com.example.monobank.entities.Status;

public interface BidService extends GeneralService<Bid> {
    Bid createAndSave(BidRequestDto bidRequestDto);

    Bid findBidToProcess(Status.BidStatus status);

    Bid updateBidStatus(Long bidId, Status bidStatus);
}
