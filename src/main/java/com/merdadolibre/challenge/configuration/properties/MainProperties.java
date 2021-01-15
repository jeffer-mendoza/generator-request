package com.merdadolibre.challenge.configuration.properties;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Main Properties.
 *
 * @author Jefferson Mendoza, jefferson.mendoza@fonyou.com
 * @since 1.0
 */
@Getter
@Setter
@Configuration
@Valid
@ConfigurationProperties(prefix = "main")
public class MainProperties {

  @NotEmpty
  private String filename;
}
