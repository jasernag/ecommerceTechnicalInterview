package com.ecommerce.price.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestSearchPriceDTO {

    @Schema(example = "2020-06-14T10:00:00", description = "Fecha de la consulta.")
    private String date;

    @Schema(example = "35455", description = "Codigo negocio del producto")
    @NotNull(message = "El codigo del producto no puede estar vacio.")
    @Min(value = 0, message = "El código del producto debe ser un número.")
    private Long productCode;

    @Schema(example = "1", description = "Codigo negocio de la marca")
    @NotNull(message = "El codigo de la marca no puede estar vacio.")
    @Min(value = 0, message = "El código de la marca debe ser un número.")
    private Long brandCode;

    @JsonIgnore
    private LocalDateTime dateFind;
}