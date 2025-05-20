package com.ecommerce.price.controller.service;


import com.ecommerce.price.domain.dto.request.RequestSearchPriceDTO;
import com.ecommerce.price.domain.dto.response.ResponsePriceDTO;

public interface PriceService {
    ResponsePriceDTO getProductPrice(RequestSearchPriceDTO requestSearchPriceDTO);
}

