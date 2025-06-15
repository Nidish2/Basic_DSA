package dsa;

//this is with standard logic

import java.util.ArrayList;

public class AA18_StackUsingArrayList {
    public static ArrayList<Integer> s = new ArrayList<>();

    // Add element to the top of the stack
    public static void push(int data) {
        s.add(data); // Add to the end of the list, which represents the top of the stack
    }

    // Remove and return the top element of the stack
    public static int pop() {
        if (s.isEmpty()) {
            return -1; // Return -1 if the stack is empty
        }
        return s.remove(s.size() - 1); // Remove the last element, which is the top of the stack
    }

    // Return the top element of the stack without removing it
    public static int peek() {
        if (s.isEmpty()) {
            return -1; // Return -1 if the stack is empty
        }
        return s.get(s.size() - 1); // Access the last element, which is the top of the stack
    }

    // Display all elements of the stack from top to bottom
    public static void display() {
        if (s.isEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }
        for (int i = s.size() - 1; i >= 0; i--) { // Iterate from top to bottom
            System.out.println(s.get(i));
        }
    }

    public static void main(String[] args) {
        push(10);
        push(20);
        push(30);
        push(40);
        display();
        System.out.println();

        while (!s.isEmpty()) {
            System.out.println("Peek: " + peek());
            System.out.println("Pop: " + pop() + "\n");
        }
    }
}
//This is using My logic

//package dsa;
//
//import java.util.ArrayList;
//
//public class AA18_StackUsingArrayList {
//    public static ArrayList<Integer> s = new ArrayList<>();
//
//    public static void push(int data) {
//        s.add(0, data);
//    }
//
//    public static int pop() {
//        if (s.isEmpty())
//            return -1;
//        return s.remove(0);
//    }
//
//    public static int peek() {
//        if (s.isEmpty())
//            return -1;
//        return s.get(0);
//    }
//
//    public static void display() {
//        if (s.isEmpty())
//            return;
//        for (int i = 0; i < s.size(); i++)
//            System.out.println(s.get(i));
//    }
//
//    public static void main(String[] args) {
//        push(10);
//        push(20);
//        push(30);
//        push(40);
//        display();
//        System.out.println();
//
//        while (!s.isEmpty()) {
//            System.out.println(peek());
//            System.out.println(pop() + "\n");
//        }
//    }
//}
