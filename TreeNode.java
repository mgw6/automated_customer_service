/**
* The TreeNode class implements a TreeNode object that contains a question and refernece
*to other Nodes
*
* @Author MacGregor Winegard
*	Email: macgregor.winegard@stonybrook.edu
*	Stony Brook ID: 114152787
*
* @version 1 Build 1 July 30 2020
**/
public class TreeNode
{

	private TreeNode parent;
	private TreeNode[] childList = new TreeNode[9];
	private String label;
	private String message;
	private String prompt;
	
	/*
	Invariants
	parent refers to the node that led to this one
	childList is a list of all potential child nodes
	label is the label of the node
	message is the question the node delivers
	prompt is the possible answer to the previous question
	*/
	
	/**
	*Returns an instance of a TreeNode
	*
	* Postcondition: 
	*	the node has been initiated
	**/
	
	public TreeNode()
	{
		this.parent = null;
		this.childList = childList;
		this.label = null;
		this.message = null;
		this.prompt = null;
	}
	
	/**
	*sets the parent reference of this TreeNode
	*
	* @param inParent
	* 	TreeNode object that is the parent of this TreeNode
	**/
	public void setParent(TreeNode inParent)
	{
		this.parent = inParent;
	}
	
	/**
	* returns the parent of the TreeNode
	*
	* @returns
	* 	the parent of this TreeNode
	**/
	public TreeNode getParent()
	{
		return this.parent;
	}
	
	/**
	*gets the label of this node
	*
	* @returns 
	*	label of this node
	**/
	public String getLabel()
	{
		return this.label;
	}
	
	/**
	* Sets the label of this node
	*
	* @param inLabel
	*	the desired label of this node
	**/
	public void setLabel(String inLabel)
	{
		this.label = inLabel;
	}
	
	/**
	*returns the message of this node
	*
	* @returns 
	* this.message
	**/
	public String getMessage()
	{
		return this.message;
	}
	
	/**
	* Sets the message of this node
	*
	* @param inMessage
	* 	the desired message of this ndoe
	**/
	public void setMessage(String inMessage)
	{
		this.message = inMessage;
	}
	
	/**
	* gets the prompt of this node
	*
	*@returns
	* 	prompt of this node
	**/
	public String getPrompt()
	{
		return this.prompt;
	}
	
	/**
	* sets the prompt of this node
	*
	* @param inPrompt
	*	the desired prompt of this node
	**/
	public void setPrompt (String inPrompt)
	{
		this.prompt = inPrompt;
	}
	
	/**
	* Returns this list of childs nodes
	*
	* @returns
	*	this list of childs nodes
	**/
	public TreeNode[] getChildList()
	{
		return this.childList;
	} 
	
	/**
	* says whether or not this node is a leaf
	*
	* @ returns
	* 	whether or not this node is a leaf
	**/
	public boolean isLeaf()
	{
		if (childList[0] == null)
			return true;
		else
			return false;
	}
	
	/**
	* Gets a count of how many this children this node has
	*
	* @returns
	*	the number of children node
	**/
	public int childCount()
	{
		int x = 0;
		while (this.childList[x] != null)
		{
				x++;
		}
		return x;
	}
	
	/**
	* Prints all the info about this node
	**/
	public void printNode()
	{
		System.out.println("\nLabel: " + this.label);
		System.out.println("Message: " + this.message);
		System.out.println("Prompt: " + this.prompt);
	}
		
	/**
	* Recursive function that will print the nodes in Preorder
	* Used in conjunction with the preorder function in the Tree class
	**/
	public void nodePreorder()
	{
		if (this == null)
			System.out.println("This tree is empty!");
		else {
			if (this.parent != null)
			{
				System.out.println("\nParent: " + this.parent.getLabel());
			} else {
				System.out.println("This is the Root");
			}
			System.out.println("Label: " + this.label);
			System.out.println("Message: " + this.message);
			System.out.println("Prompt: " + this.prompt);
			
			for (int x = 0; x<9; x++)
			{
				if (this.childList[x] != null)
					this.childList[x].nodePreorder();
			}
		}
	}
	
	/**
	*Prints out all the prompts of the child nodes, and returns how many menu choices there are
	*
	* @returns 
	*	the number of menu choices there are 
	**/
	public int printNodeMenu()
	{
		int menuCount = 0;
		System.out.printf("\n\n%s",this.message);
		for (int y = 0; y <9; y++)
		{
			if (this.childList[y] !=null)
			{
				menuCount++;
				System.out.printf("\n\t%d: %s", menuCount, (this.childList[y].getPrompt()));	
			}
		}
		System.out.println("\n\t0: Exit Session");
		System.out.println("\tB: Return to previous question");
		System.out.printf("Choice:");
		return menuCount;
	}
	
	/**
	* gets the specifified child node
	*
	* @param inLoc
	* which child node we want
	* 
	* @rturns 
	*	the child node at this location
	**/
	public TreeNode getChildNode(int inLoc)
	{
		return this.childList[inLoc];
	}
	
	/**
	* Sets the specified child of the node
	*
	* @param inChild
	* 	the child we want
	*
	* Postcondition:
	*	the child has been added to the farthest left location open
	**/
	public void addChildNode(TreeNode inChild)
	{
		this.childList[this.childCount()] = inChild;
		
	}
	
	/**
	*Implements a Breadth First Search to find the node with the desired label
	*
	* @param inLabel
	*	the label of the desired node
	*
	* @return
	*	the node when it is found
	**/
	public TreeNode getNodeReference(String inLabel)
	{ // https://stackoverflow.com/questions/8617790/recursive-search-for-a-node-in-non-binary-tree
		//System.out.printf("\n Label: %s\n", inLabel);
		if (this.getLabel().equals(inLabel))
		{
			//System.out.println("Its this one");
			return this;
		}
		else 
		{
			//System.out.println("Its not this one");
			TreeNode result = null;
			for (int w = 0; result ==null && w<9 && this.childList[w] !=null; w++)
			{
				result = this.childList[w].getNodeReference(inLabel);	
			}
			return result;
		}
	}
	

} // end class
