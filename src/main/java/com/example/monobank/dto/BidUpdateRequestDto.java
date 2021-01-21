package com.example.monobank.dto;

import com.example.monobank.validators.ValidStatusName;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BidUpdateRequestDto {
    @NotNull(message = "id can't be null")
    @Min(value = 0, message = "Id can't be less then zero")
    private Long id;

    @ValidStatusName
    private String statusName;
}
