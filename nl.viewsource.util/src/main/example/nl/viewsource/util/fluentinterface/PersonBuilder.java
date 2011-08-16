package nl.viewsource.util.fluentinterface;

import nl.viewsource.util.FluentInterface;

public final class PersonBuilder {

	private PersonBuilder() {

	}

	public static IPersonBuilder with() {
		return with(new Person());
	}

	public static IPersonBuilder with(Person person) {
		return FluentInterface.create(person, IPersonBuilder.class);
	}

	static interface IPersonBuilder {
		IPersonBuilder firstName(String firstName);

		IPersonBuilder lastName(String lastName);

		IPersonBuilder age(int age);

		Person create();
	}
}
