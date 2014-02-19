import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Reads in the dictionary text file and adds only the words with same length as the 
 * beginning/ending word to a List.
 * @author Hang Lin
 *
 */
public class ReadFile {
	private Scanner scanner;
	public List<String> dictionary = new ArrayList<String>();
	public int wordSize = 0;
	public String wd;
	
	/**
	 * 
	 * @param size the size of the beginning/ending word
	 */
	public ReadFile(int size){
		wordSize = size;
	}
	/**
	 * opens the text file.
	 */
	public void openFile() {
		try {
  		  scanner = new Scanner ( new File ("dictionary.txt"));
  		  }
		catch (Exception e) {	//Catch exception if any
  		  System.err.println("Error: " + e.getMessage());
  		  }
	}
	
	/**
	 * adds the word to list if the size match.
	 */
	public void readFile() {
		while(scanner.hasNextLine()) {
		wd = scanner.nextLine();
			if (wordSize == wd.length()) {
				dictionary.add(wd);
			}
		}
	}
	
	/**
	 * close the text file.
	 */
	public void close() {
		scanner.close();
	}
	
	/**
	 * 
	 * @return the list that usable for word ladder
	 */
	public List<String> getList() {
		return dictionary;
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
