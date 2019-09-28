package javaEightfeatures;

public class RunnableInterfaceExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//this is before jdk1.8
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println("test"); 
				
			}
			
		};
       new Thread(runnable).start();
       
       //java 8 - lamda syntax is syntax ()->{}
       Runnable runnablelabda = ()-> System.out.println("This is lamda");
       new Thread(runnablelabda).start();
	}
	

}
