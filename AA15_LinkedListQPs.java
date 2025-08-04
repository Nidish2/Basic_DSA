package dsa;

public class AA15_LinkedListQPs {

	// To delete just nth node from Last
	public static void removeNFromLast(MyLinkedList.ListNode head, int n) {
		if (head == null)
			return;
		MyLinkedList.ListNode temp = head;
		for (int i = 1; i < MyLinkedList.getSize() - n; i++) {
			temp = temp.next;
		}
		System.out.println("Removed Element from last at " + n + " is: " + temp.next.val);
		temp.next = temp.next.next;
	}

	public static boolean isPalindrome(MyLinkedList.ListNode head) {
		if (head == null || head.next == null)
			return true;
		MyLinkedList.ListNode temp = head;
		for (int i = 1; i < MyLinkedList.getSize() / 2; i++) {
			temp = temp.next;
		}
		MyLinkedList.ListNode reverse = AA14_ReverseList.reverseIterate(temp);
		temp = head;
		while (temp != null && reverse != null) {
			if (temp.val != reverse.val)
				return false;
			temp = temp.next;
			reverse = reverse.next;
		}
		return temp == null && reverse == null;
	}

	public static void main(String[] args) {
		MyLinkedList list = new MyLinkedList();
		list.addAtTail(12);
		list.addAtTail(22);
		list.addAtTail(32);
		list.addAtTail(42);
		list.addAtTail(52);
		list.addAtHead(10);
		list.addAtHead(20);
		list.addAtHead(30);
		list.addAtHead(40);
		list.addAtHead(50);
		list.addAtTail(60);
		list.addAtHead(70);
		list.printLinkedList(list.head);
		System.out.println("Size: " + MyLinkedList.getSize());
		System.out.println("Element should be: " + list.get(MyLinkedList.getSize() - 5));
		removeNFromLast(list.head, 5);
		System.out.println("Element should be: " + list.get(MyLinkedList.getSize() - 5));
		list.deleteAtIndex(MyLinkedList.getSize() - 5);
		list.printLinkedList(list.head);

		list.addAtHead(10);
		list.addAtTail(20);
		list.addAtTail(10);
		list.printLinkedList(list.head);
		System.out.println(isPalindrome(list.head));

	}
}
