package com.example.monobank.service.implementation;

import com.example.monobank.dto.BidCreateRequestDto;
import com.example.monobank.dto.BidUpdateRequestDto;
import com.example.monobank.entities.Bid;
import com.example.monobank.entities.Status;
import com.example.monobank.entities.Status.StatusName;
import com.example.monobank.exception.DataProcessingException;
import com.example.monobank.mapper.BidMapper;
import com.example.monobank.repositories.BidRepository;
import com.example.monobank.service.BidService;
import com.example.monobank.service.StatusService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidServiceImpl implements BidService {
    private final BidRepository bidRepository;
    private final StatusService statusService;
    private final BidMapper bidMapper;

    @Autowired
    public BidServiceImpl(BidRepository bidRepository,
                          StatusService statusService,
                          BidMapper bidMapper) {
        this.bidRepository = bidRepository;
        this.statusService = statusService;
        this.bidMapper = bidMapper;
    }

    @Override
    public Bid save(Bid bid) {
        return bidRepository.save(bid);
    }

    @Override
    public List<Bid> saveAll(Iterable<Bid> iterableOrders) {
        return bidRepository.saveAll(iterableOrders);
    }

    @Override
    public List<Bid> getAll() {
        return bidRepository.findAll();
    }

    @Override
    public Bid get(Long bidId) {
        return bidRepository.findById(bidId)
                .orElseThrow(() -> new DataProcessingException("Not found bid with bidId: "
                        + bidId));
    }

    @Override
    public Bid createAndSave(BidCreateRequestDto bidCreateRequestDto) {
        return bidRepository.save(bidMapper.mapCreateDtoToBid(bidCreateRequestDto));
    }

    @Override
    public Bid findBidToProcess(StatusName statusName) {
        Status status = statusService.getByStatusName(statusName);
        return bidRepository.findFirstByStatus(status)
                .orElseThrow(() -> new DataProcessingException("Not found bid with bidStatus: "
                        + statusName));
    }

    @Override
    public Bid updateBidStatus(BidUpdateRequestDto bidUpdateRequestDto) {
        long bidId = bidUpdateRequestDto.getId();
        StatusName statusName = StatusName.valueOf(bidUpdateRequestDto.getStatusName());
        Status status = statusService.getByStatusName(statusName);

        Bid repositoryBid = bidRepository.findById(bidId)
                .orElseThrow(() -> new DataProcessingException("Not found bid with bidId: "
                        + bidId));

        repositoryBid.setStatus(status);
        return bidRepository.save(repositoryBid);
    }
}
