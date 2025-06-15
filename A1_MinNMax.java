package dsa;

import java.util.Scanner;

public class A1_MinNMax {

	public static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
	Scanner sc = new Scanner(System.in);
	static int size;
	public static int arr[];

	int[] insert(int[] arr) {
		for (int i = 0; i < size; i++) {
			arr[i] = sc.nextInt();
		}
		System.out.println();
		return arr;
	}

	void display(int[] arr) {
		for (int i = 0; i < size; i++) {
			System.out.print(arr[i] + "\t");
		}
		System.out.println();
	}

	int min(int[] arr) {
		int min = arr[0];
		for (int i = 0; i < size; i++) {
//			if (min > arr[i])
//				min = arr[i];

//			 Using in built functions
			min = Math.min(arr[i], min);
		}
		return min;
	}

	int max(int[] arr) {
		int max = arr[0];
		for (int i = 0; i < size; i++) {
//			if (max < arr[i])
//				max = arr[i];

//			 Using in built functions
			max = Math.max(arr[i], max);
		}
		return max;
	}

	void minNMax() {
		min = max = arr[0];
		for (int i = 0; i < size; i++) {
//			if (max < arr[i])
//				max = arr[i];
//			if (min > arr[i])
//				min = arr[i];

//			 Using in built functions
			max = Math.max(arr[i], max);
			min = Math.min(arr[i], min);
		}
	}

	void minNMax(int i, int j) {
		int mid = i + (j - i) / 2;
		if (i == j) {
			max = min = arr[i];
		} else if (i == j - 1) {
//			if (arr[i] > arr[j]) {
//				max = arr[i];
//				min = arr[j];
//			} else {
//				max = arr[j];
//				min = arr[i];
//			}

//			 Using in built functions
			max = Math.max(arr[i], arr[j]);
			min = Math.min(arr[i], arr[j]);
		} else {
			minNMax(i, mid);
			int max1 = max, min1 = min;
			minNMax(mid + 1, j);
//			if (max < max1)
//				max = max1;
//			if (min > min1)
//				min = min1;

//			 Using in built functions
			max = Math.max(max, max1);
			min = Math.min(min, min1);
		}
	}

	public static void main(String[] args) {
		A1_MinNMax s = new A1_MinNMax();
		A5_Sorting a5 = new A5_Sorting();
		size = 50000;
		arr = new int[size];

		arr = a5.insertDirectlyRandom(arr, size);
//		arr = s.insert(arr);
		s.display(arr);

//		Finding Min Using Linear search 
		long start = System.nanoTime();
		System.out.println("\nMin: " + s.min(arr));
		long end = System.nanoTime();
		System.out.println("Time taken to find Min in " + size + " items using Linear search:  " + (end - start));

//		Finding Max Using Linear search
		start = System.nanoTime();
		System.out.println("\nMax: " + s.max(arr));
		end = System.nanoTime();
		System.out.println("Time taken to find Max in " + size + " items using Linear search:  " + (end - start));

//		Finding Min and Max Using Linear search
		start = System.nanoTime();
		s.minNMax();
		end = System.nanoTime();
		System.out.println("\nMin: " + min + "\nMax: " + max);
		System.out
				.println("Time taken to find Min and Max in " + size + " items using Linear search:  " + (end - start));

//		 Finding Min and Max Using Binary search
		start = System.nanoTime();
		s.minNMax(0, size - 1);
		end = System.nanoTime();
		System.out.println("\nMin: " + min + "\nMax: " + max);
		System.out
				.println("Time taken to find Min and Max in " + size + " items using Binary search:  " + (end - start));
	}
}
