package javaEightfeatures.streamApi;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import domain.StudentDataBase;
import domain.Student;

public class StreamApiExample {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//student name and activaties into map
		//list
		Map<String, List<String>> studentmapObj= StudentDataBase.getAllStudents().stream()
				.filter(student -> student.getGpa() >=3.9)
				.collect(Collectors.toMap(Student::getName, Student::getActivaties));
		
		System.out.println("The map object" +studentmapObj);

		
		
		List<String> listofstudents = StudentDataBase.getAllStudents().stream()
										.filter(student -> student.getGpa() >=3.9)
										.map(Student::getName)
										.collect(Collectors.toList());
		

		System.out.println("The list object" +listofstudents);
		
		//set
		Set<String> setofstudents =StudentDataBase.getAllStudents().stream()
				.filter(student -> student.getGpa() >=3.9)
				.map(Student::getName)
				.collect(Collectors.toSet());
				System.out.println("The set object" +setofstudents);
				
		// SET - MAPPING SHORT CUT
				//list- short cut
				Set<String> setactivites = StudentDataBase.getAllStudents().stream()
						.collect(Collectors.mapping(Student::getName, Collectors.toSet()));
				System.out.println("The setmapping object" +setactivites);
				
	    //List - list of activities - list of list			
				
				List<String> listofactivites = StudentDataBase.getAllStudents().stream()
												.filter(student -> student.getGpa() >=3.9)
												.map(Student::getActivaties)
												.flatMap(List::stream).distinct() // this method gives a list of list- removes duplicates
												.collect(Collectors.toList());
				
				System.out.println("The flat object" +listofactivites);
				//list- short cut - MAPPING
				List<String> listofsortedactivites1 = StudentDataBase.getAllStudents().stream()
						.collect(Collectors.mapping(Student::getName, Collectors.toList()));
				System.out.println("The shortcutlist object" +listofsortedactivites1);
				//list- short cut -0 MAPPING USASGE	
				List<List<String>> listofsortedactivites2 = StudentDataBase.getAllStudents().stream()
						.collect(Collectors.mapping(Student::getActivaties, Collectors.toList()));
				System.out.println("The listofsortedactivites2 object" +listofsortedactivites2);
				
			//count the list of data	
				long noofstudents = StudentDataBase.getAllStudents().stream()
						.filter(student -> student.getGpa() >=3.9)
						.map(Student::getActivaties)
						.flatMap(List::stream).distinct() // this method gives a list of list- removes duplicates
						.count();
						System.out.println("The no of students" +noofstudents);
						
		   //sorted order	
						List<String> listofsortedactivites = StudentDataBase.getAllStudents().stream()
								.filter(student -> student.getGpa() >=3.9)
								.map(Student::getActivaties)
								.flatMap(List::stream).distinct() // this method gives a list of list- removes duplicates
								.sorted()
								.collect(Collectors.toList());
						System.out.println("The flat object- sorted order" +listofsortedactivites);
						
						
						
			//sorted order by name
					List<Student> studetList = StudentDataBase.getAllStudents().stream()
							//asc order
							//.sorted(Comparator.comparing(Student::getName))
							//desc order
							.sorted(Comparator.comparing(Student::getName).reversed())
							.collect(Collectors.toList());
					//listofsortedactivites.forEach(System.out::println);
					
					System.out.println("The name sorted order" +studetList);			
		
					//Terminal operation - mapping same as set,list and map - collect the data in a collection
				
					
	}

}
