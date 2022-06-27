package heritage;

public class Student extends Person {

	public Student(int age) {
		super(age);
		// TODO Auto-generated constructor stub
	}

	public static void GoToClasses() {
		System.out.println("I'm going to class. ");
	}
	
	public static void displayAge(int age) {
		System.out.println("My age is : " + age +  " years old.");
	}
	
	
	
}
