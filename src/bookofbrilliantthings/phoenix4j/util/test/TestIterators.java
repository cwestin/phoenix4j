package bookofbrilliantthings.phoenix4j.util.test;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class TestIterators {

  @Test
  public void test_IntegerSequenceIterator() {
    final IntegerSequenceIterator is = new IntegerSequenceIterator(0, 9, 1);
    int check = 0;
    while(is.hasNext()) {
      final int i = is.next();
      Assert.assertEquals(i, check);
      ++check;
    }
    Assert.assertEquals(10, check);
    assertFalse(is.hasNext());
  }
}
