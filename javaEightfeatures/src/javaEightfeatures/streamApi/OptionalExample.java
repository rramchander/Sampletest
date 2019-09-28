package javaEightfeatures.streamApi;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import domain.Student;
import domain.StudentDataBase;

public class OptionalExample {
//used to avoid nullpointer Exception and unnecssary null checks
	private static List<Student> getStudentList(){
		Optional<List<Student>> optionalList = Optional.ofNullable(StudentDataBase.getAllStudents());
		if(optionalList.isPresent()) {
			//return optionalList.get();
			return optionalList.get().stream().collect(Collectors.toList());
			
		}
		return null;
	
		
	}
	
	
	public static void main(String[] args) {
		List<Student> studentList = getStudentList().stream().collect(Collectors.toList());
		System.out.println("The optional values are " +studentList);
		studentList.forEach(s -> {
			Optional<List<String>> activatiesList = Optional.ofNullable(s.getActivaties().stream().filter(student-> student.equalsIgnoreCase("swimming")).collect(Collectors.toList()));
			if(activatiesList.isPresent()) {
				List<String> activiesList = activatiesList.get();
				activiesList.forEach(studentactivies -> System.out.println(studentactivies));
			}
		});
	}
}
