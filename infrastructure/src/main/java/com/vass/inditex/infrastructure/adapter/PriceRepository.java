package com.vass.inditex.infrastructure.adapter;

import com.vass.inditex.infrastructure.entity.PriceEntity;
import java.sql.Timestamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

  // Selecting the price by criteria parameters with limit 1
  @Query(
      value =
          "SELECT * FROM ECOMVASS_SCHEMA.PRICE "
              + "WHERE PRODUCT_ID = :productId AND BRAND_ID = :brandId "
              + "AND (START_DATE <= :requiredDate AND END_DATE >= :requiredDate) "
              + "ORDER BY PRIORITY DESC LIMIT 1",
      nativeQuery = true)
  PriceEntity findPriceByDateAndProductIdAndBrandId(
      @Param("requiredDate") Timestamp requiredDate,
      @Param("productId") Integer productId,
      @Param("brandId") Long brandId);
}
