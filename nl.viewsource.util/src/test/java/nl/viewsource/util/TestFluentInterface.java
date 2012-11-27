package nl.viewsource.util;


import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class TestFluentInterface {

    private Person john;
    private Person buildJohn;

    @Before
    public void setup() {
        john = new Person();
        john.setFirstName("John");
        john.setLastName("Doe");
        john.setAge(101);

        buildJohn =
                PersonBuilder.with()
                        .firstName("John")
                        .lastName("Doe")
                        .age(101).create();
    }

    @Test
    public void with__equals() {
        assertThat(john, is(buildJohn));
    }

    @Test
    public void with_john_notEqual() {
        PersonBuilder.with(buildJohn)
                .firstName("Mary")
                .lastName("Jane");
        assertThat(john, is(not(buildJohn)));
    }

    @Test
    public void with_mary_equal() {
        Person jane =
                PersonBuilder.with()
                        .firstName("Jane")
                        .lastName("Doe")
                        .age(95).create();

        PersonBuilder.with(buildJohn)
                .firstName("Jane")
                .age(95);
        assertThat(jane, is(buildJohn));
    }
}
