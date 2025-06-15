package dsa;

import java.util.ArrayList;
import java.util.Collections;

public class AA12_Collection {

	public static void arrayList(ArrayList<Integer> list) {
		// to add elements
		list.add(3);

		// to get elements
		list.get(0);

		// to add in between list.add(index,value)
		list.add(0, 4);

		// to set element
		list.set(1, 2);

		// to removw element
		list.remove(1);

		// to return size
		System.out.println(list.size());

		// to sort
		Collections.sort(list);
	}

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		arrayList(list);
	}

}
