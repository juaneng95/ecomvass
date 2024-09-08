package com.vass.inditex.application.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.vass.inditex.application.handler.ApiExceptionHandler;
import com.vass.inditex.application.mapper.RestMapper;
import com.vass.inditex.application.model.PriceDto;
import com.vass.inditex.application.model.PriceQueryDto;
import com.vass.inditex.domain.model.PriceModel;
import com.vass.inditex.domain.model.PriceModelQuery;
import com.vass.inditex.domain.port.out.PriceUseCase;
import java.sql.Timestamp;
import java.util.List;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {PriceUseCase.class})
class EcomControllerTest {

  private MockMvc mockMvc;
  @MockBean PriceUseCase priceUseCase;
  @MockBean RestMapper restMapper;

  // Random objects.
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

  final PriceModelQuery priceModelQuery = easyRandom.nextObject(PriceModelQuery.class);
  final PriceDto priceDto = easyRandom.nextObject(PriceDto.class);
  final PriceQueryDto priceQueryDto = easyRandom.nextObject(PriceQueryDto.class);
  final List<PriceModel> priceModelList = easyRandom.objects(PriceModel.class, 5).toList();

  // Values
  final String requiredDate = "2020-06-14 15:00:00";
  final String pathGetAllPrices = "/ecom/list";
  final String pathGetPriceByCriteriaQuery = "/ecom/list/price";

  @BeforeEach
  void setUp() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(new EcomController(priceUseCase, restMapper))
            .setControllerAdvice(new ApiExceptionHandler())
            .build();
  }

  @Test
  void getAllPrices_OK() throws Exception {

    // WHEN
    when(priceUseCase.getAllPrices()).thenReturn(priceModelList);
    when(restMapper.toDto(any(PriceModel.class))).thenReturn(priceDto);

    // THEN
    mockMvc
        .perform(
            get(pathGetAllPrices)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void getPriceByCriteriaQuery_OK() throws Exception {

    // WHEN
    when(priceUseCase.getPriceByCriteriaQuery(
            any(Timestamp.class), any(Integer.class), any(Long.class)))
        .thenReturn(priceModelQuery);
    when(restMapper.toQueryDto(priceModelQuery)).thenReturn(priceQueryDto);

    // THEN
    mockMvc
        .perform(
            get(pathGetPriceByCriteriaQuery)
                .param("requiredDate", requiredDate)
                .param("productId", String.valueOf(priceModelQuery.getProductId()))
                .param("brandId", String.valueOf(priceModelQuery.getBrandId()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void getPriceByCriteriaQuery_MethodWithoutParameter() throws Exception {

    // WHEN
    when(priceUseCase.getPriceByCriteriaQuery(
            any(Timestamp.class), any(Integer.class), any(Long.class)))
        .thenReturn(null);

    // THEN
    mockMvc
        .perform(
            get(pathGetPriceByCriteriaQuery)
                .param("requiredDate", requiredDate)
                .param("brandId", String.valueOf(priceQueryDto.getBrandId()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }
}
