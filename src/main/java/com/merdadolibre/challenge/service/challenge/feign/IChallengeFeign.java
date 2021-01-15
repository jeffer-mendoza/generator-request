package com.merdadolibre.challenge.service.challenge.feign;

import com.merdadolibre.challenge.configuration.FeignClientConfiguration;
import com.merdadolibre.challenge.configuration.properties.FeignProperties;
import feign.Headers;
import feign.RequestLine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

/**
 * @author Jefferson Mendoza, jefferson.mendoza@fonyou.com
 */
public interface IChallengeFeign {

  @RequestLine("POST /")
  @Headers({"Content-Type: application/json", "Accept: application/json"})
  ResponseEntity<Object> isMutant(Object request);


  @Configuration
  public static class Config {

    /**
     * payUTransactionApi.
     *
     * @param feignBuilderType FeignBuilderType
     * @param feignProperties feignProperties
     * @return PayUApi
     */
    @Bean
    public IChallengeFeign challengeFeign(FeignClientConfiguration.FeignBuilderType feignBuilderType,
                                          FeignProperties feignProperties) {
      return feignBuilderType.build(IChallengeFeign.class, feignProperties.getUrl());
    }
  }
}
