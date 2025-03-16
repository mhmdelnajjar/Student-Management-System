import java.util.ArrayList;

public class StudentTree<E> {

	private int year;
	private StudentTreeNode<E> root;

	public StudentTree(int year) {
		super();
		this.year = year;

	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public E search(int id) {
		StudentTreeNode<E> current = root;
		while (current.key != id) {
			if (id < current.key)
				current = current.leftChild;
			else
				current = current.rightChild;
			if (current == null)
				return null;
		}
		return current.data;
	}

	public void insert(int k, E e) {
		StudentTreeNode<E> newNode = new StudentTreeNode(k, e);
		if (root == null)
			root = newNode;
		else {
			StudentTreeNode current = root;
			StudentTreeNode parent;
			while (true) {
				parent = current;
				if (k < current.key) {
					current = current.leftChild;
					if (current == null) {
						parent.leftChild = newNode;
						return;
					}
				} else {
					current = current.rightChild;
					if (current == null) {
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}

	public boolean delete(int k) {
		StudentTreeNode current = root;
		StudentTreeNode parent = root;
		boolean isLeftChild = true;
		while (current.key != k) {
			parent = current;
			if (k < current.key) {
				isLeftChild = true;
				current = current.leftChild;
			} else {
				isLeftChild = false;
				current = current.rightChild;
			}
			if (current == null)
				return false;
		}
		if (current.leftChild == null && current.rightChild == null) {
			if (current == root)
				root = null;
			else if (isLeftChild)
				parent.leftChild = null;
			else
				parent.rightChild = null;
		} else if (current.rightChild == null)
			if (current == root)
				root = current.leftChild;
			else if (isLeftChild)
				parent.leftChild = current.leftChild;
			else // right child of parent
				parent.rightChild = current.leftChild;
		else if (current.leftChild == null)
			if (current == root)
				root = current.rightChild;
			else if (isLeftChild) // left child of parent
				parent.leftChild = current.rightChild;
			else // right child of parent
				parent.rightChild = current.rightChild;
		else {

			StudentTreeNode successor = getSuccessor(current);
			if (current == root)
				root = successor;
			else if (isLeftChild)
				parent.leftChild = successor;
			else
				parent.rightChild = successor;
			successor.leftChild = current.leftChild;
		}
		return true;

	}

	private StudentTreeNode<E> getSuccessor(StudentTreeNode<E> delNode) {
		StudentTreeNode<E> successorParent = delNode;
		StudentTreeNode<E> successor = delNode;
		StudentTreeNode<E> current = delNode.rightChild; // go to right child
		while (current != null) // until no more
		{ // left children,
			successorParent = successor;
			successor = current;
			current = current.leftChild; // go to left child
		}
		// if successor not
		if (successor != delNode.rightChild) // right child,
		{ // make connections
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}

	public void display() {
		System.out.println("All the student in the year: " + year + "\n");
		display(root);

	}

	public void printPreOrder() {
		preorder(root);
	}

	public void preorder(StudentTreeNode<E> n) {
		if (n == null)
			return;
		else {
			n.display();
			preorder(n.leftChild);
			preorder(n.rightChild);
		}

	}

	public void display(StudentTreeNode<E> n) {
		if (n == null)
			return;
		else {
			display(n.leftChild);
			n.display();
			display(n.rightChild);
		}

	}

	public void postorder(StudentTreeNode<E> n) {
		if (n == null)
			return;
		else {
			postorder(n.leftChild);
			postorder(n.rightChild);
			n.display();
		}
	}

	public int max() {
		StudentTreeNode<E> current = root;
		while (current.rightChild != null)
			current = current.rightChild;
		return current.key;
	}

	public int min() {
		StudentTreeNode<E> current = root;
		while (current.leftChild != null)
			current = current.leftChild;
		return current.key;
	}

	public int depth(int k) {
		int d = 0;
		StudentTreeNode<E> current = root;

		while (current.key != k) {
			if (k < current.key)
				current = current.leftChild;
			else
				current = current.rightChild;
			d = d + 1;
			if (current == null)
				return -1;
		}
		return d;
	}

	public void printLeaves() {
		printAllLeaves(root);
	}

	private void printAllLeaves(StudentTreeNode n) {
		if (n != null)
			if (n.leftChild == null && n.rightChild == null)
				n.display();
			else {
				printAllLeaves(n.leftChild);
				printAllLeaves(n.rightChild);
			}
	}

	public int count() {
		return countNodes(root);
	}

	private int countNodes(StudentTreeNode n) {
		if (n == null)
			return 0;
		else
			return 1 + countNodes(n.leftChild) + countNodes(n.rightChild);
	}

	public int countLeaves() {
		return countAllLeaves(root);
	}

	private int countAllLeaves(StudentTreeNode<E> n) {
		if (n != null)
			if (n.leftChild == null && n.rightChild == null)
				return 1;
			else
				return countAllLeaves(n.leftChild) + countAllLeaves(n.rightChild);
		return 0;
	}

	public int treeHeight() {
		return nodeHeight(root) - 1;

	}

	private int nodeHeight(StudentTreeNode<E> n) {
		if (n == null)
			return 0;
		else
			return 1 + Math.max(nodeHeight(n.leftChild), nodeHeight(n.rightChild));
	}

	public boolean isFull() {
		return checkFull(root);
	}

	private boolean checkFull(StudentTreeNode<E> n) {
		if (n.leftChild == null && n.rightChild == null)
			return true;
		if (n.leftChild != null && n.rightChild != null)
			return checkFull(n.leftChild) && checkFull(n.rightChild);
		return false;
	}

	public Student GetMaxGpa() {
		return getMaxGpaR(root);
	}

	public Student getMaxGpaR(StudentTreeNode<E> root) {
		if (root == null) {
			return null;
		} else {
			Student st = (Student) root.data;
			double gpaNow = st.getGpa();

			Student maxLeft = getMaxGpaR(root.leftChild);
			Student maxRight = getMaxGpaR(root.rightChild);

			Student maxGpaStudent = st;

			if (maxLeft != null && maxLeft.getGpa() > maxGpaStudent.getGpa()) {
				maxGpaStudent = maxLeft;
			}

			if (maxRight != null && maxRight.getGpa() > maxGpaStudent.getGpa()) {
				maxGpaStudent = maxRight;
			}

			return maxGpaStudent;
		}

	}

	public StudentTreeNode<E> getRoot() {
		return root;
	}

	public void setRoot(StudentTreeNode<E> root) {
		this.root = root;
	}

	public boolean remove(int studentId) {
		if (search(studentId) == null) {
			return false; // Student not found
		}
		root = removeRecursive(root, studentId);
		return true; // Student found and removed
	}

	public void printInOrder() {
		printInOrderRecursive(root);
	}

	private void printInOrderRecursive(StudentTreeNode node) {
		if (node != null) {
			printInOrderRecursive(node.leftChild);
			System.out.println(node.data);
			printInOrderRecursive(node.rightChild);
		}
	}

	private StudentTreeNode removeRecursive(StudentTreeNode current, int studentId) {

		if (current == null) {
			return null;
		}

		if (studentId < current.key) {
			current.leftChild = removeRecursive(current.leftChild, studentId);
		}

		else if (studentId > current.key) {
			current.rightChild = removeRecursive(current.rightChild, studentId);
		}

		else {

			if (current.leftChild == null) {
				return current.rightChild;
			} else if (current.rightChild == null) {
				return current.leftChild;
			}

			current.key = minValue(current.rightChild);

			current.rightChild = removeRecursive(current.rightChild, current.key);
		}

		return current;
	}

	public ArrayList<Student> getStudent() {
		return studentsList(root);
		
	}

	private ArrayList<Student> studentsList(StudentTreeNode node) {
		ArrayList<Student> re = new ArrayList<>();
		if (node != null) {
			re.addAll(studentsList(node.leftChild));
			re.add((Student) node.data);
			re.addAll(studentsList(node.rightChild));
		}
		return re;
	}

	private int minValue(StudentTreeNode root) {

		int minValue = root.key;
		while (root.leftChild != null) {
			minValue = root.leftChild.key;
			root = root.leftChild;
		}
		return minValue;
	}

}
