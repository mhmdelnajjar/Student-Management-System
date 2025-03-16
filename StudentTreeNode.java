
public class StudentTreeNode<E> {

	int key;
	E data;
	StudentTreeNode<E> leftChild;
	StudentTreeNode<E> rightChild;
	public StudentTreeNode(int k,E e)
	{
		key=k;
		data=e;
		leftChild=null;
		rightChild=null; 
	}
	public void display() {
		System.out.print(key+":");
		System.out.println(data);

	}


}	
