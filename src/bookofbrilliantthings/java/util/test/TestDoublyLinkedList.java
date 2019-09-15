package bookofbrilliantthings.java.util.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import bookofbrilliantthings.java.util.DoubleLink;
import bookofbrilliantthings.java.util.DoublyLinkedLink;
import bookofbrilliantthings.java.util.DoublyLinkedList;

public class TestDoublyLinkedList {

	public static class MyInt {
		public int i;
		public final DoubleLink<MyInt> link;

		public MyInt() {
			link = new DoubleLink<>(this);
		}

		public MyInt(int i) {
			this();
			this.i = i;
		}

		public static class GetLink implements DoublyLinkedLink<MyInt> {
			@Override
			public DoubleLink<MyInt> getLink(MyInt myInt) {
				return myInt.link;
			}
		}

		public static final GetLink GET_LINK = new GetLink();
	}

	@Test
	public void testToArray() {
		DoublyLinkedList<MyInt> list = new DoublyLinkedList<>();
		for(int i = 0; i < 10; ++i) {
			list.addLinkBefore((new MyInt(i)).link);
		}

		final MyInt[] zeroArray = new MyInt[0];
		final MyInt[] array = list.toArray(zeroArray);
		assertNotSame(array, zeroArray);
		for(int i = 0; i < 10; ++i) {
			assertEquals("array[" + i + "] == " + array[i], i, array[i].i);
		}
		final MyInt[] sizedArray = new MyInt[array.length];
		final MyInt[] sizedArrayReturn = list.toArray(sizedArray);
		if (sizedArrayReturn != sizedArray) {
			fail("sized array was not used");
		}
	}

	@Test
	public void testSize() {
		DoublyLinkedList<MyInt> list = new DoublyLinkedList<>();
		assertEquals(0, list.size());

		final int nItems = 7;
		for(int i = 0; i < nItems; ++i) {
			list.addLinkBefore((new MyInt(i)).link);
		}

		assertEquals(nItems, list.size());
	}

	@Test
	public void testContains() {
		final MyInt myInt = new MyInt(42);
		DoublyLinkedList<MyInt> list = new DoublyLinkedList<>();
		assertFalse(list.contains(myInt));

		final int nItems = 7;
		final MyInt[] theInts = new MyInt[nItems];
		for(int i = 0; i < nItems; ++i) {
			theInts[i] = new MyInt(i);
			list.addLinkBefore(theInts[i].link);
		}

		assertFalse(list.contains(myInt));
		for(int i = 0; i < nItems; ++i) {
			assertTrue(list.contains(theInts[i]));
		}
	}
}
