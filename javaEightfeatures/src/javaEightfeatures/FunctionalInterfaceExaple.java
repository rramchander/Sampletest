package javaEightfeatures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import domain.Student;
import domain.StudentDataBase;
import domain.Testdomain;

public class FunctionalInterfaceExaple {
	
	/*  consumer - no retun type pass only one parameter   */
	private static void printall() {
		//domainobject consume
		Consumer<Student> consumerstubj = (i) -> System.out.println(i);
		List<Student> studentObj = StudentDataBase.getAllStudents();
		studentObj.forEach(consumerstubj);	
	}
	
	//consumer - chain 
	private static void printNameAndActivites() {
		//consumer chain
		Consumer<Student> consumerObj = (student)->System.out.println(student.getName());
		Consumer<Student> consumerObj1 = (Student) -> System.out.println(Student.getActivaties());
		List<Student> studentList = StudentDataBase.getAllStudents();
		studentList.forEach(consumerObj.andThen(consumerObj1));
		
	}
	private static void printNameAndActivitiesusingCondition() {
		System.out.println("printNameAndActivitiesusingCondition");
				Consumer<Student> consumerObj = (student)->System.out.println(student.getName());
				Consumer<Student> consumerObj1 = (Student) -> System.out.println(Student.getActivaties());
				List<Student> studentList = StudentDataBase.getAllStudents();
				studentList.forEach((student ->{
					if(student.getGender().equals("female") && student.getGpa() >=4) {
						consumerObj1.andThen(consumerObj).accept(student);
					}
				}));
	}
//Bi consumer will take two parameter
	private static void biconsumerprintNameAndActivities() {
		System.out.println("biconsumerprintNameAndActivities");
		List<Testdomain> domainList = new ArrayList<Testdomain>();
		BiConsumer<String, List<String>> biconsumerObj = (name, activites)-> {
			Testdomain testdomainObj = new Testdomain();
			testdomainObj.setName(name);
			testdomainObj.setActivaties(activites);
			System.out.println("the name is" + name+ "Activites:" +activites);
			domainList.add(testdomainObj);
			};
		Consumer<String> activitiesObj = (activities) -> System.out.println("The activies are" +activities);
		List<Student> studentList = StudentDataBase.getAllStudents();
		studentList.forEach((student) ->{
			biconsumerObj.accept(student.getName(),student.getActivaties());
			List<String> activiesList = student.getActivaties();
			activiesList.forEach((studentactivies -> activitiesObj.accept(studentactivies)));
		});
		Consumer<Testdomain> activitiesObj1 = (activities) -> System.out.println("The Testdomain are" +activities.getName());
		Consumer<Testdomain> activitiesObj2 = (activities) -> System.out.println("The Testdomain activates are" +activities.getActivaties());
		//domainList.forEach(activitiesObj1.andThen(activitiesObj2));
		domainList.forEach((testdomain->{
			activitiesObj1.andThen(activitiesObj2).accept(testdomain);
			
		}));
	}
	//predicate example with consumer
	private static void pridicateCondition() {
		System.out.println("pridicateCondition");
		Predicate<Student> predicateObj =(student)-> student.getGender().equals("female");
		Predicate<Student> predicateObj1 =(student)-> student.getGpa() >= 4;
		
		Consumer<Student> consumerObj = (student)->System.out.println(student.getName());
		Consumer<Student> consumerObj1 = (Student) -> System.out.println(Student.getActivaties());
				List<Student> studentList = StudentDataBase.getAllStudents();
				studentList.forEach((student ->{
					if(predicateObj.test(student)) {
						System.out.println("Entered in predicateobj condition");
						consumerObj1.andThen(consumerObj).accept(student);
					}
					if(predicateObj.and(predicateObj1).test(student)) {
						System.out.println("Entered in predicateobj both and condition ");
						consumerObj1.andThen(consumerObj).accept(student);
					}
					if(predicateObj.or(predicateObj1).test(student)) {
						System.out.println("Entered in predicateobj both or condition ");
						consumerObj1.andThen(consumerObj).accept(student);
					}
					if(predicateObj.or(predicateObj1).negate().test(student)) {
						System.out.println("Entered in predicateobj both or negate condition ");
						consumerObj1.andThen(consumerObj).accept(student);
					}
				}));
	}
	//BIpredicate example with biconsumer accept two parameters
	private static void bipredicatecondition() {
		System.out.println("bipredicatecondition");
		BiPredicate<String, Double> bipredicateObj = (gender, gpa)->gender.equals("female") && gpa >= 4 ;
		BiConsumer<String, List<String>> biconsumerObj = (name, activities) -> System.out.println("The name is" +name+" and " +activities);
		List<Student> studentList = StudentDataBase.getAllStudents();
		studentList.forEach((student)->{
			if(bipredicateObj.test(student.getGender(), student.getGpa() )) {
				biconsumerObj.accept(student.getName(), student.getActivaties());
			}
			
		});
		
		
	}
	//function inteface - accept two parameter input and output
	private static void functionInterfaceExample() {
		System.out.println("functionInterfaceExample");
		Function<String, String> functionobj =(name) -> name.toUpperCase().concat("default");
		Function<String, String> functionobj1 =(name) -> name.toUpperCase();
		System.out.println("Thge function interface string is" +functionobj.apply("Ramfunction"));
		System.out.println("Thge function and then interface string is" +functionobj.andThen(functionobj1).apply("Ramfunction"));
	}
	
	private static Map<String, Double> functionInterfaceExample1(){
		System.out.println("functionInterfaceExample1");
		Map<String, Double> mapObj = new HashMap<String, Double>();
		Predicate<Student> predObj = (student)-> student.getGender().equalsIgnoreCase("female");
		Function<List<Student>, Map<String, Double>> functionObj = ((students->{
			students.forEach((student -> {
				if(predObj.test(student)) {
					mapObj.put(student.getName(), student.getGpa());
				}
			}));
			return mapObj;	
		}));
		System.out.println("Mapfunction object is" +functionObj.apply(StudentDataBase.getAllStudents()));
		return mapObj;
	}
	private static Map<String, Double> bifunctionInterfaceExample(){
		System.out.println("bifunctionInterfaceExample object is");
		BiFunction<List<Student>, Predicate<Student>, Map<String, Double>> bifunctionalObj = ((students, studentPredicate)->{
			Map<String, Double> mapObj = new HashMap<String, Double>();
			students.forEach(student->{
				if(studentPredicate.test(student)) {
					mapObj.put(student.getName(), student.getGpa());
				}
			});
		    return mapObj;
		});
		Predicate<Student> predicateObj = (student)-> student.getGpa()>= 3.9;
		System.out.println("BIfunctional object is" +bifunctionalObj.apply(StudentDataBase.getAllStudents(),predicateObj));
	return null;
	}
	//suppliers - have return but no parameter - opposite to consumer
	private static List<Student> suplierExample() {
		System.out.println("The suplierExample");
		Supplier<List<Student>> supplierObj = ()-> StudentDataBase.getAllStudents();
		System.out.println("The supplier values are" + supplierObj.get());
		return supplierObj.get();
	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//functional interface: consumer - bi consumer, predicate - bipredicate, function- bifunction-unaryoperator-binaryoperator, supplier
		// consumer - accept input return void
		Consumer<String> consumerObj = (i) -> System.out.println(i.toUpperCase());
		consumerObj.accept("Ram");

		// integer - consumer
		Consumer<Integer> consumerInt = (i) -> System.out.println(i);
		consumerInt.accept(1);
		// Object - consumer
		Consumer<Object> consumerIntOfbj = (i) -> System.out.println(i);
		consumerIntOfbj.accept("2");
		//domainobject consume
		printall();
		 printNameAndActivites() ;
		 printNameAndActivitiesusingCondition();
		 biconsumerprintNameAndActivities();
		 
		 BiConsumer<String, String> biconsumerObj = (a, b)-> System.out.println("The value of a is:" +a+ "the value of b is: "+b);
		 biconsumerObj.accept("java8", "Ram8");
		 
		 //predicate - it return true or false based on condition accept only 1 parameter
		 pridicateCondition();
		//Bipredicate - it return true or false based on condition accept only two parameter
		 bipredicatecondition();
		 //functional interface
		 functionInterfaceExample();
		 Map<String, Double> functionMap = functionInterfaceExample1();
		 System.out.println("the mapfunction is" );
		 bifunctionInterfaceExample();
		 //suppliers
		 List<Student> supplierList =  suplierExample() ;
		 supplierList.forEach((student->{
			 System.out.println("The loop name is" +student.getName());
		 }));
	}

}
