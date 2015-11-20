import java.util.HashMap;
import java.util.Map;

public class Subject {
	private String name;
	private Map<String, Integer> assignments = new HashMap<String,Integer>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void addAssignment(String assignmentName, int max){
		assignments.put(assignmentName, max);
	}
}
