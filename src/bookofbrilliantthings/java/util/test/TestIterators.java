package bookofbrilliantthings.java.util.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Iterator;

import org.junit.Test;

import bookofbrilliantthings.java.util.Iterators;

public class TestIterators {
	public static class IntegerSequence
			implements bookofbrilliantthings.java.util.Iterator<Integer> {
		private final int increment;
		private final int last;
		private int value;

		public IntegerSequence(int first, int last, int increment) {
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

	@Test
	public void testSequence() {
		final IntegerSequence is = new IntegerSequence(0, 9, 1);
		int check = 0;
		while(is.moveToNext()) {
			final int i = is.getCurrent();
			assertEquals(i, check);
			++check;
		}
		assertEquals(10, check);
		assertFalse(is.moveToNext());
	}

	@Test
	public void testPhoenixToNative() {
		final IntegerSequence is = new IntegerSequence(0, 9, 1);
		final java.lang.Iterable<Integer> jiterable = new java.lang.Iterable<Integer>() {
			@Override
			public java.util.Iterator<Integer> iterator() {
				return Iterators.phoenixToNative(is);
			}
		};
		int check = 0;
		for(int i : jiterable) {
			assertEquals(i, check);
			++check;
		}
		assertEquals(10, check);
	}
}
