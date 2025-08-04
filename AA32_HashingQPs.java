package dsa;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AA32_HashingQPs {

	// Majority Element appears more than n/3 times in an array
	public static void majorityElement(int[] nums) {
		HashMap<Integer, Integer> countMap = new HashMap<>();
		for (int num : nums) {
			countMap.put(num, countMap.getOrDefault(num, 0) + 1);
		}
		int n = nums.length;
		for (int key : countMap.keySet()) {
			if (countMap.get(key) > n / 3) {
				System.out.println(key); // Found a majority element
			}
		} // No majority element found
	}

	// Union of two arrays
	public static int[] union(int[] arr1, int[] arr2) {
		HashSet<Integer> set = new HashSet<>();
		int n1 = arr1.length;
		int n2 = arr2.length;
		for (int i = 0; i < Math.max(n1, n2); i++) {
			if (i < n1)
				set.add(arr1[i]);
			if (i < n2)
				set.add(arr2[i]);
		}
		return set.stream().mapToInt(Integer::intValue).sorted().toArray();
	}

	// Intersection of two arrays
	public static int[] intersection(int[] arr1, int[] arr2) {
		// Always use smaller array to build HashSet -> optimize memory & lookup time
		if (arr1.length > arr2.length) {
			return intersection(arr2, arr1);
		}

		HashSet<Integer> set = new HashSet<>();
		for (int num : arr1) {
			set.add(num);
		}

		// Result stored in list to avoid creating second HashSet
		List<Integer> resultList = new ArrayList<>();
		HashSet<Integer> seen = new HashSet<>();

		for (int num : arr2) {
			if (set.contains(num) && !seen.contains(num)) {
				resultList.add(num);
				seen.add(num); // avoid duplicates in result
			}
		}

		// Convert to int[]
		return resultList.stream().mapToInt(Integer::intValue).sorted().toArray(); // sorted is optional
	}

	// To find Itinerary from a list of tickets using a HashMap
	public static List<String> findItinerary(HashMap<String, String> tickets) {
		List<String> itinerary = new ArrayList<>();

		if (tickets == null || tickets.isEmpty()) {
			return itinerary;
		}

		// Step 1: Create a reverse map to find the starting point
		HashSet<String> destinations = new HashSet<>(tickets.values());

		String start = null;
		for (String from : tickets.keySet()) {
			if (!destinations.contains(from)) {
				start = from;
				break;
			}
		}

		// Edge case: if no start found (cyclic input)
		if (start == null) {
			return List.of("Invalid input - no unique starting point 🚫");
		}

		// Step 2: Build itinerary from start
		while (start != null) {
			itinerary.add(start);
			start = tickets.get(start);
		}

		return itinerary;
	}

	// To find the Subarray sum equal to a k
	public static int subarraySum(int[] nums, int k) {
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, 1); // Base case for sum = k
		int count = 0, sum = 0;

		for (int num : nums) {
			sum += num;
			if (map.containsKey(sum - k)) {
				count += map.get(sum - k);
			}
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}
		return count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("----------- Hashing QPs -----------\n");
		// Majority Element
		System.out.println("Majority Element in an array:");
		int[] nums = { 3, 2, 3, 2, 2, 1, 1, 1 };
		System.out.println("Array: " + Arrays.toString(nums));
		System.out.println("Majority Element(s) in the array:");
		majorityElement(nums);
		System.out.println();

		// Union and Intersection of two arrays
		System.out.println("Union and Intersection of two arrays:");
		int[] arr1 = { 1, 2, 3, 4, 5 };
		int[] arr2 = { 4, 5, 6, 7, 8 };
		System.out.println("Array 1: " + Arrays.toString(arr1));
		System.out.println("Array 2: " + Arrays.toString(arr2));
		int[] unionResult = union(arr1, arr2);
		System.out.println("Union of Array 1 and Array 2: " + Arrays.toString(unionResult));
		int[] intersectionResult = intersection(arr1, arr2);
		System.out.println("Intersection of Array 1 and Array 2: " + Arrays.toString(intersectionResult));

		// Itinerary from tickets
		System.out.println("\nFinding Itinerary from tickets:");
		HashMap<String, String> tickets = new HashMap<>();
		tickets.put("JFK", "SFO");
		tickets.put("SFO", "LAX");
		tickets.put("LAX", "JFK");
		tickets.put("LAX", "ATL");
		tickets.put("ATS", "JFK");
		List<String> itinerary = findItinerary(tickets);
		if (itinerary.isEmpty()) {
			System.out.println("No valid itinerary found.");
		} else {
			System.out.println("Itinerary: " + String.join(" -> ", itinerary));
		}
		System.out.println();

		// Subarray sum equal to k
		System.out.println("Finding Subarray sum equal to k:");
		int[] subarrayNums = { 1, 1, 3, 5, 2, 1, 1, 2, 3, 4, -1, 2 };
		int k = 10;
		System.out.println("Array: " + Arrays.toString(subarrayNums));
		int subarrayCount = subarraySum(subarrayNums, k);
		System.out.println("Number of subarrays with sum equal to " + k + ": " + subarrayCount);
	}
}
