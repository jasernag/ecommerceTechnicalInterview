package com.ecommerce.price.service.impl;

import com.ecommerce.price.controller.service.impl.PriceServiceImpl;
import com.ecommerce.price.controller.service.mapper.ResponsePriceMapper;
import com.ecommerce.price.domain.dto.request.RequestSearchPriceDTO;
import com.ecommerce.price.domain.dto.response.ResponsePriceDTO;
import com.ecommerce.price.domain.entity.Price;
import com.ecommerce.price.domain.repository.PriceRepository;
import com.ecommerce.price.exception.handler.PriceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PriceServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private ResponsePriceMapper responsePriceMapper;

    @InjectMocks
    private PriceServiceImpl priceService;

    private RequestSearchPriceDTO requestDTO;
    private Price mockPrice;
    private ResponsePriceDTO expectedResponse;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Before
    public void setUp() {
        requestDTO = new RequestSearchPriceDTO();
        requestDTO.setProductCode(35455L);
        requestDTO.setBrandCode(1L);
        requestDTO.setDate("2020-06-14T10:00:00");
        requestDTO.setDateFind(LocalDateTime.parse(requestDTO.getDate(), formatter));

        mockPrice = new Price();
        mockPrice.setProductCode(35455L);
        mockPrice.setBrandCode(1L);
        mockPrice.setPrice(BigDecimal.valueOf(35.50));

        expectedResponse = new ResponsePriceDTO();
        expectedResponse.setProductCode(35455L);
        expectedResponse.setBrandCode(1L);
        expectedResponse.setPrice(BigDecimal.valueOf(35.50));
    }

    @Test
    public void testGetProductPriceOk() {


        when(priceRepository.findTopByProductCodeAndBrandCodeAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                requestDTO.getProductCode(), requestDTO.getBrandCode(), requestDTO.getDateFind(), requestDTO.getDateFind()))
                .thenReturn(Optional.of(mockPrice));
        when(responsePriceMapper.toResponsePriceDTO(mockPrice)).thenReturn(expectedResponse);
        ResponsePriceDTO response = priceService.getProductPrice(requestDTO);
        assertNotNull(response);
        assertEquals(expectedResponse.getProductCode(), response.getProductCode());
        assertEquals(expectedResponse.getBrandCode(), response.getBrandCode());
        assertEquals(expectedResponse.getPrice(), response.getPrice());
        verify(priceRepository, times(1)).findTopByProductCodeAndBrandCodeAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                requestDTO.getProductCode(), requestDTO.getBrandCode(), requestDTO.getDateFind(), requestDTO.getDateFind());
        verify(responsePriceMapper, times(1)).toResponsePriceDTO(mockPrice);
    }

    @Test(expected = PriceException.class)
    public void testGetProductPriceFail() {
        when(priceRepository.findTopByProductCodeAndBrandCodeAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                requestDTO.getProductCode(), requestDTO.getBrandCode(), requestDTO.getDateFind(), requestDTO.getDateFind()))
                .thenReturn(Optional.empty());
        priceService.getProductPrice(requestDTO);
        verify(priceRepository, times(1)).findTopByProductCodeAndBrandCodeAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                requestDTO.getProductCode(), requestDTO.getBrandCode(), requestDTO.getDateFind(), requestDTO.getDateFind());
        fail("Se esperaba una PriceException pero no se lanzo.");
    }

    @Test(expected = PriceException.class)
    public void testGetProductPriceFailDateBlank() {
        requestDTO = new RequestSearchPriceDTO();
        requestDTO.setProductCode(35455L);
        requestDTO.setBrandCode(1L);
        requestDTO.setDate("");
        priceService.getProductPrice(requestDTO);
        fail("Se esperaba una PriceException pero no se lanzo.");
    }

    @Test(expected = PriceException.class)
    public void testGetProductPriceFailDateNull() {
        requestDTO = new RequestSearchPriceDTO();
        requestDTO.setProductCode(35455L);
        requestDTO.setBrandCode(1L);
        requestDTO.setDate(null);
        priceService.getProductPrice(requestDTO);
        fail("Se esperaba una PriceException pero no se lanzo.");
    }

    @Test(expected = PriceException.class)
    public void testGetProductPriceFailDateBadPattern() {
        requestDTO = new RequestSearchPriceDTO();
        requestDTO.setProductCode(35455L);
        requestDTO.setBrandCode(1L);
        requestDTO.setDate("ErrorPattern");
        priceService.getProductPrice(requestDTO);
        fail("Se esperaba una PriceException pero no se lanzo.");
    }
}