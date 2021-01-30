package bookofbrilliantthings.phoenix4j.util.test;

import bookofbrilliantthings.phoenix4j.util.Moverator;
import bookofbrilliantthings.phoenix4j.util.Moverators;

import com.google.common.collect.Iterators;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class TestMoverators {

	private void checkIntegerSequenceMoverator(final Moverator<Integer> is) {
		int check = 0;
		while(is.moveToNext()) {
			final int i = is.getCurrent();
			Assert.assertEquals(i, check);
			++check;
		}
		Assert.assertEquals(10, check);
		assertFalse(is.moveToNext());
	}

	@Test
	public void test_IntegerSequenceMoverator() {
		final IntegerSequenceMoverator is = new IntegerSequenceMoverator(0, 9, 1);
		checkIntegerSequenceMoverator(is);
	}

	@Test
	public void test_toIterator() {
		final Iterable<Integer> jiterable =
				() -> Moverators.toIterator(new IntegerSequenceMoverator(0, 9, 1));

		int check = 0;
		for(int i : jiterable) {
			Assert.assertEquals(i, check);
			++check;
		}
		Assert.assertEquals(10, check);
	}

	@Test
	public void test_iterationCheckSequences() {
		final IntegerSequenceMoverator ism = new IntegerSequenceMoverator(0, 9, 1);
		final Iterator<Integer> ismi = Moverators.toIterator(ism);
		final IntegerSequenceIterator is = new IntegerSequenceIterator(0, 9, 1);
		assertTrue(Iterators.elementsEqual(ismi, is));
	}

	@Test
	public void test_toMoverator() {
		final Moverator<Integer> moverator = Moverators.fromIterator(new IntegerSequenceIterator(0, 9, 1));
		checkIntegerSequenceMoverator(moverator);
	}

	@Test
	public void test_moverationCheckSequences() {
		final IntegerSequenceMoverator ism = new IntegerSequenceMoverator(0, 9, 1);
		final IntegerSequenceIterator is = new IntegerSequenceIterator(0, 9, 1);
		final Moverator<Integer> isim = Moverators.fromIterator(is);
		Moverators.elementsEqual(ism, isim);
	}
}
