import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * 
 * @author Amir Hawasly
 *
 */
public class CourseDBManager implements CourseDBManagerInterface {
	CourseDBStructure structure;
	
	
	public CourseDBManager() {
		structure = new CourseDBStructure(97);
	}
	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		CourseDBElement courseData = new CourseDBElement(id, crn, credits, roomNum, instructor);
		structure.add(courseData);
	}

	@Override
	public CourseDBElement get(int crn) {
		try {
			return structure.get(crn);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void readFile(File input) throws FileNotFoundException {
		try {
			String[] elementData = new String[6];
			FileReader logReader = new FileReader(input.getName());
			BufferedReader buffer = new BufferedReader(logReader);
			for(String line = buffer.readLine(); line != null; line = buffer.readLine()) {
				elementData = line.split(" ", 5);
				if(elementData[3].contains("Distance") && elementData[4].contains("Learning") && !elementData[3].contains("Learning")) {
					elementData = line.split(" ", 6);
					elementData[3] = "Distance-Learning";
					elementData[4] = elementData[5];
				}
				CourseDBElement data = new CourseDBElement(elementData[0], Integer.parseInt(elementData[1]), Integer.parseInt(elementData[2]), elementData[3], elementData[4]);
				structure.add(data);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<String> showAll() {
		return structure.showAll();
	}
}
