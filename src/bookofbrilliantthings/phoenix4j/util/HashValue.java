package bookofbrilliantthings.phoenix4j.util;

public class HashValue {
	private int hash;

	private static final int SEED = 0x5ac07312;
	private static final int SHIFT_LEFT = 5;
	private static final int SHIFT_RIGHT = Integer.SIZE - SHIFT_LEFT + 1;

	public HashValue() {
		hash = SEED;
	}

	public int get() {
		return hash;
	}

	public HashValue hash(boolean v) {
		hash = ((hash << SHIFT_LEFT) | (hash >> SHIFT_RIGHT)) ^ (v ? 1 : 0);
		return this;
	}

	public HashValue hash(byte v) {
		hash = ((hash << SHIFT_LEFT) | (hash >> SHIFT_RIGHT)) ^ v;
		return this;
	}

	public HashValue hash(short v) {
		hash = ((hash << SHIFT_LEFT) | (hash >> SHIFT_RIGHT)) ^ (v >> (Short.SIZE / 2));
		hash = ((hash << SHIFT_LEFT) | (hash >> SHIFT_RIGHT)) ^ (v & 0xff);
		return this;
	}

	public HashValue hash(char v) {
		hash = ((hash << SHIFT_LEFT) | (hash >> SHIFT_RIGHT)) ^ (((short) v) >> (Short.SIZE / 2));
		hash = ((hash << SHIFT_LEFT) | (hash >> SHIFT_RIGHT)) ^ (((short) v) & 0xff);
		return this;
	}

	public HashValue hash(int v) {
		hash = ((hash << SHIFT_LEFT) | (hash >> SHIFT_RIGHT)) ^ (v >> (3 * (Integer.SIZE / 4)));
		hash = ((hash << SHIFT_LEFT) | (hash >> SHIFT_RIGHT)) ^ (v >> (2 * (Integer.SIZE / 4)));
		hash = ((hash << SHIFT_LEFT) | (hash >> SHIFT_RIGHT)) ^ (v >> (1 * (Integer.SIZE / 4)));
		hash = ((hash << SHIFT_LEFT) | (hash >> SHIFT_RIGHT)) ^ (v & 0xff);
		return this;
	}

	public HashValue hash(long v) {
		hash = ((hash << SHIFT_LEFT) | (hash >> SHIFT_RIGHT)) ^ (int) (v >> (7 * (Long.SIZE / 8)));
		hash = ((hash << SHIFT_LEFT) | (hash >> SHIFT_RIGHT)) ^ (int) (v >> (6 * (Long.SIZE / 8)));
		hash = ((hash << SHIFT_LEFT) | (hash >> SHIFT_RIGHT)) ^ (int) (v >> (5 * (Long.SIZE / 8)));
		hash = ((hash << SHIFT_LEFT) | (hash >> SHIFT_RIGHT)) ^ (int) (v >> (4 * (Long.SIZE / 8)));
		hash = ((hash << SHIFT_LEFT) | (hash >> SHIFT_RIGHT)) ^ (int) (v >> (3 * (Long.SIZE / 8)));
		hash = ((hash << SHIFT_LEFT) | (hash >> SHIFT_RIGHT)) ^ (int) (v >> (2 * (Long.SIZE / 8)));
		hash = ((hash << SHIFT_LEFT) | (hash >> SHIFT_RIGHT)) ^ (int) (v & 0xff);
		return this;
	}

	public HashValue hash(byte[] v) {
		for(byte b : v) {
			hash = ((hash << SHIFT_LEFT) | (hash >> SHIFT_RIGHT)) ^ b;
		}
		return this;
	}

	public HashValue hash(String v) {
		/*
		 * The byte array will be allocated in eden space, which will get cleaned up right away;
		 * the alternative is a loop that calls String.charAt(), which seems like it will
		 * be slower, but I haven't measured that.
		 */
		final byte[] b = v.getBytes();
		return hash(b);
	}
}
