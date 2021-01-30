package bookofbrilliantthings.phoenix4j.util.test;

import java.util.Iterator;

public class IntegerSequenceIterator
      implements Iterator<Integer>
  {
    private final int increment;
    private final int last;
    private int value;

  public IntegerSequenceIterator(int first, int last, int increment) {
      value = first - increment;
      this.last = last;
      this.increment = increment;
    }

    @Override
    public boolean hasNext() {
      return (value < last);
    }

    @Override
    public Integer next() {
      return value += increment;
    }
  }
