package com.vass.inditex.application.controller;

import com.vass.inditex.application.api.EcomApi;
import com.vass.inditex.application.mapper.RestMapper;
import com.vass.inditex.application.model.PriceDto;
import com.vass.inditex.application.model.PriceQueryDto;
import com.vass.inditex.domain.exception.PriceException;
import com.vass.inditex.domain.port.out.PriceUseCase;
import java.sql.Timestamp;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EcomController implements EcomApi {

  private final PriceUseCase priceUseCase;
  private final RestMapper restMapper;

  @Override
  public ResponseEntity<List<PriceDto>> getAllPrices() throws PriceException {

    log.info("--> Running /ecom/list");
    List<PriceDto> priceDtoList =
        priceUseCase.getAllPrices().stream().map(restMapper::toDto).toList();

    return ResponseEntity.ok(priceDtoList);
  }

  @Override
  public ResponseEntity<PriceQueryDto> getPriceByCriteriaQuery(
      String requiredDate, Integer productId, Long brandId) throws PriceException {

    log.info("--> Running /ecom/list/price?");
    PriceQueryDto priceQueryDto =
        restMapper.toQueryDto(
            priceUseCase.getPriceByCriteriaQuery(
                Timestamp.valueOf(requiredDate), productId, brandId));

    return ResponseEntity.ok(priceQueryDto);
  }
}
