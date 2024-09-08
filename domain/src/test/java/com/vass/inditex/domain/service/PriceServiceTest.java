package com.vass.inditex.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.vass.inditex.domain.exception.PriceException;
import com.vass.inditex.domain.model.PriceModel;
import com.vass.inditex.domain.model.PriceModelQuery;
import com.vass.inditex.domain.port.in.PricePersistencePort;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

  @InjectMocks private PriceService priceService;
  @Mock private PricePersistencePort pricePersistence;

  final EasyRandom easyRandom =
      new EasyRandom(
          new EasyRandomParameters()
              .objectPoolSize(1)
              .randomizationDepth(1)
              .stringLengthRange(5, 25)
              .collectionSizeRange(1, 1)
              .scanClasspathForConcreteTypes(true)
              .overrideDefaultInitialization(false)
              .ignoreRandomizationErrors(false));

  // Random objects
  final PriceModelQuery priceModelQuery = easyRandom.nextObject(PriceModelQuery.class);
  final List<PriceModel> priceModelList =
      easyRandom.objects(PriceModel.class, 5).collect(Collectors.toList());

  // Values
  final String requiredDate = "2020-06-14 15:01:00";
  final Integer productId = 35455;
  final Long brandId = 1L;

  @Test
  void getAllPrices_OK() throws PriceException {

    // WHEN
    when(pricePersistence.getAllPrices()).thenReturn(priceModelList);

    // THEN
    List<PriceModel> res = priceService.getAllPrices();

    assertNotNull(res);

    assertEquals(priceModelList.size(), res.size());
    assertEquals(priceModelList.get(2), res.get(2));
  }

  @Test
  void getAllPrices_KO() throws PriceException {

    // WHEN
    when(pricePersistence.getAllPrices()).thenReturn(null);

    // THEN
    List<PriceModel> res = priceService.getAllPrices();

    assertNull(res);
  }

  @Test
  void getPriceByCriteriaQuery_OK() throws PriceException {

    // WHEN
    when(pricePersistence.getPriceByCriteriaQuery(
            any(Timestamp.class), any(Integer.class), any(Long.class)))
        .thenReturn(priceModelQuery);

    // THEN
    PriceModelQuery res =
        priceService.getPriceByCriteriaQuery(Timestamp.valueOf(requiredDate), productId, brandId);

    assertNotNull(res);

    assertEquals(priceModelQuery, res);
    assertEquals(priceModelQuery.getPrice(), res.getPrice());
  }

  @Test
  void getPriceByCriteriaQuery_NotFoundPriceException() throws PriceException {

    // WHEN
    when(pricePersistence.getPriceByCriteriaQuery(
            any(Timestamp.class), any(Integer.class), any(Long.class)))
        .thenReturn(null);

    // THEN
    PriceModelQuery res =
        priceService.getPriceByCriteriaQuery(Timestamp.valueOf(requiredDate), productId, brandId);

    assertNotEquals(priceModelQuery, res);
  }
}
