package com.example.monobank.configuration;

import com.example.monobank.entities.Bid;
import com.example.monobank.entities.Route;
import com.example.monobank.entities.Status;
import com.example.monobank.service.BidService;
import com.example.monobank.service.RouteService;
import com.example.monobank.service.StatusService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EventListener implements ApplicationListener<ApplicationStartedEvent> {
    private final StatusService statusService;
    private final RouteService routeService;
    private final BidService bidService;

    @Autowired
    public EventListener(StatusService statusService, RouteService routeService,
                         BidService bidService) {
        this.statusService = statusService;
        this.routeService = routeService;
        this.bidService = bidService;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        String routeOneNumber = "001";
        String routeTwoNumber = "002";

        Route routeOne = Route.builder()
                .externalRouteId(routeOneNumber)
                .build();
        routeService.save(routeOne);
        Route routeTwo = Route.builder()
                .externalRouteId(routeTwoNumber)
                .build();
        routeService.save(routeTwo);

        statusService.save(Status.builder().bidStatus(Status.BidStatus.NEW).build());
        statusService.save(Status.builder().bidStatus(Status.BidStatus.IN_PROGRESS).build());
        statusService.save(Status.builder().bidStatus(Status.BidStatus.ERROR).build());
        statusService.save(Status.builder().bidStatus(Status.BidStatus.DONE).build());

        Bid bid = Bid.builder()
                .dateTime(LocalDateTime.now())
                .route(routeService.getByExternalId(routeOneNumber))
                .status(statusService.get(1L))
                .build();
        bidService.save(bid);
    }
}
