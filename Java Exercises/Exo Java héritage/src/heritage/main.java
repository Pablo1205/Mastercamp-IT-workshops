package heritage;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p = new Person(21);
		p.sayHello();
		
		Student s = new Student(15);
		s.sayHello();
		s.GoToClasses();
		s.displayAge(s.isAge());
		
		Teacher t = new Teacher(40);
		t.sayHello();
		t.Explain();
	}

}
