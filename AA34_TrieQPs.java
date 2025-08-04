package dsa;

import java.util.Set;

import dsa.AA33_Trie.Trie;
import dsa.AA33_Trie.TrieNode;

public class AA34_TrieQPs {

	// Word Break Problem using Trie
	public static boolean wordBreak(Set<String> wordDict, String s) {
		Trie trie = new Trie();
		// Insert all words into the Trie
		for (String word : wordDict) {
			trie.insert(word);
		}
		// Start backtracking to check for segmentation
		return wordBreakHelper(s, 0, trie);
	}

	private static boolean wordBreakHelper(String s, int start, Trie trie) {
		if (start == s.length()) {
			return true; // Reached the end, valid segmentation
		}
		// Try all prefixes from current start
		for (int end = start + 1; end <= s.length(); end++) {
			String prefix = s.substring(start, end);
			if (trie.search(prefix) && wordBreakHelper(s, end, trie)) {
				return true; // Found a word + recursively valid remainder
			}
		}
		return false; // No valid segmentation found
	}

	// ✅ Count distinct complete words in a set using Trie
	public static int countDistinctWords(Set<String> words) {
		Trie trie = new Trie();
		for (String word : words) {
			trie.insert(word);
		}
		return countCompleteWords(trie.root);
	}

	// Counts only complete inserted words (isEndOfWord == true)
	private static int countCompleteWords(TrieNode root) {
		if (root == null)
			return 0;

		int count = root.isEndOfWord ? 1 : 0;

		for (int i = 0; i < 26; i++) {
			count += countCompleteWords(root.children[i]);
		}
		return count;
	}

	// Count all unique substrings in a string using Trie
	public static int countDistinctSubstrings(String s) {
		TrieNode root = new TrieNode(); // manual root (skip Trie class for now)
		int count = 0;

		for (int i = 0; i < s.length(); i++) {
			TrieNode curr = root;
			for (int j = i; j < s.length(); j++) {
				char ch = s.charAt(j);
				int index = ch - 'a';
				if (curr.children[index] == null) {
					curr.children[index] = new TrieNode();
					count++; // count only new paths = new substrings
				}
				curr = curr.children[index];
			}
		}
		return count + 1; // +1 for the empty substring
	}

	// To Find the Longest Word with All Prefixes Present in Dictionary
	public static String longestWordWithAllPrefixes(Set<String> words) {
		Trie trie = new Trie();
		for (String word : words) {
			trie.insert(word);
		}
		return dfs(trie.root, trie.root, new StringBuilder(), "");
	}

	private static String dfs(TrieNode node, TrieNode root, StringBuilder current, String longest) {
		if (node == null)
			return longest;

		if (node != root && !node.isEndOfWord)
			return longest;

		if (current.length() > longest.length())
			longest = current.toString();

		for (int i = 0; i < 26; i++) {
			if (node.children[i] != null) {
				current.append((char) (i + 'a'));
				longest = dfs(node.children[i], root, current, longest);
				current.deleteCharAt(current.length() - 1);
			}
		}
		return longest;
	}

	public static void main(String[] args) {
		// Word Break Problem
		System.out.println("💬 Word Break Problem using Trie:\n");
		Set<String> wordDict = Set.of("i", "love", "to", "solve", "problems");

		String s = "ilovetosolveproblems";
		System.out.println("Word Dictionary: " + wordDict);
		System.out.println("Input String: " + s);
		System.out.println("Can the string be segmented? " + wordBreak(wordDict, s));
		s = "ilovetoproblem";
		System.out.println("Input String: " + s);
		System.out.println("Can the string be segmented? " + wordBreak(wordDict, s));

		// Count Unique Prefixes
		System.out.println("\n💬 Count Unique Prefixes / substrings in a Set of Strings:\n");
		Set<String> words = Set.of("apple", "app", "banana", "bat", "batman");
		System.out.println("Words: " + words);
		System.out.println("Count of Unique Prefixes: " + countDistinctWords(words));
		words = Set.of("apple", "banana", "grape");
		System.out.println("Words: " + words);
		System.out.println("Count of Unique Prefixes: " + countDistinctWords(words));

		// Count Unique Prefixes in a String
		System.out.println("\n💬 Count Unique Prefixes / substrings in a String:\n");
		String str = "banana";
		System.out.println("String: " + str);
		System.out.println("Count of Unique Prefixes: " + countDistinctSubstrings(str));
		str = "apple";
		System.out.println("String: " + str);
		System.out.println("Count of Unique Prefixes: " + countDistinctSubstrings(str));

		// Longest Word with All Prefixes Present
		System.out.println("\n💬 Longest Word with All Prefixes Present in Dictionary:\n");
		Set<String> dict = Set.of("a", "ap", "app", "appl", "apple", "b", "ba", "bat", "apply");
		System.out.println("Dictionary: " + dict);
		System.out.println("Longest Word with All Prefixes Present: " + longestWordWithAllPrefixes(dict));
		dict = Set.of("i", "in", "int", "inte", "integ", "integr", "integrat", "integrate");
		System.out.println("Dictionary: " + dict);
		System.out.println("Longest Word with All Prefixes Present: " + longestWordWithAllPrefixes(dict));
	}
}
