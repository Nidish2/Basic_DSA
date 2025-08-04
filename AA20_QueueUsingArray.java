package dsa;

public class AA20_QueueUsingArray {

	static class Queue {
		int front, rear, capacity;
		int queue[];

		Queue(int size) {
			front = rear = 0;
			capacity = size;
			queue = new int[capacity];
		}

		void enqueue(int item) {
			if (capacity == rear) {
				System.out.println("Queue is full");
				return;
			}
			queue[rear++] = item;
		}

		int dequeue() {
			if (front == rear) {
				System.out.println("Queue is empty");
				return -1;
			}
			return queue[front++];
		}

		int peek() {
			if (front == rear) {
				System.out.println("Queue is empty");
				return -1;
			}
			return queue[front];
		}

		boolean isEmpty() {
			return front == rear;
		}

		boolean isFull() {
			return rear == capacity;
		}

		void display() {
			if (isEmpty()) {
				System.out.println("Queue is empty");
				return;
			}
			for (int i = front; i < rear; i++) {
				System.out.print(queue[i] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue queue = new Queue(5);

		System.out.println("Queue is Empty? - " + queue.isEmpty());
		queue.enqueue(10);
		queue.enqueue(20);
		queue.enqueue(30);
		queue.enqueue(40);
		queue.enqueue(50);

		queue.display(); // Display the queue
		System.out.println("Queue is Full? - " + queue.isFull());
		queue.enqueue(60); // This should print "Queue is full"
		queue.display(); // Display the queue
		System.out.println("Dequeue: " + queue.dequeue()); // Should return 10
		System.out.println("Peek: " + queue.peek()); // Should return 20
		queue.display(); // Display the queue after dequeue
		queue.enqueue(60); // Add another element

	}

}
