package com.poc.stomp.playground;

import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.Test;

public class Playground {

  @Test
  void testRandomNumber() {
    for (int i = 0; i <= 6; i++) {
      int randomNum = ThreadLocalRandom.current().nextInt(0, 7);
      System.out.println(randomNum);
    }
  }
}
