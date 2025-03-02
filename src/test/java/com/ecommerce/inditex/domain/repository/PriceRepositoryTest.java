package com.ecommerce.inditex.domain.repository;

import com.ecommerce.inditex.domain.entity.Price;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class PriceRepositoryTest {

    @Autowired
    private PriceRepository priceRepository;

    @Test
    public void testFindTopByProductCodeAndBrandCodeOk() {
        Price price = new Price();
        price.setProductCode(35455L);
        price.setBrandCode(1L);
        price.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0));
        price.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59));
        price.setPrice(BigDecimal.valueOf(35.50));
        price.setPriority(1L);
        price.setPriceList(1L);
        price.setCurr("EUR");
        priceRepository.save(price);
        Optional<Price> result = priceRepository.findTopByProductCodeAndBrandCodeAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                35455L, 1L, LocalDateTime.of(2020, 6, 14, 10, 0), LocalDateTime.of(2020, 6, 14, 10, 0));
        assertTrue(result.isPresent(), "El resultado no deberia estar vacio.");
        assertEquals(BigDecimal.valueOf(35.50), result.get().getPrice(), "El precio no coincide.");
        assertEquals(35455L, result.get().getProductCode(), "El codigo de producto no coincide.");
        assertEquals(1L, result.get().getBrandCode(), "El codigo de marca no coincide.");
    }
}