package dsa;

class MyLinkedList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val, ListNode next) {
            this(val);
            this.next = next;
        }

        ListNode(int val) {
            this.val = val;
        }
    }

    ListNode head;
    private ListNode tail;
    static int size;

    public static int getSize() {
        return size;
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode current = head;

        while (index > 0) {
            current = current.next;
            index--;
        }
        return current.val;
    }

    public void addAtHead(int val) {
        head = new ListNode(val, head);

        if (this.size == 0) {
            tail = head;
        }
        size++;
    }

    public void addAtTail(int val) {
        if (this.size == 0) {
            this.addAtHead(val);
        } else {
            tail.next = new ListNode(val);
            tail = tail.next;
            this.size++;
        }
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > this.size) {
            return;
        }

        if (index == 0) {
            this.addAtHead(val);
        } else if (index == this.size) {
            this.addAtTail(val);
        } else {
            ListNode current = head;

            while (index > 1) {
                current = current.next;
                index--;
            }

            ListNode newListNode = new ListNode(val, current.next);
            current.next = newListNode;
            this.size++;
        }
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= this.size) {
            return;
        }

        if (index == 0) {
            head = head.next;

            if (this.size == 1) {
                tail = null;
            }
        } else {
            ListNode current = head;
            while (index > 1) {
                current = current.next;
                index--;
            }

            if (current.next == tail) {
                tail = current;
            }
            System.out.println("Removed Element is: " + current.next.val);
            current.next = current.next.next;
        }
        this.size--;
    }

    // Helper method to print the linked list
    public void printLinkedList(ListNode head) {
        if (head == null)
            return;
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }
}

public class AA13_LinkedList {

    public static void main(String[] args) {
        // Create a new MyLinkedList instance
        MyLinkedList myLinkedList = new MyLinkedList();

        // Test the addAtHead method
        myLinkedList.addAtHead(1);
        myLinkedList.addAtHead(2);
        myLinkedList.addAtHead(3);

        // Expected: 3 -> 2 -> 1
        myLinkedList.printLinkedList(myLinkedList.head);

        // Test the addAtTail method
        myLinkedList.addAtTail(4);

        // Expected: 3 -> 2 -> 1 -> 4
        myLinkedList.printLinkedList(myLinkedList.head);

        // Test the addAtIndex method
        myLinkedList.addAtIndex(2, 5);

        // Expected: 3 -> 2 -> 5 -> 1 -> 4
        myLinkedList.printLinkedList(myLinkedList.head);

        // Test the get method
        System.out.println("Element at index 2: " + myLinkedList.get(2)); // Output: 5

        // Test the deleteAtIndex method
        myLinkedList.deleteAtIndex(1);

        // Expected: 3 -> 5 -> 1 -> 4
        myLinkedList.printLinkedList(myLinkedList.head);

        // Test the get method again
        System.out.println("Element at index 1: " + myLinkedList.get(1)); // Output: 5
        myLinkedList.addAtHead(6);
        myLinkedList.addAtHead(7);
        myLinkedList.addAtHead(8);
        myLinkedList.printLinkedList(myLinkedList.head);
        myLinkedList.addAtIndex(5, 10);
        myLinkedList.addAtIndex(6, 12);
        myLinkedList.addAtIndex(7, 11);
        myLinkedList.printLinkedList(myLinkedList.head);
        myLinkedList.addAtTail(44);
        myLinkedList.addAtTail(46);
        myLinkedList.addAtTail(45);
        myLinkedList.printLinkedList(myLinkedList.head);
        System.out.println("Element at index 10: " + myLinkedList.get(10));
        System.out.println(MyLinkedList.getSize());
    }

}
