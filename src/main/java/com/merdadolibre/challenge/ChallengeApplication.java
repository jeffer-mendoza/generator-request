package com.merdadolibre.challenge;

import com.merdadolibre.challenge.service.challenge.ChallengeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
@Slf4j
public class ChallengeApplication implements CommandLineRunner {

  @Autowired
  ChallengeService challengeService;

  public static void main(String[] args) {
    SpringApplication.run(ChallengeApplication.class, args);
  }

  @Override
  public void run(String... args) {
    log.info("EXECUTING : command line runner");
    final int matrixN = Integer.parseInt(args[1]);
    for (int i = 0; i < Integer.parseInt(args[0]); ++i) {
      challengeService.executeTest(matrixN);
    }
  }
}
