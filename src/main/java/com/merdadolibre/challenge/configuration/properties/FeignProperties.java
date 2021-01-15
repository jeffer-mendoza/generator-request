package com.merdadolibre.challenge.configuration.properties;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Configuration
@ConfigurationProperties(prefix = "feign")
@Getter
@Setter
public class FeignProperties {

  @NotNull private int maxHttpPool;
  @NotNull private int maxConnPerRoute;
  @NotNull private Integer connectionTimeout;
  @NotNull private Integer readTimeout;
  @NotNull private int maxAttempts;
  @NotNull private int maxPeriod;
  @NotNull private int period;
  @NotNull private String url;

}
