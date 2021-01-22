package com.example.monobank.dto;

import com.example.monobank.validators.ValidRouteNumber;
import lombok.Data;

@Data
public class RouteCreateRequestDto {
    @ValidRouteNumber
    private String routeId;
}
