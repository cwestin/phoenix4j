package bookofbrilliantthings.phoenix4j.util.test;

import bookofbrilliantthings.phoenix4j.util.Moverator;

public class IntegerSequenceMoverator
    implements Moverator<Integer> {
  private final int increment;
  private final int last;
  private int value;

  public IntegerSequenceMoverator(int first, int last, int increment) {
    value = first - increment;
    this.last = last;
    this.increment = increment;
  }

  @Override
  public boolean moveToNext() {
    if (value >= last) {
      return false;
    }

    value += increment;
    return true;
  }

  @Override
  public Integer getCurrent() {
    return value;
  }
}
