package javaEightfeatures.streamApi;

import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import domain.Student;
import domain.StudentDataBase;

public class GoupbyExample {
	//group by is type group by in SQL - return map<k,v>
	private static void groupby(){
		Map<String, List<Student>> groupbyObj= StudentDataBase.getAllStudents().stream().collect(Collectors.groupingBy(Student::getGender));
		
		System.out.println("grouping by" +groupbyObj);
	}
	private static void customisegroupby(){
		Map<String, List<Student>> custogroupbyObj= StudentDataBase.getAllStudents().stream()
				.collect(Collectors.groupingBy(student -> student.getGpa() >= 3.5 ? "outstanding" :"Average"));
		
		System.out.println("customise grouping by" +custogroupbyObj);
	}
	//two level grouping
	private static void twolevelgroupby(){
		Map<Integer, Map<String, List<Student>>> twolevelgroupbyObj= StudentDataBase.getAllStudents().stream()
				.collect(Collectors.groupingBy(Student::getGradeLevel,Collectors.groupingBy(student -> student.getGpa() >= 3.5 ? "outstanding" :"Average")));
		
		System.out.println("Two level grouping by" +twolevelgroupbyObj);
	}
	//Three aruguments
	private static void threelevelgroupby(){
		LinkedHashMap<String, Set<Student>> threelevelgroupbyObj= StudentDataBase.getAllStudents().stream()
				.collect(Collectors.groupingBy(Student::getName, LinkedHashMap:: new, Collectors.toSet()));
		
		System.out.println("Three grouping by" +threelevelgroupbyObj);
	
	}
	public static void main(String[] args) {
		 groupby();
		 customisegroupby();
		 twolevelgroupby();
		 threelevelgroupby();
		//performance check
		 long startTime = System.currentTimeMillis();
		 long endtime= System.currentTimeMillis();
		 long totalTime = startTime- endtime;
		 System.out.println("performqance check" +totalTime);
	}

}
