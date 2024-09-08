package com.vass.inditex.infrastructure.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.vass.inditex.domain.exception.NotFoundPriceException;
import com.vass.inditex.domain.model.PriceModel;
import com.vass.inditex.domain.model.PriceModelQuery;
import com.vass.inditex.infrastructure.adapter.PriceRepository;
import com.vass.inditex.infrastructure.entity.PriceEntity;
import com.vass.inditex.infrastructure.mapper.PriceMapper;
import java.sql.Timestamp;
import java.util.List;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PricePersistenceImplTest {

    @InjectMocks
    PricePersistenceImpl pricePersistenceImpl;
    @Mock
    private PriceRepository priceRepository;
    @Mock
    private PriceMapper priceMapper;

    // Random Objects
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
    final PriceModel priceModel = easyRandom.nextObject(PriceModel.class);
    final PriceModelQuery priceModelQuery = easyRandom.nextObject(PriceModelQuery.class);
    final PriceEntity priceEntity = easyRandom.nextObject(PriceEntity.class);
    final List<PriceEntity> priceEntityList =
            easyRandom.objects(PriceEntity.class, 5).toList();

    // Values
    final String requiredDateTest = "2020-06-14 15:01:00";
    final Integer productId = 35455;
    final Long brandId = 1L;

    @Test
    void getAllPrices_OK() {

        // WHEN
        when(priceRepository.findAll()).thenReturn(priceEntityList);
        when(priceMapper.asDomainPriceModel(any(PriceEntity.class))).thenReturn(priceModel);

        // THEN
        List<PriceModel> res = pricePersistenceImpl.getAllPrices();

        assertNotNull(res);

        assertEquals(priceModel, res.getFirst());
    }

    @Test
    void getPriceByCriteriaQuery_OK() throws NotFoundPriceException {

        // WHEN
        when(priceRepository.findPriceByDateAndProductIdAndBrandId(
                any(Timestamp.class), any(Integer.class), any(Long.class))).thenReturn(priceEntity);
        when(priceMapper.asDomainPriceModelQuery(priceEntity)).thenReturn(priceModelQuery);

        // THEN
        PriceModelQuery res = pricePersistenceImpl.getPriceByCriteriaQuery(
                Timestamp.valueOf(requiredDateTest), productId, brandId);

        assertNotNull(res);

        assertEquals(priceModelQuery, res);
    }

    @Test
    void getPriceByCriteriaQuery_NotFoundPriceException() {

        // WHEN
        when(priceRepository.findPriceByDateAndProductIdAndBrandId(
                any(Timestamp.class), any(Integer.class), any(Long.class))).thenReturn(null);

        // THEN
        NotFoundPriceException ex =
                assertThrows(
                        NotFoundPriceException.class,
                        () -> pricePersistenceImpl.getPriceByCriteriaQuery(
                                Timestamp.valueOf(requiredDateTest), productId, brandId),
                        "PRICE NOT FOUND - Without results. The price doesn't exist in H2 Database.");

        assertEquals("PRICE NOT FOUND - Without results. The price doesn't exist in H2 Database.",
                ex.getMessage());
        assertEquals(404, ex.getCode());
    }

}