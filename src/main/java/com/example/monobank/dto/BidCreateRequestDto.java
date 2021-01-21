package com.example.monobank.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BidCreateRequestDto {
    @NotBlank(message = "Must contain route number")
    private String routeId;
    @NotBlank(message = "Must contain day and time of departure")
    private String dateTime;
}
