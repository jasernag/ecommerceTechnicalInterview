package com.ecommerce.inditex.controller.service.mapper;


import com.ecommerce.inditex.domain.dto.response.ResponsePriceDTO;
import com.ecommerce.inditex.domain.entity.Price;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponsePriceMapper {

    ResponsePriceDTO toResponsePriceDTO(
            Price price);
}
