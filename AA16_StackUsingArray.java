package dsa;

import java.util.Scanner;

public class AA16_StackUsingArray {
    private static int[] s;
    private static int top = -1;

    public AA16_StackUsingArray(int[] s) {
        AA16_StackUsingArray.s = s;
        AA16_StackUsingArray.top = -1;
    }

    public static void push(int data) {
        s[++top] = data;
    }

    public static int pop() {
        if (isEmpty())
            return -1;
        return s[top--];
    }

    public static int peek() {
        if (isEmpty())
            return -1;
        return s[top];
    }

    public static void display() {
        if (isEmpty())
            return;
        for (int i = top; i >= 0; i--)
            System.out.println(s[i]);
    }

    public static boolean isEmpty() {
        if (s.length >= 0 && top != -1) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size: ");
        int size = sc.nextInt();
        sc.close();
        s = new int[size];
        push(10);
        push(20);
        push(30);
        push(40);
        push(50);
        push(60);

        display();
        System.out.println();

        while (!isEmpty()) {
            System.out.println(peek());
            System.out.println(pop() + "\n");
        }
    }
}
