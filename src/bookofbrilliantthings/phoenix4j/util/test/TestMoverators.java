package bookofbrilliantthings.phoenix4j.util.test;

import bookofbrilliantthings.phoenix4j.util.Moverators;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class TestMoverators {

	@Test
	public void testIntegerSequenceMoverator() {
		final IntegerSequenceMoverator is = new IntegerSequenceMoverator(0, 9, 1);
		int check = 0;
		while(is.moveToNext()) {
			final int i = is.getCurrent();
			Assert.assertEquals(i, check);
			++check;
		}
		Assert.assertEquals(10, check);
		assertFalse(is.moveToNext());
	}

	@Test
	public void test_toIterator() {
		final IntegerSequenceMoverator is = new IntegerSequenceMoverator(0, 9, 1);
		final Iterable<Integer> jiterable = new Iterable<Integer>() {
			@Override
			public Iterator<Integer> iterator() {
				return Moverators.toIterator(is);
			}
		};

		int check = 0;
		for(int i : jiterable) {
			Assert.assertEquals(i, check);
			++check;
		}
		Assert.assertEquals(10, check);
	}

	public static <T> void assertEquals(Iterator<T> a, Iterator<T> b) {
		assertNotNull(a);
		assertNotNull(b);
		while(a.hasNext()) {
			assertTrue(b.hasNext());

			final T ta = a.next();
			final T tb = b.next();
			Assert.assertEquals(ta, tb);
		}
		assertFalse(b.hasNext());
	}
}
