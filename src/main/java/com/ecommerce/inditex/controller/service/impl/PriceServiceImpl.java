package com.ecommerce.inditex.controller.service.impl;


import com.ecommerce.inditex.controller.service.PriceService;
import com.ecommerce.inditex.controller.service.mapper.ResponsePriceMapper;
import com.ecommerce.inditex.domain.dto.request.RequestSearchPriceDTO;
import com.ecommerce.inditex.domain.dto.response.ResponsePriceDTO;
import com.ecommerce.inditex.domain.repository.PriceRepository;
import com.ecommerce.inditex.exception.handler.PriceException;
import com.ecommerce.inditex.util.ValidatorDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Servicio para el manejo de precios.
 */
@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final ResponsePriceMapper responsePriceMapper;


    /**
     * Obtiene el precio aplicable para un producto en una fecha espec&iacute;fica.
     *
     * @param requestSearchPriceDTO request del producto.
     * @return ResponsePriceDTO con la informaciOn del precio aplicable.
     * @throws PriceException Si no se encuentra un precio valido.
     */
    @Override
    public ResponsePriceDTO getProductPrice(RequestSearchPriceDTO requestSearchPriceDTO) {
        ValidatorDate.validateAndSetDate(requestSearchPriceDTO);
        return priceRepository.findTopByProductCodeAndBrandCodeAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                        requestSearchPriceDTO.getProductCode(),
                        requestSearchPriceDTO.getBrandCode(),
                        requestSearchPriceDTO.getDateFind(),
                        requestSearchPriceDTO.getDateFind())
                .map(responsePriceMapper::toResponsePriceDTO)
                .orElseThrow(() -> new PriceException("No se ha encontrado el precio para el producto: {0} y la marca: {1}", requestSearchPriceDTO.getProductCode(), requestSearchPriceDTO.getBrandCode()));
    }
}