package dsa;

public class A2_Pattern5 {

	// Pattern 5
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 1; i <= 4; i++) {
			System.out.println();
			for (int j = 1; j <= 4 - i; j++)
				System.out.print("  ");
			for (int j = 1; j <= i; j++)
				System.out.print("* ");
		}
	}
}