package com.ecommerce.inditex.domain.repository.dto.request;

import com.ecommerce.inditex.domain.dto.request.RequestSearchPriceDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RequestSearchPriceDTOTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testRequestSearchPriceDTONew() {
        RequestSearchPriceDTO request = new RequestSearchPriceDTO("2020-06-14T10:00:00", 35455L, 1L, LocalDateTime.now());
        assertThat(request.getDate()).isEqualTo("2020-06-14T10:00:00");
        assertThat(request.getProductCode()).isEqualTo(35455L);
        assertThat(request.getBrandCode()).isEqualTo(1L);
    }

    @Test
    public void testRequestSearchPriceDTOValid() {
        RequestSearchPriceDTO request = new RequestSearchPriceDTO(null, -1L, -1L, LocalDateTime.now());
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<RequestSearchPriceDTO>> violations = validator.validate(request);
        assertThat(violations).hasSize(2);
        for (ConstraintViolation<RequestSearchPriceDTO> violation : violations) {
            System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
        }
    }

}
