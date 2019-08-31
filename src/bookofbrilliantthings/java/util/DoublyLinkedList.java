package bookofbrilliantthings.java.util;

import java.lang.reflect.Array;
import java.util.Arrays;

import bookofbrilliantthings.java.lang.Iterable;

public class DoublyLinkedList<T>
		extends DoubleLink<T>
		implements Iterable<T> {

	public void unsafeRemove() {
		throw new UnsupportedOperationException();
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

	public boolean isEmpty() {
		return firstLink() == null;
	}

	public <E> E[] toArray(E[] a) {
		if (a == null) {
			throw new IllegalArgumentException("the array argument must not be null");
		}

		// Count the number of elements.
		int count = 0;
		for(DoubleLink<T> link = firstLink(); link != null; link = link.nextLink(this)) {
			++count;
		}

		/*
		 *  If the provided array isn't big enough, reallocate it.
		 *  If it's bigger than we need, fill the remainder with nulls.
		 */
		final Class<?> aClass = a.getClass().getComponentType();
		if (a.length < count) {
			a = ((E[]) Array.newInstance(aClass, count));
		} else if (a.length > count) {
			Arrays.fill(a, count, a.length - 1, null);
		}

		int index = 0;
		for(DoubleLink<T> link = firstLink(); link != null; link = link.nextLink(this)) {
			final Object owner = link.getOwner();
			if (!aClass.isInstance(owner)) {
				throw new ClassCastException("Object at index " + index + " has class "
						+ owner.getClass() + " but requested array class is " + aClass);
			}
			a[index++] = (E) owner;
		}

		return a;
	}

	public T first() {
		return firstLink().getOwner();
	}

	public DoubleLink<T> firstLink() {
		return nextLink(this);
	}

	private static class DoublyLinkedIterator<T> implements Iterator<T> {
		private final DoublyLinkedList<T> list;
		private DoubleLink<T> currentLink;

		private DoublyLinkedIterator(DoublyLinkedList<T> list) {
			this.list = list;
		}

		@Override
		public boolean moveToNext() {
			if (currentLink == null) {
				return false;
			}

			currentLink = currentLink.nextLink(list);
			return currentLink != null;
		}

		@Override
		public T getCurrent() {
			return currentLink == null ? null : currentLink.getOwner();
		}
	}

	public Iterator<T> iterator()
	{
		return new DoublyLinkedIterator<T>(this);
	}
}
