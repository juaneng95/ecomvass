package com.vass.inditex.infrastructure.service;

import com.vass.inditex.domain.exception.NotFoundPriceException;
import com.vass.inditex.domain.model.PriceModel;
import com.vass.inditex.domain.model.PriceModelQuery;
import com.vass.inditex.domain.port.in.PricePersistencePort;
import com.vass.inditex.infrastructure.adapter.PriceRepository;
import com.vass.inditex.infrastructure.entity.PriceEntity;
import com.vass.inditex.infrastructure.mapper.PriceMapper;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PricePersistenceImpl implements PricePersistencePort {

  private final PriceRepository priceRepository;
  private final PriceMapper priceEntityMapper;

  @Override
  public List<PriceModel> getAllPrices() {

    log.info("Running query: findAll()");
    return priceRepository.findAll().stream().map(priceEntityMapper::asDomainPriceModel).toList();
  }

  @Override
  public PriceModelQuery getPriceByCriteriaQuery(
      Timestamp requiredDate, Integer productId, Long brandId) throws NotFoundPriceException {

    log.info("Running query: findPriceByDateAndProductIdAndBrandId()");
    PriceEntity priceEntity =
        priceRepository.findPriceByDateAndProductIdAndBrandId(requiredDate, productId, brandId);

    // If priceEntity is null
    if (Objects.isNull(priceEntity)) {
      throw new NotFoundPriceException(
          "PRICE NOT FOUND - Without results. The price doesn't exist in H2 Database.");
    }

    return priceEntityMapper.asDomainPriceModelQuery(priceEntity);
  }
}
