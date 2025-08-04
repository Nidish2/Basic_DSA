package dsa;

import java.io.*;

public class AA24_SerializeTree {

	// ------------------------- 1️⃣ Manual Serialization -------------------------
	static class Manual {

		// Binary Tree Node for Manual Serialization
		static class TreeNode {
			int val;
			TreeNode left, right;

			TreeNode(int val) {
				this.val = val;
			}
		}

		// Manual Serialization using Preorder with null markers
		public static String serialize(TreeNode root) {
			StringBuilder sb = new StringBuilder();
			serializePreorder(root, sb);
			return sb.toString();
		}

		private static void serializePreorder(TreeNode node, StringBuilder sb) {
			if (node == null) {
				sb.append("null").append(",");
				return;
			}
			sb.append(node.val).append(",");
			serializePreorder(node.left, sb);
			serializePreorder(node.right, sb);
		}

		// Create sample binary tree
		public static TreeNode createTree() {
			TreeNode root = new TreeNode(1);
			root.left = new TreeNode(2);
			root.right = new TreeNode(3);
			root.right.left = new TreeNode(4);
			root.right.right = new TreeNode(5);
			return root;
		}
	}

	// ------------------------- 2️⃣ Inbuilt Java Serialization
	// -------------------------
	static class Inbuilt {

		// TreeNode must implement Serializable
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

		// Java ObjectOutputStream based Serialization to a file
		public static void serialize(TreeNode root, String filePath) {
			try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
				out.writeObject(root);
				System.out.println("✅ Inbuilt Java Serialization done to: " + filePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Create sample binary tree
		public static TreeNode createTree() {
			TreeNode root = new TreeNode(1);
			root.left = new TreeNode(2);
			root.right = new TreeNode(3);
			root.right.left = new TreeNode(4);
			root.right.right = new TreeNode(5);
			return root;
		}
	}

	// ------------------------- 🧪 Main Demo -------------------------
	public static void main(String[] args) {

		// 🌳 Manual
		Manual.TreeNode manualRoot = Manual.createTree();
		System.out.println("✅ Manual Preorder Serialization:");
		String serializedManual = Manual.serialize(manualRoot);
		System.out.println(serializedManual); // Expected: 1,2,null,null,3,4,null,null,5,null,null,

		System.out.println("\n--------------------------------------------------\n");

		// 💾 Inbuilt
		System.out.println("✅ Inbuilt Java Serialization:");
		Inbuilt.TreeNode inbuiltRoot = Inbuilt.createTree();
		String filePath = "tree_data.ser";
		Inbuilt.serialize(inbuiltRoot, filePath);
	}
}
