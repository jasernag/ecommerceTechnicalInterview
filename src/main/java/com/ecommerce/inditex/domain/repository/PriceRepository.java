package com.ecommerce.inditex.domain.repository;

import com.ecommerce.inditex.domain.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;


@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    Optional<Price> findTopByProductCodeAndBrandCodeAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
            Long productId, Long brandId, LocalDateTime date, LocalDateTime date2);

}