package com.example.monobank.service;

import com.example.monobank.entities.Status;

public interface StatusService extends GeneralService<Status> {
    Status findByBidStatus(Status.BidStatus bidStatus);
}
