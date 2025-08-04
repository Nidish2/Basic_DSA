package dsa;

import java.io.*;
import java.util.*;

public class AA25_DeserializeTree {

	// ------------------------- 1️⃣ Manual Deserialization
	// -------------------------
	static class Manual {

		static class TreeNode {
			int val;
			TreeNode left, right;

			TreeNode(int val) {
				this.val = val;
			}
		}

		// Deserialize from Preorder string with nulls
		public static TreeNode deserialize(String data) {
			Deque<String> nodes = new LinkedList<>(Arrays.asList(data.split(",")));
			return buildTree(nodes);
		}

		private static TreeNode buildTree(Deque<String> nodes) {
			if (nodes.isEmpty())
				return null;

			String val = nodes.poll();
			if (val.equals("null"))
				return null;

			TreeNode node = new TreeNode(Integer.parseInt(val));
			node.left = buildTree(nodes);
			node.right = buildTree(nodes);
			return node;
		}

		// Inorder Traversal to test deserialization
		public static void printInorder(TreeNode node) {
			if (node == null)
				return;
			printInorder(node.left);
			System.out.print(node.val + " ");
			printInorder(node.right);
		}
	}

	// ------------------------- 2️⃣ Inbuilt Java Deserialization
	// -------------------------
	static class Inbuilt {

		static class TreeNode implements Serializable {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			int val;
			TreeNode left, right;

			TreeNode(int val) {
				this.val = val;
			}
		}

		// Java Deserialization from .ser file
		public static TreeNode deserialize(String filePath) {
			try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
				return (TreeNode) in.readObject();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		}

		// Inorder traversal to test deserialization
		public static void inOrder(TreeNode node) {
			if (node == null)
				return;
			inOrder(node.left);
			System.out.print(node.val + " ");
			inOrder(node.right);
		}
	}

	// ------------------------- 🧪 Main Demo -------------------------
	public static void main(String[] args) {

		// 🔁 Manual Deserialize
		String serializedManual = "1,2,null,null,3,4,null,null,5,null,null,";
		System.out.println("✅ Manual Deserialization (from String):");
		Manual.TreeNode manualRoot = Manual.deserialize(serializedManual);
		System.out.print("Inorder Traversal: ");
		Manual.printInorder(manualRoot); // Expected: 2 1 4 3 5

		System.out.println("\n\n--------------------------------------------------\n");

		// 🔁 Inbuilt Deserialize
		String filePath = "tree_data.ser";
		System.out.println("✅ Inbuilt Java Deserialization (from .ser):");
		Inbuilt.TreeNode inbuiltRoot = Inbuilt.deserialize(filePath);
		System.out.print("Inorder Traversal: ");
		Inbuilt.inOrder(inbuiltRoot); // Expected: 2 1 4 3 5
	}
}
