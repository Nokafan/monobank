package com.example.monobank.controllers;

import com.example.monobank.dto.RouteCreateRequestDto;
import com.example.monobank.dto.RouteUpdateRequestDto;
import com.example.monobank.entities.Route;
import com.example.monobank.service.RouteService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/route")
public class RouteController {
    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/all")
    public List<Route> getAllRoutes() {
        return routeService.getAll();
    }

    @GetMapping("/{id}")
    public Route getRouteById(@PathVariable Long id) {
        return routeService.get(id);
    }

    @PostMapping("/create")
    public Route createRote(@Valid @RequestBody RouteCreateRequestDto routeCreateRequestDto) {
        Route route = Route.builder().externalRouteId(routeCreateRequestDto.getRouteId()).build();
        return routeService.save(route);
    }

    @PutMapping("/update")
    public Route updateRoute(@Valid @RequestBody RouteUpdateRequestDto routeUpdateRequestDto) {
        return routeService.updateRoute(routeUpdateRequestDto);
    }
}
