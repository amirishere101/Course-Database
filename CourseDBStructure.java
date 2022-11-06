import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
/**
 * A class that Stores data into a hash table with bucketing
 * @author Amir Hawasly
 *
 */
public class CourseDBStructure implements CourseDBStructureInterface{
	
	private Node[] courseDBArray;
	
	/**
	 * Constructor that takes in the estimated number of courses and 
	 * creates an array with a load factor of 1.5 and the 
	 * size must be a prime number.
	 * @param numOfCourses
	 */
	public CourseDBStructure(int numOfCourses) {
		int k = 0;
		int prime = 4*k + 3;
		while(prime <= numOfCourses/1.5) {
			k++;
			prime = 4*k + 3;	
		}
		while(!isPrimeNumber(prime)) {
			k++;
			prime = 4*k + 3;
		}
		courseDBArray = new Node[prime];
	} 

	/**
	 * Constructor that creates an array for testing purposes.
	 * @param test
	 * @param numOfCourses
	 */
	public CourseDBStructure(String test, int numOfCourses) {
		courseDBArray = new Node[numOfCourses];
	}
	
	/**
	 * takes in the CRN of a class as a string and hashes it to a 
	 * spot in the array to be placed.
	 * @param crn
	 * @return index of the array the class with this CRN should be placed
	 */
	public int hashFunction(String crn) {
		int hash = 0;
		char[] charArray = crn.toCharArray();
		for(int i = 0; i < charArray.length; i++) {
			if(i < 3) {
				hash += charArray[i];
			} else if (i == 3) {
				hash *= charArray[i];	
			} else {
				hash += charArray[i];
			}	
		}
		return hash % courseDBArray.length;
	}
	
	/**
	 * takes in a CourseDBElement and adds it to the array based on the CRN
	 * @param element
	 */
	@Override
	public void add(CourseDBElement element) {
		Node node = new Node(element);
		int index = hashFunction(element.crnString());
		Node temp = courseDBArray[index];
		while(temp != null) {
			if(temp.data.getCRN() == element.getCRN()) {
				System.out.println("update");
				temp.data = element;
				return;
			}
			temp = temp.next;
		}
		if(courseDBArray[index] == null) {
			System.out.println(node.data + " was added at spot " + index);
			courseDBArray[index] = node;
		} else {
			while(temp.next != null) {
				temp = temp.next;
			}
			System.out.println(node.data + " was added at spot " + index);
			temp.next = node;
		}
	}
	
	/**
	 * searches the linked list in the array for the course with the CRN and returns it
	 * @param crn
	 * @return CourseDBElement
	 */
	@Override
	public CourseDBElement get(int crn) throws IOException {
		Node pointer = courseDBArray[hashFunction(String.valueOf(crn))];
		while(pointer != null) {
			if(pointer.data.getCRN() == crn) {
				return pointer.data;
			}
		}
		throw new IOException();
	}
	
	/**
	 * turns the array into an array list of strings
	 * @return Array List of Strings
	 */
	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> showAll = new ArrayList<String>();
		Node temp = new Node();
		for(int i = 0; i < courseDBArray.length; i++) {
				temp = courseDBArray[i];
			while(temp != null && temp.data != null) {
				showAll.add(temp.data.toString());
				temp = temp.next;
			}
		}
		System.out.println(showAll);
		return showAll;
	}
	/**
	 * @return length of the array
	 */
	@Override
	public int getTableSize() {
		return courseDBArray.length;
	}
	
	/**
	 * checks if the number passed in is a prime number
	 * @param num
	 * @return
	 */
	private boolean isPrimeNumber(int num) {
		if(num <= 1)
			return false;
		for (int i = 2; i < num; i++) {
			if((num % i) == 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * A class that creates a node for linked lists
	 * @author Amir Hawasly
	 *
	 */
	private class Node {
		CourseDBElement data;
		Node next;
		private Node(CourseDBElement data) {
			this.data = data;
		}
		
		private Node() {
			data = null;
		}
	}
}
