package dsa;

public class AA27_BinaryTreeQPs {

	// Count the number of nodes in a binary tree
	public int countNodes(AA26_Traversals.TreeNode root) {
		if (root == null) {
			return 0;
		}
		return 1 + countNodes(root.left) + countNodes(root.right);
	}

	// Sum of all nodes in a binary tree
	public int sumOfNodes(AA26_Traversals.TreeNode root) {
		if (root == null) {
			return 0;
		}
		return root.val + sumOfNodes(root.left) + sumOfNodes(root.right);
	}

	// Count the height of the binary tree
	public int height(AA26_Traversals.TreeNode root) {
		if (root == null) {
			return 0;
		}
		int leftHeight = height(root.left);
		int rightHeight = height(root.right);
		return Math.max(leftHeight, rightHeight) + 1;
	}

	// Count the Diameter of the binary tree with O(n^2)
	public int diameter(AA26_Traversals.TreeNode root) {
		if (root == null) {
			return 0;
		}
		int maxHeight = height(root.left) + height(root.right) + 1;
		int leftDiameter = diameter(root.left);
		int rightDiameter = diameter(root.right);
		return Math.max(maxHeight, Math.max(leftDiameter, rightDiameter));
	}

	static class TreeInfo {
		int ht;
		int diam;

		TreeInfo(int ht, int diam) {
			this.ht = ht;
			this.diam = diam;
		}
	}

	// Count the Diameter of the binary tree with O(n)
	public static TreeInfo diameter2(AA26_Traversals.TreeNode root) {
		if (root == null) {
			return new TreeInfo(0, 0);
		}

		TreeInfo left = diameter2(root.left);
		TreeInfo right = diameter2(root.right);

		int myHeight = Math.max(left.ht, right.ht) + 1;

		int diam1 = left.diam;
		int diam2 = right.diam;
		int diam3 = left.ht + right.ht + 1;

		int myDiam = Math.max(Math.max(diam1, diam2), diam3);

		return new TreeInfo(myHeight, myDiam);
	}

	// To Find subtree of another tree
	public static boolean isSubtree(AA26_Traversals.TreeNode root, AA26_Traversals.TreeNode subRoot) {
		if (subRoot == null) {
			return true;
		}
		if (root == null) {
			return false;
		}
		if (isSameTree(root, subRoot)) {
			return true;
		}
		return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
	}

	private static boolean isSameTree(AA26_Traversals.TreeNode p, AA26_Traversals.TreeNode q) {
		if (p == null && q == null) {
			return true;
		}
		if (p == null || q == null || p.val != q.val) {
			return false;
		}
		return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}

	// To find the sum of nodes at kth level
	public int sumAtKthLevel(AA26_Traversals.TreeNode root, int k) {
		if (root == null) {
			return 0;
		}
		if (k == 0) {
			return root.val;
		}
		return sumAtKthLevel(root.left, k - 1) + sumAtKthLevel(root.right, k - 1);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AA27_BinaryTreeQPs tree = new AA27_BinaryTreeQPs();
		AA26_Traversals.TreeNode root = AA26_Traversals.createTree();
		AA26_Traversals.preorder(root);
		System.out.println("\nNumber of nodes in the binary tree: " + tree.countNodes(root));
		System.out.println("Sum of all nodes in the binary tree: " + tree.sumOfNodes(root));
		System.out.println("Height of the binary tree: " + tree.height(root));
		System.out.println("Diameter of the binary tree with O (n^2): " + tree.diameter(root));
		TreeInfo result = diameter2(root);
		System.out.println("Diameter of the binary tree with O (n): " + result.diam);
		AA26_Traversals.TreeNode subRoot = new AA26_Traversals.TreeNode(2);
		subRoot.left = new AA26_Traversals.TreeNode(4);
		subRoot.right = new AA26_Traversals.TreeNode(5);
		System.out.println("Is subRoot a subtree of root? " + AA27_BinaryTreeQPs.isSubtree(root, subRoot));
		int k = 2;
		System.out.println("Sum of nodes at level " + k + ": " + tree.sumAtKthLevel(root, k));
	}

}
