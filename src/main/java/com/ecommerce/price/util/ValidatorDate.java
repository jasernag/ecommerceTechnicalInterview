package com.ecommerce.price.util;

import com.ecommerce.price.domain.dto.request.RequestSearchPriceDTO;
import com.ecommerce.price.exception.handler.PriceException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class ValidatorDate {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static void validateAndSetDate(RequestSearchPriceDTO requestSearchPriceDTO) {
        if (Objects.isNull(requestSearchPriceDTO.getDate()) || requestSearchPriceDTO.getDate().isBlank()) {
            requestSearchPriceDTO.setDateFind(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        } else if (!requestSearchPriceDTO.getDate().matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$")) {
            throw new PriceException("El formato de la fecha debe ser: yyyy-MM-dd'T'HH:mm:ss.");
        } else {
            requestSearchPriceDTO.setDateFind(LocalDateTime.parse(requestSearchPriceDTO.getDate(), formatter));
        }
    }

}
