package dsa;

public class AA22_QueueUsingSLL {

	static class Node {
		int data;
		Node next;

		Node(int data) {
			this.data = data;
			this.next = null;
		}
	}

	static class Queue {
		Node front, rear;

		Queue() {
			front = rear = null;
		}

		void enqueue(int item) {
			Node newNode = new Node(item);
			if (rear == null) {
				front = rear = newNode;
				return;
			}
			rear.next = newNode;
			rear = newNode;
		}

		int dequeue() {
			if (front == null) {
				System.out.println("Queue is empty");
				return -1;
			}
			int item = front.data;
			front = front.next;
			if (front == null) {
				rear = null;
			}
			return item;
		}

		int peek() {
			if (front == null) {
				System.out.println("Queue is empty");
				return -1;
			}
			return front.data;
		}

		boolean isEmpty() {
			return front == null;
		}

		void display() {
			if (isEmpty()) {
				System.out.println("Queue is empty");
				return;
			}
			Node temp = front;
			while (temp != null) {
				System.out.print(temp.data + " ");
				temp = temp.next;
			}
			System.out.println();
		}

		public static void main(String[] args) {
			Queue queue = new Queue();

			queue.enqueue(10);
			queue.enqueue(20);
			queue.enqueue(30);

			queue.display(); // Output: 10 20 30

			System.out.println("Dequeue: " + queue.dequeue()); // Output: 10
			queue.display(); // Output: 20 30

			System.out.println("Peek: " + queue.peek()); // Output: 20

			queue.enqueue(40);
			queue.display(); // Output: 20 30 40

			System.out.println("Is queue empty? " + queue.isEmpty()); // Output: false
		}

	}

	public static void main(String[] args) {
		Queue queue = new Queue();
		System.out.println("Queue is Empty? - " + queue.isEmpty());
		queue.enqueue(10);
		queue.enqueue(20);
		queue.enqueue(30);
		queue.enqueue(40);
		queue.enqueue(50);
		queue.display(); // Output: 10 20 30 40 50
		System.out.println("Dequeue: " + queue.dequeue()); // Output: 10
		queue.display(); // Output: 20 30 40 50
		System.out.println("Peek: " + queue.peek()); // Output: 20
		queue.enqueue(60);
		queue.display(); // Output: 20 30 40 50 60
		System.out.println("Is queue empty? " + queue.isEmpty()); // Output: false
		System.out.println("Dequeue: " + queue.dequeue()); // Output: 20
		queue.display(); // Output: 30 40 50 60
	}
}
