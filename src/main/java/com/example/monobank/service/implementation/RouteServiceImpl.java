package com.example.monobank.service.implementation;

import com.example.monobank.dto.RouteUpdateRequestDto;
import com.example.monobank.entities.Route;
import com.example.monobank.exception.DataProcessingException;
import com.example.monobank.repositories.RouteRepository;
import com.example.monobank.service.RouteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public Route save(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public List<Route> saveAll(Iterable<Route> iterableRoutes) {
        return routeRepository.saveAll(iterableRoutes);
    }

    @Override
    public List<Route> getAll() {
        return routeRepository.findAll();
    }

    @Override
    @ExceptionHandler({DataProcessingException.class})
    public Route get(Long routeId) {
        return routeRepository.findById(routeId)
                .orElseThrow(() ->
                        new DataProcessingException("No such a route with id: "
                                + routeId + " in DB"));
    }

    @Override
    @ExceptionHandler({DataProcessingException.class})
    public Route getByExternalId(String externalRouteId) {
        return routeRepository.getRouteByExternalRouteId(externalRouteId)
                .orElseThrow(() ->
                        new DataProcessingException("No such a route with externalRouteId: "
                                + externalRouteId + " in DB"));
    }

    @Override
    @ExceptionHandler({DataProcessingException.class, IllegalArgumentException.class})
    public Route updateRoute(RouteUpdateRequestDto requestDto) {
        Route repositoryRoute = routeRepository.getRouteByExternalRouteId(requestDto.getRouteId())
                .orElseThrow(() ->
                        new DataProcessingException("No such a route with externalRouteId: "
                                + requestDto.getRouteId() + " in DB"));
        repositoryRoute.setExternalRouteId(requestDto.getNewRouteId());
        return routeRepository.save(repositoryRoute);
    }
}
