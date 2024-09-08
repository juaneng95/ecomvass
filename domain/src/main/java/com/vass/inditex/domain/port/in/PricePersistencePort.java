package com.vass.inditex.domain.port.in;

import com.vass.inditex.domain.exception.NotFoundPriceException;
import com.vass.inditex.domain.model.PriceModel;
import com.vass.inditex.domain.model.PriceModelQuery;
import java.sql.Timestamp;
import java.util.List;

public interface PricePersistencePort {
  List<PriceModel> getAllPrices() throws NotFoundPriceException;

  PriceModelQuery getPriceByCriteriaQuery(Timestamp requiredDate, Integer productId, Long brandId)
      throws NotFoundPriceException;
}
