package bookofbrilliantthings.java.util;

public class DoubleLink<T> {
	private final T owner;
	private DoubleLink<T> next;
	private DoubleLink<T> previous;

	protected DoubleLink() {
		this(null);
	}

	protected void reset() {
		next = this;
		previous = this;
	}

	public DoubleLink(T owner) {
		this.owner = owner;
		reset();
	}

	public T getOwner() {
		return owner;
	}

	public DoubleLink<T> nextLink(DoublyLinkedList<T> list) {
		return next == list ? null : next;
	}

	public DoubleLink<T> previousLink(DoublyLinkedList<T> list) {
		return previous == list ? null : previous;
	}

	public void unsafeRemove() {
		next.previous = previous;
		previous.next = next;
	}

	public void remove() {
		unsafeRemove();
		reset();
	}

	public void addLinkAfter(DoubleLink<T> newLink)
	{
		newLink.next = next;
		newLink.previous = this;
		next.previous = newLink;
		next = newLink;
	}

	public void addLinkBefore(DoubleLink<T> newLink) {
		newLink.next = this;
		newLink.previous = previous;
		previous.next = newLink;
		previous = newLink;
	}
}
