package com.example.monobank.dto;

import com.example.monobank.validators.ValidRouteNumber;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class BidCreateRequestDto {
    @NotBlank(message = "Must contain route number")
    @ValidRouteNumber
    private String routeId;
    @NotBlank(message = "Must contain day and time of departure")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private String dateTime;
}
