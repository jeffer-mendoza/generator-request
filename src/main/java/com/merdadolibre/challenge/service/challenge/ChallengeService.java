package com.merdadolibre.challenge.service.challenge;

import com.merdadolibre.challenge.configuration.properties.MainProperties;
import com.merdadolibre.challenge.model.Dna;
import com.merdadolibre.challenge.service.challenge.feign.IChallengeFeign;
import com.merdadolibre.challenge.utils.AdnGenerator;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Jefferson Mendoza, jefferson.mendoza@fonyou.com
 */
@Slf4j
@AllArgsConstructor
@Service
public class ChallengeService {

  private static final String DATE_FORMATTER = "yyyyMMdd_HH";

  private MainProperties config;
  private final IChallengeFeign challengeFeign;

  /**
   * Executor test.
   */
  @Async
  public void executeTest(final int matrixN) {
    Dna request = new Dna();
    request.setDna(AdnGenerator.generator(matrixN));
    final long initTime = System.currentTimeMillis();
    boolean isMutant = false;
    try {
      challengeFeign.isMutant(request);
      isMutant = true;
    } catch (Exception ex) {
      log.error("Exception", ex);
    } finally {
      final String text = System.currentTimeMillis() - initTime + "," + isMutant;
      appendToFile(text.concat("\n"));
    }
  }


  /**
   * appendFile.
   * @param text text
   */
  @Async
  public void appendToFile(String text) {
    log.debug("text: {}", text);
    try {
      File file = new File(getCdrFullFilename());
      boolean isNewFile = file.createNewFile();
      if (!isNewFile) {
        log.debug("file already exists, we continue writting.");
      }
      Files.write(Paths.get(getCdrFullFilename()), text.getBytes(), StandardOpenOption.APPEND);
    } catch (Exception ex) {
      log.error("Error writing on file!", ex);
    }
  }

  public String getCdrFullFilename() {
    return config.getFilename() + LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMATTER)) + ".csv";
  }
}
