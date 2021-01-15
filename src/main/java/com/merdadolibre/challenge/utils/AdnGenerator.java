package com.merdadolibre.challenge.utils;

import java.util.Random;

/**
 * @author Jefferson Mendoza, jefferson.mendoza@fonyou.com
 */
public class AdnGenerator {

  /**
   * Genetator String with 1 o many elements.
   * @param matrixN level matrix
   * @return array
   */
  public static String[] generator(final int matrixN) {
    char[] chars = "ATGC".toCharArray();
    Random random = new Random();
    String[] result = new String[matrixN];
    char letter;
    for (int i = 0; i < matrixN; i++) {
      StringBuilder sb = new StringBuilder(matrixN);
      for (int j = 0; j < matrixN; j++) {
        letter = chars[random.nextInt(chars.length)];
        sb.append(letter);
      }
      result[i] = sb.toString();
    }
    return result;
  }
}
