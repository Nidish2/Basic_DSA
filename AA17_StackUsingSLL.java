package dsa;

public class AA17_StackUsingSLL {
    public static MyLinkedList.ListNode head;

    public AA17_StackUsingSLL() {
        head = null;
    }

    public static boolean isEmpty() {
        return head == null;
    }

    public static void push(int data) {
        MyLinkedList.ListNode nn = new MyLinkedList.ListNode(data);
        if (isEmpty())
            head = nn;
        nn.next = head;
        head = nn;
    }

    public static int pop() {
        if (isEmpty())
            return -1;
        int v = head.val;
        head = head.next;
        return v;
    }

    public static int peek() {
        if (isEmpty())
            return -1;
        return head.val;
    }

    public static void display() {
        if (isEmpty())
            return;
        MyLinkedList.ListNode t = head;
        while (t != null) {
            System.out.println(t.val);
            t = t.next;
        }
    }

    public static void main(String[] args) {
        push(10);
        push(20);
        push(30);
        push(40);
        push(50);

//        System.out.println(peek());
//        System.out.println(pop());
//
//        System.out.println(peek());
//        System.out.println(pop());
//
//        System.out.println(peek());
//        System.out.println(pop());
//
//        System.out.println(peek());
//        System.out.println(pop());
//
//        System.out.println(peek());
//        System.out.println(pop());


        while (!isEmpty()) {

            System.out.println(peek());
            System.out.println(pop());
        }

        display();
    }
}

