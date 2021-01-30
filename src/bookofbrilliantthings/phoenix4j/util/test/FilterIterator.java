package bookofbrilliantthings.phoenix4j.util.test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class FilterIterator<T>
    implements Iterator<T> {
  private final Iterator<T> iterator;
  private final Predicate<T> predicate;
  private boolean haveNext = false;
  private T theNext;

  public FilterIterator(Iterator<T> iterator, Predicate<T> predicate) {
    this.iterator = iterator;
    this.predicate = predicate;
  }

  @Override
  public boolean hasNext() {
    if (haveNext) {
      return true;
    }

    while(iterator.hasNext()) {
      final T itsNext = iterator.next();
      if (predicate.test(itsNext)) {
        theNext = itsNext;
        haveNext = true;
        break;
      }
    }
    return false;
  }

  @Override
  public T next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }

    haveNext = false;
    return theNext;
  }
}
