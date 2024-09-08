package com.vass.inditex.domain.service;

import com.vass.inditex.domain.exception.PriceException;
import com.vass.inditex.domain.model.PriceModel;
import com.vass.inditex.domain.model.PriceModelQuery;
import com.vass.inditex.domain.port.in.PricePersistencePort;
import com.vass.inditex.domain.port.out.PriceUseCase;
import java.sql.Timestamp;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceService implements PriceUseCase {

  private final PricePersistencePort priceManagement;

  @Override
  public List<PriceModel> getAllPrices() throws PriceException {

    return priceManagement.getAllPrices();
  }

  @Override
  public PriceModelQuery getPriceByCriteriaQuery(
      Timestamp requiredDate, Integer productId, Long brandId) throws PriceException {

    return priceManagement.getPriceByCriteriaQuery(requiredDate, productId, brandId);
  }
}
