package com.vass.inditex.application.mapper;

import com.vass.inditex.application.model.PriceDto;
import com.vass.inditex.application.model.PriceQueryDto;
import com.vass.inditex.domain.model.PriceModel;
import com.vass.inditex.domain.model.PriceModelQuery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestMapper {

  // Fixing timestamp mapping error
  @Mapping(target = "startDate", dateFormat = "yyyy-MM-dd' 'HH:mm:ss")
  @Mapping(target = "endDate", dateFormat = "yyyy-MM-dd' 'HH:mm:ss")
  PriceDto toDto(PriceModel src);

  // Fixing timestamp mapping error
  @Mapping(target = "startDate", dateFormat = "yyyy-MM-dd' 'HH:mm:ss")
  @Mapping(target = "endDate", dateFormat = "yyyy-MM-dd' 'HH:mm:ss")
  PriceQueryDto toQueryDto(PriceModelQuery src);
}
