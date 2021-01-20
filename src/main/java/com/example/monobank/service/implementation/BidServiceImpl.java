package com.example.monobank.service.implementation;

import com.example.monobank.dto.BidRequestDto;
import com.example.monobank.entities.Bid;
import com.example.monobank.entities.Status;
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
    public Bid get(Long orderId) {
        return bidRepository.findById(orderId).orElseThrow();
    }

    @Override
    public Bid createAndSave(BidRequestDto bidRequestDto) {
        return bidRepository.save(bidMapper.bitDtoToBid(bidRequestDto));
    }

    @Override
    public Bid findBidToProcess(Status.BidStatus bidStatus) {
        Status status = statusService.findByBidStatus(bidStatus);
        return bidRepository.findFirstByStatus(status).orElseThrow();
    }

    @Override
    public Bid updateBidStatus(Long bidId, Status status) {
        Bid repositoryBid = bidRepository.findById(bidId).orElseThrow();
        repositoryBid.setStatus(status);
        return bidRepository.save(repositoryBid);
    }
}
