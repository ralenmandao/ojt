/**
 * Bassam Qoutah
 * Section(002)
 */


abstract public class Assignment {
	String assignmentName;
	int section , essay1 ,test1 , essay2 , test2 , finalGrade;

	public String getAssignmentName() {
		return assignmentName;
	}

	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}

	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
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
