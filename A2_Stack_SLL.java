package dsa;

public class A2_Stack_SLL {

    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head; // Top of the stack

    public A2_Stack_SLL() {
        this.head = null;
    }

    // Push operation
    void push(int data) {
        Node newNode = new Node(data);
        newNode.next = head; // Insert at head
        head = newNode;
    }

    // Pop operation
    int pop() {
        if (head == null) {
            System.out.println("Stack Underflow!");
            return -1;
        }
        int poppedData = head.data;
        head = head.next; // Remove the top element
        return poppedData;
    }

    // Peek operation
    int peek() {
        if (head == null) {
            System.out.println("Stack is empty!");
            return -1;
        }
        return head.data; // Return top element
    }

    // Traverse and display stack elements
    void display() {
        if (head == null) {
            System.out.println("Stack is empty!");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        A2_Stack_SLL stack = new A2_Stack_SLL();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        
        stack.display(); // Should display 30 20 10
        
        System.out.println("Peek: " + stack.peek()); // Should display 30
        
        System.out.println("Pop: " + stack.pop()); // Should display 30
        stack.display(); // Should display 20 10
    }
}
