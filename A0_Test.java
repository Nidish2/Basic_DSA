package dsa;

import java.util.List;

public class A0_Test {
	
	public static int maxCost(List<Integer> cost, List<String> labels, int dailyCount) {
		int n = cost.size();
		int maxCost = 0;
		for (int i = 0; i < n; i++) {
			if (labels.get(i).equals("A")) {
				maxCost += cost.get(i);
			}
		}
		return maxCost;
	}

	public static void printNum(int n) {
		// Base case to terminate recursion
		if (n <= 0 || n >= 20) {
			System.out.println("It has reached base case: " + n);
			return;
		}

		System.out.println("Recursive call: " + n);
		// Recursive call with n-1
		printNum(n - 1);

		// Backtracking message
		System.out.println("Backtracking: " + n);

		// Recursive call with n-1

		printNum(n - 1);
		System.out.println("Backtracking for 2nd time: " + n);
//	        n++;
//	        System.out.println("Backtracking for 2nd time (n++): " + n);
		printNum(n - 1);
		System.out.println("Backtracking for 3rd time: " + n);
	}

	public static void main(String[] args) {
		System.out.println("First Call:");
		printNum(5); // First call with n = 5
		System.out.println(maxCost(List.of(1, 2, 3, 4, 5), List.of("A", "B", "A", "C", "A"), 3));
	}
}
