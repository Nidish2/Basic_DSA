package dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A8_Recursion_3 {

	// to print all the permutations of a String;
	public void printPerm(String s, String perm) {
		if (s.length() == 0) {
			System.out.println(perm);
			return;
		}
		for (int i = 0; i < s.length(); i++) {
			String newString = s.substring(0, i) + s.substring(i + 1);
			printPerm(newString, perm + s.charAt(i));
		}
	}

	// downwards and right movement are allowed
	// using 4 parameters as an arguments
	// it has high time n space complexity
	public int countPaths(int i, int j, int m, int n) {
		if (i >= m || j >= n)
			return 0;
		if (i == m - 1 && j == n - 1)
			return 1;
		return countPaths(i + 1, j, m, n) + countPaths(i, j + 1, m, n);
	}

	// same implementation
	// downwards and right movement are allowed
	// uses only 2 parameters as an arguments
	// it has high time n space complexity
	public int countPaths(int m, int n) {
		if (m <= 0 || n <= 0)
			return 0;
		if (m == 1 && n == 1)
			return 1;
		return countPaths(m - 1, n) + countPaths(m, n - 1);
	}

	// Using Dynamic Programming
	// it has low time n space complexity
	public int countPathsUsingDP(int m, int n) {
		// Check for invalid dimensions
		if (m == 0 || n == 0)
			return 0;
		// Create a 1D array to store number of paths
		int[] dp = new int[n];
		// Initialize the first row with 1 (there's only one way to reach cells in the
		// first row)
		Arrays.fill(dp, 1);
		// Iterate over each row
		for (int i = 1; i < m; i++) {
			// Iterate over each column
			for (int j = 1; j < n; j++) {
				// Calculate paths to reach current cell (i, j)
				// Paths to reach (i, j) = Paths from above (i-1, j) + Paths from left (i, j-1)
				dp[j] += dp[j - 1];
			}
		}
		// The last element of dp array will have the number of unique paths to reach
		// bottom-right corner
		return dp[n - 1];
	}

	// Placing a tiles of 1*n to the Floor of m*n
	// 1-Vertically 2-Horizontaly
	public int placeTiles(int m, int n) {
		if (m == n)
			return 2;
		if (m < n)
			return 1;
		// Vertically + Horizontaly
		return placeTiles(m - n, n) + placeTiles(m - 1, n);

	}

	// Inviting the guests- Single or pairs
	public int inviteGuests(int n) {
		if (n <= 1)
			return 1;
		return inviteGuests(n - 1) + (n - 1) * inviteGuests(n - 2);
	}

	// Print the subset of n natural no.s
	public void printSubset(ArrayList<Integer> subset) {
		for (int i = 0; i < subset.size(); i++) {
			System.out.print(subset.get(i) + "\t");
		}
		System.out.println();
	}

	public void findSubset(int n, ArrayList<Integer> subset) {
		if (n == 0) {
			printSubset(subset);
			return;
		}
		subset.add(n);
		findSubset(n - 1, subset);
		subset.remove(subset.size() - 1);
		findSubset(n - 1, subset);
	}

	// To print all the permutations of the array of int
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> permn = new ArrayList<>();
		boolean used[] = new boolean[nums.length];
		permNum(nums, used, permn, result);
		return result;
	}

	void permNum(int[] nums, boolean used[], List<Integer> permn, List<List<Integer>> result) {
		if (permn.size() == nums.length) {
			result.add(new ArrayList<>(permn));
		}
		for (int i = 0; i < nums.length; i++) {
			if (!used[i]) {
				permn.add(nums[i]);
				used[i] = true;
				permNum(nums, used, permn, result);
				permn.remove(permn.size() - 1);
				used[i] = false;
			}
		}
	}

	public static void main(String[] args) {
		A8_Recursion_3 r = new A8_Recursion_3();
		r.printPerm("abcd", "");
		System.out.println(r.countPaths(0, 0, 3, 4));
		System.out.println(r.countPaths(5, 7));
		System.out.println(r.countPathsUsingDP(5, 7));
		System.out.println(r.placeTiles(6, 7));
		System.out.println(r.inviteGuests(9));
		ArrayList<Integer> subset = new ArrayList<Integer>();
		r.findSubset(12, subset);
		int[] nums = { 1, 2, 3, 4, 5 };
		List<List<Integer>> result = r.permute(nums);
		System.out.println("Total numbers of permutations are : " + result.size() + "\n");
		for (List<Integer> permn : result)
			System.out.println(permn + "\n");
	}
}