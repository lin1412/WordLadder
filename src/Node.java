/**
 * The node contains the words from the dictionary text.
 * @author Hang
 *
 */

public class Node {
	private String word;
	private Node parent;
	private boolean visited = false;
	
	/**
	 * The constructor
	 * @param w the word this node will hold.
	 */
	Node(String w){
		word = w;
	}
	
	/**
	 * @return the String it holds
	 */
	public String getWord(){
		return word;
	}
	
	/**
	 * setup a parent node for this node
	 * @param n the parent of this node
	 */
	public void setParent(Node n){
		parent = n;
	}
	
	/** 
	 * @return the parent node
	 */
	public Node getParent(){
		return parent;
	}
	
	/**
	 * Set the condition of this node.
	 * @param v whether or not this node has been visited. 
	 */
	public void setVisited(boolean v){
		visited = v;
	}
	
	/**
	 * @return boolean value of the node's condition
	 */
	public boolean visited(){
		return visited;
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
//Hang Lin
