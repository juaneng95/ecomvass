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
public class PriceModel implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  private Long id;
  private Long brandId;
  private Timestamp startDate;
  private Timestamp endDate;
  private Integer priceList;
  private Integer productId;
  private Short priority;
  private Float price;
  private String curr;
}
