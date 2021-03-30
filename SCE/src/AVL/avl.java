package AVL;
import java.util.Scanner;

class Node {
	long phone;
	int height;
	String name;
	Node left, right;

	Node(long n,String name) {
		phone = n;
		height = 1;
		this.name = name;
	} 
}//C:\Users\rohit\eclipse-workspace\SCE

public class avl {
 
	Node root;
	int non;
    public avl(){
    	root=null;
    }
	int height(Node N) {
		if (N == null)
			return 0;
		return N.height;
	}


	int max(int a, int b) {
		return (a > b) ? a : b;
	}

	Node rightRotate(Node y) {
		Node x = y.left;
		Node T2 = x.right;
		x.right = y;
		y.left = T2;

		// Update heights
		y.height = max(height(y.left), height(y.right)) + 1;
		x.height = max(height(x.left), height(x.right)) + 1;
		return x;
	}

	Node leftRotate(Node x) {
		Node y = x.right;
		Node T2 = y.left;
		y.left = x;
		x.right = T2;

		// Update heights
		x.height = max(height(x.left), height(x.right)) + 1;
		y.height = max(height(y.left), height(y.right)) + 1;
		return y;
	}

	int getBalance(Node N) {
		if (N == null)
			return 0;

		return height(N.left) - height(N.right);
	}

	Node insert(Node node, Node newnode) {

		if (node == null)
			return (newnode);

		if (newnode.name.compareTo(node.name)<=-1)
			node.left = insert(node.left, newnode);
		else if (newnode.name.compareTo(node.name)>=1)
			node.right = insert(node.right,newnode);
		else // Duplicate keys not allowed
			return node;

		/* 2. Update height of this ancestor node */
		node.height = 1 + max(height(node.left),
							height(node.right));


		int balance = getBalance(node);

		// are 4 cases Left Left Case
		if (balance > 1 && newnode.name.compareTo(node.left.name)<=-1)
		 	return rightRotate(node);

		// Right Right Case
		if (balance < -1 && newnode.name.compareTo(node.right.name)>=1)
			 return leftRotate(node);

		// Left Right Case
		if (balance > 1 && newnode.name.compareTo(node.left.name)>=1)
		{
			 node.left = leftRotate(node.left);
			 return rightRotate(node);
	 	 }

		// Right Left Case
		if (balance < -1 && newnode.name.compareTo(node.right.name)<=-1) {
			 node.right = rightRotate(node.right);
			 return leftRotate(node);
		 }

		/* return the (unchanged) node pointer */
		 return node;
	 }
	
    public Node getRoot() {
     	return root;
     }
    
    
	public	void inOrder(Node node) {
			//System.out.println("Hi\n");
			if (node != null) {
				inOrder(node.left);
				System.out.print(node.name + " "+node.phone+"\n");
				inOrder(node.right);
			}
		}
   
	public  void insert_call(long n,String str) {
			Node new_node = new Node(n,str);
			root = insert(root,new_node);	
	}
	
	public long search_call(String str) {
	      	long num = searchavl(root,str);
	      	return num;
	}
   long searchavl(Node root,String key) {
	   long mark=-1;
		if (root == null)
		{System.out.println("Node not fount\n");
		return -1;}
			
		if (key.compareTo(root.name)<=-1)
			mark= searchavl(root.left, key);

		else if (key.compareTo(root.name)>=1)
			mark=searchavl(root.right, key);
		else {
			System.out.println("Node found>>  ");
			System.out.println(root.name+" "+root.phone);
	         return  root.phone;	
		}
		return mark;
	}
	
	Node minValueNode(Node node)
	{
		Node current = node;

		/* loop down to find the leftmost leaf */
		while (current.left != null)
		current = current.left;

		return current;
	}
	
	
    public int delete_call(String str) {
    	if(search_call(str)==-1) {
    		return 0;
    	}
    	root = deleteNode(root,str);
    	return 1;
    }
    
    
	 Node deleteNode(Node root, String key)
	{               //PERFORM STANDARD BST DELETE
		if (root == null)
		{System.out.println("Node not found1\n");
		return root;}
			
		if (key.compareTo(root.name)<=-1)
			root.left = deleteNode(root.left, key);

		else if (key.compareTo(root.name)>=1)
			root.right = deleteNode(root.right, key);

		// if key is same as root's key, then this is the node
		// to be deleted
		else
		{
			// node with only one child or no child
			if ((root.left == null) || (root.right == null))
			{
				Node temp = null;
				if (temp == root.left)
					temp = root.right;
				else
					temp = root.left;

				// No child case
				if (temp == null)
				{
					temp = root;
					root = null;
				}
				else // One child case
					root = temp; // Copy the contents of
								// the non-empty child
			}
			else
			{
				// successor (smallest in the right subtree)
				Node temp = minValueNode(root.right);

				// Copy the InOrder successor's data to this node
				root.name = temp.name;
				root.phone = temp.phone;

				// Delete the inOrder successor
				root.right = deleteNode(root.right, temp.name);
			}
			System.out.println("Node successfully deleted!!!");
		}

		// If the tree had only one node then return
		if (root == null)
			return root;

		root.height = max(height(root.left), height(root.right)) + 1;
		int balance = getBalance(root);

		if (balance > 1 && getBalance(root.left) >= 0)
			return rightRotate(root);

		if (balance > 1 && getBalance(root.left) < 0)
		{
			root.left = leftRotate(root.left);
			return rightRotate(root);
		}

		if (balance < -1 && getBalance(root.right) <= 0)
			return leftRotate(root);
		
		if (balance < -1 && getBalance(root.right) > 0)
		{
			root.right = rightRotate(root.right);
			return leftRotate(root);
		}
        
		return root;
	}
	 
	 
	 
	public Object[][] getdata_call(Object[][] data){
	    non=getdata(data,root,-1);
	    return data;
	}
	
	
	public int getdata(Object[][]data,Node node,int n) {
		if (node != null) {
			n=getdata(data,node.left,n);
			n++;
			System.out.println("count="+n+"\n");
			data[n][0]= n+1;
//			data[n][1]= new Object();
//			data[n][2]= new Object();
			data[n][1]=node.name;
			data[n][2]=node.phone;
			n=getdata(data,node.right,n);
		}
		return n;
	}
}



/* 
10 
rohit
10,20,30,40,50,25
       30
      /  \
     20   40
    / \    \
   10  25   50
       */
