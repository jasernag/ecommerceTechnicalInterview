package com.ecommerce.inditex.service.mapper;

import com.ecommerce.inditex.controller.service.mapper.ResponsePriceMapper;
import com.ecommerce.inditex.domain.dto.response.ResponsePriceDTO;
import com.ecommerce.inditex.domain.entity.Price;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResponsePriceMapperTest {

    @Autowired
    private ResponsePriceMapper mapper;

    @Test
    public void testToResponsePriceDTO() {
        Price price = new Price();
        price.setProductCode(35455L);
        price.setBrandCode(1L);
        price.setStartDate(LocalDateTime.of(2020, 6, 14, 10, 0));
        price.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59));
        price.setPriceList(1L);
        price.setPrice(BigDecimal.valueOf(35.50));
        ResponsePriceDTO responseDTO = mapper.toResponsePriceDTO(price);
        assertNotNull(responseDTO);
        assertEquals(price.getProductCode(), responseDTO.getProductCode());
        assertEquals(price.getBrandCode(), responseDTO.getBrandCode());
        assertEquals(price.getStartDate(), responseDTO.getStartDate());
        assertEquals(price.getEndDate(), responseDTO.getEndDate());
        assertEquals(price.getPriceList(), responseDTO.getPriceList());
        assertEquals(price.getPrice(), responseDTO.getPrice());
    }
}
