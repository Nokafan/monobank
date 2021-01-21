package com.example.monobank.controllers;

import com.example.monobank.dto.BidRequestDto;
import com.example.monobank.entities.Bid;
import com.example.monobank.entities.Status;
import com.example.monobank.service.BidService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bid")
public class BidController {
    private final BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @PostMapping("/create")
    public Long createOrder(@RequestBody @Valid BidRequestDto bidRequestDto) {
        return bidService.createAndSave(bidRequestDto).getId();
    }

    @GetMapping("/status")
    public Status getStatus(@RequestParam(name = "id") @Valid Long orderId) {
        return bidService.get(orderId).getStatus();
    }

    @GetMapping("/all")
    public List<Bid> getAllBids() {
        return bidService.getAll();
    }

    @GetMapping("/process")
    public Bid bidToProcess(@RequestParam(name = "status") Status.BidStatus status) {
        return bidService.findBidToProcess(status);
    }
}
