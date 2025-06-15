package dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dsa.MyLinkedList.ListNode;

public class AA14_ReverseList {
	public static ListNode reverseIterate(ListNode head) {
		if (head == null)
			return null;
		if (head.next == null) {
			return head;
		}
		ListNode prev = head;
		ListNode cur = head.next;
		while (cur != null) {
			ListNode next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		head.next = null;
//		head=prev;
		return prev;
	}

	public static ListNode reverseRecursive(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode newHead = reverseRecursive(head.next);
		head.next.next = head;
		head.next = null;
		return newHead;
	}

	public static void main(String[] args) {
		MyLinkedList myLinkedList = new MyLinkedList();
		myLinkedList.addAtHead(10);
		myLinkedList.addAtHead(20);
		myLinkedList.addAtHead(30);
		myLinkedList.addAtHead(40);
		myLinkedList.addAtTail(50);
		myLinkedList.addAtTail(60);
		myLinkedList.addAtTail(70);
		myLinkedList.addAtTail(80);
		myLinkedList.printLinkedList(myLinkedList.head);
		myLinkedList.head = reverseIterate(myLinkedList.head);
		myLinkedList.printLinkedList(myLinkedList.head);
		myLinkedList.head = reverseRecursive(myLinkedList.head);
		myLinkedList.printLinkedList(myLinkedList.head);

		List<Integer> list = new ArrayList<>(Arrays.asList(40, 30, 20, 10, 50, 60, 70, 80));
		System.out.println(list.reversed());
	}

}
