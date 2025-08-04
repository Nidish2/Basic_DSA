package dsa;

import java.util.ArrayDeque;
import java.util.Deque;

public class AA23_QueueQPs {

	static class method1 {
// Queue using two stacks with O (n) time complexity for enqueue operation
		Deque<Integer> stack1 = new ArrayDeque<>(); // Stack acts as the main queue
		Deque<Integer> stack2 = new ArrayDeque<>(); // Temporary stack for transferring elements

		void enqueue(int item) {
			while (!isEmpty()) {
				stack2.push(stack1.pop());
			}
			stack1.push(item);
			while (!stack2.isEmpty()) {
				stack1.push(stack2.pop());
			}
		}

		int dequeue() {
			if (isEmpty()) {
				System.out.println("Queue is empty");
				return -1;
			}
			return stack1.pop();
		}

		int peek() {
			if (isEmpty()) {
				System.out.println("Queue is empty");
				return -1;
			}
			return stack1.peek();
		}

		boolean isEmpty() {
			return stack1.isEmpty();
		}
	}

	static class method2 {
		// Queue using two stacks with O(n) time complexity for dequeue and peek
		// operation
		Deque<Integer> stack1 = new ArrayDeque<>(); // Temporary stack for transferring elements
		Deque<Integer> stack2 = new ArrayDeque<>(); // Stack acts as the main queue

		void enqueue(int item) {
			stack1.push(item);
		}

		int dequeue() {
			if (isEmpty()) {
				System.out.println("Queue is empty");
				return -1;
			}
			if (stack2.isEmpty()) {
				while (!stack1.isEmpty()) {
					stack2.push(stack1.pop());
				}
			}
			return stack2.pop();
		}

		int peek() {
			if (isEmpty()) {
				System.out.println("Queue is empty");
				return -1;
			}
			if (stack2.isEmpty()) {
				while (!stack1.isEmpty()) {
					stack2.push(stack1.pop());
				}
			}
			return stack2.peek();
		}

		boolean isEmpty() {
			return stack1.isEmpty() && stack2.isEmpty();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		method1 queue1 = new method1();
		System.out.println("Queue is Empty? - " + queue1.isEmpty());
		queue1.enqueue(10);
		queue1.enqueue(20);
		queue1.enqueue(30);
		queue1.enqueue(40);
		queue1.enqueue(50);
		queue1.enqueue(60);
		System.out.println("Queue is Empty? - " + queue1.isEmpty());

		System.out.println("\nDequeue: " + queue1.dequeue());
		System.out.println("Peek: " + queue1.peek());
		while (!queue1.isEmpty()) {
			System.out.print(queue1.dequeue() + " ");
		}
		System.out.println();

		method2 queue2 = new method2();
		System.out.println("\nQueue is Empty? - " + queue2.isEmpty());
		queue2.enqueue(70);
		queue2.enqueue(80);
		queue2.enqueue(90);
		queue2.enqueue(100);
		queue2.enqueue(110);
		queue2.enqueue(120);
		System.out.println("Queue is Empty? - " + queue2.isEmpty());

		System.out.println("\nDequeue: " + queue2.dequeue());
		System.out.println("Peek: " + queue2.peek());
		while (!queue2.isEmpty()) {
			System.out.print(queue2.dequeue() + " ");
		}
	}
}
