package dsa;

public class A3_Queue {

    class QArray {
        int[] q;
        int n, f, r;

        public QArray(int[] q, int n) {
            this.n = n;
            this.q = new int[n];
            f = -1;
            r = -1;
        }

        void enqueue(int data) {
            if (r >= n - 1) { // Adjusted condition for overflow
                System.out.println("Queue Overflow");
                return;
            }
            if (f > r && f != -1) {
                System.out.println("There is no space");
                return;
            }
            if (f == r && f == -1) {
                f++;
            }
            q[++r] = data;
        }

        int dequeue() {
            if (f == -1 || f > r) { // Adjusted condition for underflow
                System.out.println("Queue Underflow");
                return -1;
            }
            int data = q[f++];
            if (f > r) { // Reset queue when all elements are dequeued
                f = -1;
                r = -1;
            }
            return data;
        }

        void display() {
            if (f == -1 || f > r) { // Adjusted condition for underflow
                System.out.println("Queue Underflow");
            } else {
                for (int i = f; i <= r; i++) {
                    System.out.print(q[i] + " ");
                }
                System.out.println();
            }
        }

    }

    public static void main(String[] args) {
        int n = 5; // You can change the size of the queue here
        int[] q = new int[n];
        QArray queue = new A3_Queue().new QArray(q, n);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        queue.display();

        queue.dequeue();
        queue.dequeue();

        queue.display();

        queue.enqueue(6);
        queue.enqueue(7);

        queue.display();
    }

}
