package dsa;

import java.util.ArrayList;
import java.util.LinkedList;

public class A1_Stack {

	class arrayListS {
		ArrayList<Integer> s = new ArrayList<Integer>();

		public arrayListS(ArrayList<Integer> s) {
			super();
			this.s = s;
		}

		void push(int data) {
			s.add(data);
			System.out.println(data + " is Pushed");
		}

		int pop() {
			return (s.isEmpty()) ? -1 : s.removeLast();
		}
		
		

		int peek() {
			return s.isEmpty() ? -1 : s.getLast();
		}

		void display() {
			if (s.isEmpty()) {
				System.out.println("Stack is Empty");
				return;
			}
			System.out.println("The elements are");
			for (int i = s.size() - 1; i >= 0; i--)
				System.out.println(s.get(i));
			System.out.println();
		}
	}

	class linkedlistS {
		LinkedList<Integer> s=new LinkedList<Integer>();

		public linkedlistS(LinkedList<Integer> s) {
			this.s = s;
		}
		
		void push(int data) {
			s.add(data);
			System.out.println(data + " is Pushed");
		}

		int pop() {
			return (s.isEmpty()) ? -1 : s.removeLast();
		}

		int peek() {
			return s.isEmpty() ? -1 : s.getLast();
		}

		void display() {
			if (s.isEmpty()) {
				System.out.println("Stack is Empty");
				return;
			}
			System.out.println("The elements are");
			for (int i = s.size() - 1; i >= 0; i--)
				System.out.println(s.get(i));
			System.out.println();
		}
	}

	public static void main(String[] args) {
		A1_Stack S = new A1_Stack();
		
		ArrayList<Integer> s = new ArrayList<Integer>();
		arrayListS a = S.new arrayListS(s);

		a.push(10);
		System.out.println("Peek: " + a.peek());
		a.display();
		a.push(20);
		System.out.println("Peek: " + a.peek());
		a.display();
		a.push(30);
		System.out.println("Peek: " + a.peek());
		a.display();
		a.push(40);
		System.out.println("Peek: " + a.peek());
		a.display();
		a.push(50);
		System.out.println("Peek: " + a.peek());
		a.display();

		System.out.println("Popped Element: " + a.pop());
		System.out.println("Peek: " + a.peek());
		a.display();
		System.out.println("Popped Element: " + a.pop());
		System.out.println("Peek: " + a.peek());
		a.display();
		System.out.println("Popped Element: " + a.pop());
		System.out.println("Peek: " + a.peek());
		a.display();
		System.out.println("Popped Element: " + a.pop());
		System.out.println("Peek: " + a.peek());
		a.display();
		System.out.println("Popped Element: " + a.pop());
		System.out.println("Peek: " + a.peek());
		a.display();
		
		LinkedList<Integer> B=new LinkedList<Integer>();
		linkedlistS b = S.new linkedlistS(B);
		
		a.push(10);
		System.out.println("Peek: " + a.peek());
		b.display();
		b.push(20);
		System.out.println("Peek: " + b.peek());
		b.display();
		b.push(30);
		System.out.println("Peek: " + b.peek());
		b.display();
		b.push(40);
		System.out.println("Peek: " + b.peek());
		b.display();
		b.push(50);
		System.out.println("Peek: " + b.peek());
		b.display();

		System.out.println("Popped Element: " + b.pop());
		System.out.println("Peek: " + b.peek());
		b.display();
		System.out.println("Popped Element: " + b.pop());
		System.out.println("Peek: " + b.peek());
		b.display();
		System.out.println("Popped Element: " + b.pop());
		System.out.println("Peek: " + b.peek());
		b.display();
		System.out.println("Popped Element: " + b.pop());
		System.out.println("Peek: " + b.peek());
		b.display();
		System.out.println("Popped Element: " + b.pop());
		System.out.println("Peek: " + b.peek());
		b.display();
		
	}
}
