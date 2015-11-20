/**
 * Bassam Qoutah
 * Section(002)
 */


import java.util.List;
import java.util.Scanner;

public class MainClass {

	public static void main(String args[]) {
		boolean flag = true;
		Scanner scanner = new Scanner(System.in);
		String command = "";
		String fileName = null, fileTitle, studentName = "", exam = "";
		Utilities util = new Utilities();
		List<Student> list = null;

		try {
			System.out.println("Class Stats program by Bassam Qoutah");

			while (flag) {
				System.out.print("\n> ");
				command = scanner.nextLine();

				// help
				if (command.trim().toLowerCase().equals("help")) {
					util.help();

					// load
				} else if (command.trim().toLowerCase().split(" ")[0].equals("load")
						&& command.trim().toLowerCase().split(" ").length > 1) {
					try{
						fileName = command.trim().split(" ")[1];
						util.setFileName(fileName);
						util.setAssigment();
						list = util.getAllStudent();
						System.out.printf("      loaded class %s, section %d", util.getAssignmentName(), util.getSection());
					}catch(Exception e){
						System.out.print("      Cannot load file " + fileName);
					}
					// students
				} else if (command.trim().toLowerCase().equals("students") && list != null) {
					int totalScorePossible = util.getEssay1() + util.getEssay2() + util.getTest1() + util.getTest2()
							+ util.getFinalGrade();
					System.out.printf(
							"      Student Grades for  %s, section %d \n      Total points possible: %d\n\n",
							util.getAssignmentName(), util.getSection(), totalScorePossible);
					System.out.println("      First Name\t\tLast Name\t\tPoints\t\tGrade");
					System.out.println("      ----------\t\t---------\t\t------\t\t-----");
					for (Student student : list) {
						int points = student.getEssay1() + student.getEssay2() + student.getTest1() + student.getTest2()
								+ student.getFinalGrade();
						System.out.printf("      %s\t\t\t%s\t\t   %d\t\t   %s\n",
								util.setWordAllignment(student.getFirstName()),
								util.setWordAllignment(student.getLastName()), points, util.gradeRating(points));
					}
					// assignments
				} else if (command.trim().toLowerCase().equals("assignments")) {
					System.out.printf("      Assignments for %s, section %d\n\n", util.getAssignmentName(),
							util.getSection());
					System.out.println("      Assignment\t\tPoints");
					System.out.println("      ----------\t\t------");
					System.out.printf("      essay 1  \t\t\t  %d\n", util.getEssay1());
					System.out.printf("      test 1   \t\t\t  %d\n", util.getTest1());
					System.out.printf("      essay 2  \t\t\t  %d\n", util.getEssay2());
					System.out.printf("      test 2   \t\t\t  %d\n", util.getTest2());
					System.out.printf("      final    \t\t\t  %d\n", util.getFinalGrade());

					// search [student name]
				} else if (command.trim().toLowerCase().split(" ")[0].equals("search")
						&& command.trim().toLowerCase().split(" ").length > 1) {
					studentName = command.trim().split(" ")[1];
					Student student = util.searchStudent(list, studentName);
					if(student == null){
						System.out.print("      Cannot find student " + studentName);
						continue;
					}
					System.out.println("      First Name\t\tLast Name\t\tPoints\t\tGrade");
					System.out.println("      ----------\t\t---------\t\t------\t\t-----");
					if (student != null) {
						int points = student.getEssay1() + student.getEssay2() + student.getTest1() + student.getTest2()
								+ student.getFinalGrade();
						System.out.printf("      %s\t\t\t%s\t\t   %d\t\t   %s\n",
								util.setWordAllignment(student.getFirstName()),
								util.setWordAllignment(student.getLastName()), points, util.gradeRating(points));
					}

					// grades
				} else if (command.trim().toLowerCase().equals("grades")) {
					System.out.printf("      Grade breakdown for %s, section %d\n", util.getAssignmentName(),
							util.getSection());
					System.out.printf("\n      Low: %d%%\n      High: %d%%\n      Ave: %d%%\n\n", (int) util.min(list),
							(int) util.max(list), (int) util.average(list));
					util.studentGrade(list);

					// student [student name]
				} else if (command.trim().split(" ")[0].toLowerCase().equals("student")
						&& command.trim().split(" ").length > 1) {
					try {
						studentName = command.trim().split(" ")[1];
						Student student = util.searchStudent(list, studentName);
						if(student == null){
							System.out.print("      Cannot find student " + studentName);
							continue;
						}
						if (student != null) {
							int points = student.getEssay1() + student.getEssay2() + student.getTest1()
									+ student.getTest2() + student.getFinalGrade();
							int totalPoint, totalPossible;
							totalPoint = student.getEssay1() + student.getEssay2() + student.getTest1()
									+ student.getTest2() + student.getFinalGrade();
							totalPossible = util.getEssay1() + util.getEssay2() + util.getTest1() + util.getTest2()
									+ util.getFinalGrade();

							System.out.printf("      Grades for %s %s\n\n", student.getFirstName(), student.getLastName());
							System.out.println("      Assignment\t\tPoints\t\tPossible");
							System.out.println("      ----------\t\t------\t\t--------");
							System.out.printf("      essay 1   \t\t  %d\t\t   %d\n", student.getEssay1(), util.getEssay1());
							System.out.printf("      test 1    \t\t  %d\t\t   %d\n", student.getTest1(), util.getTest1());
							System.out.printf("      essay 2   \t\t  %d\t\t   %d\n", student.getEssay2(), util.getEssay2());
							System.out.printf("      test 2    \t\t  %d\t\t   %d\n", student.getTest2(), util.getTest2());
							System.out.printf("      final     \t\t  %d\t\t   %d\n", student.getFinalGrade(),
									util.getFinalGrade());
							System.out.printf("      total     \t\t  %d\t\t   %d\n\n", totalPoint, totalPossible);
							System.out.printf("      Final Grade: %s \n", util.gradeRating(points));
						}
					} catch (Exception e) {

					}
					// assignment [assignment]
				} else if (command.trim().toLowerCase().split(" ")[0].equals("assignment")
						&& command.trim().split(" ").length > 1) {
					exam = command.trim().toLowerCase().split(" ")[1];
					if (command.trim().split(" ").length == 3)
						exam += " " + command.trim().toLowerCase().split(" ")[2];
					if(util.testScores(exam, list) == null){
						System.out.print("      Cannot find assignment " + exam);
						continue;
					}
					System.out.printf("      %s: %s points\n      grade breakdown\n", exam, "5");
					System.out.printf("      A: %d\n", util.testScores(exam, list).get(0));
					System.out.printf("      B: %d\n", util.testScores(exam, list).get(1));
					System.out.printf("      C: %d\n", util.testScores(exam, list).get(2));
					System.out.printf("      D: %d\n", util.testScores(exam, list).get(3));
					System.out.printf("      F: %d\n", util.testScores(exam, list).get(4));
				}else if(command.trim().toLowerCase().equals("exit")){
					System.exit(0);
				}else{
					System.out.print("      Invalid command");
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
