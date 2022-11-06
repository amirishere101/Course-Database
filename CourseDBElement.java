/**
 * A class that creates an object that contains a courses data
 * @author Amir Hawasly
 *
 */
public class CourseDBElement implements Comparable<Object>{
	private String courseID;
	private String roomNum;
	private String instructor;
	private int crn;
	private int credits;
	
	/**
	 * Constructor
	 * @param courseID
	 * @param crn
	 * @param credits
	 * @param roomNum
	 * @param instructor
	 */
	public CourseDBElement(String courseID, int crn, int credits, String roomNum, String instructor) {
		this.courseID = courseID;
		this.crn = crn;
		this.credits = credits;
		this.roomNum = roomNum;
		this.instructor = instructor;
	}
	
	/**
	 * default constructor
	 */
	public CourseDBElement() {
		courseID = "";
		roomNum = "";
		instructor = "";
		crn = 0;
		credits = 0;
	}

	/**
	 * compares the current object and the object passed in based on crn.
	 */
	public int compareTo(Object o) {
		CourseDBElement compare = (CourseDBElement)o;
		return crn - compare.getCRN();
	}

	public void setCRN(int crn) {
		this.crn = crn;
	}
	
	public int getCRN() {
		return crn;
	}
	
	 public void setID(String courseID) {
		 this.courseID = courseID;
	 }

	public String getID() {
		return courseID;
	}
	
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomNum() {
		return roomNum;
	}
	
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	public String getInstructor() {
		return instructor;
	}
	
	public void setCredits(int credits) {
		this.credits = credits;
	}
	
	public int getCredits() {
		return credits;
	}
	
	public String toString() {
		return "\nCourse:" + courseID + " CRN:" + crn + " Credits:" + credits + " Instructor:" + instructor + " Room:" + roomNum;
	}
	
	public String crnString() {
		return String.valueOf(crn);
	}
}
