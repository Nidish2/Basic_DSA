package dsa;

public class A3_Pattern6 {
	public static void main(String[] args) {
		int k=1;
		for(int i=1;i<=5;i++) 
		{System.out.println();
			for(int j=1;j<=i;j++) {
				System.out.print(k+++" ");
			}
		}
	}
}