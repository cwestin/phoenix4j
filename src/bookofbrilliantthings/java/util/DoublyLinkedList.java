package bookofbrilliantthings.java.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import bookofbrilliantthings.java.lang.UnimplementedException;

public class DoublyLinkedList<T> extends PrimordialLink<T>
		implements Iterable<T>, Collection<T> {

	@Override
	public boolean isEmpty() {
		return firstLink() == null;
	}

	@SuppressWarnings("unchecked")
	public <E> E[] toArray(E[] a) {
		if (a == null) {
			throw new IllegalArgumentException("the array argument must not be null");
		}

		// Count the number of elements.
		final int count = size();

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

	private static class DoublyLinkedAltIterator<T>
			implements bookofbrilliantthings.java.util.Iterator<T> {
		private final DoublyLinkedList<T> list;
		private DoubleLink<T> currentLink;

		private DoublyLinkedAltIterator(DoublyLinkedList<T> list) {
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

	/* TODO(cwestin) */
	public bookofbrilliantthings.java.util.Iterator<T> altIterator()
	{
		return new DoublyLinkedAltIterator<T>(this);
	}

	private static class DoublyLinkedIterator<T> implements Iterator<T> {
		private DoubleLink<T> nextLink;
		private final DoublyLinkedList<T> list;

		public DoublyLinkedIterator(DoublyLinkedList<T> list) {
			this.list = list;
			nextLink = list.firstLink();
		}

		@Override
		public boolean hasNext() {
			return nextLink != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			final DoubleLink<T> returnValue = nextLink;
			nextLink = nextLink.nextLink(list);
			return returnValue.getOwner();
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new DoublyLinkedIterator<T>(this);
	}

	@Override
	public int size() {
		int count = 0;
		for(DoubleLink<T> link = firstLink(); link != null; link = link.nextLink(this)) {
			++count;
		}

		return count;
	}

	@Override
	public boolean contains(Object o) {
		for(DoubleLink<T> link = firstLink(); link != null; link = link.nextLink(this)) {
			final T owner = link.getOwner();
			if (owner == o) {
				return true;
			}
		}

		// If we got here, we didn't find it.
		return false;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(T e) {
		/*
		 * We need the pointer to the link. This could be done
		 * with reflection; we could use a method to get the link.
		 * We could also be given a closure that would return the
		 * link for an object of type T.
		 */
		throw new UnimplementedException();
	}

	/**
	 * TODO
	 * @param link
	 * @return
	 */
	public boolean addLink(DoubleLink<T> link) {
		addLinkBefore(link);
		return true;
	}

	@Override
	public boolean remove(Object o) {
		for(DoubleLink<T> link = firstLink(); link != null; link = link.nextLink(this)) {
			final T owner = link.getOwner();
			if (owner == o) {
				link.remove();
				return true;
			}
		}

		// If we got here, we didn't find it.
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}
}
