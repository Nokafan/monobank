package com.example.monobank.service;

import com.example.monobank.entities.Status;
import com.example.monobank.entities.Status.StatusName;

public interface StatusService extends GeneralService<Status> {
    Status getByStatusName(StatusName statusName);
}
