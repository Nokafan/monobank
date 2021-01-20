package com.example.monobank.mapper;

import com.example.monobank.dto.BidRequestDto;
import com.example.monobank.entities.Bid;
import com.example.monobank.entities.Status;
import com.example.monobank.service.RouteService;
import com.example.monobank.service.StatusService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class BidMapper {
    private final RouteService routeService;
    private final StatusService statusService;
    private final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Autowired
    public BidMapper(RouteService routeService, StatusService statusService) {
        this.routeService = routeService;
        this.statusService = statusService;
    }

    public Bid bitDtoToBid(BidRequestDto requestDto) {
        Status defaultStatus = statusService.findByBidStatus(Status.BidStatus.NEW);
        return Bid.builder()
                .route(routeService.getByExternalId(requestDto.getRouteId()))
                .dateTime(LocalDateTime.parse(requestDto.getDateTime(), dateTimeFormatter))
                .status(defaultStatus)
                .build();
    }
}
