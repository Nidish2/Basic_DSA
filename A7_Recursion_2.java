package dsa;

import java.util.HashSet;

public class A7_Recursion_2 {
	void towerOfHanoi(int n, String src, String helper, String dest) {
		if (n == 1) {
			System.out.println("Tranfer " + n + " disk from " + src + " to " + dest);
			return;
		}
		towerOfHanoi(n - 1, src, dest, helper);
		System.out.println("Tranfer " + n + " disk from " + src + " to " + dest);
		towerOfHanoi(n - 1, helper, src, dest);
	}

	void stringReverse(int i, String s) {
		if (i < 0)
			return;

		System.out.println(s.charAt(i));
		stringReverse(i - 1, s);
//		System.out.println(s.charAt(i));

	}

	// To find first and last occurrence;
	public static int f = -1, l = -1;

	void lastOccur(int i, String s, char c) {
		if (i == s.length()) {
			if (f != -1) {
				System.out.println("First occurrence of " + c + " is @ " + f + " and last @ " + l);
			} else {
				System.out.println(c + " is not found");
			}
			return;
		}
		if (s.charAt(i) == c) {
			if (f == -1) {
				f = i;
			}
			l = i; // Update last occurrence whenever the character is found
		}
		lastOccur(i + 1, s, c);
	}

	void findOccur(int i, String s, char c) {
		if (i == s.length()) {
			System.out.println("First occurrence of " + c + " is @ " + f + " and last @ " + l);
			return;
		}
		if (s.charAt(i) == c) {
			if (f == -1)
				f = i;
			l = i;
		}
		findOccur(i + 1, s, c);
	}

	boolean isSorted(int i, int[] arr) {
		if (i == arr.length - 1)
			return true;
		if (arr[i] < arr[i + 1])
			return isSorted(i + 1, arr);
		else
			return false;
	}

	// Moving a character to last of the String;
	public static String n = "";

	public static String movXL(int i, String s, char c) {
		if (i == s.length())
			return n;
		if (s.charAt(i) != c)
			n += s.charAt(i);
		movXL(i + 1, s, c);
		if (s.charAt(i) == c)
			n += c;
		return n;
	}

	// Removing the duplicates through hashing;(ht=hash table);
	public static boolean ht[] = new boolean[26];

	public static String rmDup(int i, String s) {
		if (i == s.length())
			return n;
		if (!ht[s.charAt(i) - 'a']) {
			ht[s.charAt(i) - 'a'] = true;
			n += s.charAt(i);
		}
		rmDup(i + 1, s);
		return n;
	}

	// VErrrrrrrrryyy IMP!;
	// Printing all the subsequence in order with 2 choices
	public static String seq[] = new String[20];

	public static void subSeq(int i, String s, String n) {
		if (i == s.length()) {
			System.out.println(n);
			return;
		}
		// to be; true;
		subSeq(i + 1, s, n + s.charAt(i));

		// not to be; false;
		subSeq(i + 1, s, n);
	}

	// VErrrrrrrrryyy IMP!;
	// Printing all the Unique subsequence in order with 2 choices

	public static void uqSubSeq(int i, String s, String n, HashSet<String> set) {
		if (i == s.length()) {
			if (set.contains(n))
				return;
			else {
				System.out.println(n);
				set.add(n);
				return;
			}
		}
		// to be; true;
		uqSubSeq(i + 1, s, n + s.charAt(i), set);

		// not to be; false;
		uqSubSeq(i + 1, s, n, set);
	}

	// To print all Keypad combinations
	// Mapping Numbers to Strings
	public static String[] keypad = { ".", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

	public static void keyComb(int i, String s, String comb) {
		if (i == s.length()) {
			System.out.println(comb);
			return;
		}
		String mapping = keypad[s.charAt(i) - '0'];
		for (int j = 0; j < mapping.length(); j++)
			keyComb(i + 1, s, comb + mapping.charAt(j));
	}

	public static void main(String[] args) {
		A7_Recursion_2 r = new A7_Recursion_2();
		r.towerOfHanoi(5, "S", "H", "D");
		r.stringReverse(4, "Hello"); // s.length()-1;
		r.lastOccur(0, "aabbccddabcd", 'd'); // to use declare String " ", n for Char ' ';
		r.findOccur(0, "aabbccdda", 'f');
		System.out.println(r.isSorted(0, new int[] { 1, 5 })); // passing directly array as a parameter
																// 'new int[]'
		System.out.println(movXL(0, "axbxcxdxe", 'x'));
		System.out.println(rmDup(0, "aabbccddeeffajbvcdhfjvuitytrsjioutrx"));
		subSeq(0, "aaaaa", "");
		uqSubSeq(0, "aaaaa", "", new HashSet<String>());
		keyComb(0, "235", "");
	}
}