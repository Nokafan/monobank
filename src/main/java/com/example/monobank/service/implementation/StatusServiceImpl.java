package com.example.monobank.service.implementation;

import com.example.monobank.entities.Status;
import com.example.monobank.entities.Status.StatusName;
import com.example.monobank.exception.DataProcessingException;
import com.example.monobank.repositories.StatusRepository;
import com.example.monobank.service.StatusService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;

    @Autowired
    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Status save(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public List<Status> saveAll(Iterable<Status> iterableStatuses) {
        return statusRepository.saveAll(iterableStatuses);
    }

    @Override
    public List<Status> getAll() {
        return statusRepository.findAll();
    }

    @Override
    public Status get(Long statusId) {
        return statusRepository.findById(statusId)
                .orElseThrow(() -> new DataProcessingException("Not found status with statusId: "
                        + statusId));
    }

    @Override
    public Status getByStatusName(StatusName statusName) {
        return statusRepository.findByStatusName(statusName)
                .orElseThrow(() -> new DataProcessingException("Not found status with statusName: "
                        + statusName));
    }
}
