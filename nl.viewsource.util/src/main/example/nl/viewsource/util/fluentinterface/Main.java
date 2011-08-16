package nl.viewsource.util.fluentinterface;

public class Main {
	public static void main(String[] args) {
		Person john =
			PersonBuilder.with()
				.firstName("John")
				.lastName("Doe")
				.age(101).create();
		
		Person jane =
			PersonBuilder.with()
			.firstName("Jane")
			.lastName("Doe")
			.age(95).create();
		
		System.out.println("john.equals(jane) = " + john.equals(jane));
		
		// sex change? (change existing)
		PersonBuilder.with(john)
			.firstName("Mary")
			.lastName("Jane");
		
		// the real mary
		Person mary = 
			PersonBuilder.with()
				.firstName("Mary")
				.lastName("Jane")
				.age(101).create();
			
		System.out.println("john.equals(mary) = " + john.equals(mary));
	}
}
