package com.ecommerce.price.controller.service.mapper;


import com.ecommerce.price.domain.dto.response.ResponsePriceDTO;
import com.ecommerce.price.domain.entity.Price;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponsePriceMapper {

    ResponsePriceDTO toResponsePriceDTO(
            Price price);
}
