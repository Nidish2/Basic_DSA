package dsa;

import java.util.Stack;

public class AA19_StackQPs {

    public static void pushAtBottom(Stack<Integer> s, int data) {
        if (s.isEmpty()) {
            s.push(data);
            return;
        }
        int t = s.pop();
        pushAtBottom(s, data);
        s.push(t);
    }

    public static void reverseStack(Stack<Integer> s) {
        if (s.isEmpty())
            return;
        int t = s.pop();
        reverseStack(s);
        pushAtBottom(s, t);
    }

    public static void display(Stack<Integer> s) {
        for (int i = s.size() - 1; i >= 0; i--)
            System.out.println(s.get(i));
        System.out.println();
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(10);
        s.push(20);
        s.push(30);
        display(s);
        pushAtBottom(s, 40);
        display(s);
        reverseStack(s);
        display(s);
    }
}
