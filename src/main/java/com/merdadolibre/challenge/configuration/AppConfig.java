package com.merdadolibre.challenge.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author Jefferson Mendoza, jefferson.mendoza@fonyou.com
 */
@Configuration
@EnableAsync
@Slf4j
public class AppConfig {

  /**
   * Load Business External Configuration.
   */
  @Configuration
  @PropertySource(value = "file:${business.properties.path}", encoding = "UTF-8")
  static class BasicBusinessProperties
  {}

  /**
   * Thread Pool Executer Bean.
   * @return Thread Pool Executer Bean
   */
  @Bean("taskExecutorhandle")
  public TaskExecutor taskExecutor(@Value("${main.threadPool.poolCoreSize}") int poolSize,
                                   @Value("${main.threadPool.maxPoolSize}") int maxPoolSize,
                                   @Value("${main.threadPool.threadNamePrefix}") String threadNamePrefix,
                                   @Value("${main.threadPool.keepAliveSeconds}") int keeAliveSeconds

  ) {
    log.info("ThreadPool: poolSize: {}, maxPoolSize: {}, threadNamePrefix: {}, keepAliveSeconds: {}", poolSize,
        maxPoolSize, threadNamePrefix, keeAliveSeconds);
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(poolSize);
    executor.setMaxPoolSize(maxPoolSize);
    executor.setThreadNamePrefix(threadNamePrefix);
    executor.setKeepAliveSeconds(keeAliveSeconds);
    executor.initialize();
    return executor;
  }
}
