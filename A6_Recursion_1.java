package dsa;

public class A6_Recursion_1 {
	// Overloading
	void print(int i) {
		if (i < 0)
			return;
		else {
			System.out.println(i);
			print(i - 1);
		}
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
		printNum(n - 1);
		System.out.println("Backtracking for 3rd time: " + n);
	}

	void print(int i, int n) {
		if (i == n + 1)
			return;
		else {
			System.out.println(i);
			print(i + 1, n);
		}
	}

	void sum(int i, int n, int sum) {
		if (i == n + 1) {
			System.out.println(sum);
			return;
		} else {
			sum += i;
			sum(i + 1, n, sum);
		}
	}

	void fact(int n, int fact) {
		if (n == 1) {
			System.out.println(fact);
			return;
		} else {
			fact *= n;
			fact(n - 1, fact);
		}
	}

	int fact(int n) {
		if (n == 1 || n == 0)
			return 1;
		else {
			return n * fact(n - 1);
		}
	}

	// good logic;
	// its the series of n natural no.s;
	int sumSeries(int n) {
		int sum = 0;
		if (n == 0) {
			return sum;
		} else {
			sum = sumSeries(n - 1);
			System.out.print(sum + "\t"); // In order to print the sequence;
			return sum + n;
		}
	}

	void fib(int a, int b, int n) {
		if (n <= -1) // it will print last number as well
			return;
		else {
			System.out.print(a + "\t");
			fib(b, a + b, n - 1);
		}
	}

	int xPn(int x, int n) {
		if (x == 0)
			return 0;
		if (n == 0)
			return 1;
		{
			int X = xPn(x, n - 1);
			return x * X;
		}
	}

	// Verryyyy Imp!;
	// decreases size from n to log n of Stack;
	int xPnlogn(int x, int n) {
		if (x == 0)
			return 0;
		if (n == 0)
			return 1;
		if (n % 2 == 0)
			return xPnlogn(x, n / 2) * xPnlogn(x, n / 2);
		else
			return xPnlogn(x, n / 2) * xPnlogn(x, n / 2) * x;
	}

	public static void main(String[] args) {
		A6_Recursion_1 r = new A6_Recursion_1();
		r.print(6);
		r.print(2, 6);
		r.sum(0, 2, 0);
		r.fact(5, 1);
		System.out.println(r.fact(5));
		System.out.println(r.sumSeries(5));
		r.fib(0, 1, 15);
		System.out.println();
		System.out.println(r.xPn(5, 5));
		System.out.println(r.xPnlogn(2, 19));
		printNum(5);
	}
}