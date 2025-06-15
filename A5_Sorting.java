package dsa;

import java.util.*;

public class A5_Sorting {
	Scanner sc = new Scanner(System.in);
	int size;

	// This is the method to insert MANUALLY
	public int[] insertManually(int[] arr, int size) {
		if (isEmpty(arr))
			return null;
		for (int i = 0; i < size; i++) {
			arr[i] = sc.nextInt();
		}
		System.out.println();
		return arr;
	}

	// This is the method to insert DIRECTLY in DECREASING ORDER
	public int[] insertDirectlyDecreasing(int[] arr, int size) {
		for (int i = size - 1; i >= 0; i--) { // Start from size-1 down to 0
			arr[size - 1 - i] = i + 1; // Insert i into arr at index size-1, size-2, ..., 0
		}
		return arr;
	}

	// This is the method to insert DIRECTLY in RANDOM ORDER
	public int[] insertDirectlyRandom(int[] arr, int size) {
		Random rn = new Random();
		for (int i = 0; i < size; i++) {
			arr[i] = rn.nextInt(size);
		}
		System.out.println();
		return arr;
	}

	public boolean isEmpty(int[] arr) {
		if (arr.length <= 0)
			return true;
		return false;
	}

	public void swap(int[] arr, int pos1, int pos2) {
		if (isEmpty(arr))
			return;
		int temp = arr[pos1];
		arr[pos1] = arr[pos2];
		arr[pos2] = temp;
	}

	public void display(int[] arr, int size) {
		for (int i = 0; i < size; i++) {
			System.out.print(arr[i] + "\t");
		}
		System.out.println();
	}

	public int[] bubbleSort(int[] arr) {
		if (isEmpty(arr))
			return null;
		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
//					int temp = arr[j];
//					arr[j] = arr[j + 1];
//					arr[j + 1] = temp;
					swap(arr, j, j + 1);
				}
			}
		}
		System.out.println();
		return arr;
	}

	public int[] selectionSort(int[] arr) {
		if (isEmpty(arr))
			return null;
		int i, j;
		for (i = 0; i < size - 1; i++) {
			int min = arr[i], pos = i;
			for (j = i + 1; j < size; j++) {
				if (min > arr[j]) {
					min = arr[j];
					pos = j;
				}
			}
//			int temp = arr[i];
//			arr[i] = arr[pos];
//			arr[pos] = temp;
			swap(arr, i, pos);
		}
		return arr;
	}

	public int[] insertionSort(int[] arr) {
		if (isEmpty(arr))
			return null;
		for (int i = 1; i < size; i++) {
			int max = arr[i];
			int j = i - 1;
			while (j >= 0 && max < arr[j]) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = max;
		}
		return arr;
	}

	public int[] mergeSort(int[] arr) {
		if (isEmpty(arr))
			return null;
		divide(arr, 0, arr.length - 1);
		return arr;
	}

	// O (log n)
	private void divide(int[] arr, int si, int ei) {
		if (si < ei) {
			int mid = si + (ei - si) / 2;
			divide(arr, si, mid);
			divide(arr, mid + 1, ei);
			conquer(arr, si, mid, ei);
		}
	}

	// O (n)
	private void conquer(int[] arr, int si, int mid, int ei) {
		int[] merged = new int[ei - si + 1];
		int ind1 = si, ind2 = mid + 1, x = 0;

		// Merge the two halves into merged array
		while (ind1 <= mid && ind2 <= ei) {
			if (arr[ind1] <= arr[ind2]) {
				merged[x++] = arr[ind1++];
			} else {
				merged[x++] = arr[ind2++];
			}
		}

		// Copy remaining elements from the first half, if any
		while (ind1 <= mid) {
			merged[x++] = arr[ind1++];
		}

		// Copy remaining elements from the second half, if any
		while (ind2 <= ei) {
			merged[x++] = arr[ind2++];
		}

		// Copy merged array back to original array
		for (int i = 0; i < merged.length; i++) {
			arr[si + i] = merged[i];
		}
	}

	public int[] quickSort(int[] arr) {
		if (isEmpty(arr))
			return null;
		pivot(arr, 0, arr.length - 1);
		return arr;
	}

	private void pivot(int[] arr, int low, int high) {
		if (low < high) {
			int pind = partition(arr, low, high);
			pivot(arr, low, pind - 1);
			pivot(arr, pind + 1, high);
		}
	}

	private int partition(int[] arr, int low, int high) {
		int pivotElem = arr[high];
		int i = low - 1;

		for (int j = low; j < high; j++) {
			if (arr[j] < pivotElem) {
				i++;
//				int temp = arr[i];
//				arr[i] = arr[j];
//				arr[j] = temp;
				swap(arr, i, j);
			}
		}
		// Place the pivot element in its correct position
//		int temp = arr[i + 1];
//		arr[i + 1] = arr[high];
//		arr[high] = temp;
		swap(arr, i + 1, high);

		return i + 1; // Return the index of the pivot element
	}

	public static void main(String[] args) {
		A5_Sorting s = new A5_Sorting();
		s.size = 3500;
		int[] arr = new int[s.size];

		long start, end;
//		arr = s.insertManually(arr,s.size);
//		arr = s.insertDirectlyDecreasing(arr, s.size);
		arr = s.insertDirectlyRandom(arr, s.size);
		s.display(arr, s.size);

//		Bubble Sort - O (n^2)
//		arr = s.insertManually(arr,s.size);
//		arr = s.insertDirectlyDecreasing(arr, s.size); // Worst case
//		arr = s.insertDirectlyRandom(arr, s.size);
		start = System.nanoTime();
		arr = s.bubbleSort(arr);
		end = System.nanoTime();
		System.out.println("The time taken to Bubble Sort " + s.size + " items is " + (end - start) + " ns\n");
		s.display(arr, s.size);
		System.out.println();

//		Selection sort - O (n^2)
//		arr = s.insertManually(arr,s.size);
//		arr = s.insertDirectlyDecreasing(arr, s.size);//worst case
		arr = s.insertDirectlyRandom(arr, s.size);
		start = System.nanoTime();
		arr = s.selectionSort(arr);
		end = System.nanoTime();
		System.out.println("The time taken to Selection Sort " + s.size + " items is " + (end - start) + " ns\n");
		s.display(arr, s.size);
		System.out.println();

//		Insertion sort - O (n^2)
//		arr = s.insertManually(arr,s.size);
//		arr = s.insertDirectlyDecreasing(arr, s.size); // Worst case
		arr = s.insertDirectlyRandom(arr, s.size);
		start = System.nanoTime();
		arr = s.insertionSort(arr);
		end = System.nanoTime();
		System.out.println("The time taken to Insertion Sort " + s.size + " items is " + (end - start) + " ns\n");
		s.display(arr, s.size);
		System.out.println();

//		Merge sort - O (n*log n) in all (best,avearge,worst) 
//		SC - O (n^2) as it uses additional space for (Mergeded Array)
//		arr = s.insertManually(arr,s.size);
//		arr = s.insertDirectlyDecreasing(arr, s.size);	//Worst case
		arr = s.insertDirectlyRandom(arr, s.size);
		start = System.nanoTime();
		arr = s.mergeSort(arr);
		end = System.nanoTime();
		System.out.println("The time taken to Merge Sort " + s.size + " items is " + (end - start) + " ns\n");
		s.display(arr, s.size);
		System.out.println();

//		Quick sort - Worst case - O (n^2)
//		happens when the pivot is always the smallest or largest in array (ie. sorted arr (A or D))
//		Average case - O (n*log n)
//		SC - O (n)
//		arr = s.insertManually(arr,s.size);
//		arr = s.insertDirectlyDecreasing(arr, s.size);	//Worst case
		arr = s.insertDirectlyRandom(arr, s.size);
		start = System.nanoTime();
		arr = s.quickSort(arr);
		end = System.nanoTime();
		System.out.println("The time taken to Quick Sort " + s.size + " items is " + (end - start) + " ns\n");
		s.display(arr, s.size);
		System.out.println();
	}
}