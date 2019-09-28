package javaEightfeatures;

import java.util.Comparator;
import java.util.function.Consumer;

public class comparatorLamdaExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//compare the biggernumber
		Comparator<Integer> compareLamda = (a,  b)-> a.compareTo(b);
        System.out.println("compare the value" +compareLamda.compare(3, 2));
        //Function Inteface - consumer
        Consumer<String> consumerObj =(i)->System.out.println(i.toUpperCase());
        consumerObj.accept("Ram");
	}

}
