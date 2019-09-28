package javaEightfeatures.streamApi;

import java.util.Optional;

import domain.Student;
import domain.StudentDataBase;

public class MatchExample {
	//predicate as input - return boolean as an output
	//AnyMatch- AllMatch - none match
	//AnyMatch -> return true if any one element matches the predicate otherwise false
	//AllMatch -> return true if all the element in the stream matches
	//noneMatch -> opposite to AllMatch return true if none of the element in the stream matches
	
	private static boolean allMatch() {
		return StudentDataBase.getAllStudents().stream().allMatch(student -> student.getGpa()>=3.5);
	}
	private static boolean anyMatch() {
		return StudentDataBase.getAllStudents().stream().anyMatch(student -> student.getGpa()>=4.0);
	}
	private static boolean noneMatch() {
		return StudentDataBase.getAllStudents().stream().noneMatch(student -> student.getGpa()>=4.1);
	}
//This is used find the element in the stream - return the optional
	//findfirst() - return the first element
	private static Optional<Student> findfirst(){
		Optional<Student> findfirstOptional = StudentDataBase.getAllStudents().stream()
											.filter(student ->student.getGpa()>= 3.9)
											.findFirst();
		return findfirstOptional;
	}
	
	//findAny - return the first encountered element	
	private static Optional<Student> findAny(){
		Optional<Student> findanyOptional = StudentDataBase.getAllStudents().stream()
											.filter(student ->student.getGpa()>= 3.9)
											.findAny();
		return findanyOptional;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       System.out.println("Allmatch" +allMatch());
       System.out.println("Anymatch" +anyMatch());
       System.out.println("nonematch" +noneMatch());
       Optional<Student> findanyOptional= findAny();
       if(findanyOptional.isPresent()) {
    	   System.out.println("findAny" +findAny().get());
       }else {
    	   System.out.println("findAny no data" );
       }
       
       Optional<Student> findfirstOptional= findfirst();
       if(findfirstOptional.isPresent()) {
    	   System.out.println("findfirst" +findfirst().get());
       }else {
    	   System.out.println("findfirst no data" );
       }
	}

}
