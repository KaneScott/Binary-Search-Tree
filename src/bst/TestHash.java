package bst;

import java.io.File;
import java.util.Scanner;

import account.AccountNode;

/**
 * 
 * @author Kane Scott. Student ID: 1298685
 *
 */
public class TestHash {
	/**
	 * Creates a hashtable of objects based on file input from Assignment 3.
	 * 
	 * @param args
	 *            args[0] = collision resolution type, linear(L) or
	 *            key-offset(K). args[1] = size of hash table to be used.
	 *            args[2] = file to create hashtable from.
	 */
	public static void main(String args[]) {
		File chosenF = null;
		int hashSize = 0;
		MyHashTable mh = null;
		// Check argument input length
		if (args.length < 3) {
			System.err.println(
					"Requires 3 inputs.\nL or K that indicates whether linear-probing or key-offset probing should be used.\nA positive integer specifying the initial capacity of the hashtable.\nThe name of a transaction file to be used as input.");
			System.exit(0);
		} else {
			hashSize = Integer.parseInt(args[1]);
			chosenF = new File(args[2]);
			// Check if specified file exists.
			if (!chosenF.exists()) {
				System.err.println("Invalid file input for second argument. File does not exist.");
				System.exit(0);
			}
			mh = new MyHashTable(hashSize);
			// Specify collision resolution method depending on argument input.
			switch (args[0]) {
			// Default is linear
			case "L":
				break;
			case "K":
				mh.setKeyOffset();
				break;
			default:
				// Throws error if collision resolution input differs from L or
				// K.
				System.err.println("Invalid input for first argument. Requires L or K only.");
				System.exit(0);
			}
		}
		try {
			Scanner sc = new Scanner(chosenF);
			Integer acc = -1;
			Float amt = null;
			String type = null;
			// Ends hashtable entries when entire file has been read or
			// loadfactor is over 80%.
			while (sc.hasNext() && mh.loadFactor() < 0.8) {
				// Gets the 3 text inputs for account, type, and amount.
				if (sc.hasNextInt())
					acc = sc.nextInt();
				if (!sc.hasNextInt())
					type = sc.next();
				if (sc.hasNextFloat())
					amt = sc.nextFloat();
				if (acc != -1 && type != null && amt != null) {
					AccountNode<Integer> found = null;
					// On any deposit or withdrawal attempt to add new node.
					// MyHashTable does not accept duplicates, so only new
					// objects will be added.
					if (((type.equals("d") || type.equals("w")))) {
						found = new AccountNode<Integer>(acc);
						mh.put(acc, found);
					}
					if (type.equals("d")) {
						found.addBalance(amt);
					} else if (type.equals("w")) {
						found.addBalance(-amt);
					} else if (type.equals("c")) {
						mh.remove(acc);
					} else {
						System.err.println("Invalid type input.");
					}
				}
			}
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println("----------RESULTS----------");
		System.err.println(mh.toString());
		System.err.println("LOAD FACTOR: " + mh.loadFactor());
		System.out.println("COLLISIONS: " + mh.getCollisions());
	}

}
