package dsa;

import java.util.ArrayList;
import java.util.List;

import dsa.AA28_BST.ManualBST;
import dsa.AA28_BST.TreeNode;

public class AA29_BSTQPs {

	// Print nodes in given range
	public static void printInRange(TreeNode root, int low, int high) {
		if (root == null) {
			return;
		}
		if (root.key >= low && root.key <= high) {
			System.out.print(root.key + " ");
		}
		if (root.key > low) {
			printInRange(root.left, low, high);
		}
		if (root.key < high) {
			printInRange(root.right, low, high);
		}
	}

	// Print root to leaf paths
	public static void printRootToLeafPaths(TreeNode root) {
		printPaths(root, new ArrayList<>());
		// Alternatively, you can use backtracking
		System.out.println("Root to Leaf Paths (Backtracking):");
		printPathsBackTrack(root, new ArrayList<>());
	}

	private static void printPaths(TreeNode root, List<Integer> path) {
		if (root == null)
			return;
		path.add(root.key);
		if (root.left == null && root.right == null) {
			System.out.println(path);
		} else {
			printPaths(root.left, new ArrayList<>(path));
			printPaths(root.right, new ArrayList<>(path));
		}
	}

	private static void printPathsBackTrack(TreeNode root, List<Integer> path) {
		if (root == null)
			return;

		path.add(root.key);

		if (root.left == null && root.right == null) {
			System.out.println(path);
		} else {
			printPathsBackTrack(root.left, path);
			printPathsBackTrack(root.right, path);
		}

		// 🔙 Backtrack
		path.remove(path.size() - 1);
	}

	public static void main(String[] args) {
		ManualBST bst = new ManualBST();
		bst.insert(50);
		bst.insert(30);
		bst.insert(70);
		bst.insert(20);
		bst.insert(40);
		bst.insert(60);
		bst.insert(80);

		System.out.println("Inorder Traversal:");
		bst.inorder();

		System.out.println("Nodes in range 30 to 70:");
		printInRange(bst.root, 30, 70);

		System.out.println("\nRoot to leaf paths:");
		printRootToLeafPaths(bst.root);
	}
}
