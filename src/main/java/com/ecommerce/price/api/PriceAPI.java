package com.ecommerce.price.api;

import com.ecommerce.price.domain.dto.request.RequestSearchPriceDTO;
import com.ecommerce.price.domain.dto.response.ResponsePriceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "price-api")
@RequestMapping("/api/v1/private/prices")
public interface PriceAPI {

    @Operation(
            operationId = "getPrice",
            summary = "Servicio de consulta del precio de un producto, por fecha, brandCode y productCode.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "BAD REQUEST",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "UNAUTHORIZED",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "403",
                            description = "FORBIDDEN",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "NOT FOUND",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "500",
                            description = "INTERNAL SERVER ERROR",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "501",
                            description = "NOT IMPLEMENTED",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)))
            })
    @GetMapping(
            value = "/getPrice",
            produces = {"application/json"})
    ResponseEntity<ResponsePriceDTO> getPrice(@Valid @ParameterObject RequestSearchPriceDTO requestSearchPriceDTO);
}