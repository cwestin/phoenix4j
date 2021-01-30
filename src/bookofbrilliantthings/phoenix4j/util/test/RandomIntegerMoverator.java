package bookofbrilliantthings.phoenix4j.util.test;

import bookofbrilliantthings.phoenix4j.util.Moverator;

import java.util.NoSuchElementException;
import java.util.Random;

public class RandomIntegerMoverator
    implements Moverator<Integer> {
  private final Random random;
  private boolean haveCurrent = false;
  private int current;

  public RandomIntegerMoverator(long seed) {
    random = new Random(seed);
  }

  @Override
  public boolean moveToNext() {
    current = random.nextInt();
    haveCurrent = true;
    return true;
  }

  @Override
  public Integer getCurrent() {
    if (!haveCurrent) {
      throw new NoSuchElementException();
    }

    return current;
  }
}
