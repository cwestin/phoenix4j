package bookofbrilliantthings.java.util;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

public class PutGetBuffer<T> implements Puterator<T>, java.util.Iterator<T> {
	private boolean isClosed;
	private final T[] buffer;
	private int writeIndex;
	private int readIndex;


	public PutGetBuffer(Class<?> clazz, int size) {
		buffer = (T[]) Array.newInstance(clazz, size);
	}

	@Override
	public void close() throws Exception {
		isClosed = true;
	}

	public boolean canPut() {
		return !isClosed && (writeIndex + 1) % buffer.length != readIndex;
	}

	@Override
	public boolean put(T t) {
		if (!canPut()) {
			return false;
		}

		buffer[writeIndex++] = t;
		return true;
	}

	@Override
	public boolean hasNext() {
		return readIndex != writeIndex;
	}

	@Override
	public T next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}

		final T t = buffer[readIndex++];
		if (readIndex >= buffer.length)
			readIndex = 0;
		return t;
	}
}
