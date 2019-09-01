package bookofbrilliantthings.java.util;

/* package */ class PrimordialLink<T> {
	protected PrimordialLink<T> next;
	protected PrimordialLink<T> previous;

	protected void reset() {
		next = this;
		previous = this;
	}

	public PrimordialLink() {
		reset();
	}

	public DoubleLink<T> nextLink(DoublyLinkedList<T> list) {
		/*
		 * Because addLinkAfter() and addLinkBefore() only accept
		 * DoubleLink<T>, we know that this value must have originally
		 * had that type.
		 */
		return next == list ? null : (DoubleLink<T>) next;
	}

	public DoubleLink<T> previousLink(DoublyLinkedList<T> list) {
		/*
		 * Because addLinkAfter() and addLinkBefore() only accept
		 * DoubleLink<T>, we know that this value must have originally
		 * had that type.
		 */
		return previous == list ? null : (DoubleLink<T>) previous;
	}

	public void addLinkAfter(DoubleLink<T> newLink) {
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

