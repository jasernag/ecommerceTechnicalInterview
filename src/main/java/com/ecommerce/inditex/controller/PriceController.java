package com.ecommerce.inditex.controller;


import com.ecommerce.inditex.api.PriceAPI;
import com.ecommerce.inditex.controller.service.PriceService;
import com.ecommerce.inditex.domain.dto.request.RequestSearchPriceDTO;
import com.ecommerce.inditex.domain.dto.response.ResponsePriceDTO;
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