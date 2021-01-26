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
import java.time.format.DateTimeParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
    @ExceptionHandler({DateTimeParseException.class})
    public Bid save(Bid bid) {
        return bidRepository.save(bid);
    }

    @Override
    @ExceptionHandler({DateTimeParseException.class})
    public List<Bid> saveAll(Iterable<Bid> iterableOrders) {
        return bidRepository.saveAll(iterableOrders);
    }

    @Override
    public List<Bid> getAll() {
        return bidRepository.findAll();
    }

    @Override
    @ExceptionHandler({DataProcessingException.class})
    public Bid get(Long bidId) {
        return bidRepository.findById(bidId)
                .orElseThrow(() -> new DataProcessingException("Not found bid with bidId: "
                        + bidId));
    }

    @Override
    @ExceptionHandler({DateTimeParseException.class})
    public Bid createAndSave(BidCreateRequestDto bidCreateRequestDto) {
        return bidRepository.save(bidMapper.mapCreateDtoToBid(bidCreateRequestDto));
    }

    @Override
    @ExceptionHandler({DataProcessingException.class, IllegalArgumentException.class})
    public Bid findBidToProcess(String stringStatusName) {
        StatusName statusName = StatusName.valueOf(stringStatusName);
        Status status = statusService.getByStatusName(statusName);
        return bidRepository.findFirstByStatus(status)
                .orElseThrow(() -> new DataProcessingException("Not found bid with bidStatus: "
                        + stringStatusName));
    }

    @Override
    @ExceptionHandler({DataProcessingException.class, IllegalArgumentException.class})
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
