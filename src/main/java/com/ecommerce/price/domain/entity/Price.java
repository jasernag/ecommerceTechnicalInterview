package com.ecommerce.price.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICE")
@NoArgsConstructor
@Getter
@Setter
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long brandCode;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Long priceList;

    private Long productCode;

    private Long priority;

    private BigDecimal price;

    private String curr;

}