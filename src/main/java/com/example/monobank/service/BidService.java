package com.example.monobank.service;

import com.example.monobank.dto.BidCreateRequestDto;
import com.example.monobank.dto.BidUpdateRequestDto;
import com.example.monobank.entities.Bid;

public interface BidService extends GeneralService<Bid> {
    Bid createAndSave(BidCreateRequestDto bidCreateRequestDto);

    Bid findBidToProcess(String statusName);

    Bid updateBidStatus(BidUpdateRequestDto bidUpdateRequestDto);
}
