package com.example.monobank.service;

import com.example.monobank.dto.BidCreateRequestDto;
import com.example.monobank.dto.BidUpdateRequestDto;
import com.example.monobank.entities.Bid;
import com.example.monobank.entities.Status.StatusName;

public interface BidService extends GeneralService<Bid> {
    Bid createAndSave(BidCreateRequestDto bidCreateRequestDto);

    Bid findBidToProcess(StatusName statusName);

    Bid updateBidStatus(BidUpdateRequestDto bidUpdateRequestDto);
}
