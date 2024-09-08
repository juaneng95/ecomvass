package com.vass.inditex.infrastructure.mapper;

import com.vass.inditex.domain.model.PriceModel;
import com.vass.inditex.domain.model.PriceModelQuery;
import com.vass.inditex.infrastructure.entity.PriceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {

  PriceModel asDomainPriceModel(PriceEntity src);

  PriceModelQuery asDomainPriceModelQuery(PriceEntity src);
}
