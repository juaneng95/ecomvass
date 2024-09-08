package com.vass.inditex.domain.model;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceModelQuery implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  private Integer productId;
  private Long brandId;
  private Integer priceList;
  private Timestamp startDate;
  private Timestamp endDate;
  private Float price;
}
