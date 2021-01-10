package bookofbrilliantthings.java.util.test;

import org.junit.Test;

import bookofbrilliantthings.java.util.DoubleLink;
import bookofbrilliantthings.java.util.DoublyLinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
	}

	@Test
	public void testToArray() {
		DoublyLinkedList<MyInt> list = new DoublyLinkedList<>();
		for(int i = 0; i < 10; ++i) {
			list.addLinkBefore((new MyInt(i)).link);
		}

		final MyInt[] zeroArray = new MyInt[0];
		final MyInt[] array = list.toArray(zeroArray);
		assertTrue(zeroArray != array);
		for(int i = 0; i < 10; ++i) {
			assertEquals("array[" + i + "] == " + array[i], i, array[i].i);
		}
		final MyInt[] sizedArray = new MyInt[array.length];
		final MyInt[] sizedArrayReturn = list.toArray(sizedArray);
		if (sizedArrayReturn != sizedArray) {
			fail("sized array was not used");
		}
	}
}
