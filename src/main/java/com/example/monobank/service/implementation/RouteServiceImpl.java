package com.example.monobank.service.implementation;

import com.example.monobank.entities.Route;
import com.example.monobank.repositories.RouteRepository;
import com.example.monobank.service.RouteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Route get(Long routeId) {
        return routeRepository.findById(routeId).orElseThrow();
    }

    public Route getByExternalId(String externalRouteId) {
        return routeRepository.getRouteByExternalRouteId(externalRouteId).orElseThrow();
    }
}
