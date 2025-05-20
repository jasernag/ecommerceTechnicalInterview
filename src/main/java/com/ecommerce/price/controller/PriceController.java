package com.ecommerce.price.controller;


import com.ecommerce.price.api.PriceAPI;
import com.ecommerce.price.controller.service.PriceService;
import com.ecommerce.price.domain.dto.request.RequestSearchPriceDTO;
import com.ecommerce.price.domain.dto.response.ResponsePriceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PriceController implements PriceAPI {


    private final PriceService priceService;

    @Override
    public ResponseEntity<ResponsePriceDTO> getPrice(RequestSearchPriceDTO requestSearchPriceDTO) {

        return Optional.ofNullable(priceService.getProductPrice(requestSearchPriceDTO))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}