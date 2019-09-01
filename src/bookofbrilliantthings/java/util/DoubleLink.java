package bookofbrilliantthings.java.util;

public class DoubleLink<T> extends PrimordialLink<T> {
	private final T owner;

	public DoubleLink(T owner) {
		this.owner = owner;
	}

	public T getOwner() {
		return owner;
	}

	public void unsafeRemove() {
		next.previous = previous;
		previous.next = next;
	}

	public void remove() {
		unsafeRemove();
		reset();
	}
}
