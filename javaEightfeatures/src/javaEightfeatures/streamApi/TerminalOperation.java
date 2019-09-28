package javaEightfeatures.streamApi;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import domain.Student;
import domain.StudentDataBase;

//collect the data for you - foreach,min,max,reduce,collect and etc
//joining - string concatenation on the element in the stream
public class TerminalOperation {
	//joining - AdamJennyEmilyDavesophiaJames - print the name as concentation
	private static String joining_1() {
		return StudentDataBase.getAllStudents().stream().map(Student::getName)
		.collect(Collectors.joining());
	}
	//delimiter string - Adam-Jenny-Emily-Dave-sophia-James
	private static String joining_2() {
		return StudentDataBase.getAllStudents().stream().map(Student::getName)
		.collect(Collectors.joining("-"));
	}
	//joining the prefix and suffix
	private static String joining_3() {
		return StudentDataBase.getAllStudents().stream().map(Student::getName)
		.collect(Collectors.joining("-","(",")"));
	}
	
	//counting
	private static long count() {
		return StudentDataBase.getAllStudents().stream().collect(Collectors.counting());
	}
	private static long count_1() {
		return StudentDataBase.getAllStudents().stream()
				.filter(student-> student.getGpa()>=4.0).collect(Collectors.counting());
	}
	
	//min by
	private static Optional<Student> getminby(){
		return StudentDataBase.getAllStudents().stream().collect(Collectors.minBy(Comparator.comparing(Student::getGpa)));
	}
	//min by
		private static Optional<Student> getmaxby(){
			return StudentDataBase.getAllStudents().stream().collect(Collectors.maxBy(Comparator.comparing(Student::getGpa)));
		}
	
	public static void main(String[] args) {
		System.out.println(" joining_1()" + joining_1());
		System.out.println(" joining_2()" + joining_2());
		System.out.println(" joining_3()" + joining_3());
		
		//counting
		System.out.println(" count()" + count());
		System.out.println(" count_1()" + count_1());
		
		Optional<Student> minby= getminby();
		if(minby.isPresent()) {
			System.out.println(" minby()************" + minby.get());
		}
		Optional<Student> maxby= getmaxby();
		if(maxby.isPresent()) {
			System.out.println(" maxby()************" + maxby.get());
		}
	}

}
