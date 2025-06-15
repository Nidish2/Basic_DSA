package dsa;

public class A4_Star {
	public static int max = 5;

	public static void main(String[] args) {

		A4_Star s = new A4_Star();
		s.print();
		pattern p = s.new pattern();
		p.six();
		p.seven();
		p.eight();
		p.nine();
		p.ten();
		p.eleven();
		p.twelve();
		p.thirteen();
		p.fourteen();
		p.fifteen();
		p.sixteen();
		p.seventeen();

	}

	public void print() {
		System.out.println("Welcome :) \n");
	}

	class pattern {
		A4_Star st = new A4_Star();

		void six() {
			int i, j = 1;
			for (i = 1; i <= max; i++) {
				for (j = 1; j <= i; j++) {
					System.out.print(j + " ");

				}
				System.out.println();

			}

		}

		void seven() {
			int i, j = 1;
			for (i = max; i >= 0; i--) {
				for (j = 1; j <= i; j++) {
					System.out.print(j + " ");

				}
				System.out.println();

			}

		}

		void eight() {
			int i, j = 1, k = 1;
			for (i = 1; i <= max; i++) {
				for (j = 1; j <= i; j++) {
					System.out.print(k++ + " ");

				}
				System.out.println();

			}

		}

		void nine() {
			int i, j = 1, k = 1;
			for (i = 1; i <= max; i++) {
				for (j = 1; j <= i; j++) {

					if (k % 2 == 1) {
						System.out.print("1 ");
					} else {
						System.out.print("0 ");

					}
					k++;

				}
				System.out.println();

			}

		}

		void ten() {
			int i, j = 1;
			for (i = 1; i <= max; i++) {
				for (j = 1; j <= i; j++) {

					if ((i + j) % 2 == 1) {
						System.out.print("0 ");
					} else {
						System.out.print("1 ");

					}

				}
				System.out.println();

			}

		}

		void eleven() {
			int i, j = 1;
			for (i = 1; i <= max; i++) {
				for (j = 1; j <= i; j++) {

					if ((i + j) % 2 == 1) {
						System.out.print("1 ");
					} else {
						System.out.print("0 ");

					}

				}
				System.out.println();

			}
		}

		void twelve() {
			for (int i = 1; i <= max; i++) {
				for (int j = 1; j <= i; j++) {
					System.out.print("* ");
				}
				for (int j = 1; j <= 2 * (max - i); j++) {
					System.out.print("  ");
				}
				for (int j = 1; j <= i; j++) {
					System.out.print("* ");
				}
				System.out.println();
			}
			for (int i = max; i >= 1; i--) {
				for (int j = 1; j <= i; j++) {
					System.out.print("* ");
				}
				for (int j = 1; j <= 2 * (max - i); j++) {
					System.out.print("  ");
				}
				for (int j = 1; j <= i; j++) {
					System.out.print("* ");
				}
				System.out.println();
			}
		}

		void thirteen() {
			for (int i = 1; i <= max; i++) {
				for (int j = 1; j <= max - i; j++) {
					System.out.print("  ");
				}
				for (int j = 1; j <= max; j++) {
					System.out.print("* ");
				}
				System.out.println();
			}

		}

		void fourteen() {
			for (int i = 1; i <= max; i++) {
				for (int j = 1; j <= max - i; j++) {
					System.out.print("  ");
				}
				for (int j = 1; j <= i; j++) {
					System.out.print(i + " ");
				}
				for (int j = 1; j < i; j++) {
					System.out.print(i + " ");
				}

				System.out.println();
			}
		}

		void fifteen() {
			for (int i = 1; i <= max; i++) {
				for (int j = 1; j <= max - i; j++) {
					System.out.print("  ");
				}
				for (int j = 1; j <= i; j++) {
					System.out.print(i + "   ");
				}
				System.out.println();
			}
		}

		void sixteen() {
			for (int i = 1; i <= max; i++) {
				for (int j = 1; j <= max - i; j++) {
					System.out.print("  ");
				}
				for (int j = i; j >= 1; j--) {
					System.out.print(j + " ");
				}
				for (int j = 2; j <= i; j++) {
					System.out.print(j + " ");
				}
				System.out.println();
			}
		}

		void seventeen() {
			for (int i = 1; i <= max; i++) {
				for (int j = 1; j <= max - i; j++) {
					System.out.print("  ");
				}
				for (int j = 1; j <= (2 * i) - 1; j++) {
					System.out.print("* ");
				}
				System.out.println();
			}
			for (int i = max - 1; i >= 1; i--) { // max-1 is because to get accurate diamond shape.
				for (int j = 1; j <= max - i; j++) {
					System.out.print("  ");
				}
				for (int j = 1; j <= (2 * i) - 1; j++) {
					System.out.print("* ");
				}
				System.out.println();
			}
		}

	}

}
