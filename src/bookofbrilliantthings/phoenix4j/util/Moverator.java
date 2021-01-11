package bookofbrilliantthings.phoenix4j.util;

public interface Moverator<T> {
	boolean moveToNext();
	T getCurrent() /* TODO throws NoSuchElementException */;
}
