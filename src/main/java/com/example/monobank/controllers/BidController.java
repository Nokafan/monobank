package com.example.monobank.controllers;

import com.example.monobank.dto.BidCreateRequestDto;
import com.example.monobank.dto.BidUpdateRequestDto;
import com.example.monobank.entities.Bid;
import com.example.monobank.entities.Status.StatusName;
import com.example.monobank.service.BidService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bid")
public class BidController {
    private final BidService bidService;

    @Autowired
    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @PostMapping("/create")
    public Long createOrder(@RequestBody @Valid BidCreateRequestDto bidCreateRequestDto) {
        return bidService.createAndSave(bidCreateRequestDto).getId();
    }

    @GetMapping("/status")
    public StatusName getStatus(@RequestParam(name = "orderId") @Valid Long orderId) {
        return bidService.get(orderId).getStatus().getStatusName();
    }

    @GetMapping("/all")
    public List<Bid> getAllBids() {
        return bidService.getAll();
    }

    @GetMapping("/process")
    public Bid bidToProcess(@RequestParam(name = "status") StatusName status) {
        return bidService.findBidToProcess(status);
    }

    @PutMapping(value = "/update")
    public Bid updateBidStatus(@Valid @RequestBody BidUpdateRequestDto requestUpdateDto) {
        return bidService.updateBidStatus(requestUpdateDto);
    }
}
