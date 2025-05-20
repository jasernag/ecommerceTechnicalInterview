package com.ecommerce.price.domain.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ResponsePriceDTO {


    private Long brandCode;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Long priceList;

    private Long productCode;

    private BigDecimal price;

}