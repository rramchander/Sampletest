package javaEightfeatures.streamApi;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import domain.Student;
import domain.StudentDataBase;

public class ReduceExample {

	//reduce will take two parameters as an input - this is used most of the calculation
	//firstparameter default to initial value - second parameter - binary operator
	private static int performMultiplication(List<Integer> integerList) {
		
		return integerList.stream().reduce(1, (a,b) -> a*b);
	}
private static Optional<Integer> performMultiplicationWithoutIdentity(List<Integer> integerList) {
		
		return integerList.stream().reduce((a,b) -> a*b);
	}
	//print highestvalue of gpa
	private static Optional<Student> getHighestGpaStudent(){
		/*
		 * return StudentDataBase.getAllStudents().stream().reduce((s1,s2)->{
		 * if(s1.getGpa() > s2.getGpa()) { return s1; }else { return s2; } });
		 */
		
		return StudentDataBase.getAllStudents().stream().reduce((s1,s2)->(s1.getGpa() >s2.getGpa() ?s1 : s2 ));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> integers= Arrays.asList(1,3,5);
		System.out.println("The values are" +performMultiplication(integers));
		Optional<Integer>  optionalId = performMultiplicationWithoutIdentity(integers);
		System.out.println("The performMultiplicationWithoutIdentity are" +optionalId.get());
		Optional<Student> studentObj = getHighestGpaStudent();
		System.out.println("The getHighestGpaStudent are" +studentObj.get());
	}

}
