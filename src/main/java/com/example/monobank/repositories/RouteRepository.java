package com.example.monobank.repositories;

import com.example.monobank.entities.Route;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    Optional<Route> getRouteByExternalRouteId(String externalRouteId);

}
