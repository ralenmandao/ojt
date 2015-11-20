/**
 * Bassam Qoutah
 * Section(002)
 */


public class Student {
	
	private String firstName , lastName;
	private int essay1, test1 , essay2, test2 , finalGrade;
	
	public Student(String firstName, String lastName, int essay1, int test1, int essay2, int test2, int finalGrade) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.essay1 = essay1;
		this.test1 = test1;
		this.essay2 = essay2;
		this.test2 = test2;
		this.finalGrade = finalGrade;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getEssay1() {
		return essay1;
	}
	public void setEssay1(int essay1) {
		this.essay1 = essay1;
	}
	public int getTest1() {
		return test1;
	}
	public void setTest1(int test1) {
		this.test1 = test1;
	}
	public int getEssay2() {
		return essay2;
	}
	public void setEssay2(int essay2) {
		this.essay2 = essay2;
	}
	public int getTest2() {
		return test2;
	}
	public void setTest2(int test2) {
		this.test2 = test2;
	}
	public int getFinalGrade() {
		return finalGrade;
	}
	public void setFinalGrade(int finalGrade) {
		this.finalGrade = finalGrade;
	}

}
