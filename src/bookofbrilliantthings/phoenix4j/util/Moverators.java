package bookofbrilliantthings.phoenix4j.util;

import org.junit.Assert;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class Moverators {
	private Moverators() {}

	public static <T> void elementsEqual(Moverator<T> a, Moverator<T> b) {
		assertNotNull(a);
		assertNotNull(b);
		while(a.moveToNext()) {
			assertTrue(b.moveToNext());

			final T ta = a.getCurrent();
			final T tb = b.getCurrent();
			Assert.assertEquals(ta, tb);
		}
		assertFalse(b.moveToNext());
	}

	private static class Filter<T>
			implements Moverator<T> {
		private final Moverator<T> moverator;
		private final Predicate<T> predicate;

		public Filter(Moverator<T> moverator, Predicate<T> predicate) {
			this.moverator = moverator;
			this.predicate = predicate;
		}

		@Override
		public boolean moveToNext() {
			while(moverator.moveToNext()) {
				final T current = moverator.getCurrent();
				if (predicate.test(current)) {
					return true;
				}
			}

			return false;
		}

		@Override
		public T getCurrent() {
			return moverator.getCurrent();
		}
	}

	public static <T> Moverator<T> filter(Moverator<T> moverator, Predicate<T> predicate) {
		return new Filter(moverator, predicate);
	}


	private static class FromIterator<T> implements Moverator<T> {
		private final Iterator<T> n;
		private T current;

		public FromIterator(Iterator<T> n) {
			this.n = n;
		}

		@Override
		public boolean moveToNext() {
			if (!n.hasNext()) {
				return false;
			}

			current = n.next();
			return true;
		}

		@Override
		public T getCurrent() {
			return current;
		}
	}

	public static <T> Moverator<T> fromIterator(Iterator<T> j) {
		return new FromIterator<>(j);
	}

	private static class ToIterator<T> implements Iterator<T> {
		private final Moverator<T> p;
		private boolean hasNext;

		public ToIterator(Moverator<T> p) {
			this.p = p;
			hasNext = p.moveToNext();
		}

		@Override
		public boolean hasNext() {
			return hasNext;
		}

		@Override
		public T next() {
			if (!hasNext) {
				throw new NoSuchElementException();
			}

			final T current = p.getCurrent();
			hasNext = p.moveToNext();
			return current;
		}
	}

	public static <T> Iterator<T> toIterator(Moverator<T> p) {
		return new ToIterator<>(p);
	}
}
