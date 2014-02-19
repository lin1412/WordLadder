import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Runs the Word Ladder program with the user's input of beginning word, ending word, and a 
 * dictionary text file.
 * @author Hang Lin 
 *
 */
public class WordLadder {
	private static Node begin; //The source word.
	private static Node end; //The destination word.
	private static int size = 0; //Number of words in usable.
	private static Node[] usable; // Words out of the Dictionary.txt that has same length as size.
	private static Node[] links; // Temporary node used to contain neighbors.
	private static Stack<Node>solution = new Stack<Node>();//Contains the route to the solution.
	private static LinkedList<Node>BFS = new LinkedList<Node>();//Used to do a Breadth-First Search.
	private static boolean solvable = false;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner input = new Scanner(new File("input.txt"));
		begin = new Node(input.next());
		end = new Node(input.next());
		
		// set up an array of words from the dictionary file that has the same length as the
		// beginning/ending word.
		ReadFile reader = new ReadFile(begin.getWord().length());
		reader.openFile();
		reader.readFile();
		reader.close();	
		
		//add the words from the List to the Array of Nodes.
		List<String> temp = reader.getList();
		temp.remove(begin.getWord());
		temp.remove(end.getWord());
		usable = new Node[temp.size()+2];	

		usable[0] = begin;
		usable[1] = end;
		for (int i = 0; i < temp.size(); i++){
			usable[i+2] = new Node(temp.get(i));
		}
		size = usable.length;

    	System.out.println("Finding shortest route from source to destination word...");
		solveA();
		printA();
		
	}
	
	/**
	 * Check if 2 words can be linked together.
	 * @param a the 1st word.
	 * @param b the 2nd word.
	 * @return true if they have 1 letter difference, and false otherwise.
	 */
    public static boolean linkable(String a, String b) {
        int difference = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
            	difference++;
            }
        }
        return (difference == 1);
    }
	
    /**
     * 
     * @return true is there is an solution route, false otherwise.
     */
    public static boolean solveA(){	
    	
    	BFS.add(begin);
    	begin.setParent(null);
    	begin.setVisited(true);
    	int q = 1;
    	
    	//If the Queue is not empty, keep searching through
    	while(!BFS.isEmpty()){
    		System.out.println("time ran: " + q);
    		Node parent = BFS.remove();    		
    		Node[] tempArray = getLink(parent);
    		
    		for ( int i = 0; i < tempArray.length; i++){
    			//if destination word is found, stop
    			if (tempArray[i].getWord().equals(end.getWord())){
    				tempArray[i].setParent(parent);
    				tempArray[i].setVisited(true);
    				solvable = true;
    				return true;
    			}
    			
    			//if the word has not been visited before, add to BFS
    			if(!tempArray[i].visited()){
    				tempArray[i].setParent(parent);
    				tempArray[i].setVisited(true);
    				BFS.add(tempArray[i]);
    			}
    		}
    		
    		System.out.print("LinkedList contains: ");
    		for (int k = 0; k < BFS.size(); k++){
    			System.out.print(BFS.get(k).getWord() + ", ");
    		}
    		System.out.println();
    		System.out.println();
    		q++;
    	}
		return false;
    }

    /**
     * Find the Nodes that can be linked with the parent node.
     * @param n The parent node, find all other node thats have a edge with it.
     * @return an array of Node
     */
    public static Node[] getLink(Node n){
    	List<Node> tempList = new ArrayList<Node>();
    	for ( int i = 0; i < size; i++){
    		if(linkable(usable[i].getWord(),n.getWord())){
    			tempList.add(usable[i]);
    		}
    	}
    	// convert ArrayList to Array
    	links = new Node[tempList.size()];
    	tempList.toArray(links);
    	
    	System.out.print(n.getWord() + " is linked with: ");
    	for ( int a = 0; a < links.length; a++){
    		System.out.print(links[a].getWord() + ",");
			}
    	System.out.println();
		return links;
    	
    }
	/**
	 * Print out the solution is it has one, otherwise tell user it has no solution.
	 */
	public static void printA() {
		// Set up the solution into an stack, starting from the destination word.
	    Node temp = end;
	    solution.add(temp);
	    while(temp.getParent()!=null){
	    	temp = temp.getParent();
	    	solution.add(temp);
	    }
	    
	    //output the solution into an text file.
		try {
		      PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
		      if (solvable){
		    	  System.out.println("Route found!!!");
		    	  while (!solution.empty()){
		    		  if ( solution.size()!=1) {
		    			  out.print(solution.pop().getWord() + ", ");
		    		  }
		    		  else {
		    			  out.print(solution.pop().getWord());
		    		  }
		    	  }
		      }
		      else {
		    	  System.out.println("Route does no exist.");
		    	  out.println("No solution");
		      }
		} 
		catch (FileNotFoundException e) {
		      e.printStackTrace();
		}
	}
}
//On my honor:
//
//- I have not used source code obtained from another student,
//or any other unauthorized source, either modified or
//unmodified.
//
//- All source code and documentation used in my program is
//either my original work, or was derived by me from the
//source code published in the textbook for this course.
//
//- I have not discussed coding details about this project with
//anyone other than my partner (in the case of a joint
//submission), instructor, ACM/UPE tutors or the TAs assigned
//to this course. I understand that I may discuss the concepts
//of this program with other students, and that another student
//may help me debug my program so long as neither of us writes
//anything during the discussion or modifies any computer file
//during the discussion. I have violated neither the spirit nor
//letter of this restriction.
// Hang Lin
