package com.ecommerce.inditex.controller;

import com.ecommerce.inditex.controller.service.PriceService;
import com.ecommerce.inditex.domain.dto.request.RequestSearchPriceDTO;
import com.ecommerce.inditex.domain.dto.response.ResponsePriceDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PriceControllerTest {

    @Mock
    private PriceService priceService;

    private RequestSearchPriceDTO requestDTO;

    private ResponsePriceDTO responseDTO;

    private PriceController priceController;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        priceController = new PriceController(priceService);

        requestDTO = new RequestSearchPriceDTO();
        requestDTO.setProductCode(35455L);
        requestDTO.setBrandCode(1L);
        requestDTO.setDate("2020-06-14T10:00:00");

        responseDTO = new ResponsePriceDTO();
        responseDTO.setProductCode(35455L);
        responseDTO.setBrandCode(1L);
        responseDTO.setPrice(BigDecimal.valueOf(35.50));
    }

    @Test
    public void testGetPriceOk() {
        when(priceService.getProductPrice(any(RequestSearchPriceDTO.class))).thenReturn(responseDTO);
        ResponsePriceDTO serviceResponse = priceService.getProductPrice(requestDTO);
        ResponseEntity<ResponsePriceDTO> responseEntity = priceController.getPrice(requestDTO);
        assertNotNull("El ResponseEntity no deberia ser null.", responseEntity);
        assertNotNull("El body del ResponseEntity no deberia ser null.", responseEntity.getBody());
        ResponsePriceDTO response = responseEntity.getBody();
        assertEquals((Long) 35455L, response.getProductCode());
        assertEquals((Long) 1L, response.getBrandCode());
        assertEquals(BigDecimal.valueOf(35.50), response.getPrice());

        verify(priceService, atLeastOnce()).getProductPrice(any(RequestSearchPriceDTO.class));

    }

    @Test
    public void testGetPriceKo() {
        when(priceService.getProductPrice(any(RequestSearchPriceDTO.class))).thenReturn(null);
        ResponseEntity<ResponsePriceDTO> responseEntity = priceController.getPrice(requestDTO);
        assertNull("El body del ResponseEntity debería ser null", responseEntity.getBody());
    }
}
