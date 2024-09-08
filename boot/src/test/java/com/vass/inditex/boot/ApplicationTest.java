package com.vass.inditex.boot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.vass.inditex.application.model.PriceDto;
import com.vass.inditex.application.model.PriceQueryDto;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTest {

  @LocalServerPort int port;
  private String baseUrl = "http://localhost";
  private static RestTemplate restTemplate;

  @BeforeAll
  static void init() {
    System.setProperty("BBDD_USER", "admin");
    System.setProperty("BBDD_PWD", "admin");
    restTemplate = new RestTemplate();
  }

  @BeforeEach
  void beforeSetup() {
    baseUrl = baseUrl.concat(":" + port);
  }

  @Test
  void getPriceList_OK() {
    List<PriceDto> priceDtoList =
        restTemplate
            .exchange(
                baseUrl.concat("/ecom/list"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PriceDto>>() {})
            .getBody();

    assertNotNull(priceDtoList);
    assertEquals(7, priceDtoList.size());
  }

  @Test
  void getPriceListById_QueryTesting() {

    // - Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    PriceQueryDto priceQueryDto =
        restTemplate
            .exchange(
                baseUrl.concat(
                    "/ecom/list/price?requiredDate=2020-06-14 10:00:00&productId=35455&brandId=1"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PriceQueryDto>() {})
            .getBody();
    assertNotNull(priceQueryDto);

    // - Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    priceQueryDto =
        restTemplate
            .exchange(
                baseUrl.concat(
                    "/ecom/list/price?requiredDate=2020-06-14 16:00:00&productId=35455&brandId=1"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PriceQueryDto>() {})
            .getBody();
    assertNotNull(priceQueryDto);

    // - Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    priceQueryDto =
        restTemplate
            .exchange(
                baseUrl.concat(
                    "/ecom/list/price?requiredDate=2020-06-14 21:00:00&productId=35455&brandId=1"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PriceQueryDto>() {})
            .getBody();
    assertNotNull(priceQueryDto);

    // - Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
    priceQueryDto =
        restTemplate
            .exchange(
                baseUrl.concat(
                    "/ecom/list/price?requiredDate=2020-06-15 10:00:00&productId=35455&brandId=1"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PriceQueryDto>() {})
            .getBody();
    assertNotNull(priceQueryDto);

    // -  Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
    priceQueryDto =
        restTemplate
            .exchange(
                baseUrl.concat(
                    "/ecom/list/price?requiredDate=2020-06-16 21:00:00&productId=35455&brandId=1"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PriceQueryDto>() {})
            .getBody();
    assertNotNull(priceQueryDto);
  }

}
