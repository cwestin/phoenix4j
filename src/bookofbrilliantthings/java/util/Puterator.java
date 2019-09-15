package bookofbrilliantthings.java.util;

public interface Puterator<T> extends AutoCloseable {
	boolean put(T t);
}
