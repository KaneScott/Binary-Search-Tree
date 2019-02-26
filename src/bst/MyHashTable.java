package bst;

/**
 * 
 * @author Kane Scott. Student ID: 1298685
 *
 */
public class MyHashTable {
	private int capa, used, col;
	private int[] kList;
	private Object[] oList;
	private boolean linear;

	/**
	 * Creates a hashtable with specified capacity. Default collision resolution
	 * is set to linear. Collision resolution can be changed via setLinear() and
	 * setKeyOffset(), to change to respective collision resolution methods.
	 * 
	 * @param size
	 *            int capacity of hash table.
	 */
	public MyHashTable(int size) {
		capa = size;
		used = 0;
		col = 0;
		linear = true;
		kList = new int[size];
		oList = new Object[size];
	}

	/**
	 * Use linear collision resolution.
	 */
	public void setLinear() {
		linear = true;
	}

	/**
	 * Use key-offset collision resolution.
	 */
	public void setKeyOffset() {
		linear = false;
	}

	/**
	 * Adds element with specified key using key-offset collision resolution, or
	 * linear collision resolution. Collision resolution methods defined in
	 * setKeyOffset() and setLinear().
	 * 
	 * @param d
	 *            element to be added
	 * @param k
	 *            key of element to be added
	 */
	public void put(int k, Object d) {
		if (linear)
			put_linear(k, d);
		else
			put_keyOffset(k, d);

	}

	/**
	 * Returns element with specified key using key-offset collision resolution,
	 * or linear collision resolution. Collision resolution methods defined in
	 * setKeyOffset() and setLinear().
	 * 
	 * @param k
	 *            key of element to be returned
	 */
	public Object get(int k) {
		if (linear)
			return get_linear(k);
		else
			return get_keyOffset(k);
	}

	/**
	 * Removes element with specified key using key-offset collision resolution,
	 * or linear collision resolution. Collision resolution methods defined in
	 * setKeyOffset() and setLinear().
	 * 
	 * @param k
	 *            key of element to be removed
	 */
	public void remove(int k) {
		if (linear)
			remove_linear(k);
		else
			remove_keyOffset(k);
	}

	/**
	 * Adds the object with specified key to the hash table. Uses key-offset
	 * resolution. Will not add duplicate elements.
	 * 
	 * @param k
	 *            key of element to be added
	 * @param d
	 *            element to be added
	 */
	private void put_keyOffset(int k, Object d) {
		double hash = k % capa;
		if (oList[(int) hash] == null) {
			kList[(int) hash] = k;
			oList[(int) hash] = d;
		}
		// No duplicates allowed
		else if (kList[(int) hash] == k) {
			return;
		}
		// Collision resolution
		else {
			double colPnt = hash;
			while (oList[(int) hash] != null) {
				col++;
				hash += Math.max(1, (k / capa) % capa);
				// Wrap-around
				if (hash >= capa)
					hash -= capa;
				// Encountered initial collision, full table.
				if (hash == colPnt)
					return;
				// No duplicates allowed.
				if (kList[(int) hash] == k) {
					System.out.println("Already exists");
					return;
				}
			}
			kList[(int) hash] = k;
			oList[(int) hash] = d;
		}
		used++;
	}

	/**
	 * Returns element with specified key using key-offset collision resolution.
	 * 
	 * @param k
	 *            key of element to be returned
	 * @return found element, or null if not found
	 */
	private Object get_keyOffset(int k) {
		int hash = k % capa;
		if (kList[hash] == k)
			return oList[hash];
		else {
			int colPnt = hash;
			while (kList[hash] != k) {
				col++;
				hash += Math.max(1, (k / capa) % capa);
				// Wrap-around
				if (hash >= capa) {
					hash -= capa;
				}
				// Encountered initial collision, full table or object does not
				// exist.
				if (hash == colPnt)
					return null;
			}
			return oList[hash];
		}
	}

	/**
	 * Removes element with specified key using key-offset collision resolution.
	 * 
	 * @param k
	 *            key of element to be removed
	 */
	private void remove_keyOffset(int k) {
		int hash = k % capa;
		if (kList[hash] != k) {
			int colPnt = hash;
			while (kList[hash] != k) {
				col++;
				hash += Math.max(1, (k / capa) % capa);
				// Wrap-around
				if (hash >= capa)
					hash -= capa;
				// Encountered initial collision, object does not exist
				if (hash == colPnt)
					return;
			}
		}
		kList[hash] = -1;
		oList[hash] = null;
		used--;
	}

	/**
	 * Adds the object with specified key to the hash table. Uses linear
	 * collision resolution. Will not add duplicate elements.
	 * 
	 * @param k
	 *            key of element to be added
	 * @param d
	 *            element to be added
	 */
	private void put_linear(int k, Object d) {
		int hash = k % capa;
		if (oList[hash] == null) {
			kList[hash] = k;
			oList[hash] = d;
		}
		// No duplicates allowed
		else if (kList[hash] == k) {
			return;
		} else {
			int colPnt = hash;
			while (oList[hash] != null) {
				col++;
				hash++;
				// Wrap-around
				if (hash >= capa) {
					System.out.println("Wrap");
					hash -= capa;
				}
				// Encountered initial collision, full table
				if (hash == colPnt)
					return;
				// No duplicates allowed
				if (kList[hash] == k) {
					return;
				}
			}
			kList[hash] = k;
			oList[hash] = d;
		}
		used++;
	}

	/**
	 * Returns element with specified key using linear collision resolution.
	 * 
	 * @param k
	 *            key of element to be returned
	 * @return found element, or null if not found
	 */
	private Object get_linear(int k) {
		int hash = k % capa;
		if (kList[hash] == k)
			return oList[hash];
		else {
			int colPnt = hash;
			while (kList[hash] != k) {
				col++;
				hash++;
				// Wrap-around
				if (hash >= capa) {
					hash -= capa;
				}
				// Encountered initial collision, full table or object does not
				// exist
				if (hash == colPnt)
					return null;
			}
			return oList[hash];
		}
	}

	/**
	 * Removes element with specified key using linear collision resolution.
	 * 
	 * @param k
	 *            key of element to be removed
	 */
	private void remove_linear(int k) {
		int hash = k % capa;
		if (kList[hash] != k) {
			int colPnt = hash;
			while (kList[hash] != k) {
				col++;
				hash++;
				// Wrap-around
				if (hash >= capa) {
					hash -= capa;
				}
				// Encountered initial collision, object does not exist
				if (hash == colPnt)
					return;
			}
		}
		kList[hash] = -1;
		oList[hash] = null;
		used--;
	}

	/**
	 * Returns a float representing the load factor of the hash table.
	 * 
	 * @return load factor value
	 */
	public float loadFactor() {
		return (float) used / capa;
	}

	/**
	 * Returns a string describing the elements held within the hash table.
	 */
	public String toString() {
		String s = "";
		int i = 0;
		for (Object o : oList) {
			if (o != null) {
				s += o.toString() + "\ti: " + i + "\n";
				i++;
			}

		}
		return s;
	}

	/**
	 * Returns the amount of comparison collisions that have occurred during use
	 * of this hash table.
	 * 
	 * @return the integer amount of collisions
	 */
	public int getCollisions() {
		return col;
	}
}
