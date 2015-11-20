/**
 * Bassam Qoutah
 * Section(002)
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utilities extends Assignment{

		private String fileName;
		private StringBuilder builder = new StringBuilder();
		private String assignmentName;
		int section , essay1 ,test1 , essay2 , test2 , finalGrade;
		
		public List<Student> getAllStudent() throws IOException{
			builder.setLength(0);
			List<Student> list = new ArrayList<Student>();
			int counter = 0;
			for(String line: readFile(fileName).split("\n")){
				if((counter += 1) > 2)
					builder.append(line + ":");
				
			}
			
			for(String line : builder.toString().split(":")){
				String attribute[] = line.split(",");
				list.add(new Student(attribute[0] , attribute[1] , Integer.parseInt(attribute[2]) ,
						Integer.parseInt(attribute[3]) , Integer.parseInt(attribute[4]) , Integer.parseInt(attribute[5]) , Integer.parseInt(attribute[6])));
			}
			return list;
		}
		
		public void setAssigment() throws IOException{
			int counter = 0;
			builder.setLength(0);
			for(String line: readFile(fileName).split("\n")){
				if((counter += 1) == 2)
					builder.append(line);				
			}
			
			String attribute[] = builder.toString().split(",");
			this.setAssignmentName(attribute[0]);
			this.setSection(Integer.parseInt(attribute[1]));
			this.setEssay1(Integer.parseInt(attribute[2]));
			this.setTest1(Integer.parseInt(attribute[3]));
			this.setEssay2(Integer.parseInt(attribute[4]));
			this.setTest2(Integer.parseInt(attribute[5]));
			this.setFinalGrade(Integer.parseInt(attribute[6]));
	
		}
		
		private String readFile(String file) throws IOException {
		    BufferedReader reader = new BufferedReader( new FileReader (file));
		    String line = null;
		    StringBuilder  stringBuilder = new StringBuilder();
		    String ls = System.getProperty("line.separator");
		    
		    while( ( line = reader.readLine() ) != null ) {		       
		        stringBuilder.append(line).toString();
		        stringBuilder.append(ls);
		    }
		    reader.close();
		    return stringBuilder.toString();
		}
		
		
		public void setFileName(String fileName){
			this.fileName = fileName;
		}
		
		public String getFileName(String fileName){
			return fileName;
		}
		
		public void help(){
			System.out.println("      Accepted commands:");
			System.out.println("      exit");
			System.out.println("      help");
			System.out.println("      load [filename");
			System.out.println("      students");
			System.out.println("      search [partial name");
			System.out.println("      assignments");
			System.out.println("      grades");
			System.out.println("      student [student name");
			System.out.print("      assignment [assignment name]");
		}
		
		public void grades(){
			
		}
		
		public char gradeRating(int points){
			if(points >= 90 && points <= 100)
				return 'A';
			else if(points >= 80 && points < 90)
				return 'B';
			else if(points >= 70 && points < 80)
				return 'C';
			else if(points >= 60 && points < 70)
				return 'D';
			else if(points >= 0 && points < 80)
				return 'F';
			else
				return '-';
		}
		
		public String setWordAllignment(String word){
			if(word.length() <= 8){
				int length = word.length();
				for(int ctr = 0; ctr < 9 - length ; ctr++){
					word += " ";
				}
				
				return word;
			}
			return word;
		}
		
		public Student searchStudent(List<Student> list , String studentName){
			for(Student student : list){
				if(student.getFirstName().contains(studentName) || student.getLastName().contains(studentName) ||
						student.getFirstName().toLowerCase().contains(studentName.toLowerCase()) || student.getLastName().toLowerCase().contains(studentName.toLowerCase()))
					return student;
			}
				return null;
		}
		
		public void studentGrade(List<Student> list){
			int grades[] = new int[5];
			for(Student student : list){
				
				int points = student.getEssay1() + student.getEssay2() + student.getTest1() + student.getTest2() + student.getFinalGrade();
				if(String.valueOf(this.gradeRating(points)).equals("A"))
					grades[0] += 1;
				else if(String.valueOf(this.gradeRating(points)).equals("B"))
					grades[1] += 1;
				else if(String.valueOf(this.gradeRating(points)).equals("C"))
					grades[2] += 1;
				else if(String.valueOf(this.gradeRating(points)).equals("D"))
					grades[3] += 1;
				else if(String.valueOf(this.gradeRating(points)).equals("F"))
					grades[4] += 1;
			}
			
			System.out.println("      A: "+ grades[0]);
			System.out.println("      B: "+ grades[1]);
			System.out.println("      C: "+ grades[2]);
			System.out.println("      D: "+ grades[3]);
			System.out.println("      F: "+ grades[4]);
	}
		
		public double average(List<Student> list){
			double answer = 0;
			for(Student student : list)
				 answer += student.getEssay1() + student.getEssay2() + student.getTest1() + student.getTest2() + student.getFinalGrade();
			
			return answer / list.size();
		}
		
		public double max(List<Student> list){
			double array[] = new double[list.size()];
			int counter = 0;
			
			for(Student student : list){
				 array[counter] = student.getEssay1() + student.getEssay2() + student.getTest1() + student.getTest2() + student.getFinalGrade();
				 ++counter;
			}
			
			double temp = 0;
			for(int ctr = 0 ;ctr  < array.length-1 ; ctr++){
				for(int ctr1 = 0 ;ctr1  < array.length-1 ; ctr1++){
					if(array[ctr1] < array[ctr1+1]){
						temp = array[ctr1];
						array[ctr1] = array[ctr1+1];
						array[ctr1+1] = temp;
					}
						
				}
			}
			return array[0];
			
		}
		
		public double min(List<Student> list){
			double array[] = new double[list.size()];
			int counter = 0;
			
			for(Student student : list){
				 array[counter] = student.getEssay1() + student.getEssay2() + student.getTest1() + student.getTest2() + student.getFinalGrade();
				 ++counter;
			}
			
			double temp = 0;
			for(int ctr = 0 ;ctr  < array.length-1 ; ctr++){
				for(int ctr1 = 0 ;ctr1  < array.length-2 ; ctr1++){
					if(array[ctr1] > array[ctr1+1]){
						temp = array[ctr1];
						array[ctr1] = array[ctr1+1];
						array[ctr1+1] = temp;
					}
						
				}
			}
			return array[0];
			
		}
		
		public List<Integer> testScores(String exam , List<Student> list){
			List<Integer> numList = new ArrayList<Integer>();
			int[] count = new int[5];
			if("essay 1".equals(exam.toLowerCase())){
				
				for(Student student : list){
					if(student.getEssay1() > this.percentage(80, this.getEssay1()))
						count[0] ++;
					else if(student.getEssay1() > this.percentage(60, this.getEssay1()))
						count[1] ++;
					else if(student.getEssay1() > this.percentage(40, this.getEssay1()))
						count[2] ++;
					else if(student.getEssay1() > this.percentage(20, this.getEssay1()))
						count[3] ++;
					else if(student.getEssay1() > this.percentage(0, this.getEssay1()))
						count[4] ++;				
				}
				
				for(int num : count)
					numList.add(num);
				
				return numList;
			}else if("essay 2".equals(exam.toLowerCase())){
				for(Student student : list){
					if(student.getEssay2() > this.percentage(80, this.getEssay2()))
						count[0] ++;
					else if(student.getEssay2() > this.percentage(60, this.getEssay2()))
						count[1] ++;
					else if(student.getEssay2() > this.percentage(40, this.getEssay2()))
						count[2] ++;
					else if(student.getEssay2() > this.percentage(20, this.getEssay2()))
						count[3] ++;
					else if(student.getEssay2() > this.percentage(0, this.getEssay2()))
						count[4] ++;				
				}
				
				for(int num : count)
					numList.add(num);
				
				return numList;
			}else if("test 1".equals(exam.toLowerCase())){
				for(Student student : list){
					if(student.getTest1() > this.percentage(80, this.getTest1()))
						count[0] ++;
					else if(student.getTest1() > this.percentage(60, this.getTest1()))
						count[1] ++;
					else if(student.getTest1() > this.percentage(40, this.getTest1()))
						count[2] ++;
					else if(student.getTest1() > this.percentage(20, this.getTest1()))
						count[3] ++;
					else if(student.getTest1() > this.percentage(0, this.getTest1()))
						count[4] ++;				
				}
				
				for(int num : count)
					numList.add(num);
				
				return numList;
			}else if("test 1".equals(exam.toLowerCase())){
				for(Student student : list){
					if(student.getTest2() > this.percentage(80, this.getTest2()))
						count[0] ++;
					else if(student.getTest2() > this.percentage(60, this.getTest2()))
						count[1] ++;
					else if(student.getTest2() > this.percentage(40, this.getTest2()))
						count[2] ++;
					else if(student.getTest2() > this.percentage(20, this.getTest2()))
						count[3] ++;
					else if(student.getTest2() > this.percentage(0, this.getTest2()))
						count[4] ++;				
				}
				
				for(int num : count)
					numList.add(num);
				
				return numList;
			}else if("final".equals(exam.toLowerCase())){
				for(Student student : list){
					if(student.getFinalGrade() > this.percentage(80, this.getFinalGrade()))
						count[0] ++;
					else if(student.getFinalGrade() > this.percentage(60, this.getFinalGrade()))
						count[1] ++;
					else if(student.getFinalGrade() > this.percentage(40, this.getFinalGrade()))
						count[2] ++;
					else if(student.getFinalGrade() > this.percentage(20, this.getFinalGrade()))
						count[3] ++;
					else if(student.getFinalGrade() > this.percentage(0, this.getFinalGrade()))
						count[4] ++;				
				}
				
				for(int num : count)
					numList.add(num);
				
				return numList;
			}
			
			return null;
		}
		
		public double percentage(double whatIs , double percentageOf){
			return (whatIs * 0.01) * percentageOf;
			
		}
		
		
	
		
}
