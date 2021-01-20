package com.example.monobank.service;

import com.example.monobank.entities.Route;

public interface RouteService extends GeneralService<Route> {
    Route getByExternalId(String externalRouteId);
}
