package bookofbrilliantthings.java.util;

import java.util.NoSuchElementException;

public class Iterators {
	private Iterators() {}

	private static class NativeToPhoenix<T> implements bookofbrilliantthings.java.util.Iterator<T> {
		private final java.util.Iterator<T> n;
		private T current;

		public NativeToPhoenix(java.util.Iterator<T> n) {
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

	public static <T> bookofbrilliantthings.java.util.Iterator<T> nativeToPhoenix(java.util.Iterator<T> j) {
		return new NativeToPhoenix<T>(j);
	}

	private static class PhoenixToNative<T> implements java.util.Iterator<T> {
		private final bookofbrilliantthings.java.util.Iterator<T> p;
		private boolean hasNext;

		public PhoenixToNative(bookofbrilliantthings.java.util.Iterator<T> p) {
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

	public static <T> java.util.Iterator<T> phoenixToNative(bookofbrilliantthings.java.util.Iterator<T> p) {
		return new PhoenixToNative<T>(p);
	}
}
