package dsa;

public class AA21_CirQueueUsingArray {

	static class CircularQueue {
		int front, rear, capacity;
		int queue[];

		CircularQueue(int size) {
			front = rear = 0;
			capacity = size + 1;
			queue = new int[capacity];
		}

		void enqueue(int item) {
			if ((rear + 1) % capacity == front) {
				System.out.println("Queue is full");
				return;
			}
			queue[rear] = item;
			rear = (rear + 1) % capacity;
		}

		int dequeue() {
			if (front == rear) {
				System.out.println("Queue is empty");
				return -1;
			}
			int item = queue[front];
			front = (front + 1) % capacity;
			return item;
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
			return (rear + 1) % capacity == front;
		}

		void display() {
			if (isEmpty()) {
				System.out.println("Queue is empty");
				return;
			}
			for (int i = front; i != rear; i = (i + 1) % capacity) {
				System.out.print(queue[i] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CircularQueue queue = new CircularQueue(5);
		System.out.println("Queue is Empty? - " + queue.isEmpty());
		queue.enqueue(10);
		queue.enqueue(20);
		queue.enqueue(30);
		queue.enqueue(40);
		queue.enqueue(50);
		queue.display();
		System.out.println("Queue is Full? - " + queue.isFull());
		queue.enqueue(60); // This should print "Queue is full"
		System.out.println("Dequeue: " + queue.dequeue());
		queue.display(); // Display the queue
		System.out.println("Peek: " + queue.peek()); // Should return 20
		queue.enqueue(60);
		queue.display(); // Display the queue after enqueueing 60
		System.out.println("Dequeue: " + queue.dequeue()); // Should return 20
		queue.display(); // Display the queue after dequeueing
		System.out.println("Peek: " + queue.peek()); // Should return 30
	}
}
