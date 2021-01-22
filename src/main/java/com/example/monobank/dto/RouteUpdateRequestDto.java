package com.example.monobank.dto;

import com.example.monobank.validators.ValidRouteNumber;
import lombok.Data;

@Data
public class RouteUpdateRequestDto {
    @ValidRouteNumber
    private String routeId;

    @ValidRouteNumber
    private String newRouteId;
}
