package com.vass.inditex.domain.port.out;

import com.vass.inditex.domain.exception.PriceException;
import com.vass.inditex.domain.model.PriceModel;
import com.vass.inditex.domain.model.PriceModelQuery;
import java.sql.Timestamp;
import java.util.List;

public interface PriceUseCase {
  List<PriceModel> getAllPrices() throws PriceException;

  PriceModelQuery getPriceByCriteriaQuery(Timestamp requiredDate, Integer productId, Long brandId)
      throws PriceException;
}
