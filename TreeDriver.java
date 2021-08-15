/**
*The TreeDriver class implements a main method that allows the user
* to interact with the tree claas
*
* @author MacGregor Winegard
*	Email: macgregor.winegard@stonybrook.edu
*	Stony Brook ID: 114152787
*
* @version 1 Build 1 August 1 2020
**/

import java.util.Scanner;
import java.io.File; //https://www.youtube.com/watch?v=lHFlAYaNfdo
import java.io.FileNotFoundException; //https://www.youtube.com/watch?v=lHFlAYaNfdo
public class TreeDriver
{
	public static void main(String args[]) throws FileNotFoundException
	{
		System.out.println("\nIs your washing machine running? ");		
		System.out.println("Well then you better go catch it!!"); 
		
		//general variables
		boolean doneMain = false; // used to terminate the session
		String menuChoice; // what the users menu input will be entered into each time
		Scanner inScanner = new Scanner(System.in); // main scanner that will be used for most aspects
		Tree mainTree = new Tree(); // the tree we will use in this program
		
		//load file variables
		String fileName; // used to parse out the file name
		int numChild; // the number of children that will be made
		String makingChildFor; // the string of the node that is having children added to it
		
		//Help Menu variables
		String helpMenuChoice; // the decision the user makes
		int numOptions; // how many option choices the user has for the question
		boolean doneHelp = false; // used to determine if session is ended early
		
		
		
		while(!doneMain) // menu we keep cycling through until quit
		{
			printMenu();
			menuChoice = inScanner.nextLine().toUpperCase();
			
			if (menuChoice.equals("L")) // load a tree
			{
				System.out.printf("\nPlease input the file you want to load: ");
				fileName = inScanner.nextLine();
				
				File file = new File(fileName);
				Scanner fileScan = new Scanner(file);
				
				while (fileScan.hasNextLine())
				{					
					String tempLabel = fileScan.nextLine();
					// the first time this will be the root, every time
					// after that it will be how many children that node has
					if (tempLabel.startsWith("root"))
					{ // initiates and sets a root TreeNode
						TreeNode tempRoot = new TreeNode();
						tempRoot.setLabel(tempLabel.replaceAll(" ", ""));
						tempRoot.setPrompt(fileScan.nextLine().replaceAll(" ", ""));
						tempRoot.setMessage(fileScan.nextLine());
						mainTree.setRoot(tempRoot);
						
						/*
						System.out.println("\nLabel: " + mainTree.getRoot().getLabel());
						System.out.println("Message: " + mainTree.getRoot().getMessage());
						System.out.println("Prompt: " + mainTree.getRoot().getPrompt()); */
						
						char numChildStr = (fileScan.nextLine().charAt(5));
						numChild = Character.getNumericValue(numChildStr);
						
						for  (int makeChild = 0; makeChild < numChild; makeChild++)
						{
							//System.out.printf("\nMaking child: ");
							mainTree.addNode(fileScan.nextLine().replaceAll(" ", ""),
																	fileScan.nextLine(),
																	fileScan.nextLine(),
																	tempLabel.replaceAll(" ", ""));
						}
						
					} else { // the current label doesn't say root so we need to split into the name of the parent and how many children we want
						String[] labelSplit = tempLabel.split(" ", 3); //https://www.geeksforgeeks.org/split-string-java-examples/
						//System.out.println("Parent Label:" + labelSplit[0] + ":"); // should be the label
						//System.out.println(labelSplit[1]); // should be the number of children
						numChild = Integer.parseInt(labelSplit[1]);
						
						for (int a = 0; a < numChild; a++)
						{
							//System.out.printf("\nMaking child: ");
							mainTree.addNode(fileScan.nextLine().replaceAll(" ", ""), // label
																	fileScan.nextLine(), //prompt
																	fileScan.nextLine(), // message
																	labelSplit[0]); // parent label	
						}
					}			
				}
				
				
				
				
				
			} else if (menuChoice.equals("H")) { // begin a help session
				
				TreeNode pointer = mainTree.getRoot();
				System.out.println("\nStarting Help Session:");
				doneHelp = false;
				
				while (pointer.getChildNode(0) !=null && doneHelp == false)
				{
					numOptions = pointer.printNodeMenu();
					helpMenuChoice = inScanner.nextLine();
					
					if (helpChecker(helpMenuChoice) == true)
					{
					
						if (helpMenuChoice.toUpperCase().equals("B"))
						{
							if (pointer.getParent() == null)
								System.out.println("Cannot go back!");
							else 
								pointer = pointer.getParent();
						}  else if (Integer.parseInt(helpMenuChoice) == 0) { // quit menu
							doneHelp = true;
							System.out.println("Ending help session prematurely.");
							
						} else if (Integer.parseInt(helpMenuChoice)<= numOptions) {
							pointer = pointer.getChildNode((Integer.parseInt(helpMenuChoice) -1));
						} else {
							System.out.println("That is not an option!!");
							
						}	
					} else {
						System.out.println("That is not an option!");
					}
				} 
				
				if (doneHelp == false)
					System.out.println(pointer.getMessage());
				
				
			} else if (menuChoice.equals("T")) { // travers a tree in Preorder
			
				System.out.println("\n\n");
				mainTree.preOrder();
				System.out.println("\n");
				
			} else if (menuChoice.equals("Q")){ // quit
				doneMain = true;
			} else {
				System.out.println("That is not an option!");
			}
			
		} //  end while !doneMain
		System.out.println("Have a great day!\n");
	} // end main
	
	/**
	* Prints out a nciely formatted menu at the start of each round through
	**/
	public static void printMenu()
	{
		System.out.println("\nMenu:");
		System.out.println("\tL - Load a Tree.");
		System.out.println("\tH - Begin a help session.");
		System.out.println("\tT - Traverse a Tree in preorder");
		System.out.println("\tQ - Quit");
		System.out.printf("Choice: ");	
	}
	
	/**
	*Determines whether the input is valid using regex
	* used in the hepler method to make sure no errors are thrown
	*
	* @param helpInput
	* 	the input put in durin the help menu
	*
	* @returns
	*	whether or not it matches the required inputs
	**/
	public static boolean helpChecker(String helpInput)
	{ 
		return helpInput.matches("[Bb0-9]");
	}
	
	
} // end class