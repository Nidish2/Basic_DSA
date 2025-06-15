package dsa;

public class A0_Test {

	public static void printNum(int n) {
	    // Base case to terminate recursion
	    if (n <= 0 || n >= 20) {
	    	System.out.println("It has reached base case: "+n);
	        return;
	    }

	    System.out.println("Recursive call: "+n);
	        // Recursive call with n-1
	        printNum(n - 1);

	        // Backtracking message
	        System.out.println("Backtracking: " + n);

	        // Recursive call with n-1
	        
	        printNum(n-1);
	        System.out.println("Backtracking for 2nd time: " + n);  
//	        n++;
//	        System.out.println("Backtracking for 2nd time (n++): " + n);
	        printNum(n-1);
	        System.out.println("Backtracking for 3rd time: " + n);
	    }

    public static void main(String[] args) {
        System.out.println("First Call:");
        printNum(5); // First call with n = 5
    }
}

