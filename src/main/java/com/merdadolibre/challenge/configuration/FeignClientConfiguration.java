package com.merdadolibre.challenge.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.merdadolibre.challenge.configuration.properties.FeignProperties;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import java.util.concurrent.TimeUnit;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@AllArgsConstructor
@Configuration
@Slf4j
public class FeignClientConfiguration {


  @Valid
  private final FeignProperties feignProperties;

  /**
   * okHttpClient.
   *
   * @return OkHttpClient
   */
  @Bean
  public OkHttpClient okHttpClient() {
    return new OkHttpClient();
  }

  /**
   * Generic builder for Feign Clients.
   *
   * @param client client
   *
   * @return FeignBuilderType instance
   */
  @Bean
  public FeignBuilderType genericBuilder(final ObjectMapper mapper, final OkHttpClient client) {
    return new FeignBuilderType() {

      @Override
      public <T> T build(Class<T> clazz, String url) {

        return Feign.builder()
            .client(client)
            .retryer(new Retryer.Default(feignProperties.getPeriod(), feignProperties.getMaxPeriod(),
                feignProperties.getMaxAttempts()))
            .encoder(new JacksonEncoder(mapper))
            .decoder(new JacksonDecoder(mapper))
            .options(
                new Request.Options(
                    feignProperties.getConnectionTimeout(), TimeUnit.MILLISECONDS,
                    feignProperties.getReadTimeout(), TimeUnit.MILLISECONDS,
                    true))
            .logger((new Slf4jLogger(clazz)))
            .logLevel(Logger.Level.FULL)
            .target(clazz, url);
      }
    };
  }

  @FunctionalInterface
  public interface FeignBuilderType {

    <T> T build(Class<T> clazz, String url);
  }
}
