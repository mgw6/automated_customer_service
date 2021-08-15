/**
* The tree class implements a tree object that can be used to reference tree nodes
*
* @author MacGregor Winegard
*	email: macgregor.winegard@stonybrook.edu
* 	Stony Brook ID:
*
* @version 1 Build 1 July 30 2020
**/

public class Tree
{
	private TreeNode root;
	private TreeNode pointer;
	
	// invariants
	// the root is like the head, used to find the start
	// the pointer will be used to travers the tree
	
	/**
	* returns an instance of a tree
	**/
	public Tree()
	{
		this.root = null;
		this.pointer = null;
	}
	
	/**
	* sets the root of this tree
	*
	* @param inRoot
	*	the desired root of this tree
	**/
	public void setRoot(TreeNode inRoot)
	{
		this.root = inRoot;
	}
	
	/**
	* gets the root of this tree
	*
	* @returns 
	* the root of the tree
	**/
	public TreeNode getRoot()
	{
		return this.root;
	}
	
	
	/**
	* Adds a child node do the specified parent 
	*
	* @param label
	*	the label of the new node
	*
	* @param prompt
	*	the prompt for the new node
	*
	* @param message
	*	the desired message for the new node
	*
	* @param parentLabel
	*	the label of the parent we are adding to
	*
	* @returns
	*	whether or not the specified ndoe could be added
	**/
	public boolean addNode(String label, String prompt, String message, String parentLabel)
	{
		TreeNode foundParent = this.root.getNodeReference(parentLabel);
		
		if (foundParent == null)
		{
			//System.out.println("Cant find parent!!");
			return false;
		}
		else 
		{
			TreeNode tempNode = new TreeNode();
			tempNode.setParent(foundParent);
			tempNode.setLabel(label);
			tempNode.setMessage(message);
			tempNode.setPrompt(prompt);
			
			foundParent.addChildNode(tempNode);
			//System.out.println("Parent is found YAYYYAYAYAYAYAYYYYYYYYYY!!");
			return true;
		}
	}
	
	/**
	*Prints out the nodes of this tree in pre order
	**/
	public void preOrder()
	{
		if (this.root !=null)
		{
			System.out.println("Traversing the tree in Pre Order!\n");
			this.root.nodePreorder();
		}
		else
			System.out.println("The tree is empty!");
	}
	
	
} // end class