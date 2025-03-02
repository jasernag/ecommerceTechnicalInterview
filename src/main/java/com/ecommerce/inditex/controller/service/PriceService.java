package com.ecommerce.inditex.controller.service;


import com.ecommerce.inditex.domain.dto.request.RequestSearchPriceDTO;
import com.ecommerce.inditex.domain.dto.response.ResponsePriceDTO;

public interface PriceService {
    ResponsePriceDTO getProductPrice(RequestSearchPriceDTO requestSearchPriceDTO);
}

