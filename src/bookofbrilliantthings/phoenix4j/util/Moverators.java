package bookofbrilliantthings.phoenix4j.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Moverators {
	private Moverators() {}

	private static class ToMoverator<T> implements Moverator<T> {
		private final Iterator<T> n;
		private T current;

		public ToMoverator(Iterator<T> n) {
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

	public static <T> Moverator<T> toMoverator(Iterator<T> j) {
		return new ToMoverator<>(j);
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
