package bookofbrilliantthings.phoenix4j.util.test;

import java.util.Iterator;
import java.util.Random;

public class RandomIntegerIterator
    implements Iterator<Integer> {
  private final Random random;

  public RandomIntegerIterator(long seed) {
    random = new Random(seed);
  }

  @Override
  public boolean hasNext() {
    return true;
  }

  @Override
  public Integer next() {
    return random.nextInt();
  }
}
